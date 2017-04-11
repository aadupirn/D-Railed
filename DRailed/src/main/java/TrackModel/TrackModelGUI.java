package TrackModel;

/**
 * Created by andrew on 1/21/17.
 */

import TrackModel.Model.*;
import TrainModel.Train;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class TrackModelGUI {

    //UI Constants
    private String RED_TEXT = "#bf140a";
    private String GREEN_TEXT = "#01bd37";
    private String BLUE_TEXT = "#050dff";
    private String BLACK_TEXT = "#000000";
    private final String applicationTitle = "Track Model";
    private final Stage stage = new Stage();
    private Scene scene;

    private final int windowWidth = 950;
    private final int windowHeight = 650;
    private final int gridBlock = 50;

    // Model Structures
    private Block selectedBlock = new Block();
    private Track track = new Track();

    // Main Grid
    private GridPane mainGrid = getGridPane();

    // Layout Panes (Track Navigator, Button Interface, Block Information)
    private GridPane trackLayout = getLayoutPane();
    private FlowPane buttonMenuLayout = getButtonMenuLayoutPane();
    private GridPane blockMonitorLayout = getLayoutPane();

    // Block Info Displays (Info, Infrastructure, Status)
    private GridPane blockInfo = getBlockInfoPane();
    private GridPane blockInfra = new GridPane();
    private GridPane blockStatus = new GridPane();

    // Infrastructure Icon Resources
    private ImageView trainIcon = new ImageView(new Image(getClass().getResource("/TrackModel/images/trainIcon.png").toString()));
    private ImageView switchIcon = new ImageView(new Image(getClass().getResource("/TrackModel/images/switchIcon.png").toString()));
    private ImageView stationIcon = new ImageView(new Image(getClass().getResource("/TrackModel/images/stationIcon.png").toString()));
    private ImageView crossingIcon = new ImageView(new Image(getClass().getResource("/TrackModel/images/crossingIcon.png").toString()));
    private ImageView lightsIcon = new ImageView(new Image(getClass().getResource("/TrackModel/images/lightsIcon.png").toString()));
    private ImageView heaterIcon = new ImageView(new Image(getClass().getResource("/TrackModel/images/heaterIcon.png").toString()));
    private ImageView beaconIcon = new ImageView(new Image(getClass().getResource("/TrackModel/images/beaconIcon.png").toString()));

    // Status Icon Resources
    private ImageView openIcon = new ImageView(new Image(getClass().getResource("/TrackModel/images/openIcon.png").toString()));
    private ImageView closedIcon = new ImageView(new Image(getClass().getResource("/TrackModel/images/closedIcon.png").toString()));
    private ImageView railIcon = new ImageView(new Image(getClass().getResource("/TrackModel/images/railIcon.png").toString()));
    private ImageView circuitIcon = new ImageView(new Image(getClass().getResource("/TrackModel/images/circuitIcon.png").toString()));
    private ImageView powerIcon = new ImageView(new Image(getClass().getResource("/TrackModel/images/powerIcon.png").toString()));

    public TrackModelGUI(Track track) throws IOException {

        stage.setTitle(applicationTitle);

        this.track = track;

        // Layout Menu
        Label layoutMenuTitle = getLayoutLabel();
        ScrollPane scrollPane = parseTrackForDisplay();
        trackLayout.add(scrollPane, 0, 0);

        // Button Menu
        buttonMenuLayout = getButtonMenu();

        // Button Info Section
        blockInfo = getBlockInfoPane();
        blockMonitorLayout.add(blockInfo, 0, 0);

        ///////////////////////////////////////////////
        // Block Infrastructure Segment              //
        ///////////////////////////////////////////////

        Label blockInfraLabel = new Label("Block Infrastructure: ");
        blockInfraLabel.setTextAlignment(TextAlignment.LEFT);
        blockInfraLabel.setFont(Font.font(blockInfraLabel.getFont().getFamily(), FontWeight.BOLD, blockInfraLabel.getFont().getSize()+5));


        blockInfra.setMinHeight(windowHeight/2);
        blockInfra.setMaxHeight(windowHeight/2);
        blockInfra.setMinWidth(windowWidth/3);
        blockInfra.setMaxWidth(windowWidth/3);

        FlowPane trainInfra = null;
        FlowPane switchInfra = null;
        FlowPane stationInfra = null;
        FlowPane crossingInfra = null;
        FlowPane lightsInfra = null;

        Train train = selectedBlock.getTrain();
        if(train != null)
            trainInfra = getTrainInfoPane();

        Switch dispSwitch = selectedBlock.getSwitch();
        if(dispSwitch != null)
            switchInfra = getSwitchInfoPane(blockInfra.getMinHeight(), blockInfra.getMinWidth(), dispSwitch);

        Station dispStation = selectedBlock.getStation();
        if(dispStation != null)
            stationInfra = getStationInfoPane(blockInfra.getMinHeight(), blockInfra.getMinWidth(), dispStation);

        Crossing dispCrossing = selectedBlock.getCrossing();
        if(dispCrossing != null)
            crossingInfra = getCrossingInfoPane(blockInfra.getMinHeight(), blockInfra.getMinWidth(), dispCrossing);

        Light light = selectedBlock.getLight();
        if(light != null)
            lightsInfra = getLightInfoPane();

        blockInfra = populateBlockInfrastructure(blockInfraLabel, trainInfra, switchInfra, stationInfra, crossingInfra, lightsInfra);
        blockMonitorLayout.add(blockInfra, 1,0);

        ///////////////////////////////////////////////
        // Block Status Segment                      //
        ///////////////////////////////////////////////

        Label blockStatusLabel = new Label("Block Status: ");
        blockStatusLabel.setFont(Font.font(blockStatusLabel.getFont().getFamily(), FontWeight.BOLD, blockStatusLabel.getFont().getSize()+5));

        blockStatus.setMinHeight(windowHeight/2);
        blockStatus.setMaxHeight(windowHeight/2);
        blockStatus.setMinWidth(windowWidth/3);
        blockStatus.setMaxWidth(windowWidth/3);

        FlowPane conditionStatus = getConditionStatusPane();
        FlowPane railStatus = getRailStatusPane();
        FlowPane circuitStatus = getCircuitStatusPane();
        FlowPane powerStatus = getPowerStatusPane();

        blockStatus = populateBlockStatus(blockStatusLabel, conditionStatus, railStatus, circuitStatus, powerStatus);
        blockMonitorLayout.add(blockStatus, 2, 0);

        ////////////////////////////////////
        // Add Segments To Main Grid      //
        ////////////////////////////////////

        mainGrid = populateMainGrid(layoutMenuTitle);

        scene = new Scene(mainGrid, windowWidth, windowHeight);
        stage.setScene(scene);
        stage.show();
    }

    private GridPane populateBlockInfrastructure(Label blockInfraLabel, FlowPane trainInfra, FlowPane switchInfra, FlowPane stationInfra, FlowPane crossingInfra, FlowPane lightsInfra) {

        GridPane blockInfra = new GridPane();
        blockInfra.setMinHeight(windowHeight/2);
        blockInfra.setMaxHeight(windowHeight/2);
        blockInfra.setMinWidth(windowWidth/3);
        blockInfra.setMaxWidth(windowWidth/3);

        int i = 1;

        blockInfra.add(blockInfraLabel, 0, 0);

        if(trainInfra != null){
            blockInfra.add(trainInfra, 0, i);
            i++;
        }
        if(switchInfra != null){
            blockInfra.add(switchInfra, 0, i);
            i++;
        }
        if(stationInfra != null){
            blockInfra.add(stationInfra, 0, i);
            i++;
        }
        if(crossingInfra != null){
            blockInfra.add(crossingInfra, 0, i);
            i++;
        }
        if(lightsInfra != null){
            blockInfra.add(lightsInfra, 0, i);
            i++;
        }

        return blockInfra;

    }

    private FlowPane getLightInfoPane() {
        FlowPane lightsInfra = new FlowPane();
        lightsInfra.setMaxHeight((blockInfra.getMaxHeight()/6)-3);
        lightsInfra.setMinHeight((blockInfra.getMinHeight()/6)-3);
        lightsInfra.setMaxWidth(blockInfra.getMinWidth());
        lightsInfra.setMinWidth(blockInfra.getMaxWidth());

        lightsIcon.setFitHeight(lightsInfra.getMaxHeight());
        lightsIcon.setFitWidth(50);

        GridPane lightsLabels = new GridPane();
        lightsLabels.setAlignment(Pos.CENTER_LEFT);

        Label lightsStatusLabel = new Label("Lights Status: ");
        lightsStatusLabel.setFont(Font.font(lightsStatusLabel.getFont().getFamily(), FontWeight.BOLD, lightsStatusLabel.getFont().getSize()));

        Label lightsStatusValue = null;

        if(selectedBlock.getLightStatus()){
            lightsStatusValue = new Label("GREEN");
            lightsStatusValue.setTextFill(Color.web(GREEN_TEXT));
        }else{
            lightsStatusValue = new Label("RED");
            lightsStatusValue.setTextFill(Color.web(RED_TEXT));
        }

        lightsLabels.add(lightsStatusLabel, 0, 0);
        lightsLabels.add(lightsStatusValue, 1, 0);
        lightsLabels.setPadding(new Insets(0, 0, 0, 10));

        lightsInfra.getChildren().add(lightsIcon);
        lightsInfra.getChildren().add(lightsLabels);
        return lightsInfra;
    }

    private FlowPane getTrainInfoPane() {
        FlowPane trainInfra = new FlowPane();
        trainInfra.setMaxHeight((blockInfra.getMaxHeight()/6)-4);
        trainInfra.setMinHeight((blockInfra.getMinHeight()/6)-4);
        trainInfra.setMaxWidth(blockInfra.getMinWidth());
        trainInfra.setMinWidth(blockInfra.getMaxWidth());

        GridPane trainLabels = new GridPane();
        trainLabels.setAlignment(Pos.CENTER_LEFT);

        Label trainLabel = new Label("Train: ");
        Label trainValue = new Label("" + selectedBlock.getTrain().getId());
        trainLabel.setFont(Font.font(trainLabel.getFont().getFamily(), FontWeight.BOLD, trainLabel.getFont().getSize()));

        Label trainLoadLabel = new Label("Unloading: ");
        Label trainLoadValue = new Label("" + selectedBlock.getTrain().getUnloading());
        trainLoadLabel.setFont(Font.font(trainLoadLabel.getFont().getFamily(), FontWeight.BOLD, trainLoadLabel.getFont().getSize()));

        trainLabels.add(trainLabel, 0, 0);
        trainLabels.add(trainValue, 1, 0);
        trainLabels.add(trainLoadLabel, 0, 1);
        trainLabels.add(trainLoadValue, 1, 1);
        trainLabels.setPadding(new Insets(0,0,0,10));

        trainIcon.setFitHeight(trainInfra.getMaxHeight());
        trainIcon.setFitWidth(50);

        trainInfra.getChildren().add(trainIcon);
        trainInfra.getChildren().add(trainLabels);
        return trainInfra;
    }

    private GridPane populateMainGrid(Label layoutMenuTitle) {

        GridPane mainGrid = new GridPane();

        mainGrid.add(layoutMenuTitle, 0, 0);
        mainGrid.add(trackLayout, 0, 2);
        mainGrid.add(buttonMenuLayout, 0,3);
        mainGrid.add(blockMonitorLayout, 0, 4);

        return mainGrid;
    }

    private GridPane populateBlockStatus(Label blockStatusLabel, FlowPane conditionStatus, FlowPane railStatus, FlowPane circuitStatus, FlowPane powerStatus) {

        GridPane blockStatus = new GridPane();
        blockStatus.setMinHeight(windowHeight/2);
        blockStatus.setMaxHeight(windowHeight/2);
        blockStatus.setMinWidth(windowWidth/3);
        blockStatus.setMaxWidth(windowWidth/3);

        blockStatus.add(blockStatusLabel, 0, 0);
        blockStatus.add(conditionStatus, 0, 1);
        blockStatus.add(railStatus, 0, 2);
        blockStatus.add(circuitStatus, 0, 3);
        blockStatus.add(powerStatus, 0, 4);

        return blockStatus;
    }

    private FlowPane getPowerStatusPane() {
        FlowPane powerStatus = new FlowPane();
        powerStatus.setMaxHeight((blockInfra.getMaxHeight()/5)-4);
        powerStatus.setMinHeight((blockInfra.getMinHeight()/5)-4);
        powerStatus.setMaxWidth(blockInfra.getMinWidth());
        powerStatus.setMinWidth(blockInfra.getMaxWidth());

        powerIcon.setFitHeight(powerStatus.getMaxHeight());
        powerIcon.setFitWidth(50);

        GridPane powerLabels = new GridPane();
        powerLabels.setAlignment(Pos.CENTER_LEFT);

        Label powerStatusLabel = new Label("Power Status: ");
        powerStatusLabel.setFont(Font.font(powerStatusLabel.getFont().getFamily(), FontWeight.BOLD, powerStatusLabel.getFont().getSize()));

        Label powerStatusValue = null;

        if(selectedBlock.isPowerState()) {
            powerStatusValue = new Label("Good");
            powerStatusValue.setTextFill(Color.web(GREEN_TEXT));
        }else{
            powerStatusValue = new Label("Failing");
            powerStatusValue.setTextFill(Color.web(RED_TEXT));
        }

        powerLabels.add(powerStatusLabel, 0, 0);
        powerLabels.add(powerStatusValue, 1, 0);
        powerLabels.setPadding(new Insets(0,0,0,10));

        powerStatus.getChildren().add(powerIcon);
        powerStatus.getChildren().add(powerLabels);
        return powerStatus;
    }

    private FlowPane getCircuitStatusPane() {
        FlowPane circuitStatus = new FlowPane();
        circuitStatus.setMaxHeight((blockInfra.getMaxHeight()/5)-4);
        circuitStatus.setMinHeight((blockInfra.getMinHeight()/5)-4);
        circuitStatus.setMaxWidth(blockInfra.getMinWidth());
        circuitStatus.setMinWidth(blockInfra.getMaxWidth());

        circuitIcon.setFitHeight(circuitStatus.getMaxHeight());
        circuitIcon.setFitWidth(50);

        GridPane circuitLabels = new GridPane();
        circuitLabels.setAlignment(Pos.CENTER_LEFT);

        Label circuitStatusLabel = new Label("Track Circuit Status: ");
        circuitStatusLabel.setFont(Font.font(circuitStatusLabel.getFont().getFamily(), FontWeight.BOLD, circuitStatusLabel.getFont().getSize()));

        Label circuitStatusValue = null;

        if(selectedBlock.isCircuitState()) {
            circuitStatusValue = new Label("Good");
            circuitStatusValue.setTextFill(Color.web(GREEN_TEXT));
        }else{
            circuitStatusValue = new Label("Failing");
            circuitStatusValue.setTextFill(Color.web(RED_TEXT));
        }

        circuitLabels.add(circuitStatusLabel, 0, 0);
        circuitLabels.add(circuitStatusValue, 1, 0);
        circuitLabels.setPadding(new Insets(0,0,0,10));

        circuitStatus.getChildren().add(circuitIcon);
        circuitStatus.getChildren().add(circuitLabels);
        return circuitStatus;
    }

    private FlowPane getRailStatusPane() {
        FlowPane railStatus = new FlowPane();
        railStatus.setMaxHeight((blockInfra.getMaxHeight()/5)-4);
        railStatus.setMinHeight((blockInfra.getMinHeight()/5)-4);
        railStatus.setMaxWidth(blockInfra.getMinWidth());
        railStatus.setMinWidth(blockInfra.getMaxWidth());

        railIcon.setFitHeight(railStatus.getMaxHeight());
        railIcon.setFitWidth(50);

        GridPane railLabels = new GridPane();
        railLabels.setAlignment(Pos.CENTER_LEFT);

        Label railStatusLabel = new Label("Rail Status: ");
        railStatusLabel.setFont(Font.font(railStatusLabel.getFont().getFamily(), FontWeight.BOLD, railStatusLabel.getFont().getSize()));

        Label railStatusValue = null;

        if(selectedBlock.isRailState()) {
            railStatusValue = new Label("Good");
            railStatusValue.setTextFill(Color.web(GREEN_TEXT));
        }else{
            railStatusValue = new Label("Failing");
            railStatusValue.setTextFill(Color.web(RED_TEXT));
        }

        railLabels.add(railStatusLabel, 0, 0);
        railLabels.add(railStatusValue, 1, 0);
        railLabels.setPadding(new Insets(0,0,0,10));

        railStatus.getChildren().add(railIcon);
        railStatus.getChildren().add(railLabels);
        return railStatus;
    }

    private FlowPane getConditionStatusPane() {

        FlowPane conditionStatus = new FlowPane();

        conditionStatus.setMaxHeight((blockInfra.getMaxHeight()/5)-4);
        conditionStatus.setMinHeight((blockInfra.getMinHeight()/5)-4);
        conditionStatus.setMaxWidth(blockInfra.getMinWidth());
        conditionStatus.setMinWidth(blockInfra.getMaxWidth());

        openIcon.setFitHeight(conditionStatus.getMaxHeight());
        openIcon.setFitWidth(50);
        closedIcon.setFitHeight(conditionStatus.getMaxHeight());
        closedIcon.setFitWidth(50);

        GridPane conditionLabels = new GridPane();
        conditionLabels.setAlignment(Pos.CENTER_LEFT);

        Label conditionStatusLabel = new Label("Overall Block Status: ");
        Label conditionStatusValue = new Label(selectedBlock.getTrackState());

        conditionStatusLabel.setFont(Font.font(conditionStatusLabel.getFont().getFamily(), FontWeight.BOLD, conditionStatusLabel.getFont().getSize()));
        conditionStatusLabel.setPadding(new Insets(0,0,0,10));
        conditionLabels.add(conditionStatusLabel, 0, 0);
        conditionLabels.setPadding(new Insets(0, 0, 0, 10));

        if(selectedBlock.getTrackState().contains("OPEN")){
            conditionStatusValue.setTextFill(Color.web(GREEN_TEXT));
            conditionStatus.getChildren().add(openIcon);
        }else{
            conditionStatusValue.setTextFill(Color.web(RED_TEXT));
            conditionStatus.getChildren().add(closedIcon);
        }

        conditionStatus.getChildren().add(conditionStatusLabel);
        conditionStatus.getChildren().add(conditionStatusValue);
        return conditionStatus;
    }

    private FlowPane getButtonMenu() {

        FlowPane menu = new FlowPane();
        menu.setAlignment(Pos.CENTER);
        menu.setPadding(new Insets(5,0,5,0));

        FileChooser fileChooser = new FileChooser();
        Stage fileSelect = new Stage();
        fileSelect.setTitle("Choose a track layout data file to import:");

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("TrackModel/tracks/").getFile());
        fileChooser.setInitialDirectory(file);

        // Import Track Button
        Button importTrack = new Button("Import Track");
        importTrack.setMinHeight(30);
        importTrack.setMaxHeight(30);

        // import a track and modify the track layout
        importTrack.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                File file = fileChooser.showOpenDialog(fileSelect);
                if(file != null)
                {
                    track.tm.importTrack(file.getName());
                    ScrollPane scrollPane = parseTrackForDisplay();
                    trackLayout.add(scrollPane, 0, 0);
                }
            }
        });

        Button infraSettings = new Button("Settings");
        importTrack.setMinHeight(30);
        importTrack.setMaxHeight(30);

        infraSettings.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                if(selectedBlock.getBlockNumber() == null){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Settings Error");
                    alert.setHeaderText("No Block Selected");
                    alert.setContentText("Please select a block to use this function!");
                    alert.show();
                    return;
                }

                Stage settings = new Stage();
                GridPane settingsPanel = new GridPane();

                VBox changeSettings = new VBox();
                HBox confirm = new HBox();

                Label todo = new Label("Select systems to simulate block infrastructure:");
                todo.setPadding(new Insets(10));

                CheckBox switchToggle = new CheckBox("Toggle Switch Position");
                CheckBox crossingToggle = new CheckBox("Toggle Crossing State");
                CheckBox stationToggle = new CheckBox("Generate New Station Departure Amount");
                CheckBox lightToggle = new CheckBox("Toggle Light State");

                Button okButton = new Button("OK");
                okButton.setOnAction(new EventHandler<ActionEvent>(){
                    public void handle(ActionEvent e){
                        if(switchToggle.isSelected())
                            selectedBlock.getSwitch().toggleState();

                        if(crossingToggle.isSelected())
                            selectedBlock.getCrossing().toggleActive();

                        if(stationToggle.isSelected())
                            selectedBlock.getStation().depart();

                        if(lightToggle.isSelected())
                            selectedBlock.getLight().toggleActive();

                        updateBlockMonitor();
                        settings.close();
                    }
                });

                Button cancelButton = new Button("Cancel");
                cancelButton.setOnAction(new EventHandler<ActionEvent>(){
                    public void handle(ActionEvent e){
                        settings.close();
                    }
                });

                if(selectedBlock.getSwitch() != null)
                    changeSettings.getChildren().add(switchToggle);

                if(selectedBlock.getCrossing() != null)
                    changeSettings.getChildren().add(crossingToggle);

                if(selectedBlock.getStation() != null)
                    changeSettings.getChildren().add(stationToggle);

                if(selectedBlock.getLight() != null)
                    changeSettings.getChildren().add(lightToggle);

                changeSettings.setPadding(new Insets(10));

                confirm.getChildren().addAll(okButton, cancelButton);
                confirm.setPadding(new Insets(10));

                settingsPanel.add(todo, 0, 0);
                settingsPanel.add(changeSettings, 0, 1);
                settingsPanel.add(confirm, 0, 3);

                Scene murphyScene = new Scene(settingsPanel, 400, 500);
                settings.setTitle("Infrastructure Settings");
                settings.setScene(murphyScene);
                settings.show();

            }
        });


        Button murphyCtrl = new Button("Murphy Controls");
        murphyCtrl.setMinHeight(30);
        murphyCtrl.setMaxHeight(30);

        // modify status of a selected block
        murphyCtrl.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                if(selectedBlock.getBlockNumber() == null){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Murphy Control Error");
                    alert.setHeaderText("No Block Selected");
                    alert.setContentText("Please select a block to use this function!");
                    alert.show();
                    return;
                }

                Stage murphy = new Stage();
                GridPane murphyPanel = new GridPane();

                VBox murphyChange = new VBox();
                HBox confirm = new HBox();

                Label todo = new Label("Select systems to simulate track state:");
                todo.setPadding(new Insets(10));

                Label currentBlock = new Label("Line: " + selectedBlock.getLine() + " Section: " + selectedBlock.getSection() +  " Block: " + selectedBlock.getBlockNumber());
                currentBlock.setPadding(new Insets(10));

                CheckBox condButton = new CheckBox("Close Track");
                CheckBox railButton = new CheckBox("Break Rail");
                CheckBox circuitButton = new CheckBox("Track Circuit Failure");
                CheckBox powerButton = new CheckBox("Power Failure");

                Button okButton = new Button("OK");

                okButton.setOnAction(new EventHandler<ActionEvent>(){
                    public void handle(ActionEvent e){
                        if(condButton.isSelected())
                            selectedBlock.toggleTrackState();

                        if(railButton.isSelected())
                            selectedBlock.toggleRailState();

                        if(circuitButton.isSelected())
                            selectedBlock.toggleCircuitState();

                        if(powerButton.isSelected())
                            selectedBlock.togglePowerState();

                        updateBlockMonitor();
                        murphy.close();
                    }
                });

                Button cancelButton = new Button("Cancel");

                cancelButton.setOnAction(new EventHandler<ActionEvent>(){
                    public void handle(ActionEvent e){
                        murphy.close();
                    }
                });

                murphyChange.getChildren().addAll(condButton, railButton, circuitButton, powerButton);
                murphyChange.setPadding(new Insets(10));

                confirm.getChildren().addAll(okButton, cancelButton);
                confirm.setPadding(new Insets(10));

                murphyPanel.add(todo, 0, 0);
                murphyPanel.add(currentBlock, 0, 1);
                murphyPanel.add(murphyChange, 0, 2);
                murphyPanel.add(confirm, 0, 3);

                Scene murphyScene = new Scene(murphyPanel, 400, 500);
                murphy.setTitle("Murphy Control");
                murphy.setScene(murphyScene);
                murphy.show();

            }
        });

        menu.getChildren().addAll(importTrack, infraSettings, murphyCtrl);
        return menu;
    }

    private void updateBlockMonitor() {
        updateBlockInfoView();
        updateBlockStatus();
        updateBlockInfrastructure();
    }

    private ScrollPane parseTrackForDisplay() {
        Accordion lineAccordion = new Accordion();
        List<Accordion> sectionAccordions = new ArrayList<>();
        List<TitledPane> linePanes = new ArrayList<>();
        List<TitledPane> sectionPanes = new ArrayList<>();
        List<Button> blockButtons = new ArrayList<>();
        List<VBox> buttonBoxes = new ArrayList<>();

        for(Line line : track.tm.getLines()){
            Accordion sectionAccordion = new Accordion();
            for(Section section : line.getSections()){
                VBox buttonBox = new VBox(5);
                for(Block block : section.getBlocks()){
                    Button blockButton = new Button("Block: " + block.getBlockNumber());
                    blockButton.setMinWidth(windowWidth);
                    blockButton.setAlignment(Pos.CENTER_LEFT);
                    blockButton.setTextAlignment(TextAlignment.LEFT);
                    if(block.isOccupied()) {
                        blockButton.setTextFill(Color.web(BLUE_TEXT));
                    }
                    blockButton.setOnAction((event) -> {
                        selectedBlock = block;
                        updateBlockMonitor();
                    });
                    blockButtons.add(blockButton);
                    buttonBox.getChildren().add(blockButton);
                }
                buttonBox.setPadding(new Insets(0));
                TitledPane sectionPane = new TitledPane("Section: " + section.getSection(), buttonBox);
                sectionAccordion.getPanes().add(sectionPane);
            }
            TitledPane linePane = new TitledPane("Line: " + line.getLine(), sectionAccordion);
            linePanes.add(linePane);
        }

        // Accordion Settings
        lineAccordion.getPanes().addAll(linePanes);
        lineAccordion.setMinWidth(windowWidth);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(lineAccordion);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return scrollPane;
    }

    private void updateBlockInfrastructure() {
        Label blockInfraLabel = new Label("Block Infrastructure: ");
        blockInfraLabel.setTextAlignment(TextAlignment.LEFT);
        blockInfraLabel.setFont(Font.font(blockInfraLabel.getFont().getFamily(), FontWeight.BOLD, blockInfraLabel.getFont().getSize()+5));

        blockInfra.getChildren().removeAll();

        blockInfra.setMinHeight(windowHeight/2);
        blockInfra.setMaxHeight(windowHeight/2);
        blockInfra.setMinWidth(windowWidth/3);
        blockInfra.setMaxWidth(windowWidth/3);

        FlowPane trainInfra = null;
        FlowPane switchInfra = null;
        FlowPane stationInfra = null;
        FlowPane crossingInfra = null;
        FlowPane lightsInfra = null;

        Train train = selectedBlock.getTrain();
        if(train != null)
            trainInfra = getTrainInfoPane();

        Switch dispSwitch = selectedBlock.getSwitch();
        if(dispSwitch != null)
            switchInfra = getSwitchInfoPane(blockInfra.getMinHeight(), blockInfra.getMinWidth(), dispSwitch);

        Station dispStation = selectedBlock.getStation();
        if(dispStation != null)
            stationInfra = getStationInfoPane(blockInfra.getMinHeight(), blockInfra.getMinWidth(), dispStation);

        Crossing dispCrossing = selectedBlock.getCrossing();
        if(dispCrossing != null)
            crossingInfra = getCrossingInfoPane(blockInfra.getMinHeight(), blockInfra.getMinWidth(), dispCrossing);

        Light light = selectedBlock.getLight();
        if(light != null)
            lightsInfra = getLightInfoPane();

        blockMonitorLayout.getChildren().remove(1);

        blockInfra = populateBlockInfrastructure(blockInfraLabel, trainInfra, switchInfra, stationInfra, crossingInfra, lightsInfra);
        blockMonitorLayout.add(blockInfra, 2, 0);
    }

    private void updateBlockStatus() {

        Label blockStatusLabel = new Label("Block Status: ");
        blockStatusLabel.setFont(Font.font(blockStatusLabel.getFont().getFamily(), FontWeight.BOLD, blockStatusLabel.getFont().getSize()+5));

        FlowPane conditionStatus = getConditionStatusPane();
        FlowPane railStatus = getRailStatusPane();
        FlowPane circuitStatus = getCircuitStatusPane();
        FlowPane powerStatus = getPowerStatusPane();

        blockStatus = populateBlockStatus(blockStatusLabel, conditionStatus, railStatus, circuitStatus, powerStatus);

        blockMonitorLayout.getChildren().remove(2);
        blockMonitorLayout.add(blockStatus, 3, 0);

    }

    private void updateBlockInfoView() {

        blockInfo = getBlockInfoPane();

        blockMonitorLayout.getChildren().remove(0);
        blockMonitorLayout.getChildren().add(0, blockInfo);

    }

    private Label getLayoutLabel() {

        Label layoutMenuTitle = new Label("TrackLayoutMenu");
        layoutMenuTitle.setFont(Font.font(layoutMenuTitle.getFont().getFamily(), FontWeight.BOLD, layoutMenuTitle.getFont().getSize()));

        layoutMenuTitle.setPadding(new Insets(0,0,0,10));
        layoutMenuTitle.setTextAlignment(TextAlignment.LEFT);
        layoutMenuTitle.setMinWidth(gridBlock);
        layoutMenuTitle.setMinHeight(20);
        layoutMenuTitle.setMaxHeight(20);
        layoutMenuTitle.setAlignment(Pos.CENTER_LEFT);

        return layoutMenuTitle;
    }

    private FlowPane getButtonMenuLayoutPane() {

        FlowPane buttonMenu = new FlowPane();

        buttonMenu.setAlignment(Pos.CENTER);
        buttonMenu.setMinWidth(windowWidth);
        buttonMenu.setMaxWidth(windowWidth);
        buttonMenu.setMinHeight(40);
        buttonMenu.setMaxHeight(40);

        return buttonMenu;

    }

    private GridPane getGridPane() {
        GridPane mainGrid = new GridPane();
        mainGrid.setAlignment(Pos.TOP_CENTER);
        mainGrid.setPadding(new Insets(0, 0, 0, 0));
        return mainGrid;
    }

    private GridPane getLayoutPane() {

        GridPane trackLayout = new GridPane();

        trackLayout.setAlignment(Pos.TOP_LEFT);
        trackLayout.setMinHeight((windowHeight/2)-70);
        trackLayout.setMaxHeight((windowHeight/2)-70);
        trackLayout.setMinWidth(windowWidth);
        trackLayout.setMaxWidth(windowWidth);

        return trackLayout;

    }

    private FlowPane getSwitchInfoPane(double height, double width, Switch aSwitch) {

        FlowPane switchInfra = new FlowPane();

        switchInfra.setMaxHeight((height/5)-4);
        switchInfra.setMinHeight((height/5)-4);
        switchInfra.setMaxWidth(width);
        switchInfra.setMinWidth(width);

        switchIcon.setFitHeight(switchInfra.getMaxHeight());
        switchIcon.setFitWidth(40);

        GridPane switchLabels = new GridPane();
        switchLabels.setAlignment(Pos.CENTER_LEFT);

        Label switchLabel = new Label("Switch: ");
        switchLabel.setFont(Font.font(switchLabel.getFont().getFamily(), FontWeight.BOLD, switchLabel.getFont().getSize()));

        Label switchValue = null;

        if(selectedBlock.getBlockNumber().equals(aSwitch.getMain())) {
            switchValue = new Label(aSwitch.getSwitchNumber() + " [M]");
        }else if(selectedBlock.getBlockNumber().equals(aSwitch.getTop())){
            switchValue = new Label(aSwitch.getSwitchNumber() + " [T]");
        }else{
            switchValue = new Label(aSwitch.getSwitchNumber() + " [B]");
        }

        Label switchStatusLabel = new Label("Status: ");
        switchStatusLabel.setFont(Font.font(switchLabel.getFont().getFamily(), FontWeight.BOLD, switchLabel.getFont().getSize()));
        Label switchStatusValue = new Label("" + aSwitch.getState());

        Label switchConnectMain = new Label("Main: ");
        switchConnectMain.setFont(Font.font(switchLabel.getFont().getFamily(), FontWeight.BOLD, switchLabel.getFont().getSize()));
        Label switchMainValue = new Label("" + aSwitch.getMain());

        Label switchConnectTop = new Label("Top: ");
        switchConnectTop.setFont(Font.font(switchLabel.getFont().getFamily(), FontWeight.BOLD, switchLabel.getFont().getSize()));
        Label switchTopValue = new Label("" +aSwitch.getTop());

        Label switchConnectBottom = new Label("Bottom: ");
        switchConnectBottom.setFont(Font.font(switchLabel.getFont().getFamily(), FontWeight.BOLD, switchLabel.getFont().getSize()));
        Label switchBottomValue = new Label("" + aSwitch.getBottom());

        switchLabel.setPadding(new Insets(0,0,0,10));
        switchStatusLabel.setPadding(new Insets(0,0,0,10));
        switchValue.setPadding(new Insets(0,10,0,5));
        switchStatusValue.setPadding(new Insets(0,10,0,5));

        switchLabels.add(switchLabel, 0, 0);
        switchLabels.add(switchValue, 1, 0);
        switchLabels.add(switchStatusLabel, 0, 1);
        switchLabels.add(switchStatusValue,1,1);
        switchLabels.add(switchConnectMain, 2, 0);
        switchLabels.add(switchMainValue,3,0);
        switchLabels.add(switchConnectTop, 2,1);
        switchLabels.add(switchTopValue, 3, 1);
        switchLabels.add(switchConnectBottom, 2,2);
        switchLabels.add(switchBottomValue, 3,2);

        switchInfra.getChildren().add(switchIcon);
        switchInfra.getChildren().add(switchLabels);

        return switchInfra;

    }

    private FlowPane getStationInfoPane(double height, double width, Station station) {

        FlowPane stationInfra = new FlowPane();

        stationInfra.setMaxHeight((height / 6) - 4);
        stationInfra.setMinHeight((height / 6) - 4);
        stationInfra.setMaxWidth(width);
        stationInfra.setMinWidth(width);

        stationIcon.setFitHeight(stationInfra.getMaxHeight());
        stationIcon.setFitWidth(50);

        GridPane stationLabels = new GridPane();
        stationLabels.setAlignment(Pos.CENTER_LEFT);

        Label stationLabel = new Label("Station: ");
        stationLabel.setFont(Font.font(stationLabel.getFont().getFamily(), FontWeight.BOLD, stationLabel.getFont().getSize()));
        Label stationValue = new Label(station.getStationName());

        Label stationOccupancyLabel = new Label("Departing: ");
        stationOccupancyLabel.setFont(Font.font(stationOccupancyLabel.getFont().getFamily(), FontWeight.BOLD, stationOccupancyLabel.getFont().getSize()));
        Label stationOccupancy = new Label("" + station.getDeparting());

        stationLabels.add(stationLabel, 0, 0);
        stationLabels.add(stationValue, 1, 0);
        stationLabels.add(stationOccupancyLabel, 0, 1);
        stationLabels.add(stationOccupancy, 1, 1);

        stationLabel.setPadding(new Insets(0, 0, 0, 10));
        stationOccupancyLabel.setPadding(new Insets(0, 0, 0, 10));

        stationInfra.getChildren().add(stationIcon);
        stationInfra.getChildren().add(stationLabels);

        return stationInfra;
    }

    private FlowPane getCrossingInfoPane(double height, double width, Crossing crossing) {

        FlowPane crossingInfra = new FlowPane();

        crossingInfra.setMaxHeight((height / 6) - 4);
        crossingInfra.setMinHeight((height / 6) - 4);
        crossingInfra.setMaxWidth(width);
        crossingInfra.setMinWidth(width);

        crossingIcon.setFitHeight(crossingInfra.getMaxHeight());
        crossingIcon.setFitWidth(50);

        GridPane crossingLabels = new GridPane();
        crossingLabels.setAlignment(Pos.CENTER_LEFT);

        Label crossingStatusLabel = new Label("Crossroad Status: ");
        Label crossingStatus = null;

        if(crossing != null && crossing.isActive()){
            crossingStatus = new Label("ACTIVE");
            crossingStatus.setTextFill(Color.web(GREEN_TEXT));
        }else{
            crossingStatus = new Label("INACTIVE");
            crossingStatus.setTextFill(Color.web(BLUE_TEXT));
        }

        crossingLabels.add(crossingStatusLabel, 0, 0);
        crossingLabels.add(crossingStatus, 1, 0);
        crossingLabels.setPadding(new Insets(0, 0, 0, 10));

        crossingInfra.getChildren().add(crossingIcon);
        crossingInfra.getChildren().add(crossingLabels);

        return crossingInfra;
    }

    private GridPane getBlockInfoPane() {

        GridPane blockInfoPanel = new GridPane();

        Label blockInfoLabel = new Label("Block Info:");
            blockInfoLabel.setPadding(new Insets(0,0,20,10));
            blockInfoLabel.setFont(Font.font(blockInfoLabel.getFont().getFamily(), FontWeight.BOLD, blockInfoLabel.getFont().getSize()+5));

        Label blockLineLabel = new Label( "Line: ");
        Label blockLineValue = new Label("" + selectedBlock.getLine());
            blockLineLabel.setPadding(new Insets(0,0,0,10));
            blockLineLabel.setFont(Font.font(blockLineLabel.getFont().getFamily(), FontWeight.BOLD, blockLineLabel.getFont().getSize()));
            blockLineValue.setPadding(new Insets(0,0,0,10));

        Label blockSectionLabel = new Label("Section: ");
        Label blockSectionValue = new Label("" + selectedBlock.getSection());
            blockSectionLabel.setPadding(new Insets(0,0,0,10));
            blockSectionLabel.setFont(Font.font(blockLineLabel.getFont().getFamily(), FontWeight.BOLD, blockLineLabel.getFont().getSize()));
            blockSectionValue.setPadding(new Insets(0,0,0,10));

        Label blockLabel = new Label("Block: ");
        Label blockValue = new Label("" + selectedBlock.getBlockNumber());
            blockLabel.setPadding(new Insets(0,0,20,10));
            blockLabel.setFont(Font.font(blockLineLabel.getFont().getFamily(), FontWeight.BOLD, blockLineLabel.getFont().getSize()));
            blockValue.setPadding(new Insets(0,0,20,10));

        Label length = new Label("Length (mi): ");
        Label lengthValue = null;
        if(selectedBlock.getLength() != null) {
            lengthValue = new Label("" + round(selectedBlock.getLength() * 0.0006, 2));
        }else{
            lengthValue = new Label("" + selectedBlock.getLength());
        }
            length.setPadding(new Insets(0,0,0,10));
            length.setFont(Font.font(blockLineLabel.getFont().getFamily(), FontWeight.BOLD, blockLineLabel.getFont().getSize()));
            lengthValue.setPadding(new Insets(0,0,0,10));

        Label speedLimit = new Label("Speed Limit (mph): ");
        Label speedLimitValue = null;
        if(selectedBlock.getSpeedLimit() != null) {
            speedLimitValue = new Label("" + round(selectedBlock.getSpeedLimit() * 2.24, 2)); // meters/seconds * miles/hour
        }else{
            speedLimitValue = new Label("" + selectedBlock.getSpeedLimit());
        }
            speedLimit.setPadding(new Insets(0,0,0,10));
            speedLimit.setFont(Font.font(blockLineLabel.getFont().getFamily(), FontWeight.BOLD, blockLineLabel.getFont().getSize()));
            speedLimitValue.setPadding(new Insets(0,0,0,10));

        Label grade = new Label("Grade (%): ");
        Label gradeValue = new Label("" + selectedBlock.getGrade());
            grade.setPadding(new Insets(0,0,0,10));
            grade.setFont(Font.font(blockLineLabel.getFont().getFamily(), FontWeight.BOLD, blockLineLabel.getFont().getSize()));
            gradeValue.setPadding(new Insets(0,0,0,10));

        Label elevation = new Label("Elevation (ft): ");
        Label elevationValue = null;
        if(selectedBlock.getElevation() != null) {
            elevationValue = new Label("" + round(selectedBlock.getElevation() * 3.28, 2));
        }else{
            elevationValue = new Label("" + selectedBlock.getElevation());
        }
            elevation.setPadding(new Insets(0,0,0,10));
            elevation.setFont(Font.font(blockLineLabel.getFont().getFamily(), FontWeight.BOLD, blockLineLabel.getFont().getSize()));
            elevationValue.setPadding(new Insets(0,0,0,10));

        Label cumElevation = new Label("Cumulative Elevation (ft): ");
        Label cumElevationValue = null;
        if(selectedBlock.getCumulativeElevation() != null){
            cumElevationValue = new Label("" + round(selectedBlock.getCumulativeElevation() * 3.28,2));
        }else{
            cumElevationValue = new Label("" + selectedBlock.getCumulativeElevation());
        }
            cumElevation.setPadding(new Insets(0,0,0,10));
            cumElevation.setFont(Font.font(blockLineLabel.getFont().getFamily(), FontWeight.BOLD, blockLineLabel.getFont().getSize()));
            cumElevationValue.setPadding(new Insets(0,0,0,10));

        Label direction = new Label("Direction: ");
        Label directionValue = new Label("" + selectedBlock.getDirection());
            direction.setPadding(new Insets(0,0,0,10));
            direction.setFont(Font.font(blockLineLabel.getFont().getFamily(), FontWeight.BOLD, blockLineLabel.getFont().getSize()));
            directionValue.setPadding(new Insets(0,0,0,10));

        Label temperature = new Label("Temperature (F): ");
        Label temperatureValue = new Label("" + selectedBlock.getTemperature());
            temperature.setPadding(new Insets(0,0,0,10));
            temperature.setFont(Font.font(blockLineLabel.getFont().getFamily(), FontWeight.BOLD, blockLineLabel.getFont().getSize()));
            temperatureValue.setPadding(new Insets(0,0,0,10));

        Label occupied = new Label("Occupied: ");
        Label occupiedValue = new Label("" + selectedBlock.isOccupied());
            occupied.setPadding(new Insets(0,0,0,10));
            occupied.setFont(Font.font(blockLineLabel.getFont().getFamily(), FontWeight.BOLD, blockLineLabel.getFont().getSize()));
            occupiedValue.setPadding(new Insets(0,0,0,10));

        blockInfoPanel.setAlignment(Pos.TOP_LEFT);
        blockInfoPanel.setMinHeight(windowHeight/2);
        blockInfoPanel.setMaxHeight(windowHeight/2);
        blockInfoPanel.setMinWidth(windowWidth/3);
        blockInfoPanel.setMaxWidth(windowWidth/3);

        blockInfoPanel.add(blockInfoLabel, 0, 0);
        blockInfoPanel.add(blockLineLabel, 0, 1);
        blockInfoPanel.add(blockLineValue, 1, 1);
        blockInfoPanel.add(blockSectionLabel, 0, 2);
        blockInfoPanel.add(blockSectionValue, 1, 2);
        blockInfoPanel.add(blockLabel, 0, 3);
        blockInfoPanel.add(blockValue, 1, 3);
        blockInfoPanel.add(length, 0, 4);
        blockInfoPanel.add(lengthValue, 1, 4);
        blockInfoPanel.add(speedLimit, 0, 5);
        blockInfoPanel.add(speedLimitValue, 1, 5);
        blockInfoPanel.add(grade, 0, 6);
        blockInfoPanel.add(gradeValue, 1, 6);
        blockInfoPanel.add(elevation, 0, 7);
        blockInfoPanel.add(elevationValue, 1, 7);
        blockInfoPanel.add(cumElevation, 0, 8);
        blockInfoPanel.add(cumElevationValue, 1, 8);
        blockInfoPanel.add(direction, 0, 9);
        blockInfoPanel.add(directionValue, 1, 9);
        blockInfoPanel.add(temperature, 0, 10);
        blockInfoPanel.add(temperatureValue, 1, 10);
        blockInfoPanel.add(occupied, 0, 11);
        blockInfoPanel.add(occupiedValue, 1, 11);

        return blockInfoPanel;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void update(){
        selectedBlock = track.getBlock(selectedBlock.getLine(), selectedBlock.getBlockNumber());
        updateBlockMonitor();
    }

}
