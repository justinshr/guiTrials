import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
public class Main {
  protected JFrame frame;
  protected JPanel aboutPanel;
  protected JPanel loadPanel;
  protected GridBagConstraints buttonPanelLayout;

  protected JPanel addPanel;
  protected JPanel savePanel;
  protected JPanel visualizePanel;

  protected JPanel overallPanel;
  protected JPanel buttonPanel;
  protected JButton aboutButton;
  protected JButton loadButton;
  protected JButton addButton;
  protected JButton saveButton;
  protected JButton visualButton;

  protected DefaultTableModel loadTableModel;

  protected Data dataObject=new Data();
  protected String[] columns={"ID", "Last Name","First Name", "Vaccine Type","Vaccination Date","Vaccine Location"};

  JLabel error;

  //Constructor, sets up the GUI application and performs other operations
  public Main(){
    
    setUpFrame();
  }

  public void setUpFrame(){
    //form basis of the GUI application, starting with the Title: Covid 19 vaccine information
    frame = new JFrame("COVID 19 Vaccine Information");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(750,500);
    //frame.setSize(300,300);
    //create a formating panel to add to the frame
    overallPanel=new JPanel(new GridBagLayout());
    //overallPanel.setBorder(new EmptyBorder(0,0,0,0));

    //create a panel of buttons to put vertically
    buttonPanel=new JPanel(new GridLayout(10,1,0,2));
    //create the buttons
    aboutButton = new JButton("About");
    //when about button gets pressed, open the About frame by sending a message to openFrame method
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
    addButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent event)
      {
        openFrame("add");
      }
    });

    saveButton=new JButton("Save Data");
    saveButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent event)
      {
        openFrame("save");
      }
    });

    visualButton=new JButton("Visualize Data");
    visualButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent event)
      {
        openFrame("visual");
      }
    });

    //create Panels that show up when buttons are pressed
    setUpPanels();

    //add buttons to the buttonPanel
    buttonPanel.add(aboutButton);
    buttonPanel.add(loadButton);
    buttonPanel.add(addButton);
    buttonPanel.add(saveButton);
    buttonPanel.add(visualButton);

    buttonPanelLayout = new GridBagConstraints();
    buttonPanelLayout.anchor=GridBagConstraints.FIRST_LINE_START;
    overallPanel.add(buttonPanel,0);

    //keeps button on the left side of the application
    frame.add(overallPanel,BorderLayout.LINE_START);

    frame.setVisible(true);
  }

  public void setUpPanels()
  {
    //set up Panels to be to the right of the buttonPanel
    GridBagConstraints panelLayout = new GridBagConstraints();
    panelLayout.anchor=GridBagConstraints.FIRST_LINE_START;
    //add padding to the left so the new panels are not too close to the buttons
    panelLayout.insets=new Insets(0,10,0,0);
    
    //set up individual panels
    setUpAbout(panelLayout);
    preLoad(panelLayout);
    //setUpLoad(panelLayout);
    setUpAdd(panelLayout);
    setUpSave(panelLayout);
    setUpVisualize(panelLayout);
  }

  public void setUpAbout(GridBagConstraints panelLayout)
  {
    //use HTML to create new lines 
    JLabel aboutTitle=new JLabel("<html> <h3>Team 45</h3> <br/> Justin Shr <br/> Manav Korlipara <br/> Sohan Kancherla <br/> Ayush Gaur </html>");
    aboutPanel=new JPanel();
    aboutPanel.add(aboutTitle);
    overallPanel.add(aboutPanel, panelLayout);
    //make this panel invisible, until the About button is pressed
    aboutPanel.setVisible(false);
  }

  //helper method that creates a textfield for users to put in path to csv file
  public void preLoad(GridBagConstraints panelLayout)
  {
    loadPanel=new JPanel();
    JLabel ask=new JLabel("Enter Valid Path");
    JTextField textInput=new JTextField(5);
    //creates a text field for user to put in valid path
    textInput.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent event) {
        String input=textInput.getText();
        //removes the text field to create table
        ask.setVisible(false);
        textInput.setVisible(false);
        //calls this method to check if path is valid and creates table
        setUpLoad(panelLayout,input);
      }
    });
    loadPanel.add(ask);
    loadPanel.add(textInput);
    overallPanel.add(loadPanel, panelLayout);

    //make this panel invisible, until the Load button is pressed
    loadPanel.setVisible(false);
    //setUpLoad(panelLayout," ");
    
  }

  //main load method that takes user input and creates table with bars
  public void setUpLoad(GridBagConstraints panelLayout, String input)
  {
    if(dataObject.validPath(input))
    {
      dataObject.addLines(input);
      String[][] data=dataObject.returnData;
      if(data==null)
      {
        data=new String[1][6];
        for(int i=0;i<6;i++)
        {
          data[0][i]="null";
        }
      }
      //createTable(data, columns);
      loadTableModel=new DefaultTableModel(data,columns);
      //loadTable=new JTable(data, columns);
      JTable loadTable=new JTable(loadTableModel);

      //made some columns wider as some of the column names were too long
      loadTable.getColumnModel().getColumn(3).setPreferredWidth(100);
      loadTable.getColumnModel().getColumn(4).setPreferredWidth(150);
      loadTable.getColumnModel().getColumn(5).setPreferredWidth(150);

      JScrollPane scrollPane = new JScrollPane(loadTable);

      //to create Horizontal scrollbars
      loadTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

      loadPanel.add(scrollPane);
    }
    else
    {
      
      JLabel error=new JLabel("<html><b>Invalid Path</b></html>");
      loadPanel.add(error);
      
    }
    overallPanel.add(loadPanel, panelLayout);
  }

  public void setUpAdd(GridBagConstraints panelLayout)
  {
    JPanel labelPanel=new JPanel(new GridLayout(0,1));
    JPanel textfieldPanel=new JPanel(new GridLayout(0,1));
    addPanel=new JPanel();

    String addDate="Date: ";
    String addId="ID:";
    String addLastName="Last Name:";
    String addFirstName="First Name:";
    String addType="Vaccination Type: ";
    String addLocation="Location: ";

    JLabel dateLabel=new JLabel(addDate);
    JLabel idLabel=new JLabel(addId);
    JLabel lastNameLabel=new JLabel(addLastName);
    JLabel firstNameLabel=new JLabel(addFirstName);
    JLabel typeLabel=new JLabel(addType);
    JLabel locationLabel=new JLabel(addLocation);

    labelPanel.add(dateLabel);
    labelPanel.add(idLabel);
    labelPanel.add(lastNameLabel);
    labelPanel.add(firstNameLabel);
    labelPanel.add(typeLabel);
    labelPanel.add(locationLabel);

    JFormattedTextField dateField=new JFormattedTextField();
    //dateField.setValue(new Integer(numPeriods));
    dateField.setColumns(10);
 
    JFormattedTextField idField=new JFormattedTextField();
      //idField.setValue(new Integer(numPeriods));
    idField.setColumns(10);
    

    JTextField lastField=new JTextField(10);
    JTextField firstField=new JTextField(10);
    JTextField typeField=new JTextField(10);
    JTextField locationField=new JTextField(10);
    

    textfieldPanel.add(dateField);
    textfieldPanel.add(idField);
    textfieldPanel.add(lastField);
    textfieldPanel.add(firstField);
    textfieldPanel.add(typeField);
    textfieldPanel.add(locationField);

    JButton submitButton=new JButton("Submit Data");
    submitButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent event)
      {
        String id=idField.getText();  //non string
        String date=dateField.getText(); //non string
        String last=lastField.getText();
        String first=firstField.getText();
        String type=typeField.getText();
        String location=locationField.getText();
        if(last.equals("")||first.equals("")||type.equals("")||location.equals("")|| !validInputs( id, last, first, type,  date, location)){
          error=new JLabel("invalid inputs");
          error.setVisible(true);
          addPanel.add(error);
        }
        else{
          String newRow[]={id,last,first,type,date,location};
          loadTableModel.addRow(newRow);
          dataObject.addRow(id,last,first,type,date,location);
        }
        dateField.setText("");
        idField.setText("");
        lastField.setText("");
        firstField.setText("");
        typeField.setText("");
        locationField.setText("");
      }
    });
    
    
    addPanel.add(labelPanel,BorderLayout.CENTER);
    addPanel.add(textfieldPanel,BorderLayout.LINE_END);
    addPanel.add(submitButton);
    
    overallPanel.add(addPanel, panelLayout);


    //make this panel invisible, until the Add button is pressed
    addPanel.setVisible(false);
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

  public void setUpSave(GridBagConstraints panelLayout)
  {
    JLabel loadTitle=new JLabel("Save");
    savePanel=new JPanel();
    JLabel ask=new JLabel("Enter Valid File To Write To");
    JTextField textInput=new JTextField(5);
    //creates a text field for user to put in valid path
    textInput.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent event) {
        String input=textInput.getText();
        //removes the text field to create table
        ask.setVisible(false);
        textInput.setVisible(false);
        //calls this method to check if path is valid and creates table
        dataObject.writeFile(input);
      }
    });
    //savePanel.add(loadTitle);
    savePanel.add(ask);
    savePanel.add(textInput);
    overallPanel.add(savePanel, panelLayout);
    //make this panel invisible, until the Load button is pressed
    savePanel.setVisible(false);
  }

  public void setUpVisualize(GridBagConstraints panelLayout)
  {
    JLabel loadTitle=new JLabel("Visualize");
    visualizePanel=new JPanel();
    visualizePanel.add(loadTitle);
    overallPanel.add(visualizePanel, panelLayout);
    //make this panel invisible, until the Visualize button is pressed
    visualizePanel.setVisible(false);
  }

  private void openFrame(String buttonName){
    //make panels invisible first, so no conflict of frames to show
    loadPanel.setVisible(false);
    aboutPanel.setVisible(false);
    addPanel.setVisible(false);
    savePanel.setVisible(false);
    visualizePanel.setVisible(false);
    if(error!=null)
    {
      error.setVisible(false);
    }
    //following are if statements based on which button was pressed to show what frame
    if(buttonName.equals("about"))
    {
      aboutPanel.setVisible(true);
    }
    else if(buttonName.equals("load"))
    {
      loadPanel.setVisible(true);

    }
    else if(buttonName.equals("add"))
    {
      addPanel.setVisible(true);
    }
    else if(buttonName.equals("save"))
    {
      savePanel.setVisible(true);
    }
    else if(buttonName.equals("visual"))
    {
      visualizePanel.setVisible(true);
    }

  }
  public static void main(String[] args) {
    //call the GUI application
    new Main();
  }
}