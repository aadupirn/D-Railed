package TrackController;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import TrackController.Classes.*;

/**
 * Created by Jonathan on 2/3/17.
 */
public class TrackController {
    //Test

    //Class Objects
    private Stage mainStage, sideStage;
    private Scene mainScene, murphyScene, userInScene, engScene, toTMScene;
<<<<<<< HEAD:DRailed/src/TrackController/TrackController.java
<<<<<<< HEAD:DRailed/src/main/java/TrackController/UI/TrackControllerUI.java
    private Label controllerLabel,blockLabel, controlLabel, switchLabel, occupiedLabel, lightsLabel, crossLabel, stationLabel, switchAdjLabel, mainBlockLabel, subBlock1Label,subBlock2Label,connectedLabel;
    private TextField blockID, occupiedStatus, lightsStatus,crossStatus, switchAdj, switchIDText, mainBlockText, subBlock1Text, subBlock2Text, connectedText;
=======
    private Label controllerLabel,blockLabel, controlLabel, switchLabel, occupiedLabel, lightsLabel, crossLabel, switchAdjLabel, mainBlockLabel, subBlock1Label,subBlock2Label,connectedLabel, getTrainID, getSpeed,getAuth,getCarts;
    private TextField blockID, occupiedStatus, lightsStatus,crossStatus, switchAdj, switchIDText, mainBlockText, subBlock1Text, subBlock2Text, connectedText, getTrainIDText, getSpeedText, getAuthText, getCartsText;
>>>>>>> master:DRailed/src/main/java/TrackController/UI/TrackControllerUI.java
    private TextArea notifications;
    private Text controllerLine, controllerSection;
    private Button murphyButton, userInputsButton, engInputsButton, toTrackModelButton, murphyBreakTrackButton, murphyBreakCTCComms, murphyBreakTMComms, sendEngineer, loadPLC, blockIdButton, switchIdButton, dispatchButton;
    private RadioButton blockRB, switchRB;
    private TrackController tc;
=======
    private Label controllerLabel,blockLabel, controlLabel, switchLabel, openLabel, lightsLabel, crossLabel, stationLabel, switchAdjLabel, mainBlockLabel, subBlock1Label,subBlock2Label,connectedLabel;
    private TextField blockID, openStatus, lightsStatus,crossStatus,stationStatus, switchAdj1, switchAdj2, switchIDText, mainBlockText, subBlock1Text, subBlock2Text, connectedText;
    private TextArea notifications;
    private Text controllerLine, controllerSection;
    private Button murphyButton, userInputsButton, engInputsButton, toTrackModelButton, murphyBreakTrackButton, murphyBreakCTCComms, murphyBreakTMComms, sendEngineer, loadPLC, blockIdButton, switchIdButton;
    private RadioButton trainRB, blockRB, switchRB;
>>>>>>> a0100fbb415655463bc6e1dc8155e8fff7459500:DRailed/src/TrackController/TrackController.java
    private PLC myPLC;
    private Block[] Blocks;


    public TrackController() throws IOException {

       createUI();

        Block A,B,C,D;
        A = new Block("A.1");
        B = new Block("A.2");
        C = new Block("B.1");
        D = new Block("B.2");
        Blocks = new Block[]{A,B,C,D};
        Switch sw = new Switch("1",B,C,D);
        Blocks[0].setOccupied(true);
        Blocks[1].setSwitchID1(sw);
        Blocks[2].setSwitchID1(sw);
        Blocks[2].setStationName("Test Station");
        Blocks[3].setSwitchID1(sw);
        Blocks[3].setHasCrossings(true);

    }

