package TrackController.UI;

import TrackController.TrackController;
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
import java.io.File;
import TrackController.Classes.*;
import TrackModel.Model.*;

public class TrackControllerUI {

    //Class Objects
    private Stage mainStage, sideStage;
    private Scene mainScene, murphyScene, userInScene, engScene, toTMScene;
    private Label controllerLabel,blockLabel, controlLabel, switchLabel, occupiedLabel, lightsLabel, crossLabel, switchAdjLabel, mainBlockLabel, subBlock1Label,subBlock2Label,connectedLabel, getTrainID, getSpeed,getAuth,getCarts;
    private TextField blockID, occupiedStatus, lightsStatus,crossStatus, switchAdj, switchIDText, mainBlockText, subBlock1Text, subBlock2Text, connectedText, getTrainIDText, getSpeedText, getAuthText, getCartsText, sendTrainIDText, sendSpeedText, sendAuthText, selectSwitchText, setSwitchText,breakBlockID;
    private TextArea notifications;
    private Text controllerLine, controllerSection;
    private Button murphyButton, userInputsButton, engInputsButton, toTrackModelButton, murphyBreakTrackButton, murphyBreakCTCComms, murphyBreakTMComms, sendEngineer,unsetSwitch, loadPLC, blockIdButton, switchIdButton, dispatchButton, sendData;
    private RadioButton blockRB, switchRB;
    private TrackController tc;
    private PLC myPLC;
    private Block[] Blocks;

