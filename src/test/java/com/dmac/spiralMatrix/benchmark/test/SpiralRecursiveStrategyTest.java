package com.dmac.spiralMatrix.benchmark.test;

import com.dmac.spiralMatrix.strategy.SpiralMatrixStrategy;
import com.dmac.spiralMatrix.strategy.SpiralRecursiveStrategy;

public class SpiralRecursiveStrategyTest extends AbstractSpiralStrategyTest {
    @Override
    protected SpiralMatrixStrategy strategy() {
        return new SpiralRecursiveStrategy();
    }
}


