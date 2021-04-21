import java.util.*;
//import org.jfree.chart.*;
//import org.jfree.data.general.*;

//create separate Graph class to house graph setup calculations
public class Graph{

  protected double[] counts;
  protected int total;
  protected String[][] data;
  protected List<String> vaccineTypes;
  public Graph(String[][] data, List<String> vaccineTypes){
     this.data=data;
     this.vaccineTypes=vaccineTypes;
     total=vaccineTypes.size();
  }

  //initialize count int array, each count[i] will correspond to the count of each vaccine type
  public void getCounts(){
    counts=new double[vaccineTypes.size()];
    //cycle through each vaccine type
    for(int i=0;i<vaccineTypes.size();i++)
    {
      String check=vaccineTypes.get(i);
      //check each 3rd element which should house the vaccine types
      for(int row=0;row<data.length;row++)
      {
        if(data[row][3].equals(check))
        {
          counts[i]=counts[i]+1.0;
        }
      }
    }
  }

  


}