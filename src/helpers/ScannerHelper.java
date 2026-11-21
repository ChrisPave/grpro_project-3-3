package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import itumulator.world.Location;

public class ScannerHelper {
    private final List<String> lines = new ArrayList<>();
    private final Random r = new Random();

    // Reads all non-empty lines of the input file at the given path
    public ScannerHelper(String path) throws FileNotFoundException {
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
    public ArrayList<Location> locationOf(String type){
            ArrayList<Location> result = new ArrayList<>();
            for(int i = 1; i < lines.size(); i++){
                String[] parts = lines.get(i).split("\\s+");
                if(parts[0].equals(type) && parts.length>2){
                    String string = parts[2];
                    string = string.replace("(", "");
                    string = string.replace(")", "");
                    String[] coordinates = string.split(",");
                    int x = Integer.parseInt( coordinates[0]);
                    int y = Integer.parseInt(coordinates[1]);
                    result.add(new Location(x, y));
                } 
            }
            return result;
        } 
    
        
}