    public TrackControllerUI(TrackController trackController) {
        tc = trackController;

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
        GridPane buttonSelect = new GridPane();
        GridPane switchInfo = new GridPane();
        GridPane trainInfo = new GridPane();
        GridPane trainOrBlock = new GridPane();
        ToggleGroup tOrB = new ToggleGroup();


        //Create Scenes
        mainScene = new Scene(mainPane, windowWidth, windowHeight);
        murphyScene = new Scene(murphy, sideWidth, sideHeight);
        userInScene = new Scene(userInputs, sideWidth, sideHeight);
        engScene = new Scene(engInputs, sideWidth, sideHeight);
        toTMScene = new Scene(toTrackModel, sideWidth, sideHeight);

        //Create Menu Items
        MenuBar menuBar = new MenuBar();
        Menu menuController = new Menu("Choose Controller");
        MenuItem[] trackControllers = new MenuItem[4];
        trackControllers[0] = new MenuItem("Red - 1");
        trackControllers[1] = new MenuItem("Red - 2");
        trackControllers[2] = new MenuItem("Green - 1");
        trackControllers[3] = new MenuItem("Green - 2");
        for (int i = 0; i < 4; i++)
            menuController.getItems().add(trackControllers[i]);
        menuBar.getMenus().addAll(menuController);
        mainPane.setTop(menuBar);
        trackControllers[0].setOnAction((ActionEvent a) -> {
            try {
                tc.switchUI("RED",3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        trackControllers[1].setOnAction((ActionEvent a) -> {
            try {
                tc.switchUI("RED",3); //TODO make a second one!
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        trackControllers[2].setOnAction((ActionEvent a) -> {
            try {
                tc.switchUI("GREEN",1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        trackControllers[3].setOnAction((ActionEvent a) -> {
            try {
                tc.switchUI("GREEN",2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        //Initialize Main Pane
        mainPane.setCenter(main);
        main.setAlignment(Pos.TOP_CENTER);
        main.setPadding(new Insets(inset, inset, inset, inset));
        main.setGridLinesVisible(true);


        //Set up section titles on main screen
        controllerLabel = new Label("Track Controller Controls:");
        controllerLine = new Text("Line - " + tc.getLine());
        controllerSection = new Text("Controller Number - " + tc.getID());
        blockLabel = new Label("Block Info");
        controlLabel = new Label("PLC Loaded: " + tc.plcLoaded());
        switchLabel = new Label("Switch Info");
        blockLabel.setMinWidth(windowWidth / 3 - 20);
        controlLabel.setMinWidth(windowWidth / 3 - 20);
        switchLabel.setMinWidth(windowWidth / 3 - 20);
        blockLabel.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        controlLabel.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        switchLabel.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        controllerLabel.setFont(Font.font("Garamond", FontWeight.BOLD, 26));
        controllerLine.setFont(Font.font("Garamond", FontWeight.BOLD, 26));
        controllerSection.setFont(Font.font("Garamond", FontWeight.BOLD, 26));
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
        blockIdButton.setOnAction(e -> MainUpdateButtonClicked(e));
        blockInfo.add(blockIdButton, 0, 0);

        //Block ID text
        blockID = new TextField("1");
        blockInfo.add(blockID, 1, 0);


        //Open label
        occupiedLabel = new Label("Occupied Status: ");
        occupiedLabel.setFont(new Font("Garamond", 16));
        blockInfo.add(occupiedLabel, 0, 1);

        //Open status text
        occupiedStatus = new TextField("Occupied/Not Occupied");
        blockInfo.add(occupiedStatus, 1, 1);

        //Lights label
        lightsLabel = new Label("Light Status: ");
        lightsLabel.setFont(new Font("Garamond", 16));
        blockInfo.add(lightsLabel, 0, 2);

        //Lights status text
        lightsStatus = new TextField("Greed/Red/ (N/A)");
        blockInfo.add(lightsStatus, 1, 2);

        //Crossroads label
        crossLabel = new Label("Crossings Status: ");
        crossLabel.setFont(new Font("Garamond", 16));
        blockInfo.add(crossLabel, 0, 3);

        //Crossroads status text
        crossStatus = new TextField("On/Off/(N/A)");
        blockInfo.add(crossStatus, 1, 3);

        //Switch label
        switchAdjLabel = new Label("Adjacent Switches: ");
        switchAdjLabel.setFont(new Font("Garamond", 16));
        blockInfo.add(switchAdjLabel, 0, 4);

        //Switch GridPane
        switchAdj = new TextField("ID/(N/A)");
        blockInfo.add(switchAdj, 1, 4);

        blockInfo.setMinHeight(windowHeight * 2 / 3);

        main.add(blockInfo, 0, 2, 1, 2);

        //Set up ButtonPane -----------------------------------------------------------------------
        murphyButton = new Button("Murphy Controls");
        userInputsButton = new Button("Dispatch Train");
        engInputsButton = new Button("Engineer Inputs");
        toTrackModelButton = new Button("Speed and Authority");
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
        main.add(buttonSelect, 1, 2);

        //Put track pane in
        notifications = new TextArea("");
        notifications.setText("Display Block Occupancy");
        notifications.setFont(Font.font("Garamond", 12));
        notifications.setMinHeight(windowHeight / 3);
        trainInfo.setHgap(20);
        trainInfo.add(notifications, 0, 0);
        blockRB = new RadioButton("Blocks");
        blockRB.setSelected(true);
        blockRB.setToggleGroup(tOrB);
        switchRB = new RadioButton("Switches");
        switchRB.setToggleGroup(tOrB);
        trainOrBlock.add(blockRB, 0, 0);
        trainOrBlock.add(switchRB, 1, 0);
        trainInfo.add(trainOrBlock, 0, 1);
        main.add(trainInfo, 1, 3);

        //Set up switch info ------------------------------------------------------------------
        switchInfo.setVgap(45);

        //Switch ID label
        switchIdButton = new Button("Update Switch: ");
        switchIdButton.setOnAction(e -> MainUpdateButtonClicked(e));
        switchInfo.add(switchIdButton, 0, 0);

        //Switch ID text
        switchIDText = new TextField("0");
        switchInfo.add(switchIDText, 1, 0);

        //Main block label
        mainBlockLabel = new Label("Main Block: ");
        mainBlockLabel.setFont(new Font("Garamond", 16));
        switchInfo.add(mainBlockLabel, 0, 1);

        //Main block text
        mainBlockText = new TextField("Main Block ID");
        switchInfo.add(mainBlockText, 1, 1);

        //Sub block 1 label
        subBlock1Label = new Label("Sub Block 1: ");
        subBlock1Label.setFont(new Font("Garamond", 16));
        switchInfo.add(subBlock1Label, 0, 2);

        //Sub block 1 text
        subBlock1Text = new TextField("Sub Block 1 ID");
        switchInfo.add(subBlock1Text, 1, 2);

        //Sub block 2 label
        subBlock2Label = new Label("Sub Block 2: ");
        subBlock2Label.setFont(new Font("Garamond", 16));
        switchInfo.add(subBlock2Label, 0, 3);

        //Sub block 2 text
        subBlock2Text = new TextField("Sub Block 2 ID");
        switchInfo.add(subBlock2Text, 1, 3);

        //Connected label
        connectedLabel = new Label("Sub Block Connected: ");
        connectedLabel.setFont(new Font("Garamond", 16));
        switchInfo.add(connectedLabel, 0, 4);

        //Connected text
        connectedText = new TextField("Sub Block (1 or 2)");
        switchInfo.add(connectedText, 1, 4);

        main.add(switchInfo, 2, 2, 1, 2);

        //End main pane -----------------------------------------------------------------------


        //Initialize Murphy stuff
        murphyBreakTrackButton = new Button("Toggle Track");
        murphyBreakCTCComms = new Button("CTC");
        murphyBreakTMComms = new Button("Track Model");
        murphyBreakTrackButton.setOnAction(e -> MurphyButtonClicked(e));
        murphyBreakCTCComms.setOnAction(e -> MurphyButtonClicked(e));
        murphyBreakTMComms.setOnAction(e -> MurphyButtonClicked(e));
        breakBlockID = new TextField("");
        Label breakCommsLabel = new Label("Toggle Comms with: ");
        GridPane breakComms = new GridPane();
        murphy.setVgap(20);
        breakComms.setHgap(10);

        //break track text field
        murphy.add(breakBlockID, 0, 0);

        //break track button
        murphy.add(murphyBreakTrackButton, 1, 0);

        //Break comms label
        murphy.add(breakCommsLabel, 0, 1);

        //Break comms girdpane
        breakComms.add(murphyBreakCTCComms, 0, 0);
        breakComms.add(murphyBreakTMComms, 0, 1);

        murphy.add(breakComms, 1, 1);

        //End murphy --------------------------------------------------------------------

        //Engineer Inputs
        Label setSwitchLabel = new Label("Select Switch: ");
        Label setSwitchToggleLabel = new Label("Set Connecting Block: ");
        selectSwitchText = new TextField("");
        setSwitchText = new TextField("");
        sendEngineer = new Button("Set Switch");
        unsetSwitch = new Button("Unset Switch");
        loadPLC = new Button("Upload PLC File");
        sendEngineer.setOnAction(e -> EngineerButtonClicked(e));
        loadPLC.setOnAction(e -> EngineerButtonClicked(e));
        unsetSwitch.setOnAction(e -> EngineerButtonClicked(e));

        //setSwitchLabel
        setSwitchLabel.setFont(new Font("Garamond", 16));
        engInputs.add(setSwitchLabel, 0, 0);

        //selectSwitchText
        engInputs.add(selectSwitchText, 1, 0);

        //setSwitchToggleLabel
        setSwitchToggleLabel.setFont(new Font("Garamond", 16));
        engInputs.add(setSwitchToggleLabel, 0, 2);

        //setSwitchText
        engInputs.add(setSwitchText, 1, 2);

        //Send Button
        engInputs.add(sendEngineer, 0, 3);

        //Unset Button
        engInputs.add(unsetSwitch,1,3);

        //PLC button
        engInputs.add(loadPLC, 0, 4, 2, 1);

        //End Eng Inputs---------------------------------------------------------------------

        //To Track Model
        Label sendTrainID = new Label("Set Train ID: ");
        Label sendSpeed = new Label("Set Speed: ");
        Label sendAuth = new Label("Set Authority: ");
        sendTrainIDText = new TextField("");
        sendSpeedText = new TextField("");
        sendAuthText = new TextField("");
        sendData = new Button("Send Message");
        sendData.setOnAction(e->setSpeedAndAuthorityClicked(e));


        //Assemble
        sendTrainID.setFont(new Font("Garamond", 16));
        toTrackModel.add(sendTrainID, 0, 0);

        sendSpeed.setFont(new Font("Garamond", 16));
        toTrackModel.add(sendSpeed, 0, 1);

        sendAuth.setFont(new Font("Garamond", 16));
        toTrackModel.add(sendAuth, 0, 2);

        toTrackModel.add(sendTrainIDText, 1, 0);

        toTrackModel.add(sendSpeedText, 1, 1);

        toTrackModel.add(sendAuthText, 1, 2);

        toTrackModel.add(sendData, 0, 3, 2, 1);

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
        getTrainID.setFont(new Font("Garamond", 16));
        userInputs.add(getTrainID, 0, 0);

        getSpeed.setFont(new Font("Garamond", 16));
        userInputs.add(getSpeed, 0, 1);

        getAuth.setFont(new Font("Garamond", 16));
        userInputs.add(getAuth, 0, 2);

        getCarts.setFont(new Font("Garamond", 16));
        userInputs.add(getCarts,0,3);

        userInputs.add(getTrainIDText, 1, 0);

        userInputs.add(getSpeedText, 1, 1);

        userInputs.add(getAuthText, 1, 2);

        userInputs.add(getCartsText,1,3);

        userInputs.add(dispatchButton, 0, 4, 2, 1);
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
            //ClassLoader classLoader = getClass().getClassLoader();
            //File file = new File(classLoader.getResource("TrackController/PLC").getFile());
            File file2 = fileChooser.showOpenDialog(fileSelect);
            if(file2 != null)
            {
               tc.setPLC(file2);
            }
            controlLabel.setText(("PLC Loaded: " + tc.plcLoaded()));
        }
        else if (source == sendEngineer)
        {
            tc.setSwitch(tc.getLine(),Integer.parseInt(selectSwitchText.getText()),Integer.parseInt(setSwitchText.getText()));
        }
        else if (source == unsetSwitch)
        {
            tc.unsetManualSwitch(tc.getLine(),Integer.parseInt(selectSwitchText.getText()));
        }
    }

    public void MurphyButtonClicked(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == murphyBreakTMComms)
        {
            tc.toggleTrackComms();
        }
        else if (source == murphyBreakCTCComms)
        {
            tc.toggleCTCComms();
        }
        else if (source == murphyBreakTrackButton)
        {
            tc.toggleBlock(Integer.parseInt(breakBlockID.getText()));
        }
    }

    public void MainUpdateButtonClicked(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == blockIdButton)
        {
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
        }
    }

    public void Update()
    {
        // update the notifications field
        double point;
        if (blockRB.isSelected())
        {
            point = notifications.getScrollTop();
            notifications.setText(tc.getAllBlockOccupancies());
            notifications.setScrollTop(point);
        }
        else
        {
            point = notifications.getScrollTop();
            notifications.setText(tc.getAllSwitchStates());
            notifications.setScrollTop(point);
        }
    }

    public void DispatchButtonClicked(ActionEvent e)
    {
        try {
            tc.dispatchTrain(152, Integer.parseInt(getCartsText.getText()), Integer.parseInt(getAuthText.getText()), Double.parseDouble(getSpeedText.getText()), Integer.parseInt(getTrainIDText.getText()));
        } catch (Exception e1) {
            System.out.println("We got an exception: " + e1.toString() + "\n");
            e1.printStackTrace();
        }
    }

    public void setSpeedAndAuthorityClicked(ActionEvent e)
    {
        int id, auth;
        double speed;

        id = Integer.parseInt(sendTrainIDText.getText());
        auth = Integer.parseInt(sendAuthText.getText());
        speed = Double.parseDouble(sendSpeedText.getText());
        tc.setSpeedAndAuthority(id,speed,auth);
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
            newTitle = "Dispatch";
        }
        else if(source == engInputsButton)
        {
            sideStage.setScene(engScene);
            newTitle = "Engineer Inputs";
        }
        else if(source == toTrackModelButton)
        {
            sideStage.setScene(toTMScene);
            newTitle = "Speed and Authority";
        }
        sideStage.setTitle(newTitle);
        sideStage.show();
    }
}