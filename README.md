# Spiral Matrix Traversal – Java Implementation

## Overview

This repository implements a function to traverse a 2D matrix of integers and return a string of its elements in spiral order. The solution is written in **Java 17**, using clean, testable, and extendable design patterns.

> ✅ Assumptions, solution strategies, and testing methodology are included below.

---

## Problem Statement

**Function signature:**

```java
String matrix(int[][] input)
```

Given a matrix of integers, return a comma-separated string of the matrix values traversed in **clockwise** spiral order.

### Example:

#### Input:

```
[
  [2, 3, 4, 8],
  [5, 7, 9, 12],
  [1, 0, 6, 10]
]
```

#### Output:

```
"2, 3, 4, 8, 12, 10, 6, 0, 1, 5, 7, 9"
```

---

## Implementation Strategy

### ✅ Core Features

* Implemented multiple traversal strategies using a **Strategy Pattern**.
* The recursive strategy supports:

    * `CLOCKWISE` and `COUNTERCLOCKWISE` directions
    * All four possible `SpiralStartPosition` values: `TOP_LEFT`, `TOP_RIGHT`, `BOTTOM_RIGHT`, `BOTTOM_LEFT`
* Time complexity: `O(m * n)`
* Space complexity: `O(m * n)` for result list, `O(depth)` stack for recursion

> ℹ️ By default, the traversal starts from the top-left corner in a **clockwise** direction. However, our solution is fully parameterized to allow all combinations of start positions and directions.

### Strategy Descriptions

#### ▶ ConcatStrategy

* Iteratively traverses the matrix in spiral order using for-loops.
* Time Complexity: `O(m * n)`
* Space Complexity: `O(1)` (excluding output storage)
* Simple and performant, ideal for small matrices and reference use.

#### ▶ SpiralDirectionStrategy

* Simulates movement using a direction vector (right/down/left/up) and adjusts based on boundaries.
* Time Complexity: `O(m * n)`
* Space Complexity: `O(m * n)` for visited matrix
* Very flexible and supports arbitrary starting positions and directions.

#### ▶ SpiralRecursiveStrategy

* Uses recursive calls to peel outer layers.
* Time Complexity: `O(m * n)`
* Space Complexity: `O(depth)` due to recursive call stack
* Elegant but less optimal for large matrices due to stack depth.

---

## Estimated Time Taken

* Problem Analysis & Design: \~15 mins
* Basic Spiral Logic (Clockwise): \~15 mins
* Parameterization with Direction & Start Position: \~30 mins
* Recursive + Counterclockwise Support: \~20 mins
* Testing & Validation: \~20 mins
* Total: **\~1.5 hours**

---

## Assumptions

* Matrix is rectangular (non-jagged) and non-null.
* Empty or null matrix returns an empty string.
* For performance, the solution avoids string concatenation inside loops (`StringBuilder` or `Collectors.joining` used).

---

## API Usage Instructions

A REST API is available to test each strategy with parameters.

**Endpoint:**

```
GET /api/spiral/traverse
```

**Query Parameters:**

* `strategy` (enum): One of `ConcatStrategy`, `SpiralDirectionStrategy`, `SpiralRecursiveStrategy`, etc.
* `direction` (enum): `CLOCKWISE` or `COUNTERCLOCKWISE`
* `startPosition` (enum): `TOP_LEFT`, `TOP_RIGHT`, `BOTTOM_LEFT`, `BOTTOM_RIGHT`
* `matrix` (JSON): Example: `[[1,2,3],[4,5,6],[7,8,9]]`

**Example Request:**

```
GET /api/spiral/traverse?strategy=SpiralDirectionStrategy&direction=CLOCKWISE&startPosition=TOP_LEFT&matrix=[[1,2],[3,4]]
```

**Example Response:**

```json
{
  "strategy": "SpiralDirectionStrategy",
  "direction": "CLOCKWISE",
  "startPosition": "TOP_LEFT",
  "output": "1, 2, 4, 3"
}
```

Swagger UI is available at:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Testing & Verification

The solution is verified via:

* ✅ Unit tests for **all 8 permutations** of direction × start position
* ✅ Edge cases (empty matrix, 1×1, 1×N, N×1)
* ✅ Parameterized tests using JUnit 5
* ✅ Assertions for correctness, determinism, and value inclusion

---

## How to Run

To run tests via Maven:

```bash
mvn clean test
```

Ensure you are using **Java 17** to compile and run this project.

---

## Note

* This code is written as if for production quality: extensible, testable, and documented.
* Please keep the contents of this implementation **private**, as per the problem instructions.
