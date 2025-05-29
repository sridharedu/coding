// Solutions for Problem 2: Finding the Second Largest Number in an Array, will be implemented in this file.
import java.util.Arrays; // Required for Arrays.toString and Arrays.copyOf

public class Problem2 {

    public static void main(String[] args) {
        // Define Test Arrays
        int[] standardArray = {1, 3, 2, 5, 4};
        int[] negativeArray = {-1, -5, -2, -8, -3};
        int[] duplicateLargestArray = {5, 2, 5, 4, 3};
        int[] duplicateSecondLargestArray = {5, 4, 3, 4, 2};
        int[] allSameArray = {7, 7, 7, 7, 7};
        int[] twoElementsArray = {10, 20};
        int[] twoElementsArrayReversed = {20, 10}; // Variant of two elements
        int[] twoElementsSameArray = {10, 10};
        int[] singleElementArray = {42};
        int[] emptyArray = {};
        int[] nullArray = null;
        int[] mixedDuplicates = {1, 5, 2, 5, 3, 5, 4};
        int[] withMinValue = {Integer.MIN_VALUE, 1, 2, Integer.MIN_VALUE}; // Test with MIN_VALUE itself

        // Test Each Array
        testArray(standardArray, "Standard Array {1, 3, 2, 5, 4}", 4);
        testArray(negativeArray, "Negative Array {-1, -5, -2, -8, -3}", -2);
        testArray(duplicateLargestArray, "Duplicate Largest Array {5, 2, 5, 4, 3}", 4);
        testArray(duplicateSecondLargestArray, "Duplicate Second Largest Array {5, 4, 3, 4, 2}", 4);
        testArray(allSameArray, "All Same Array {7, 7, 7, 7, 7}", Integer.MIN_VALUE);
        testArray(twoElementsArray, "Two Elements Array {10, 20}", 10);
        testArray(twoElementsArrayReversed, "Two Elements Array Reversed {20, 10}", 10);
        testArray(twoElementsSameArray, "Two Elements Same Array {10, 10}", Integer.MIN_VALUE);
        testArray(singleElementArray, "Single Element Array {42}", Integer.MIN_VALUE);
        testArray(emptyArray, "Empty Array {}", Integer.MIN_VALUE);
        testArray(nullArray, "Null Array", Integer.MIN_VALUE);
        testArray(mixedDuplicates, "Mixed Duplicates {1, 5, 2, 5, 3, 5, 4}", 4);
        testArray(withMinValue, "With MIN_VALUE {Integer.MIN_VALUE, 1, 2, Integer.MIN_VALUE}", 1);


        // Example with one of the methods that might have specific issues with MIN_VALUE if not handled well by init
        System.out.println("\n--- Special Test for Single Iteration with MIN_VALUE as actual largest ---");
        int[] specificMinValueTest = {Integer.MIN_VALUE, -5, -10}; // Expected: -5
        testArray(specificMinValueTest, "MIN_VALUE is largest {-Integer.MIN_VALUE, -5, -10}", -5);

        System.out.println("\n--- Special Test for Single Iteration with only two elements, one MIN_VALUE ---");
        int[] twoElementsWithMin = {Integer.MIN_VALUE, 10}; // Expected: MIN_VALUE (as it's one of two values)
        testArray(twoElementsWithMin, "Two elements {MIN_VALUE, 10}", Integer.MIN_VALUE);
         // For {MIN_VALUE, 10}, largest becomes 10, secondLargest becomes MIN_VALUE. Correct.

        System.out.println("\n--- Special Test for All MIN_VALUE elements ---");
        int[] allMinValues = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        testArray(allMinValues, "All MIN_VALUE elements", Integer.MIN_VALUE);

    }

