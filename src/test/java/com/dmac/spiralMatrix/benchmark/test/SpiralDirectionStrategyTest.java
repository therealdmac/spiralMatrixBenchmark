package com.dmac.spiralMatrix.benchmark.test;

import com.dmac.spiralMatrix.strategy.SpiralDirectionStrategy;
import com.dmac.spiralMatrix.strategy.SpiralMatrixStrategy;

public class SpiralDirectionStrategyTest  extends AbstractSpiralStrategyTest {
    @Override
    protected SpiralMatrixStrategy strategy() {
        return new SpiralDirectionStrategy();
    }
}


