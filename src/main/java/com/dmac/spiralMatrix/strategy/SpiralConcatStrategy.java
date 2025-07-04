package com.dmac.spiralMatrix.strategy;

import java.util.function.BiConsumer;

public class SpiralConcatStrategy implements SpiralMatrixStrategy {
    @Override
    public String traverse(int[][] matrix) {
        return traverse(matrix, SpiralDirection.CLOCKWISE, SpiralStartPosition.TOP_LEFT);
    }

    @Override
    public String traverse(int[][] matrix, SpiralDirection direction, SpiralStartPosition startPosition) {
        if (matrix == null || matrix.length == 0) return "";
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;
        StringBuilder result = new StringBuilder();

        BiConsumer<Integer, Integer> append = (r, c) -> result.append(matrix[r][c]).append(", ");

        while (top <= bottom && left <= right) {
            switch (direction) {
                case CLOCKWISE -> {
                    switch (startPosition) {
                        case TOP_LEFT -> {
                            for (int i = left; i <= right; i++) append.accept(top, i);
                            top++;
                            for (int i = top; i <= bottom; i++) append.accept(i, right);
                            right--;
                            if (top <= bottom)
                                for (int i = right; i >= left; i--) append.accept(bottom, i);
                            bottom--;
                            if (left <= right)
                                for (int i = bottom; i >= top; i--) append.accept(i, left);
                            left++;
                        }
                        case TOP_RIGHT -> {
                            for (int i = top; i <= bottom; i++) append.accept(i, right);
                            right--;
                            for (int i = right; i >= left; i--) append.accept(bottom, i);
                            bottom--;
                            if (left <= right)
                                for (int i = bottom; i >= top; i--) append.accept(i, left);
                            left++;
                            if (top <= bottom)
                                for (int i = left; i <= right; i++) append.accept(top, i);
                            top++;
                        }
                        case BOTTOM_RIGHT -> {
                            for (int i = right; i >= left; i--) append.accept(bottom, i);
                            bottom--;
                            for (int i = bottom; i >= top; i--) append.accept(i, left);
                            left++;
                            if (top <= bottom)
                                for (int i = left; i <= right; i++) append.accept(top, i);
                            top++;
                            if (left <= right)
                                for (int i = top; i <= bottom; i++) append.accept(i, right);
                            right--;
                        }
                        case BOTTOM_LEFT -> {
                            for (int i = bottom; i >= top; i--) append.accept(i, left);
                            left++;
                            for (int i = left; i <= right; i++) append.accept(top, i);
                            top++;
                            if (left <= right)
                                for (int i = top; i <= bottom; i++) append.accept(i, right);
                            right--;
                            if (top <= bottom)
                                for (int i = right; i >= left; i--) append.accept(bottom, i);
                            bottom--;
                        }
                    }
                }
                case COUNTERCLOCKWISE -> {
                    switch (startPosition) {
                        case TOP_LEFT -> {
                            for (int i = top; i <= bottom; i++) append.accept(i, left);
                            left++;
                            for (int i = left; i <= right; i++) append.accept(bottom, i);
                            bottom--;
                            if (left <= right)
                                for (int i = bottom; i >= top; i--) append.accept(i, right);
                            right--;
                            if (top <= bottom)
                                for (int i = right; i >= left; i--) append.accept(top, i);
                            top++;
                        }
                        case TOP_RIGHT -> {
                            for (int i = right; i >= left; i--) append.accept(top, i);
                            top++;
                            for (int i = top; i <= bottom; i++) append.accept(i, left);
                            left++;
                            if (top <= bottom)
                                for (int i = left; i <= right; i++) append.accept(bottom, i);
                            bottom--;
                            if (left <= right)
                                for (int i = bottom; i >= top; i--) append.accept(i, right);
                            right--;
                        }
                        case BOTTOM_RIGHT -> {
                            for (int i = bottom; i >= top; i--) append.accept(i, right);
                            right--;
                            for (int i = right; i >= left; i--) append.accept(top, i);
                            top++;
                            if (right >= left)
                                for (int i = top; i <= bottom; i++) append.accept(i, left);
                            left++;
                            if (top <= bottom)
                                for (int i = left; i <= right; i++) append.accept(bottom, i);
                            bottom--;
                        }
                        case BOTTOM_LEFT -> {
                            for (int i = left; i <= right; i++) append.accept(bottom, i);
                            bottom--;
                            for (int i = bottom; i >= top; i--) append.accept(i, right);
                            right--;
                            if (bottom >= top)
                                for (int i = right; i >= left; i--) append.accept(top, i);
                            top++;
                            if (right >= left)
                                for (int i = top; i <= bottom; i++) append.accept(i, left);
                            left++;
                        }
                    }
                }
            }
        }

        if (result.length() >= 2) result.setLength(result.length() - 2);
        return result.toString();
    }
}
