public class SortStats {

    // This was put together following patterns from CS 201. It includes the default and overloaded constructors.
    // PRE:  None
    // POST: Initializes a SortStats object with default values or specified values.
    public String sortName;
    public String fileName;
    public long comparisons;
    public long swaps;
    public long loops;
    public long timeNano;

    //  Default constructor
    public SortStats() {
        this.sortName = "";
        this.fileName = "";
        this.comparisons = 0;
        this.swaps = 0;
        this.loops = 0;
        this.timeNano = 0;
    }

    // Overloaded constructor
    public SortStats(String sortName, String fileName, long comparisons, long swaps, long loops, long timeNano) {
        this.sortName = sortName;
        this.fileName = fileName;
        this.comparisons = comparisons;
        this.swaps = swaps;
        this.loops = loops;
        this.timeNano = timeNano;
    }
    
}