    private static void testArray(int[] arr, String description, int expected) {
        System.out.println("--- Testing: " + description + " ---");
        System.out.println("Array: " + (arr == null ? "null" : java.util.Arrays.toString(arr)) + " | Expected: " + (expected == Integer.MIN_VALUE ? "Integer.MIN_VALUE" : expected));

        // For findSecondLargestUsingSorting, pass a copy because it sorts in-place.
        int[] arrCopyForSorting = (arr == null) ? null : java.util.Arrays.copyOf(arr, arr.length);
        int resultSorting = findSecondLargestUsingSorting(arrCopyForSorting);
        System.out.println("  Sorting: " + (resultSorting == Integer.MIN_VALUE ? "Integer.MIN_VALUE" : resultSorting) + (resultSorting == expected ? " (Correct)" : " (Incorrect!)"));

        int resultTwoIterations = findSecondLargestTwoIterations(arr);
        System.out.println("  Two Iterations: " + (resultTwoIterations == Integer.MIN_VALUE ? "Integer.MIN_VALUE" : resultTwoIterations) + (resultTwoIterations == expected ? " (Correct)" : " (Incorrect!)"));
        
        int resultSingleIteration = findSecondLargestSingleIteration(arr);
        System.out.println("  Single Iteration: " + (resultSingleIteration == Integer.MIN_VALUE ? "Integer.MIN_VALUE" : resultSingleIteration) + (resultSingleIteration == expected ? " (Correct)" : " (Incorrect!)"));

        int resultStream = findSecondLargestUsingStream(arr);
        System.out.println("  Stream API: " + (resultStream == Integer.MIN_VALUE ? "Integer.MIN_VALUE" : resultStream) + (resultStream == expected ? " (Correct)" : " (Incorrect!)"));
        System.out.println();
    }

    // Implementations for finding the second largest number will be added below.

