package com.dmac.spiralMatrix.benchmark.test;

import com.dmac.spiralMatrix.strategy.SpiralDirection;
import com.dmac.spiralMatrix.strategy.SpiralStartPosition;

public record TraversalOptionsTestData(SpiralDirection dir, SpiralStartPosition pos, String expected, String label) {}