package com.dmac.spiralMatrix.benchmark;

import com.dmac.spiralMatrix.benchmark.schema.DataPoint;
import com.dmac.spiralMatrix.benchmark.schema.StrategyDoc;
import com.dmac.spiralMatrix.benchmark.schema.StrategyResult;
import com.dmac.spiralMatrix.benchmark.schema.StrategyType;
import com.dmac.spiralMatrix.strategy.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.*;
import java.util.concurrent.*;

import static com.dmac.spiralMatrix.strategy.StrategyTypeMapping.strategies;

@SpringBootApplication
@EnableAsync
@EnableCaching
@RestController
@RequestMapping("/api/benchmark")
@Tag(name = "Spiral Matrix Benchmark", description = "Benchmark various spiral traversal strategies")
public class SpiralMatrixBenchmark {

    public static void main(String[] args) {
        SpringApplication.run(SpiralMatrixBenchmark.class, args);
    }

    @Async
    @Cacheable(value = "benchmarkResults", key = "#step + '-' + #maxSize + '-' + #runs")
    @GetMapping
    @Operation(summary = "Run benchmark for all strategies", description = "Runs spiral matrix benchmark for matrix sizes increasing by step size")
    public CompletableFuture<List<StrategyResult>> runBenchmark(@RequestParam(defaultValue = "100") int step,
                                                                @RequestParam(defaultValue = "500") int maxSize,
                                                                @RequestParam(defaultValue = "5") int runs) {

        ExecutorService strategyExecutor = Executors.newFixedThreadPool(strategies.size());
        List<Future<StrategyResult>> futures = new ArrayList<>();

        for (int i = 0; i < strategies.size(); i++) {
            final int strategyIndex = i;
            futures.add(strategyExecutor.submit(() -> {
                SpiralMatrixStrategy strategy = strategies.get(StrategyType.values()[strategyIndex]);
                String name = StrategyType.values()[strategyIndex].name();

                ExecutorService sizeExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                List<Future<DataPoint>> sizeFutures = new ArrayList<>();

                for (int size = step; size <= maxSize; size += step) {
                    final int matrixSize = size;
                    sizeFutures.add(sizeExecutor.submit(() -> {
                        int[][] matrix = new int[matrixSize][matrixSize];
                        for (int r = 0, val = 0; r < matrixSize; r++)
                            for (int c = 0; c < matrixSize; c++)
                                matrix[r][c] = val++;

                        double totalTime = 0;
                        for (int run = 0; run < runs; run++) {
                            long start = System.nanoTime();
                            strategy.traverse(matrix);
                            long end = System.nanoTime();
                            totalTime += (end - start) / 1_000_000.0;
                        }
                        return new DataPoint(matrixSize, totalTime / runs);
                    }));
                }

                List<DataPoint> data = new ArrayList<>();
                for (Future<DataPoint> f : sizeFutures) {
                    try {
                        data.add(f.get());
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException("Size benchmark failed", e);
                    }
                }
                sizeExecutor.shutdown();

                return new StrategyResult(name, data);
            }));
        }

        strategyExecutor.shutdown();
        List<StrategyResult> output = new ArrayList<>();
        for (Future<StrategyResult> future : futures) {
            try {
                output.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Benchmark task failed", e);
            }
        }

        return CompletableFuture.completedFuture(output);
    }

    @PostMapping("/traverse")
    @Operation(summary = "Traverse input matrix with selected strategy",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "3x4 matrix",
                    summary = "Example 3x4 matrix input",
                    value = "[[2,3,4,8],[5,7,9,12],[1,0,6,10]]"
                )
            )
        )
    )
    public String traverseMatrix(
            @Parameter(
                    name = "strategy",
                    description = "Traversal strategy to use",
                    required = true,
                    schema = @Schema(implementation = StrategyType.class)
            )
            @RequestParam StrategyType strategy,

            @Parameter(
                    name = "direction",
                    description = "Traversal direction",
                    required = true,
                    schema = @Schema(implementation = SpiralDirection.class)
            )
            @RequestParam(defaultValue = "CLOCKWISE") SpiralDirection direction,

            @Parameter(
                    name = "startPosition",
                    description = "Starting corner",
                    required = true,
                    schema = @Schema(implementation = SpiralStartPosition.class)
            )
            @RequestParam(defaultValue = "TOP_LEFT")
            SpiralStartPosition startPosition,
            @RequestBody int[][] matrix
    ) {
        SpiralMatrixStrategy impl = strategies.get(strategy);
        return impl.traverse(matrix, direction, startPosition);
    }



    public static List<StrategyDoc> getDocumentation() {
        return List.of(
                new StrategyDoc("ConcatStrategy", "Concatenates values in spiral order using loops.", "O(n²)", "O(1)", "https://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/"),
                new StrategyDoc("RecursiveStrategy", "Traverses the matrix recursively in spiral order.", "O(n²)", "O(n²) stack", "https://www.techiedelight.com/print-matrix-spiral-order/"),
                new StrategyDoc("DirectionStrategy", "Simulates spiral direction (right/down/left/up) iteration.", "O(n²)", "O(1)", "https://leetcode.com/problems/spiral-matrix/")
        );
    }

    @GetMapping("/docs/strategies")
    @Operation(
            summary = "Get spiral matrix strategy documentation",
            description = "Returns a list of all available traversal strategies with their descriptions, time and space complexities, and references"
    )
    public List<StrategyDoc> getAllStrategies() {
        return getDocumentation();
    }
}
