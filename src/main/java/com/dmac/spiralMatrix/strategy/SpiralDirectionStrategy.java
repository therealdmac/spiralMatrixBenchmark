package com.dmac.spiralMatrix.strategy;

import java.util.*;
import java.util.stream.Collectors;

public class SpiralDirectionStrategy implements SpiralMatrixStrategy {
    @Override
    public String traverse(int[][] matrix) {
        return traverse(matrix, SpiralDirection.CLOCKWISE, SpiralStartPosition.TOP_LEFT);
    }

    @Override
    public String traverse(int[][] matrix, SpiralDirection direction, SpiralStartPosition startPosition) {
        if (matrix == null || matrix.length == 0) return "";
        int m = matrix.length, n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        List<Integer> result = new ArrayList<>();

        int[][] dirs = direction == SpiralDirection.CLOCKWISE
                ? new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}} // right, down, left, up
                : new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // down, right, up, left

        int x, y, dir;
        switch (startPosition) {
            case TOP_LEFT -> { x = 0; y = 0; dir = 0; }
            case TOP_RIGHT -> { x = 0; y = n - 1; dir = direction == SpiralDirection.CLOCKWISE ? 1 : 3; }
            case BOTTOM_RIGHT -> { x = m - 1; y = n - 1; dir = 2; }
            case BOTTOM_LEFT -> { x = m - 1; y = 0; dir = direction == SpiralDirection.CLOCKWISE ? 3 : 1; }
            default -> throw new IllegalArgumentException("Invalid start position");
        }

        for (int i = 0; i < m * n; i++) {
            result.add(matrix[x][y]);
            visited[x][y] = true;
            int nx = x + dirs[dir][0], ny = y + dirs[dir][1];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny]) {
                dir = (dir + 1) % 4;
                nx = x + dirs[dir][0];
                ny = y + dirs[dir][1];
            }
            x = nx;
            y = ny;
        }

        return result.stream().map(String::valueOf).collect(Collectors.joining(", "));
    }
}
