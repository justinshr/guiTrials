import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Data {

    private static List<String> lines = new ArrayList<>();

    public void addLines() {
        try {
            String line = "";
            String splitBy = ",";
            String currLine = "";
            BufferedReader br = new BufferedReader(new FileReader("/Users/manav/guiTrials/TeamProjectRandomData - 10People.csv"));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                currLine = (data[0] + ", " + data[1] + ", " + data[2] +
                        ", " + data[3] + ", " + data[4] + ", " + data[5]);
                lines.add(currLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        lines.remove(0);
    }

    public String getLine (int lineNumber){
        return lines.get(lineNumber - 1);
    }
}
