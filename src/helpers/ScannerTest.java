package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ScannerTest {
    private final List<String> lines = new ArrayList<>();
    private final Random r = new Random();

    // Reads all non-empty lines of the input file at the given path
    public ScannerTest(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) lines.add(line);
        }
        scanner.close();
    }

    // First line of the file
    public int size() {
        return Integer.parseInt(lines.get(0));
    }

    
    public int amountOf(String type) {
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split("\\s+");  
            if (parts[0].equals(type)) {
                String amount = parts[1];
                if (amount.contains("-")) {                
                    String[] bounds = amount.split("-");
                    int lower = Integer.parseInt(bounds[0]);
                    int upper = Integer.parseInt(bounds[1]);
                    return r.nextInt(lower, upper + 1);
                }
                return Integer.parseInt(amount);           
            }
        }
        return 0;                                          
    }
}
