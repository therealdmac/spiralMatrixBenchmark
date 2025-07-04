package com.dmac.spiralMatrix.benchmark.schema;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data point for a strategy at a given matrix size")
public record DataPoint(
        @Schema(description = "Matrix size used for the benchmark")
        int size,

        @Schema(description = "Average execution time in milliseconds")
        double avgTimeMs
) {}
