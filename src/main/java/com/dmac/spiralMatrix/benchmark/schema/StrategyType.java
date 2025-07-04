package com.dmac.spiralMatrix.benchmark.schema;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Traversal strategy to use", allowableValues = {"ConcatStrategy", "RecursiveStrategy", "DirectionStrategy"})
public enum StrategyType {

    @Schema(description = "Concatenates values in spiral order using loops. Time: O(n²), Space: O(1)")
    ConcatStrategy,

    @Schema(description = "Traverses the matrix recursively in spiral order. Time: O(n²), Space: O(n²) stack")
    RecursiveStrategy,

    @Schema(description = "Simulates spiral direction (right/down/left/up) iteration. Time: O(n²), Space: O(1)")
    DirectionStrategy,
}
