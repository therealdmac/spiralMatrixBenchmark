package com.dmac.spiralMatrix.strategy;

public interface SpiralMatrixStrategy {
    String traverse(int[][] matrix);
    String traverse(int[][] matrix, SpiralDirection spiralDirection, SpiralStartPosition spiralStartPosition);
}
