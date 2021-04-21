import java.util.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.*;
import org.jfree.data.general.*;
public class Graph {
	protected double[] counts;
	  protected int total;
	  protected String[][] data;
	  protected List<String> vaccineTypes;
	  
	  public Graph(String[][] data, List<String> vaccineTypes){
	     this.data=data;
	     this.vaccineTypes=vaccineTypes;
	     total=vaccineTypes.size();
	     getCounts();
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

	  
	  public PieDataset createPieDataset() 
	  {
		  DefaultPieDataset dataset = new DefaultPieDataset( );
		  for(int i=0;i<vaccineTypes.size();i++)
		  {
			  dataset.setValue(vaccineTypes.get(i), counts[i]);
		  }
		  return dataset;
	  }
	  
	  public CategoryDataset createBarDataset() 
	  {
		  DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		  for(int i=0;i<vaccineTypes.size();i++)
		  {
			  dataset.addValue(counts[i],"vaccine",vaccineTypes.get(i));
		  }
		  return dataset;
	  }
	  
	  public JFreeChart createPieChart(PieDataset dataset)
	  {
		  JFreeChart chart=ChartFactory.createPieChart("Vaccine Types",dataset,true, true,false);
		  return chart;
	  }
	  
	  public JFreeChart createBarChart(CategoryDataset dataset)
	  {
		  JFreeChart chart=ChartFactory.createBarChart("Vaccine Count", "Vaccine Types", "Count", createBarDataset(), PlotOrientation.VERTICAL, true, true, false);
		  return chart;
	  }

	  
}
