package com.dmac.spiralMatrix.benchmark.schema;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Grouped benchmark result per strategy")
public record StrategyResult(
        @Schema(description = "Name of the traversal strategy")
        String strategy,

        @Schema(description = "List of results for varying matrix sizes")
        List<DataPoint> data
) {}
