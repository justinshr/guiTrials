import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.*;

public class Data {

    private static List<String> lines = new ArrayList<>();
    private int columns=0;
    //returnData is one of the parameters needed for the Load Data's table
    public static String[][] returnData;
    //create a List<String> to find the different vaccine types to help with graphs
    private static List<String> vaccineTypes=new ArrayList<String>();
    public void addLines(String path) {
        try {
            String line = "";
            String splitBy = ",";
            String currLine = "";
            //BufferedReader br = new BufferedReader(new FileReader("/Users/manav/guiTrials/TeamProjectRandomData - 10People.csv"));
            BufferedReader br = new BufferedReader(new FileReader(path));
            columns=0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                //currLine = (data[0] + ", " + data[1] + ", " + data[2] +
                //        ", " + data[3] + ", " + data[4] + ", " + data[5]);
                //add individually into lines to make conversion into String [][] easier
                //try to ignore the first line of csv files that has the format of the strings
                if(!data[0].equals("ID"))
                {
                  for(int i=0;i<6;i++)
                  {
                    lines.add(data[i]);
                  }
                columns++;
                }
            }
            setUpObjectArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lines.remove(0);
    }

    public String getLine (int lineNumber){
        return lines.get(lineNumber - 1);
    }

    public void addRow(String id, String lName, String fName, String type, String date, String location)
    {
      columns+=1;
      lines.add(id);
      lines.add(lName);
      lines.add(fName);
      lines.add(type);
      lines.add(date);
      lines.add(location);
      setUpObjectArray();
    }

    public void setUpObjectArray(){
        returnData=new String[columns][6];
        //create an index to parse through lines
        int index=0;
        for(int i=0;i<columns;i++)
        {
          for(int j=0;j<6;j++)
          {
            if(index>=lines.size())
            {
              break;
            }
            returnData[i][j]=lines.get(index);
            //find individual vaccine types by looking at the 3rd element
            if(j==3 && !vaccineTypes.contains(lines.get(index)))
            {
              vaccineTypes.add(lines.get(index));
            }
            index++;
          }
        }


    }

    public boolean validPath(String input)
    {
      File file=new File(input);
      if(file.exists())
      {
        return true;
      }
      else
      {
        return false;
      }
    }

    //use this method to implement save data
    public void writeFile(String input){

    }

}
