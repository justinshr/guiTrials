import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
public class Main {
  protected JFrame frame;
  protected JPanel aboutPanel;
  protected JPanel loadPanel;

  protected JPanel overallPanel;
  protected JPanel buttonPanel;
  protected JButton aboutButton;
  protected JButton loadButton;
  protected JButton addButton;
  protected JButton saveButton;
  protected JButton visualButton;

  //Constructor, sets up the GUI application and performs other operations
  public Main(){
    
    setUpFrame();
  }

  public void setUpFrame(){
    //form basis of the GUI application, starting with the Title: Covid 19 vaccine information
    frame = new JFrame("COVID 19 Vaccine Information");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300,300);
    
    //create a formating panel to add to the frame
    overallPanel=new JPanel(new GridBagLayout());
    //overallPanel.setBorder(new EmptyBorder(0,0,0,0));

    //create a panel of buttons to put vertically
    buttonPanel=new JPanel(new GridLayout(10,1,0,2));
    //create the buttons
    aboutButton = new JButton("About");

    aboutButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent event)
      {
        openFrame("about");
      }
    });
    loadButton=new JButton("Load Data");

    loadButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent event)
      {
        openFrame("load");
      }
    });

    addButton=new JButton("Add Data");
    saveButton=new JButton("Save Data");
    visualButton=new JButton("Visualize Data");

    setUpPanels();

    //add buttons to the buttonPanel
    buttonPanel.add(aboutButton);
    buttonPanel.add(loadButton);
    buttonPanel.add(addButton);
    buttonPanel.add(saveButton);
    buttonPanel.add(visualButton);

    GridBagConstraints buttonPanelLayout = new GridBagConstraints();
    buttonPanelLayout.anchor=GridBagConstraints.FIRST_LINE_START;
    overallPanel.add(buttonPanel,0);

    //keeps button on the left side of the application
    frame.add(overallPanel,BorderLayout.LINE_START);
    frame.setVisible(true);
  }

  public void setUpPanels()
  {
    GridBagConstraints panelLayout = new GridBagConstraints();
    panelLayout.anchor=GridBagConstraints.PAGE_START;
    panelLayout.insets=new Insets(0,10,0,0);
    
    setUpAbout(panelLayout);
    setUpLoad(panelLayout);
    
  }

  public void setUpAbout(GridBagConstraints panelLayout)
  {
    JLabel aboutTitle=new JLabel("About");
    aboutPanel=new JPanel();
    aboutPanel.add(aboutTitle);
    overallPanel.add(aboutPanel, panelLayout);
    aboutPanel.setVisible(false);
  }

  public void setUpLoad(GridBagConstraints panelLayout)
  {
    JLabel loadTitle=new JLabel("Load");
    loadPanel=new JPanel();
    loadPanel.add(loadTitle);
    overallPanel.add(loadPanel, panelLayout);
    loadPanel.setVisible(false);
  }

  private void openFrame(String buttonName){
    loadPanel.setVisible(false);
    aboutPanel.setVisible(false);
    if(buttonName.equals("about"))
    {
      aboutPanel.setVisible(true);
    }
    else if(buttonName.equals("load"))
    {
      loadPanel.setVisible(true);
    }

  }
  public static void main(String[] args) {
    //call the GUI application
    new Main();
  }
}