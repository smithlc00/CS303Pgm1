public class RadixSort {
    
    //  PRE:  Accepts the array of integers and the input file name.
    //  POST: Sorts the array using Radix Sort and returns a SortStats object
    //  My original code did not run the radix sort inside the sortstats class. 
    public static SortStats sort(int[] arr, String fileName) {
        SortStats stats = new SortStats();
        stats.sortName = "Radix Sort";
        stats.fileName = fileName;

        if (arr == null || arr.length == 0) {
            return stats;
        }

        long startTime = System.nanoTime();
        
        //  Use getMax helper method to find the maximum number in the array to determine the number of digits
        int max = getMax(arr, stats);

        //  Perform Counting Sort for each digit position (exp = 1, 10, 100, ...)
        int n = arr.length;
        int[] output = new int[n]; // Temporary output array per digit pass

        // Loop through each digit position
        // LLM was prompted to create loop for each digit position.
        for (int exp = 1; max / exp > 0; exp *= 10) {
            stats.loops++;

            int[] count = new int[10];

            // Count occurrences of each digit in the current position.
            // My original code did not include the count variable, which is necessary for counting sort.
            for (int i = 0; i < n; i++) {
                stats.loops++;
                int digit = (arr[i] / exp) % 10;
                count[digit]++;
            }

            // Calculate starting positions (prefix sum)
            for (int i = 1; i < 10; i++) {
                stats.loops++;
                count[i] += count[i - 1];
            }

            // Place elements in output array (stable pass, working backward)
            // My original code started from 0 instead of 9, which would not maintain stability. The LLM suggestion iterating backwards.
            for (int i = n - 1; i >= 0; i--) {
                stats.loops++;
                int digit = (arr[i] / exp) % 10;
                output[count[digit] - 1] = arr[i];
                count[digit]--;
                stats.swaps++; // Track data movements into temporary array
            }

            // Copy output array back into original array
            for (int i = 0; i < n; i++) {
                stats.loops++;
                arr[i] = output[i];
                stats.swaps++; // Track data movements back to main array
            }
        }

        long endTime = System.nanoTime();
        stats.timeNano = endTime - startTime;

        return stats;
    }

    // PRE:  arr is a non-empty array of integers
    // POST: Returns the maximum value in the array while updating SortStats for comparisons and loops
    private static int getMax(int[] arr, SortStats stats) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            stats.loops++;
            stats.comparisons++;
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
        
}

