import java.io.File;
import java.io.IOException;
import java.util.*;

public class Question3 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("src/src/Question1.java"));
        int lineCount = 0;

        Map<String, Set<Integer>> identifierMap = new HashMap<>();
        ArrayList<String> lines = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            lines.add(line);
            Scanner lineRead = new Scanner(line);
            lineRead.useDelimiter("[^A-Za-z0-9_]+");
            while (lineRead.hasNext()) {
                String identifier = lineRead.next();
                if (identifierMap.containsKey(identifier)) {
                    identifierMap.get(identifier).add(lineCount);
                } else {
                    identifierMap.put(identifier, new HashSet<>());
                    identifierMap.get(identifier).add(lineCount);
                }
            }
            lineCount++;
        }

        for (Map.Entry<String, Set<Integer>> entry : identifierMap.entrySet()) {
            String key = entry.getKey();
            System.out.println("Identifier : "+key);
            for (Integer lineNum:identifierMap.get(key)){
                String line = lines.get(lineNum);
                System.out.println("Line :"+lineNum+" -> "+line);
            }
        }
    }
}
