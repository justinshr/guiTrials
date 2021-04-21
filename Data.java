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
    public static List<String> vaccineTypes=new ArrayList<String>();
    public void addLines(String path) {
        try {
            String line = "";
            String splitBy = ",";
            BufferedReader br = new BufferedReader(new FileReader(path));
            columns=0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
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
    }

  public String getLine (int lineNumber){
      return lines.get(lineNumber - 1);
  }

  public void addRow(String[] newRow)
  {
    columns+=1;
    for(String newFields:newRow)
    {
      lines.add(newFields);
    }
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

    //check if added inputs are valid
  public boolean validInputs(String id, String last, String first,String type, String date, String location)
  {
    return numberInputs(id,false) && stringInputs(last) && stringInputs(first) && stringInputs(type) && numberInputs(date,true) && stringInputs(location);
  }

  //check if inputs that are supposed to be Strings are strings
  public boolean stringInputs(String input)
  {
    for(int i=0;i<input.length();i++)
    {
      char check=input.charAt(i);
      if(!Character.isLetter(check))
      {
        return false;
      }
    }
    return true;
  }
  //check if inputs are numbers
  public boolean numberInputs(String input, boolean date)
  {
    if(!date && input.length()>5)
    {
      return false;
    }
    for(int i=0;i<input.length();i++)
    {
      char check=input.charAt(i);
      if(date &&(i==2 || i==5))
      {
        continue;
      }
      if(!Character.isDigit(check))
      {
        return false;
      }
    }
    return true;
  }

  //if an ID length is less than 5, add 0s in front of it
  public String idFix(String id)
  {
    if(id.length()==5)
    {
      return id;
    }
    else{
      int zeros=5-id.length();
      for(int i=1;i<=zeros;i++)
      {
        id="0"+id;
      }
      return id;
    }
  }

   //use this method to implement save data
  //return true if successful save
  public boolean setUpSave(String input)
  {
    try {
        File myObj = new File(input);
        if (myObj.createNewFile()) {
            FileWriter csvWriter = new FileWriter(input);
            
            String[] columns={"ID", "Last Name","First Name", "Vaccine Type","Vaccination Date","Vaccine Location"};
            String[][] data=returnData;
            if(data==null)
            {
              return false;
            }
            //add the columns since this was filtered out when reading the csv file initially
            for(int firstLine=0;firstLine<6;firstLine++)
            {
              csvWriter.append(columns[firstLine]);
              if(firstLine<5)
              {
                csvWriter.append(",");
              }
            }
            csvWriter.append("\n");
            //cycle through data and add to csv file
            for (int i = 0; i<data.length; i++){
                for (int j = 0; j<data[i].length; j++){
                    csvWriter.append(data[i][j]);
                    if(j<data[i].length-1)
                    {
                      csvWriter.append(",");
                    }
                }
                if(i<data.length-1)
                {
                  csvWriter.append("\n");
                }
            }
            csvWriter.flush();
            csvWriter.close();
            return true;

        } 
        else {
            return false;
        }
    } catch (IOException e) {
        return false;
    }
  }

}
