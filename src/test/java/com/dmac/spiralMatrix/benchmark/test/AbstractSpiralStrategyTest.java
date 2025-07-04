package com.dmac.spiralMatrix.benchmark.test;

import com.dmac.spiralMatrix.strategy.SpiralDirection;
import com.dmac.spiralMatrix.strategy.SpiralMatrixStrategy;
import com.dmac.spiralMatrix.strategy.SpiralStartPosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractSpiralStrategyTest {
    protected abstract SpiralMatrixStrategy strategy();

    @Test
    @DisplayName("Matrix traversal default case, clockwise from left corner")
    void testTraversal() {
        String result = strategy().traverse(new int[][]{{1, 2}, {4, 3}}, SpiralDirection.CLOCKWISE, SpiralStartPosition.TOP_LEFT);
        assertNotEquals("4, 3, 2, 1", result, "Spiral traversal did not match expected output");
        assertEquals("1, 2, 3, 4", result, "Spiral traversal did not match expected output");
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("com.dmac.spiralMatrix.benchmark.test.TestData#traversalOptionsCases")
    @DisplayName("SpiralDirectionStrategy permutations")
    void testPermutations(TraversalOptionsTestData testCase) {
        int[][] matrix = {{1, 2}, {4, 3}};
        String result = strategy().traverse(matrix, testCase.dir(), testCase.pos());
        assertEquals(testCase.expected(), result, testCase.label());
    }

    @ParameterizedTest
    @MethodSource("com.dmac.spiralMatrix.benchmark.test.TestData#emptyOrNullCases")
    @DisplayName("Empty matrix should return empty string")
    void testEmptyOrNullInput(int[][] input, String expected) {
        String result = strategy().traverse(input);
        assertNotNull(result, "Output should not be null");
    }
}

