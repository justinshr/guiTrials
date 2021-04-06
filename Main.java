import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
public class Main {
  JFrame frame;
  JPanel overallPanel;
  JPanel buttonPanel;
  public Main(){
    //form basis of the GUI application, starting with the Title: Covid 19 vaccine information
    frame = new JFrame("COVID 19 Vaccine Information");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300,300);
    
    //create a formating panel to add to the frame
    overallPanel=new JPanel(new GridBagLayout());
    overallPanel.setBorder(new EmptyBorder(0,0,0,0));

    //create a panel of buttons to put vertically
    buttonPanel=new JPanel(new GridLayout(10,1,0,2));
    //create the buttons
    JButton aboutButton = new JButton("About");
    JButton loadButton=new JButton("Load Data");
    JButton addButton=new JButton("Add Data");
    JButton saveButton=new JButton("Save Data");
    JButton visualButton=new JButton("Visualize Data");

    //add buttons to the buttonPanel
    buttonPanel.add(aboutButton);
    buttonPanel.add(loadButton);
    buttonPanel.add(addButton);
    buttonPanel.add(saveButton);
    buttonPanel.add(visualButton);
    overallPanel.add(buttonPanel);

    //keeps button on the left side of the application
    frame.add(overallPanel,BorderLayout.LINE_START);
    frame.setVisible(true);
  }
  public static void main(String[] args) {
    //call the GUI application
    new Main();
  }
}