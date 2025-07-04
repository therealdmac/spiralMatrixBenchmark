package com.dmac.spiralMatrix.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpiralRecursiveStrategy implements SpiralMatrixStrategy {

    @Override
    public String traverse(int[][] matrix) {
        return traverse(matrix, SpiralDirection.CLOCKWISE, SpiralStartPosition.TOP_LEFT);
    }

    @Override
    public String traverse(int[][] matrix, SpiralDirection direction, SpiralStartPosition startPosition) {
        List<Integer> result = new ArrayList<>();
        if (matrix != null && matrix.length > 0) {
            if (direction == SpiralDirection.CLOCKWISE) {
                spiralHelperClockwise(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1, result);
            } else {
                spiralHelperCounterClockwise(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1, result);
            }

            int rotations = switch (startPosition) {
                case TOP_LEFT -> 0;
                case TOP_RIGHT -> 1;
                case BOTTOM_RIGHT -> 2;
                case BOTTOM_LEFT -> 3;
            };
            int rotateAmount = rotations * (result.size() / 4);
            if (rotateAmount > 0) {
                if (direction == SpiralDirection.CLOCKWISE) {
                    for (int i = 0; i < rotateAmount; i++) {
                        result.add(result.remove(0));
                    }
                } else {
                    for (int i = 0; i < rotateAmount; i++) {
                        result.add(0, result.remove(result.size() - 1));
                    }
                }
            }
        }
        return result.stream().map(String::valueOf).collect(Collectors.joining(", "));
    }

    private void spiralHelperClockwise(int[][] matrix, int top, int left, int bottom, int right, List<Integer> result) {
        if (top > bottom || left > right) return;

        for (int i = left; i <= right; i++) result.add(matrix[top][i]);
        for (int i = top + 1; i <= bottom; i++) result.add(matrix[i][right]);
        if (top < bottom)
            for (int i = right - 1; i >= left; i--) result.add(matrix[bottom][i]);
        if (left < right)
            for (int i = bottom - 1; i > top; i--) result.add(matrix[i][left]);

        spiralHelperClockwise(matrix, top + 1, left + 1, bottom - 1, right - 1, result);
    }

    private void spiralHelperCounterClockwise(int[][] matrix, int top, int left, int bottom, int right, List<Integer> result) {
        if (top > bottom || left > right) return;

        for (int i = top; i <= bottom; i++) result.add(matrix[i][left]);
        for (int i = left + 1; i <= right; i++) result.add(matrix[bottom][i]);
        if (left < right)
            for (int i = bottom - 1; i >= top; i--) result.add(matrix[i][right]);
        if (top < bottom)
            for (int i = right - 1; i > left; i--) result.add(matrix[top][i]);

        spiralHelperCounterClockwise(matrix, top + 1, left + 1, bottom - 1, right - 1, result);
    }
}
