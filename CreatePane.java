
//  Description: The Assignment6 class creates a Tabbed Pane with
//               two tabs, one for Club Creation and one for
//               Club Selection.

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.event.ActionEvent;	//**Need to import
import javafx.event.EventHandler;	//**Need to import
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;

//import all other necessary javafx classes here
//----

public class CreatePane extends HBox
{
	ArrayList<Club> clubList;

    //The relationship between CreatePane and SelectPane is Aggregation
    private SelectPane selectPane;

	private Label label_display, label_title, label_members, label_university;
	private TextField tf_title, tf_members, tf_university;
	private Button button_createClub;
	private TextArea textArea;

	//constructor
	public CreatePane(ArrayList<Club> list, SelectPane sePane)
	{
		this.clubList = list;
		this.selectPane = sePane;
        
        
        //initialize each instance variable (textfields, labels, textarea, button, etc.)
        //and set up the layout
        //----

		label_display = new Label("");
		label_display.setTextFill(Color.RED);
		label_title = new Label("Title");
		label_members = new Label("Number of Members");
		label_university = new Label("University");

		tf_title = new TextField();
		tf_members = new TextField();
		tf_university = new TextField();

		button_createClub = new Button("Create a Club");

		textArea = new TextArea();
		textArea.setEditable(false);

		//create a GridPane hold those labels & text fields.
		//you can choose to use .setPadding() or setHgap(), setVgap()
		//to control the spacing and gap, etc.
		//----

		GridPane main_pane = new GridPane();
		main_pane.setVgap(10);
        main_pane.setHgap(10);
        main_pane.setPadding(new Insets(10));
        main_pane.setPrefHeight(Double.MAX_VALUE);

		//You might need to create a sub pane to hold the button
		//----
		HBox hbox2 = new HBox(30,button_createClub);
		hbox2.setAlignment(Pos.CENTER);

		HBox hbox1 = new HBox(10,label_display);
		hbox1.setAlignment(Pos.TOP_LEFT);

		//Set up the layout for the left half of the CreatePane.
		//----

        GridPane sub_pane1 = new GridPane();
        sub_pane1.add(label_title,0,0);
        sub_pane1.add(tf_title,1,0);
        sub_pane1.getColumnConstraints().addAll(labelConstraint(),textfieldConstraint());

        GridPane sub_pane2 = new GridPane();
        sub_pane2.add(label_members,0,0);
        sub_pane2.add(tf_members,1,0);
        sub_pane2.getColumnConstraints().addAll(labelConstraint(),textfieldConstraint());

        GridPane sub_pane3 = new GridPane();
        sub_pane3.add(label_university,0,0);
        sub_pane3.add(tf_university,1,0);
        sub_pane3.getColumnConstraints().addAll(labelConstraint(),textfieldConstraint());

		VBox vbox = new VBox(10,hbox1,sub_pane1,sub_pane2,sub_pane3,hbox2);
		vbox.setPadding(new Insets(20));

		//the right half of the CreatePane is simply a TextArea object
		//Note: a ScrollPane will be added to it automatically when there are no
		//enough space

		main_pane.add(vbox,0,0);
		main_pane.add(textArea,1,0,1,17);
		ColumnConstraints c1 = new ColumnConstraints();
		c1.setPercentWidth(50);
		ColumnConstraints c2 = new ColumnConstraints();
		c2.setPercentWidth(50);
		main_pane.getColumnConstraints().addAll(c1,c2);

		//Add the left half and right half to the CreatePane
		//Note: CreatePane extends from HBox
		//----

		this.getChildren().add(main_pane);

		//register/link source object with event handler
		button_createClub.setOnAction(new ButtonHandler());

	} //end of constructor

	// Method to set up the constraints of the sub pane
	private ColumnConstraints labelConstraint(){
		ColumnConstraints constraints = new ColumnConstraints();
		constraints.setPercentWidth(40);
		return constraints;
	}

	private ColumnConstraints textfieldConstraint(){
		ColumnConstraints constraints = new ColumnConstraints();
		constraints.setPercentWidth(60);
		return constraints;
	}

    //Create a ButtonHandler class
    //ButtonHandler listens to see if the button "Create" is pushed or not,
    //When the event occurs, it get a club's Title, its number of members, and its university
    //information from the relevant text fields, then create a new club and add it inside
    //the clubList. Meanwhile it will display the club's information inside the text area.
    //using the toString method of the Club class.
    //It also does error checking in case any of the textfields are empty,
    //or a non-numeric value was entered for its number of members
    private class ButtonHandler implements EventHandler<ActionEvent>
   	 {
   	    //Override the abstact method handle()
   	    public void handle(ActionEvent event)
        {
			//declare any necessary local variables here
        	String title, university;
        	int members;
        	boolean flag = false;

        	title = tf_title.getText();
        	university = tf_university.getText();

			for(Club c: clubList){
				if(c.getClubName().equalsIgnoreCase(title)){
					flag = true;
				}
			}

			//when a text field is empty and the button is pushed
        	if(title.isEmpty() || university.isEmpty() || tf_members.getText().isEmpty()){
        		label_display.setText("Please fill all fields");
			}
        	else if(flag){
				//When a club of an existing club name in the list
				//was attempted to be added, do not add it to the list
				//and display a message "Club not added - duplicate"
				label_display.setText("Club not added - duplicate");
			}
        	else{
				//when a non-numeric value was entered for its number of members
				//and the button is pushed
				//you will need to use try & catch block to catch
				//the NumberFormatException

        		try{
        			members = Integer.parseInt(tf_members.getText());

					Club club = new Club();
        			club.setClubName(title);
        			club.setNumberOfMembers(members);
        			club.setUniversity(university);
        			clubList.add(club);

					//at the end, don't forget to update the new arrayList
					//information on the SelectPanel

					selectPane.updateClubList(club);

        			textArea.appendText(club.toString());
        			label_display.setText("Club added");

				}catch (NumberFormatException nfe){
        			label_display.setText("Please enter an integer for a number of members");
				}
			}

      } //end of handle() method
   } //end of ButtonHandler class

}