    public void createUI()
    {
        //Set variables
        double windowWidth = 1250;
        double windowHeight = 500;
        int sideWidth = 400;
        int sideHeight = 200;
        int inset = 25;
        String applicationTitle = "Track Controller";

        //Set Stages
        mainStage = new Stage();
        sideStage = new Stage();
        mainStage.setTitle(applicationTitle);

        //Create Panes
        BorderPane mainPane = new BorderPane();
        GridPane main = new GridPane();
        GridPane murphy = new GridPane();
        GridPane userInputs = new GridPane();
        GridPane engInputs = new GridPane();
        GridPane toTrackModel = new GridPane();
        GridPane blockInfo = new GridPane();
        GridPane blockSwitchIDs = new GridPane();
        GridPane buttonSelect = new GridPane();
        GridPane switchInfo = new GridPane();
        GridPane trainInfo = new GridPane();
        GridPane trainOrBlock = new GridPane();
        ToggleGroup tOrB = new ToggleGroup();


        //Create Scenes
        mainScene = new Scene(mainPane, windowWidth, windowHeight);
        murphyScene =  new Scene(murphy, sideWidth,sideHeight);
        userInScene =  new Scene(userInputs, sideWidth,sideHeight);
        engScene =  new Scene(engInputs, sideWidth,sideHeight);
        toTMScene =  new Scene(toTrackModel, sideWidth,sideHeight);

        //Create Menu Items
        MenuBar menuBar = new MenuBar();
        Menu menuController = new Menu("Choose Controller");
        MenuItem[] trackControllers = new MenuItem[4];
<<<<<<< HEAD:DRailed/src/main/java/TrackController/UI/TrackControllerUI.java
        trackControllers[0] = new MenuItem("Red - 1 to 40");
        trackControllers[1] = new MenuItem("Red - 41 to 77");
        trackControllers[2] = new MenuItem("Green - 1 to 80");
        trackControllers[3] = new MenuItem("Green - 81 to 152");
        for (int i = 0; i < 4; i++)
=======
        trackControllers[0]= new MenuItem("Red - A to G");
        trackControllers[1]= new MenuItem("Red - H to U");
        trackControllers[2]= new MenuItem("Green - A to N");
        trackControllers[3]= new MenuItem("Green - O to YY");
        for(int i = 0; i < 4; i++)
>>>>>>> a0100fbb415655463bc6e1dc8155e8fff7459500:DRailed/src/TrackController/TrackController.java
            menuController.getItems().add(trackControllers[i]);
        menuBar.getMenus().addAll(menuController);
        mainPane.setTop(menuBar);



        //Initialize Main Pane
        mainPane.setCenter(main);
        main.setAlignment(Pos.TOP_CENTER);
        main.setPadding(new Insets(inset, inset, inset, inset));
        main.setGridLinesVisible(true);


        //Set up section titles on main screen
        controllerLabel = new Label("Track Controller Controls:");
        controllerLine = new Text("Line - Green");
        controllerSection = new Text("Blocks - 1 to 152");
        blockLabel = new Label("Block Info");
        controlLabel = new Label("Controls/Track Info");
        switchLabel = new Label("Switch Info");
        blockLabel.setMinWidth(windowWidth/3-20);
        controlLabel.setMinWidth(windowWidth/3-20);
        switchLabel.setMinWidth(windowWidth/3-20);
        blockLabel.setFont(Font.font("Garamond",FontWeight.BOLD,20));
        controlLabel.setFont(Font.font("Garamond",FontWeight.BOLD,20));
        switchLabel.setFont(Font.font("Garamond",FontWeight.BOLD,20));
        controllerLabel.setFont(Font.font("Garamond",FontWeight.BOLD,26));
        controllerLine.setFont(Font.font("Garamond",FontWeight.BOLD,26));
        controllerSection.setFont(Font.font("Garamond",FontWeight.BOLD,26));
        main.add(controllerLabel, 0, 0);
        main.add(controllerLine, 1, 0);
        main.add(controllerSection, 2, 0);
        main.add(blockLabel, 0, 1);
        main.add(controlLabel, 1, 1);
        main.add(switchLabel, 2, 1);

        //Block Info Section -------------------------------------------
        blockInfo.setVgap(40);
        //Block label
        blockIdButton = new Button("Update Block: ");
        blockIdButton.setOnAction(e->MainUpdateButtonClicked(e));
        blockInfo.add(blockIdButton,0,0);

        //Block ID text
<<<<<<< HEAD:DRailed/src/main/java/TrackController/UI/TrackControllerUI.java
        blockID = new TextField("1");
        blockInfo.add(blockID, 1, 0);


        //Open label
        occupiedLabel = new Label("Occupied Status: ");
        occupiedLabel.setFont(new Font("Garamond", 16));
        blockInfo.add(occupiedLabel, 0, 1);

        //Open status text
        occupiedStatus = new TextField("Occupied/Not Occupied");
        blockInfo.add(occupiedStatus, 1, 1);
=======
        blockID = new TextField("A.1");
        blockInfo.add(blockID, 1,0);


        //Open label
        openLabel = new Label("Open Status: ");
        openLabel.setFont(new Font("Garamond",16));
        blockInfo.add(openLabel,0,1);

        //Open status text
        openStatus = new TextField("Open/Closed/Occupied/Broken");
        blockInfo.add(openStatus, 1,1);
>>>>>>> a0100fbb415655463bc6e1dc8155e8fff7459500:DRailed/src/TrackController/TrackController.java

        //Lights label
        lightsLabel = new Label("Light Status: ");
        lightsLabel.setFont(new Font("Garamond",16));
        blockInfo.add(lightsLabel,0,2);

        //Lights status text
<<<<<<< HEAD:DRailed/src/main/java/TrackController/UI/TrackControllerUI.java
        lightsStatus = new TextField("Greed/Red/ (N/A)");
        blockInfo.add(lightsStatus, 1, 2);
=======
        lightsStatus = new TextField("Greed/Red");
        blockInfo.add(lightsStatus, 1,2);
>>>>>>> a0100fbb415655463bc6e1dc8155e8fff7459500:DRailed/src/TrackController/TrackController.java

        //Crossroads label
        crossLabel = new Label("Crossings Status: ");
        crossLabel.setFont(new Font("Garamond",16));
        blockInfo.add(crossLabel,0,3);

        //Crossroads status text
        crossStatus = new TextField("On/Off/(N/A)");
        blockInfo.add(crossStatus, 1,3);

<<<<<<< HEAD:DRailed/src/main/java/TrackController/UI/TrackControllerUI.java
        //Switch label
        switchAdjLabel = new Label("Adjacent Switches: ");
        switchAdjLabel.setFont(new Font("Garamond", 16));
        blockInfo.add(switchAdjLabel, 0, 4);

        //Switch GridPane
        switchAdj = new TextField("ID/(N/A)");
        blockInfo.add(switchAdj, 1, 4);
=======
        //Station label
        stationLabel = new Label("Station Name: ");
        stationLabel.setFont(new Font("Garamond",16));
        blockInfo.add(stationLabel,0,4);

        //Station status text
        stationStatus = new TextField("Name/(N/A)");
        blockInfo.add(stationStatus, 1,4);

        //Switch label
        switchAdjLabel = new Label("Adjacent Switches: ");
        switchAdjLabel.setFont(new Font("Garamond",16));
        blockInfo.add(switchAdjLabel,0,5);

        //Switch GridPane
        switchAdj1 = new TextField("ID/(N/A)");
        blockSwitchIDs.add(switchAdj1,0,0);
        switchAdj2 = new TextField("ID/(N/A)");
        blockSwitchIDs.add(switchAdj2,1,0);
        blockInfo.add(blockSwitchIDs, 1,5);
>>>>>>> a0100fbb415655463bc6e1dc8155e8fff7459500:DRailed/src/TrackController/TrackController.java

        blockInfo.setMinHeight(windowHeight*2/3);

        main.add(blockInfo,0,2,1,2);

        //Set up ButtonPane -----------------------------------------------------------------------
        murphyButton = new Button("Murphy Controls");
        userInputsButton = new Button("CTC Inputs");
        engInputsButton = new Button("Engineer Inputs");
        toTrackModelButton = new Button("Inputs To Track Model");
        murphyButton.setOnAction(e -> MainButtonClicked(e));
        userInputsButton.setOnAction(e -> MainButtonClicked(e));
        engInputsButton.setOnAction(e -> MainButtonClicked(e));
        toTrackModelButton.setOnAction(e -> MainButtonClicked(e));
        murphyButton.setAlignment(Pos.CENTER_LEFT);
        userInputsButton.setAlignment(Pos.CENTER_RIGHT);
        engInputsButton.setAlignment(Pos.CENTER_LEFT);
        toTrackModelButton.setAlignment(Pos.CENTER_RIGHT);
        buttonSelect.add(murphyButton, 0, 0);
        buttonSelect.add(userInputsButton, 1, 0);
        buttonSelect.add(engInputsButton, 0, 1);
        buttonSelect.add(toTrackModelButton, 1, 1);
        buttonSelect.setVgap(10);
        buttonSelect.setHgap(10);
        main.add(buttonSelect,1,2);

        //Put track pane in
        notifications = new TextArea("");
        notifications.appendText("17");
        notifications.setFont(Font.font("Garamond", 12));
        notifications.setMinHeight(windowHeight/3);
        trainInfo.setHgap(20);
<<<<<<< HEAD:DRailed/src/main/java/TrackController/UI/TrackControllerUI.java
        trainInfo.add(notifications, 0, 0);
=======
        trainInfo.add(notifications,0,0);
        trainRB = new RadioButton("Trains");
        trainRB.setToggleGroup(tOrB);
>>>>>>> a0100fbb415655463bc6e1dc8155e8fff7459500:DRailed/src/TrackController/TrackController.java
        blockRB = new RadioButton("Blocks");
        blockRB.setToggleGroup(tOrB);
        switchRB = new RadioButton("Switches");
        switchRB.setToggleGroup(tOrB);
<<<<<<< HEAD:DRailed/src/main/java/TrackController/UI/TrackControllerUI.java
        trainOrBlock.add(blockRB, 0, 0);
        trainOrBlock.add(switchRB, 1, 0);
        trainInfo.add(trainOrBlock, 0, 1);
        main.add(trainInfo, 1, 3);
=======
        trainOrBlock.add(trainRB, 0, 0);
        trainOrBlock.add(blockRB, 1, 0);
        trainOrBlock.add(switchRB, 2, 0);
        trainInfo.add(trainOrBlock,0,1);
        main.add(trainInfo,1,3);
>>>>>>> a0100fbb415655463bc6e1dc8155e8fff7459500:DRailed/src/TrackController/TrackController.java

        //Set up switch info ------------------------------------------------------------------
        switchInfo.setVgap(45);

        //Switch ID label
        switchIdButton = new Button("Update Switch: ");
        switchIdButton.setOnAction(e->MainUpdateButtonClicked(e));
        switchInfo.add(switchIdButton,0,0);

        //Switch ID text
<<<<<<< HEAD:DRailed/src/main/java/TrackController/UI/TrackControllerUI.java
        switchIDText = new TextField("0");
        switchInfo.add(switchIDText, 1, 0);
=======
        switchIDText = new TextField("ID/(N/A)");
        switchInfo.add(switchIDText,1,0);
>>>>>>> a0100fbb415655463bc6e1dc8155e8fff7459500:DRailed/src/TrackController/TrackController.java

        //Main block label
        mainBlockLabel = new Label("Main Block: ");
        mainBlockLabel.setFont(new Font("Garamond",16));
        switchInfo.add(mainBlockLabel, 0,1);

        //Main block text
        mainBlockText = new TextField("Main Block ID");
        switchInfo.add(mainBlockText, 1,1);

        //Sub block 1 label
        subBlock1Label = new Label("Sub Block 1: ");
        subBlock1Label.setFont(new Font("Garamond",16));
        switchInfo.add(subBlock1Label, 0,2);

        //Sub block 1 text
        subBlock1Text = new TextField("Sub Block 1 ID");
        switchInfo.add(subBlock1Text, 1,2);

        //Sub block 2 label
        subBlock2Label = new Label("Sub Block 2: ");
        subBlock2Label.setFont(new Font("Garamond",16));
        switchInfo.add(subBlock2Label, 0,3);

        //Sub block 2 text
        subBlock2Text = new TextField("Sub Block 2 ID");
        switchInfo.add(subBlock2Text, 1,3);

        //Connected label
        connectedLabel = new Label("Sub Block Connected: ");
        connectedLabel.setFont(new Font("Garamond",16));
        switchInfo.add(connectedLabel, 0,4);

        //Connected text
        connectedText = new TextField("Sub Block (1 or 2)");
        switchInfo.add(connectedText, 1,4);

        main.add(switchInfo,2,2,1,2);

        //End main pane -----------------------------------------------------------------------


        //Initialize Murphy stuff
        murphyBreakTrackButton = new Button("Break Track");
        murphyBreakCTCComms = new Button("CTC");
        murphyBreakTMComms = new Button("Track Model");
        TextField breakBlockID = new TextField("Put Block ID to break");
        Label breakCommsLabel = new Label("Break Comms with: ");
        GridPane breakComms = new GridPane();
        murphy.setVgap(20);
        breakComms.setHgap(10);

        //break track text field
        murphy.add(breakBlockID,0,0);

        //break track button
        murphyBreakTrackButton.setOnAction(e -> MurphyButtonClicked(e));
        murphy.add(murphyBreakTrackButton, 1, 0);

        //Break comms label
        murphy.add(breakCommsLabel,0,1);

        //Break comms girdpane
        breakComms.add(murphyBreakCTCComms,0,0);
        breakComms.add(murphyBreakTMComms,0,1);

        murphy.add(breakComms,1,1);

        //End murphy --------------------------------------------------------------------

        //Engineer Inputs
        Label setBlocklabel = new Label("Select Block ID: ");
        Label setLightsLabel = new Label("Set Lights: ");
        Label setCrossroadsLabel = new Label("Set Crossing Signal: ");
        Label setSwitchLabel = new Label("Select Switch: ");
        Label setOpenLabel = new Label("Set Open Status: ");
        Label setSwitchToggleLabel = new Label("Toggle Switch: ");
        TextField setBlockID = new TextField("");
        TextField setLightsText = new TextField("");
        TextField setCrossroadsText = new TextField("");
        TextField selectSwitchText = new TextField("");
        TextField setOpenText = new TextField("");
        TextField setSwitchText = new TextField("");
        sendEngineer = new Button("Send Changes");
        loadPLC = new Button("Upload PLC File");
        sendEngineer.setOnAction(e->EngineerButtonClicked(e));
        loadPLC.setOnAction(e->EngineerButtonClicked(e));


        //setBlocklabel
        setBlocklabel.setFont(new Font("Garamond",16));
        engInputs.add(setBlocklabel, 0,0);

        //setBlockID
        engInputs.add(setBlockID, 1,0);

        //setLightsLabel
        setLightsLabel.setFont(new Font("Garamond",16));
        engInputs.add(setLightsLabel, 0,1);

        //setLightsText
        engInputs.add(setLightsText, 1,1);

        //setCrossroadsLabel
        setCrossroadsLabel.setFont(new Font("Garamond",16));
        engInputs.add(setCrossroadsLabel, 0,2);

        //setCrossroadsText
        engInputs.add(setCrossroadsText, 1,2);

        //setOpenLabel
        setOpenLabel.setFont(new Font("Garamond",16));
        engInputs.add(setOpenLabel, 0,3);

        //setOpenText
        engInputs.add(setOpenText, 1,3);

        //setSwitchLabel
        setSwitchLabel.setFont(new Font("Garamond",16));
        engInputs.add(setSwitchLabel, 0,4);

        //selectSwitchText
        engInputs.add(selectSwitchText, 1,4);

        //setSwitchToggleLabel
        setSwitchToggleLabel.setFont(new Font("Garamond",16));
        engInputs.add(setSwitchToggleLabel, 0,5);

        //setSwitchText
        engInputs.add(setSwitchText, 1,5);

        //Send Button
        engInputs.add(sendEngineer,0,6,2,1);

        //PLC button
        engInputs.add(loadPLC,0,7,2,1);

        //End Eng Inputs---------------------------------------------------------------------

        //To Track Model
        Label sendTrainID = new Label("Set Train ID: ");
        Label sendSpeed = new Label("Set Speed: ");
        Label sendAuth = new Label("Set Authority: ");
        TextField sendTrainIDText = new TextField("");
        TextField sendSpeedText = new TextField("");
        TextField sendAuthText = new TextField("");
        Button sendData = new Button("Send to Track Model");
        sendData.setOnAction(e->DispatchButtonClicked(e));


        //Assemble
        sendTrainID.setFont(new Font("Garamond",16));
        toTrackModel.add(sendTrainID,0,0);

        sendSpeed.setFont(new Font("Garamond",16));
        toTrackModel.add(sendSpeed,0,1);

        sendAuth.setFont(new Font("Garamond",16));
        toTrackModel.add(sendAuth,0,2);

        toTrackModel.add(sendTrainIDText,1,0);

        toTrackModel.add(sendSpeedText,1,1);

        toTrackModel.add(sendAuthText,1,2);

        toTrackModel.add(sendData,0,3,2,1);

        //End To Track Model ----------------------------------------------------------------------

        //From CTC
        getTrainID = new Label("Set Train ID: ");
        getSpeed = new Label("Set Speed: ");
        getAuth = new Label("Set Authority: ");
        getCarts = new Label("Number of Carts: ");
        getTrainIDText = new TextField("");
        getSpeedText = new TextField("");
        getAuthText = new TextField("");
        getCartsText = new TextField("");
        dispatchButton = new Button("Dispatch Train");
        dispatchButton.setOnAction(e->DispatchButtonClicked(e));
        userInputs.setVgap(10);


        //Assemble
        getTrainID.setFont(new Font("Garamond",16));
        userInputs.add(getTrainID,0,0);

        getSpeed.setFont(new Font("Garamond",16));
        userInputs.add(getSpeed,0,1);

        getAuth.setFont(new Font("Garamond",16));
        userInputs.add(getAuth,0,2);

<<<<<<< HEAD:DRailed/src/TrackController/TrackController.java
        userInputs.add(getTrainIDText,1,0);
=======
        getCarts.setFont(new Font("Garamond", 16));
        userInputs.add(getCarts,0,3);

        userInputs.add(getTrainIDText, 1, 0);
>>>>>>> master:DRailed/src/main/java/TrackController/UI/TrackControllerUI.java

        userInputs.add(getSpeedText,1,1);

        userInputs.add(getAuthText,1,2);

<<<<<<< HEAD:DRailed/src/TrackController/TrackController.java
        userInputs.add(getData,0,3,2,1);
=======
        userInputs.add(getCartsText,1,3);

        userInputs.add(dispatchButton, 0, 4, 2, 1);
>>>>>>> master:DRailed/src/main/java/TrackController/UI/TrackControllerUI.java
        //End From CTC---------------------------------------------------------------------

        mainStage.setScene(mainScene);
    }

