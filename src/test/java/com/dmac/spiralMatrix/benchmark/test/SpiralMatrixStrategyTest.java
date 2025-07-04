package com.dmac.spiralMatrix.benchmark.test;

import com.dmac.spiralMatrix.strategy.SpiralConcatStrategy;
import com.dmac.spiralMatrix.strategy.SpiralMatrixStrategy;

public class SpiralMatrixStrategyTest extends AbstractSpiralStrategyTest {
    @Override
    protected SpiralMatrixStrategy strategy() {
        return new SpiralConcatStrategy();
    }
}

