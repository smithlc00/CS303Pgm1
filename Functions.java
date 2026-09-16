import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Functions {
    
    //  PRE:  filePath is a valid path to a text file containing integers, one per line
    //  POST: Returns an array of integers read from the specified file.
    public static int[] readDataFile(String filePath) {
        List<Integer> dataList = new ArrayList<>();
        File file = new File(filePath);

        System.out.println("Looking for file at: " + file.getAbsolutePath());

        try (Scanner scanner = new Scanner(file)) {
            scanner.useDelimiter("[,\\s]+");
            while (scanner.hasNextInt()) {
                dataList.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found at path - " + filePath);
            return new int[0];
        }

        // Convert the Integer List into an Array
        // LLM suggestion: This is done to return a primitive int array instead of a List<Integer>, it is more efficient this way.
        int[] dataArray = new int[dataList.size()];
        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i] = dataList.get(i);
        }

        return dataArray;
    }

    //  PRE:  accepts a SortStats object and a valid output file name
    //  POST: Writes the performance statistics to the output file.
    public static void writeReportToFile(SortStats stats, String outputFileName) {
        File reportFile = new File(outputFileName);
        boolean fileExists = reportFile.exists();

        try (PrintWriter writer = new PrintWriter(new FileWriter(reportFile, true))) {
            // Write column headers if the report file is newly created or empty
            if (!fileExists || reportFile.length() == 0) {
                writer.printf("%-15s %-20s %-12s %-15s %-12s%n", 
                              "Sort Name", "File", "Swaps", "Comparisons", "Loops");
                writer.println("-------------------------------------------------------------------------");
            }

            // Write 5-column data row
            writer.printf("%-15s %-20s %-12d %-15d %-12d%n",
                          stats.sortName,
                          stats.fileName,
                          stats.swaps,
                          stats.comparisons,
                          stats.loops);

        } catch (IOException e) {
            System.err.println("Error writing report to file: " + e.getMessage());
        }
    }

}
