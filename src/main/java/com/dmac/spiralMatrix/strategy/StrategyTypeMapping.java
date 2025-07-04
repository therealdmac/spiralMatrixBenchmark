package com.dmac.spiralMatrix.strategy;

import com.dmac.spiralMatrix.benchmark.schema.StrategyType;

import java.util.Map;

public class StrategyTypeMapping {
    public static final Map<StrategyType, SpiralMatrixStrategy> strategies = Map.of(
            StrategyType.ConcatStrategy, new SpiralConcatStrategy(),
//            StrategyType.StreamStrategy, new SpiralStreamStrategy(),
            StrategyType.RecursiveStrategy, new SpiralRecursiveStrategy(),
            StrategyType.DirectionStrategy, new SpiralDirectionStrategy()
//            StrategyType.FlatMappingStrategy, new SpiralFlatMappingStrategy(),
//            StrategyType.ParallelChunkStrategy, new SpiralParallelChunkStrategy(),
//            StrategyType.FunctionalIteratorStrategy, new SpiralFunctionalIteratorStrategy()
    );
}
