public class Problem1 {

    /**
     * Calculates the sum of elements in an integer array using a brute-force approach.
     *
     * Logic:
     * The method iterates through each element of the array using a standard for-loop
     * and adds the value of each element to a running total.
     *
     * Time Complexity: O(n)
     * The method iterates through the array once. 'n' is the number of elements in the array.
     * Thus, the number of operations is directly proportional to the size of the array.
     *
     * Space Complexity: O(1)
     * The method uses a fixed amount of extra space for the 'sum' variable and the loop counter 'i',
     * regardless of the input array's size.
     *
     * Advantages:
     * - Simple to understand: The logic is straightforward and easy to follow.
     * - Straightforward implementation: Easy to write and debug.
     *
     * Disadvantages:
     * - Can be verbose: Compared to using streams or other built-in methods, this approach requires more lines of code.
     * - Not inherently parallelizable: A simple for-loop is sequential.
     *
     * When to Use:
     * - Good for beginners learning about loops and array manipulation.
     * - Suitable for small datasets where the performance difference compared to more optimized methods is negligible.
     * - When code clarity and simplicity are prioritized over conciseness.
     */
    public static int sumBruteForce(int[] arr) {
        int sum = 0; // Initialize sum to 0
        // Iterate through each element of the array
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i]; // Add current element to sum
        }
        return sum; // Return the total sum
    }

    public static void main(String[] args) {
        // Test the sumBruteForce method
        int[] sampleArray = {1, 2, 3, 4, 5};
        int result = sumBruteForce(sampleArray);
        System.out.println("The sum of the array elements is (Brute Force): " + result); // Expected output: 15

        int[] emptyArray = {};
        result = sumBruteForce(emptyArray);
        System.out.println("The sum of an empty array is (Brute Force): " + result); // Expected output: 0

        int[] negativeNumbersArray = {-1, -2, -3, -4, -5};
        result = sumBruteForce(negativeNumbersArray);
        System.out.println("The sum of negative numbers array is (Brute Force): " + result); // Expected output: -15

        int[] mixedNumbersArray = {-1, 2, -3, 4, -5};
        result = sumBruteForce(mixedNumbersArray);
        System.out.println("The sum of mixed numbers array is (Brute Force): " + result); // Expected output: -3

        // Test the sumRecursive method
        System.out.println("\n--- Testing Recursive Sum ---");
        result = sumRecursive(sampleArray);
        System.out.println("The sum of the array elements is (Recursive): " + result); // Expected output: 15

        result = sumRecursive(emptyArray);
        System.out.println("The sum of an empty array is (Recursive): " + result); // Expected output: 0

        result = sumRecursive(negativeNumbersArray);
        System.out.println("The sum of negative numbers array is (Recursive): " + result); // Expected output: -15

        result = sumRecursive(mixedNumbersArray);
        System.out.println("The sum of mixed numbers array is (Recursive): " + result); // Expected output: -3

        // Test the sumFunctionalStyle method
        System.out.println("\n--- Testing Functional Style Sum ---");
        result = sumFunctionalStyle(sampleArray);
        System.out.println("The sum of the array elements is (Functional Style): " + result); // Expected output: 15

        result = sumFunctionalStyle(emptyArray);
        System.out.println("The sum of an empty array is (Functional Style): " + result); // Expected output: 0

        result = sumFunctionalStyle(negativeNumbersArray);
        System.out.println("The sum of negative numbers array is (Functional Style): " + result); // Expected output: -15

        result = sumFunctionalStyle(mixedNumbersArray);
        System.out.println("The sum of mixed numbers array is (Functional Style): " + result); // Expected output: -3

        // Test the sumUsingArraysStream method
        System.out.println("\n--- Testing Arrays.stream() Sum ---");
        result = sumUsingArraysStream(sampleArray);
        System.out.println("The sum of the array elements is (Arrays.stream()): " + result); // Expected output: 15

        result = sumUsingArraysStream(emptyArray);
        System.out.println("The sum of an empty array is (Arrays.stream()): " + result); // Expected output: 0

        result = sumUsingArraysStream(negativeNumbersArray);
        System.out.println("The sum of negative numbers array is (Arrays.stream()): " + result); // Expected output: -15

        result = sumUsingArraysStream(mixedNumbersArray);
        System.out.println("The sum of mixed numbers array is (Arrays.stream()): " + result); // Expected output: -3
    }

    /**
     * Calculates the sum of elements in an integer array using `java.util.Arrays.stream()`.
     *
     * Logic:
     * This method leverages the `java.util.Arrays.stream(arr)` utility method to obtain an
     * `IntStream` directly from the input integer array. It then calls the `sum()` terminal
     * operation on this stream to compute the total sum of its elements.
     *
     * Time Complexity: O(n)
     * The `Arrays.stream()` method itself might do some setup, but the subsequent `sum()`
     * operation iterates through all 'n' elements of the array once.
     *
     * Space Complexity: O(1)
     * Similar to `IntStream.of()`, for summing primitive integers in a sequential stream,
     * the space complexity is O(1). It primarily uses a few variables for accumulation.
     *
     * Advantages:
     * - Highly Concise: This is one of the most direct and shortest ways to sum an array in Java 8+.
     * - Very Readable: The intent is extremely clear due to the descriptive method names (`Arrays.stream`, `sum`).
     * - Standard Library Utility: Directly uses well-established and optimized standard library features.
     * - Leverages Stream Optimizations: Benefits from the underlying optimizations of the Java Streams API.
     *
     * Disadvantages:
     * - Minor Overhead for Tiny Arrays: Like other stream-based approaches, for extremely small arrays (e.g., 1-2 elements),
     *   a manual loop might be marginally faster due to less overhead, but this is often negligible and comes at the cost of readability.
     * - Requires Java 8+: Stream API is not available in older Java versions.
     *
     * When to Use:
     * - Preferred Method for Summing: Often considered the best practice for summing integer arrays in Java 8+
     *   due to its exceptional brevity and clarity.
     * - General Use Cases: Suitable for almost any scenario where you need to sum elements of an integer array
     *   and are working in a Java 8+ environment.
     */
    public static int sumUsingArraysStream(int[] arr) {
        // Use Arrays.stream() to get an IntStream from the array, then sum its elements.
        return java.util.Arrays.stream(arr).sum();
    }

    /**
     * Calculates the sum of elements in an integer array using Java Streams (functional style).
     *
     * Logic:
     * The method converts the input integer array into an `IntStream` using `IntStream.of(arr)`.
     * Then, it calls the `sum()` terminal operation on the stream, which calculates the sum
     * of all elements in the stream.
     *
     * Time Complexity: O(n)
     * The `sum()` operation is a terminal operation that effectively iterates through all elements
     * in the stream once to calculate the sum. 'n' is the number of elements in the array.
     *
     * Space Complexity: O(1) for this specific use case.
     * For summing primitive integers in a sequential stream, the space complexity is generally O(1)
     * as it primarily uses a few variables to accumulate the sum. More complex stream pipelines,
     * especially those involving intermediate operations that store collections or state (e.g., sorted(), distinct()
     * on non-primitive types, or operations that require significant buffering for parallel processing),
     * could lead to higher space complexity (e.g., O(n) or O(log n)).
     *
     * Advantages:
     * - Concise and Expressive: Code is often shorter and more directly expresses the "what" rather than the "how".
     * - Readability: For developers familiar with functional programming and Java Streams, this style can be highly readable.
     * - Leverages Internal Optimizations: The Streams API is designed to be efficient and can leverage internal optimizations.
     * - Easy Parallelization: For large datasets, the stream can often be processed in parallel by simply adding `.parallel()`
     *   (e.g., `IntStream.of(arr).parallel().sum()`), potentially speeding up computation on multi-core processors.
     *
     * Disadvantages:
     * - Overhead for Small Arrays: For very small arrays, the overhead of creating and managing a stream might be slightly
     *   higher than a simple for-loop.
     * - Learning Curve: Can be less intuitive for developers not yet familiar with Java Streams or functional programming paradigms.
     * - Debugging: Debugging complex stream pipelines can sometimes be more challenging than imperative code, though modern IDEs are improving in this area.
     *
     * When to Use:
     * - Modern Java Development: Generally preferred for processing collections or arrays in modern Java (Java 8+).
     * - Conciseness and Readability: When a clean, concise, and expressive way to perform aggregate operations is desired.
     * - Large Datasets: Particularly beneficial for large datasets where the ease of parallelization can be leveraged for performance gains.
     * - Complex Data Pipelines: When chaining multiple operations (filter, map, reduce, etc.) in a fluent manner.
     */
    public static int sumFunctionalStyle(int[] arr) {
        // Convert the array to an IntStream and then use the sum() terminal operation.
        return java.util.stream.IntStream.of(arr).sum();
    }

    // The Javadoc for sumRecursive was here in error, ensuring it's not re-added.
    // The correct order is: sumBruteForce, sumRecursive, sumFunctionalStyle, sumUsingArraysStream.
    // sumRecursive's Javadoc is already correctly placed earlier in the file.

    /**
     * Calculates the sum of elements in an integer array using a recursive approach.
     * This method serves as a public entry point and calls a private helper method
     * to perform the actual recursion, starting from index 0.
     *
     * Logic:
     * The problem of summing an array can be broken down into:
     * - The value of the first element.
     * - The sum of the rest of the array.
     * The `sumRecursiveHelper` method implements this logic.
     *
     * Time Complexity: O(n)
     * Each element of the array is visited once during the recursive calls. 'n' is the
     * number of elements in the array.
     *
     * Space Complexity: O(n)
     * This is due to the recursion call stack. In the worst case, for an array of size 'n',
     * there will be 'n' recursive calls stacked up before the base case is reached and
     * the stack starts to unwind. Each call consumes some memory on the stack.
     *
     * Advantages:
     * - Elegance: Can be a more intuitive and elegant way to think about problems that
     *   naturally break down into smaller, self-similar subproblems.
     * - Readability for certain problems: For algorithms like tree traversals or quicksort,
     *   recursion often leads to more readable and concise code.
     *
     * Disadvantages:
     * - StackOverflowError Risk: For very large arrays, the depth of the recursion can
     *   exceed the available stack space, leading to a StackOverflowError.
     * - Performance Overhead: Function calls have overhead. For simple tasks like array
     *   summation, an iterative approach is generally more performant (faster and uses less memory).
     * - Debugging: Debugging recursive functions can sometimes be more complex than iterative ones.
     *
     * When to Use:
     * - Educational purposes: Excellent for teaching and understanding the concept of recursion.
     * - Problems with inherent recursive structure: Useful for problems like tree/graph traversals,
     *   divide and conquer algorithms (e.g., merge sort, quicksort), backtracking, etc., where
     *   the recursive definition is more natural than an iterative one.
     * - Simple array summation is typically not a prime use case for recursion in production
     *   code due to performance and stack overflow risks, but it serves as a good example
     *   to illustrate the concept.
     */
    public static int sumRecursive(int[] arr) {
        // Call the helper method, starting recursion from the first element (index 0)
        return sumRecursiveHelper(arr, 0);
    }

    /**
     * Private helper method to calculate the sum of array elements recursively.
     *
     * @param arr The array of integers.
     * @param index The current index being processed.
     * @return The sum of elements from the current index to the end of the array.
     */
    private static int sumRecursiveHelper(int[] arr, int index) {
        // Base Case: If the index reaches the end of the array,
        // there are no more elements to add, so return 0.
        if (index == arr.length) {
            return 0;
        }
        // Recursive Step: Add the element at the current index to the sum of
        // the rest of the array (obtained by a recursive call for the next index).
        return arr[index] + sumRecursiveHelper(arr, index + 1);
    }

    /**
     * == Comparison Summary of Array Summation Methods ==
     *
     * This table provides a quick comparison of the different array summation methods implemented in this class.
     *
     * | Method Name             | Time Complexity | Space Complexity | Strengths                                                                                                | When to Use                                                                                                     |
     * |-------------------------|-----------------|------------------|----------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
     * | `sumBruteForce`         | O(n)            | O(1)             | - Very simple to understand and implement.<br>- Direct control over iteration.                             | - Educational purposes for beginners.<br>- Small arrays where performance is not critical and simplicity is key.     |
     * | `sumRecursive`          | O(n)            | O(n)             | - Elegant for problems with a naturally recursive structure.<br>- Helps understand recursion.                | - Primarily educational for array sums.<br>- Problems where recursion simplifies logic (e.g., tree traversal). Avoid for large arrays due to StackOverflow risk. |
     * | `sumFunctionalStyle`    | O(n)            | O(1) (typical)   | - Concise, expressive, and aligns with modern Java.<br>- Easy to parallelize (`.parallel()`).<br>- Good readability for those familiar with streams. | - General array/collection processing in Java 8+.<br>- When chaining multiple operations (map, filter, etc.).<br>- Large datasets where parallelism can be beneficial. |
     * | `sumUsingArraysStream`  | O(n)            | O(1) (typical)   | - Extremely concise and highly readable.<br>- Uses a direct standard library utility (`Arrays.stream`).      | - Often the preferred method for summing arrays in Java 8+ for its clarity and brevity for this specific task.    |
     *
     * *Note on Space Complexity for Streams:* While typically O(1) for simple sum operations on primitives,
     * more complex stream pipelines or streams of objects might incur higher space costs (e.g., O(n) for sorting, collecting).
     */
}
