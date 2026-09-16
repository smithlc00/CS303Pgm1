public class Main {
    
    public static void main(String[] args) {

        //  List of input text files to process
        String[] inputFiles = {"data1.txt", "data2.txt", "data3.txt"};
        
        //  Output file path for performance metrics
        String reportFile = "report.txt";

        System.out.println("Starting Radix Sort.");

        for (String filePath : inputFiles) {
            
            //  Read input data file into array
            int[] data = Functions.readDataFile(filePath);

            if (data.length == 0) {
                System.out.println("Skipping empty or unreadable file: " + filePath);
                continue;
            }

            //  Run Radix Sort and record performance statistics
            SortStats stats = RadixSort.sort(data, filePath);

            //  Write results to output report file
            Functions.writeReportToFile(stats, reportFile);

            System.out.println("Processed: " + filePath);
        }

        System.out.println("Results saved to " + reportFile);
    }
}
