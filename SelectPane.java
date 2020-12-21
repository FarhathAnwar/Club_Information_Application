
//  Description: The Assignment6 class creates a Tabbed Pane with
//               two tabs, one for Club Creation and one for
//               Club Selection.

import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;	//**Need to import
import javafx.event.EventHandler;	//**Need to import
import java.util.ArrayList;

//import all other necessary javafx classes here
//----

public class SelectPane extends BorderPane
{
   private ArrayList<Club> clubList;
   private Label label_display, label_result;

   private int row;
   private final int column = 0;
   private int members;

   //constructor
   public SelectPane(ArrayList<Club> list)
   {
   	   //initialize instance variables
       this.clubList = list;
       row = 1;
       members = 0;
       
       //set up the layout
       label_display = new Label("Select some clubs");
       label_result = new Label("The total number of members for the selected club(s): 0");


       
    
       //create an empty pane where you can add check boxes later
       GridPane gridPane = new GridPane();
       gridPane.setVgap(25);
       
       //SelectPane is a BorderPane - add the components here
       //----
       this.setTop(label_display);
       this.setBottom(label_result);
       this.setCenter(gridPane);

       
   } //end of constructor

 //This method uses the newly added parameter Club object
 //to create a CheckBox and add it to a pane created in the constructor
 //Such check box needs to be linked to its handler class
 public void updateClubList(Club newClub)
 {
     //-------
     CheckBox checkBox = new CheckBox(newClub.toString());
     GridPane gridPane = (GridPane) this.getCenter();
     HBox h1 = new HBox(checkBox);
     gridPane.add(h1,column,row);

     row++;

     checkBox.setOnAction(new SelectionHandler());

 }

 //create a SelectionHandler class
 private class SelectionHandler implements EventHandler<ActionEvent>
    {
        //Override the abstact method handle()
        public void handle(ActionEvent event)
        {
            //When any radio button is selected or unselected
            //the total number of members of selected clubs should be updated
            //and displayed using a label.
            CheckBox checkBox = (CheckBox) event.getSource();
            String temp = "";
            if(checkBox.isSelected()){
                temp = checkBox.getText();
                for(Club c: clubList){
                    if (temp.equals(c.toString())) {
                        members += c.getNumberOfMembers();
                    }
                }
            }else{
                temp = checkBox.getText();
                for(Club c: clubList){
                    if (temp.equals(c.toString())) {
                        members -= c.getNumberOfMembers();
                    }
                }
            }
            label_result.setText("The total number of members for the selected club(s): " + members);

     }
   } //end of SelectHandler class
} //end of SelectPane class