    /**
     * Finds the second largest number in an integer array using a sorting approach.
     *
     * Logic:
     * 1. Handle Edge Cases: If the array is null or has fewer than 2 elements,
     *    it's impossible to find a second largest element, so return {@code Integer.MIN_VALUE}.
     * 2. Sort the Array: The array is sorted in ascending order using {@code java.util.Arrays.sort()}.
     *    Note: This modifies the original array.
     * 3. Find Second Largest: After sorting, the largest element is at {@code arr[arr.length - 1]}.
     *    Iterate backwards from {@code arr[arr.length - 2]}. The first element encountered
     *    that is different from the largest element is the second largest.
     * 4. Handle No Distinct Second Largest: If the loop completes without finding an element
     *    different from the largest (e.g., all elements are the same, or only one distinct element),
     *    it means there is no distinct second largest number. In this scenario, also return
     *    {@code Integer.MIN_VALUE}.
     *
     * Return Value:
     * - The second largest number if found.
     * - {@code Integer.MIN_VALUE} if the array is null, has fewer than 2 elements,
     *   or if no distinct second largest number exists (e.g., all elements are identical).
     *
     * Time Complexity: O(n log n)
     * Dominated by the {@code Arrays.sort()} method. The subsequent scan takes O(n) in the worst case.
     *
     * Space Complexity: O(1) or O(log n)
     * {@code Arrays.sort()} for primitive types in Java typically uses an introspective sort (a hybrid of Quicksort,
     * Heapsort, and Insertion Sort). Quicksort can take O(log n) stack space on average. Some implementations
     * might be in-place (O(1)) or use heapsort which is O(1).
     *
     * Advantages:
     * - Conceptual Simplicity: The idea of sorting then picking the second (distinct) element from the end is easy to grasp.
     * - Relatively Easy Implementation: Less prone to off-by-one errors compared to some manual scan methods if not careful.
     *
     * Disadvantages:
     * - Modifies Original Array: {@code Arrays.sort()} sorts the array in-place, which might not be desirable if the
     *   original order of elements is needed later. A copy would be needed to avoid this, increasing space complexity.
     * - Slower Than Linear Scan: For specifically finding the second largest, O(n log n) is less efficient than
     *   an O(n) single-pass or two-pass scan.
     * - Duplicate Handling: Requires careful iteration after sorting to find a *distinct* second largest element.
     *
     * When to Use:
     * - If the array is already sorted or needs to be sorted for other processing steps anyway.
     * - When conceptual simplicity is prioritized over raw performance for typical array sizes.
     * - If modifying the array is acceptable or a copy can be afforded.
     *
     * @param arr The input integer array.
     * @return The second largest number, or {@code Integer.MIN_VALUE} if not found or input is invalid.
     */
    public static int findSecondLargestUsingSorting(int[] arr) {
        // Edge Case: Null or array with fewer than 2 elements
        if (arr == null || arr.length < 2) {
            return Integer.MIN_VALUE; // Cannot find a second largest
        }

        // Sort the array in ascending order. This modifies the original array.
        java.util.Arrays.sort(arr);

        // The largest element is now at the end of the array
        int largest = arr[arr.length - 1];

        // Iterate backwards from the second-to-last element
        // to find the first element that is different from the largest.
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] != largest) {
                return arr[i]; // This is the second largest distinct element
            }
        }

        // If the loop finishes, it means all elements are the same as the largest,
        // or the array had only one distinct element. No distinct second largest.
        return Integer.MIN_VALUE;
    }

    /**
     * Finds the second largest number in an integer array using two iterations (passes) over the array.
     *
     * Logic:
     * 1. Handle Edge Cases: If the array is null or has fewer than 2 elements,
     *    return {@code Integer.MIN_VALUE} as a second largest cannot be determined.
     * 2. First Iteration (Find Largest): Iterate through the array once to find the
     *    absolute largest element. Initialize `largest` to {@code Integer.MIN_VALUE}.
     * 3. Second Iteration (Find Second Largest): Iterate through the array a second time.
     *    Initialize `secondLargest` to {@code Integer.MIN_VALUE}.
     *    An element is the second largest if it is greater than the current `secondLargest`
     *    AND it is strictly less than the `largest` element found in the first pass.
     * 4. Result: After the second pass, if `secondLargest` remains {@code Integer.MIN_VALUE},
     *    it indicates that either all elements were the same, or no element qualified as
     *    the second largest (e.g., in an array like {5, 5, 5} or {5}). In such cases,
     *    {@code Integer.MIN_VALUE} is returned. Otherwise, the found `secondLargest` is returned.
     *
     * Return Value:
     * - The second largest number if a distinct one is found.
     * - {@code Integer.MIN_VALUE} if the array is null, has fewer than 2 elements,
     *   or if no distinct second largest number exists.
     *
     * Time Complexity: O(n)
     * The method involves two separate passes through the array (O(n) + O(n)),
     * which simplifies to O(n) overall.
     *
     * Space Complexity: O(1)
     * Only a few variables (`largest`, `secondLargest`, loop iterators) are used,
     * requiring constant extra space regardless of input array size.
     *
     * Advantages:
     * - Does Not Modify Array: Unlike the sorting approach, this method leaves the original array unchanged.
     * - Linear Time Performance: O(n) is generally efficient for this problem.
     * - Conceptually Clearer for Some: Breaking the problem into two distinct steps (find max, then find next max)
     *   can be easier to reason about for some developers compared to a single-pass approach with multiple conditions.
     *
     * Disadvantages:
     * - Two Passes: Iterates over the data twice, which might be slightly less performant than a single-pass
     *   solution in practice, though both are O(n).
     *
     * When to Use:
     * - When the original array must not be modified.
     * - When a linear time complexity solution is required.
     * - If the two-pass logic is preferred for its clarity over a more complex single-pass approach.
     *
     * @param arr The input integer array.
     * @return The second largest number, or {@code Integer.MIN_VALUE} if not found or input is invalid.
     */
    public static int findSecondLargestTwoIterations(int[] arr) {
        // Edge Case: Null or array with fewer than 2 elements
        if (arr == null || arr.length < 2) {
            return Integer.MIN_VALUE;
        }

        // First Iteration: Find the largest element
        int largest = Integer.MIN_VALUE;
        for (int number : arr) {
            if (number > largest) {
                largest = number;
            }
        }

        // Second Iteration: Find the second largest element
        // It must be less than 'largest' and greater than current 'secondLargest'
        int secondLargest = Integer.MIN_VALUE;
        for (int number : arr) {
            if (number > secondLargest && number < largest) {
                secondLargest = number;
            }
        }

        // If secondLargest is still Integer.MIN_VALUE, it means no distinct second largest was found.
        // This covers cases like {5, 5, 5} or an array that was too small and somehow passed the initial check
        // (though the initial check should catch arrays < 2 elements).
        // It also implicitly handles the case where all elements are Integer.MIN_VALUE itself,
        // as 'largest' would be MIN_VALUE, and nothing can be < MIN_VALUE.
        return secondLargest;
    }

    /**
     * Finds the second largest number in an integer array using a single iteration (pass)
     * by maintaining two variables: one for the largest and one for the second largest.
     *
     * Logic:
     * 1. Handle Edge Cases: If the array is null or has fewer than 2 elements,
     *    return {@code Integer.MIN_VALUE} as a second largest cannot be determined.
     * 2. Initialization: Initialize `largest` and `secondLargest` to {@code Integer.MIN_VALUE}.
     *    This helps correctly handle arrays with negative numbers and ensures that any array element
     *    will be greater than these initial values if the array is not empty.
     * 3. Single Iteration: Iterate through each `number` in the array:
     *    a. If `number > largest`: This means we've found a new largest number.
     *       The current `largest` becomes the new `secondLargest` (`secondLargest = largest`).
     *       The current `number` becomes the new `largest` (`largest = number`).
     *    b. Else if `number > secondLargest && number < largest`: This `number` is smaller
     *       than the current `largest` but greater than the current `secondLargest`.
     *       It's a candidate for the second largest, so update `secondLargest = number`.
     *       The `number < largest` condition is crucial for handling duplicate largest values correctly.
     *       For example, in an array like {5, 5, 3}, when the second 5 is encountered, it should not
     *       overwrite `secondLargest` (which would be 3 from the first 5).
     * 4. Result: After the loop, if `secondLargest` is still {@code Integer.MIN_VALUE},
     *    it implies that a distinct second largest number was not found. This can happen if:
     *    - All elements in the array are the same (e.g., {7, 7, 7}).
     *    - The array effectively had only one distinct value after considering all elements.
     *    - The array was too small (though the initial check should handle length < 2).
     *    In such cases, {@code Integer.MIN_VALUE} is returned. Otherwise, the determined `secondLargest` is returned.
     *
     * Return Value:
     * - The second largest distinct number if found.
     * - {@code Integer.MIN_VALUE} if the array is null, has fewer than 2 elements,
     *   or if no distinct second largest number exists.
     *
     * Time Complexity: O(n)
     * The method involves a single pass through the array.
     *
     * Space Complexity: O(1)
     * Only a fixed number of variables (`largest`, `secondLargest`, loop iterator) are used.
     *
     * Advantages:
     * - Optimal Efficiency: Achieves linear time complexity with constant space.
     * - No Array Modification: The input array remains unchanged.
     * - Single Pass: Processes the array only once.
     *
     * Disadvantages:
     * - Trickier Logic: The conditional logic to update `largest` and `secondLargest` (especially handling
     *   duplicates and the order of updates) can be slightly more complex to reason about initially
     *   compared to sorting or two-pass approaches. Careful testing with edge cases is important.
     *
     * When to Use:
     * - Generally the preferred method for this specific problem (finding the second largest)
     *   when performance (O(n) time, O(1) space) and non-modification of the array are key requirements.
     *
     * @param arr The input integer array.
     * @return The second largest distinct number, or {@code Integer.MIN_VALUE} if not found or input is invalid.
     */
    public static int findSecondLargestSingleIteration(int[] arr) {
        // Edge Case: Null or array with fewer than 2 elements
        if (arr == null || arr.length < 2) {
            return Integer.MIN_VALUE;
        }

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        // Iterate through each number in the array
        for (int number : arr) {
            if (number > largest) {
                // Current number is the new largest.
                // The old largest becomes the new secondLargest.
                secondLargest = largest;
                largest = number;
            } else if (number > secondLargest && number < largest) {
                // Current number is between largest and secondLargest.
                // It becomes the new secondLargest.
                // The condition 'number < largest' handles duplicates of the largest number correctly.
                // E.g., if arr = {5, 5, 3}, when the second 5 is processed:
                // 'largest' is 5, 'secondLargest' is MIN_VALUE (or whatever was before the first 5).
                // The second 5 is not > largest.
                // It's not < largest either, so this 'else if' block is skipped for the duplicate 5.
                // If arr = {5, 3, 5}, when the second 5 is processed:
                // 'largest' is 5, 'secondLargest' is 3.
                // The second 5 is not > largest.
                // It's not < largest either.
                // This ensures that 'secondLargest' isn't incorrectly updated to a duplicate of 'largest'.
                secondLargest = number;
            }
        }

        // If secondLargest is still Integer.MIN_VALUE, it means no distinct second largest was found.
        // This occurs if all elements are the same, or if the array effectively has only one distinct value.
        // Example: {7, 7, 7} -> largest = 7, secondLargest = MIN_VALUE
        // Example: {7} (handled by initial check)
        // Example: {7, MIN_VALUE} -> largest = 7, secondLargest = MIN_VALUE
        return secondLargest;
    }

    /**
     * Finds the second largest number in an integer array using the Java Stream API.
     *
     * Logic:
     * 1. Handle Edge Cases: An initial check for null or arrays with fewer than 2 elements
     *    returns {@code Integer.MIN_VALUE}. While streams can handle empty inputs, an explicit
     *    check is clearer for the problem's requirements.
     * 2. Stream Pipeline:
     *    a. Convert the array to an {@code IntStream} using {@code java.util.Arrays.stream(arr)}.
     *    b. Remove duplicate elements using {@code .distinct()}. This is crucial for finding the
     *       *distinct* second largest.
     *    c. Box the elements from {@code IntStream} to {@code Stream<Integer>} using {@code .boxed()}
     *       to allow sorting with a custom comparator (like {@code Comparator.reverseOrder()}).
     *    d. Sort the distinct elements in descending order using {@code .sorted(java.util.Comparator.reverseOrder())}.
     *       The largest element will be first.
     *    e. Skip the largest element (the first one after reverse sorting) using {@code .skip(1)}.
     *    f. Attempt to find the next element (which would be the second largest) using {@code .findFirst()}.
     *       This returns an {@code Optional<Integer>}.
     * 3. Result:
     *    - If the {@code Optional<Integer>} contains a value (i.e., {@code .isPresent()} is true),
     *      it means a second largest distinct element was found. Return this value using {@code .get()}.
     *    - Otherwise (the Optional is empty), it means there were fewer than two distinct elements
     *      in the array (e.g., array was empty, all elements were same, or only one distinct element).
     *      In this case, return {@code Integer.MIN_VALUE}.
     *
     * Return Value:
     * - The second largest distinct number if found.
     * - {@code Integer.MIN_VALUE} if the array is null, has fewer than 2 elements,
     *   or if no distinct second largest number exists.
     *
     * Time Complexity: O(n log k) or O(n log n) in worst-case.
     * - {@code Arrays.stream(arr)}: O(n)
     * - {@code .distinct()}: Can be O(n) on average if it uses a HashSet internally.
     * - {@code .boxed()}: O(n)
     * - {@code .sorted(...)}: O(k log k) where 'k' is the number of distinct elements. In the worst case, k=n,
     *   so it's O(n log n). If 'k' is very small (e.g., constant), this part could be faster.
     * - {@code .skip(1)}: O(1) effectively, as it just adjusts the stream pointer.
     * - {@code .findFirst()}: O(1) after previous operations.
     *   The dominant factor is usually sorting, so O(n log n) in the general worst-case for distinct elements,
     *   or O(n) if k is small and distinct() is efficient.
     *
     * Space Complexity: O(k)
     * - {@code .distinct()}: May require O(k) space to store the unique elements (e.g., in a HashSet).
     * - {@code .sorted(...)}: Sorting might require O(k) or O(log k) space depending on the implementation
     *   (e.g. Timsort used in Java for objects often takes O(k) in worst case, or O(log k) for some variations).
     *   'k' is the number of distinct elements.
     *
     * Advantages:
     * - Concise and Expressive: Leverages functional programming style, can be very readable for those familiar with Streams.
     * - Handles Duplicates Naturally: The {@code .distinct()} operation elegantly manages duplicate values.
     * - Chainable: Fits well into larger stream processing pipelines.
     *
     * Disadvantages:
     * - Overhead: For very small arrays, the overhead of creating and managing streams, boxing, and using comparators
     *   can be higher than direct iterative approaches.
     * - Complexity Analysis Nuances: Pinpointing exact time/space complexity can be more involved due to the
     *   internal workings of stream operations which can vary.
     * - Boxing/Unboxing: Conversion between primitive `int` and `Integer` objects (`.boxed()`) adds some overhead.
     *
     * When to Use:
     * - When working in a Java 8+ environment where a functional style is preferred or idiomatic.
     * - If the data is already part of a stream or needs other stream operations.
     * - When code conciseness and readability (for stream-savvy developers) are highly valued, and the potential
     *   overhead for typical array sizes is acceptable.
     *
     * @param arr The input integer array.
     * @return The second largest distinct number, or {@code Integer.MIN_VALUE} if not found or input is invalid.
     */
    public static int findSecondLargestUsingStream(int[] arr) {
        // Edge Case: Null or array with fewer than 2 elements
        if (arr == null || arr.length < 2) {
            return Integer.MIN_VALUE;
        }

        // Use Java Stream API to find the second largest element
        java.util.Optional<Integer> optionalResult = java.util.Arrays.stream(arr) // 1. Create an IntStream from the array
                .distinct()                               // 2. Keep only unique elements
                .boxed()                                  // 3. Convert IntStream to Stream<Integer> for custom sorting
                .sorted(java.util.Comparator.reverseOrder()) // 4. Sort in descending order
                .skip(1)                                  // 5. Skip the largest element (the first one)
                .findFirst();                             // 6. Get the next element, which is the second largest

        // Check if a second largest element was found
        if (optionalResult.isPresent()) {
            return optionalResult.get(); // Return the found second largest element
        } else {
            // No second largest element found (e.g., all elements were the same, or only one distinct element)
            return Integer.MIN_VALUE;
        }
    }

    /**
     * == Comparison Summary of Methods to Find Second Largest Number ==
     *
     * This table provides a quick comparison of the different approaches implemented in this class
     * to find the second largest number in an integer array.
     *
     * | Method Name                        | Time Complexity                        | Space Complexity                                     | Strengths                                                                                                                               | When to Use                                                                                                                                                              |
     * |------------------------------------|----------------------------------------|------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
     * | `findSecondLargestUsingSorting`    | O(n log n)                             | O(1) or O(log n) (due to in-place sort on primitives)  | - Conceptually simple: sort then pick.<br>- Easy to implement if sorting is familiar.                                                     | - If the array is already sorted or needs sorting for other reasons.<br>- When conceptual simplicity is valued and O(n log n) is acceptable.<br>- **Modifies original array.** |
     * | `findSecondLargestTwoIterations`   | O(n)                                   | O(1)                                                 | - Linear time performance.<br>- Does not modify the array.<br>- Logic is straightforward (find max, then find max less than original max). | - When array modification is not allowed.<br>- When linear time is important and the two-pass clarity is preferred.                                                       |
     * | `findSecondLargestSingleIteration` | O(n)                                   | O(1)                                                 | - Most optimal: linear time, constant space, single pass.<br>- Does not modify the array.                                                | - Generally the preferred method for this problem due to best performance characteristics and non-modification of array.                                                   |
     * | `findSecondLargestUsingStream`     | O(n log k) or O(n log n) (worst-case)  | O(k) (k = # distinct elements)                       | - Concise, functional style (Java 8+).<br>- Handles duplicates naturally with `.distinct()`.                                           | - When a functional programming style is preferred in Java 8+.<br>- Useful if data is already a stream or needs other stream operations.<br>- Readability for stream users.    |
     *
     * *Notes:*
     * - 'n' is the number of elements in the array.
     * - 'k' is the number of distinct elements in the array.
     * - Space complexity for sorting primitives with `Arrays.sort()` is typically O(1) or O(log n) due to QuickSort's recursion stack or HeapSort.
     * - Stream operations like `distinct()` and `sorted()` can have space overheads for storing intermediate data.
     */
}