    public void showUI()
    {
        mainStage.show();
    }

    public void hideUI()
    {
        mainStage.hide();
    }

    public void EngineerButtonClicked(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == loadPLC)
        {
            FileChooser fileChooser = new FileChooser();
            Stage fileSelect = new Stage();
            fileSelect.setTitle("Choose a PLC file to import:");
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("TrackController/PLC").getFile());
            fileChooser.setInitialDirectory(file);
            File file2 = fileChooser.showOpenDialog(fileSelect);
            if(file2 != null)
            {
<<<<<<< HEAD:DRailed/src/main/java/TrackController/UI/TrackControllerUI.java
               tc.setPLC(file2);
=======
                myPLC = new PLC(file, Blocks);
>>>>>>> a0100fbb415655463bc6e1dc8155e8fff7459500:DRailed/src/TrackController/TrackController.java
            }
        }
    }

    public void MurphyButtonClicked(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == murphyBreakTrackButton)
        {
            for(int i = 0; i < 10; i++)
            {
                int caretPosition = notifications.caretPositionProperty().get();
                notifications.appendText("Here i am appending text to text area"+"\n");
                notifications.positionCaret(caretPosition);
            }
        }
    }

    public void MainUpdateButtonClicked(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == blockIdButton)
        {
<<<<<<< HEAD:DRailed/src/main/java/TrackController/UI/TrackControllerUI.java
            String[] s;
            s = tc.getBlockInfo(Integer.parseInt(blockID.getText()));
            occupiedStatus.setText(s[0]);
            lightsStatus.setText(s[2]);
            crossStatus.setText(s[1]);
            switchAdj.setText(s[3]);
        }
        else if(source == switchIdButton)
        {
            String[] vals;
            vals = tc.getSwitchInfo(Integer.parseInt(switchIDText.getText()));
            mainBlockText.setText(vals[0]);
            subBlock1Text.setText(vals[1]);
            subBlock2Text.setText(vals[2]);
            connectedText.setText(vals[3]);
=======
            for(Block b : Blocks)
            {
                if(b.getID().equals(blockID.getText()))
                {
                    openStatus.setText(b.getOpenStatus());
                    lightsStatus.setText(b.getLights());
                    crossStatus.setText(b.getCrossings());
                    stationStatus.setText(b.getStationName());
                    switchAdj1.setText(b.getSwitchID1Name());
                    switchAdj2.setText(b.getSwitchID2Name());
                }
            }
        }
        else if(source == switchIdButton)
        {
            Switch sw = null;
            for(Block b : Blocks)
            {
                if(b.getSwitchID1() != null)
                {
                    sw = b.getSwitchID1();

                }
                if(b.getSwitchID2() != null)
                {
                    sw = b.getSwitchID2();
                }
                if(sw != null)
                {
                    mainBlockText.setText(sw.getMainBlock().getID());
                    subBlock1Text.setText(sw.getSubBlock1().getID());
                    subBlock2Text.setText(sw.getSubBlock2().getID());
                    connectedText.setText(sw.getState());
                }
            }
>>>>>>> a0100fbb415655463bc6e1dc8155e8fff7459500:DRailed/src/TrackController/TrackController.java
        }
    }

    public void Update()
    {
        return;
    }

    public void DispatchButtonClicked(ActionEvent e)
    {
        Object source = e.getSource();
        System.out.println("Here we go!");
        System.out.println("Source = " + source.toString());
        if (source == dispatchButton)
        {
            try {
                tc.dispatchTrain(152, Integer.parseInt(getCartsText.getText()), Integer.parseInt(getAuthText.getText()), Double.parseDouble(getSpeedText.getText()), Integer.parseInt(getTrainIDText.getText()));
                System.out.println("Train created!");
            } catch (Exception e1) {
                System.out.println("We got an exception!!!");
            }
        }
    }

    public void MainButtonClicked(ActionEvent e)
    {
        String newTitle = "Unknown Event";
        Object source = e.getSource();
        if(source == murphyButton)
        {
            sideStage.setScene(murphyScene);
            newTitle = "Murphy Controls";
        }
        else if(source == userInputsButton)
        {
            sideStage.setScene(userInScene);
            newTitle = "CTC Inputs";
        }
        else if(source == engInputsButton)
        {
            sideStage.setScene(engScene);
            newTitle = "Engineer Inputs";
        }
        else if(source == toTrackModelButton)
        {
            sideStage.setScene(toTMScene);
            newTitle = "Output To Track Model";
        }
        sideStage.setTitle(newTitle);
        sideStage.show();
    }

}