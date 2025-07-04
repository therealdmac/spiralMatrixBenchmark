package com.dmac.spiralMatrix.benchmark.test;

import com.dmac.spiralMatrix.strategy.SpiralDirection;
import com.dmac.spiralMatrix.strategy.SpiralStartPosition;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class TestData {

    public static Stream<TraversalOptionsTestData> traversalOptionsCases() {
        return Stream.of(
                new TraversalOptionsTestData(SpiralDirection.CLOCKWISE, SpiralStartPosition.TOP_LEFT, "1, 2, 3, 4", "CLOCKWISE + TOP_LEFT"),
                new TraversalOptionsTestData(SpiralDirection.CLOCKWISE, SpiralStartPosition.TOP_RIGHT, "2, 3, 4, 1", "CLOCKWISE + TOP_RIGHT"),
                new TraversalOptionsTestData(SpiralDirection.CLOCKWISE, SpiralStartPosition.BOTTOM_RIGHT, "3, 4, 1, 2", "CLOCKWISE + BOTTOM_RIGHT"),
                new TraversalOptionsTestData(SpiralDirection.CLOCKWISE, SpiralStartPosition.BOTTOM_LEFT, "4, 1, 2, 3", "CLOCKWISE + BOTTOM_LEFT"),
                new TraversalOptionsTestData(SpiralDirection.COUNTERCLOCKWISE, SpiralStartPosition.TOP_LEFT, "1, 4, 3, 2", "COUNTERCLOCKWISE + TOP_LEFT"),
                new TraversalOptionsTestData(SpiralDirection.COUNTERCLOCKWISE, SpiralStartPosition.TOP_RIGHT, "2, 1, 4, 3", "COUNTERCLOCKWISE + TOP_RIGHT"),
                new TraversalOptionsTestData(SpiralDirection.COUNTERCLOCKWISE, SpiralStartPosition.BOTTOM_RIGHT, "3, 2, 1, 4", "COUNTERCLOCKWISE + BOTTOM_RIGHT"),
                new TraversalOptionsTestData(SpiralDirection.COUNTERCLOCKWISE, SpiralStartPosition.BOTTOM_LEFT, "4, 3, 2, 1", "COUNTERCLOCKWISE + BOTTOM_LEFT")
        );
    }

    public static Stream<Arguments> matrixCases() {
        return Stream.of(
                Arguments.of(new int[][]{
                        {2, 3, 4, 8},
                        {5, 7, 9, 12},
                        {1, 0, 6, 10}
                }, "2, 3, 4, 8, 12, 10, 6, 0, 1, 5, 7, 9"),
                Arguments.of(new int[][]{{1}}, "1"),
                Arguments.of(new int[][]{{1, 2}, {3, 4}}, "1, 2, 4, 3")
        );
    }

    public static Stream<Arguments> emptyOrNullCases() {
        return Stream.of(
                Arguments.of(new int[][]{}, ""),
                Arguments.of(new int[][]{{}}, ""),
                Arguments.of(new int[][]{{},{}}, ""),
                Arguments.of(null, "")
        );
    }
}

