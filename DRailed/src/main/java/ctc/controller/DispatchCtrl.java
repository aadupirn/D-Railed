package ctc.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.javafx.image.impl.ByteIndexed.Getter;

import ctc.beanpnl.OptTrainBean;
import ctc.datamoni.OptTrainsOffer;
import ctc.events.DispatchEventUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DispatchCtrl implements Initializable  {
	@FXML
	private Label trainNumLabel;
	@FXML
	private Label lineLabel;
	@FXML
	private Label deptStationLabel;
	@FXML
	private Label destinationLabel;
	@FXML
	private Label authorityLabel;
	@FXML
	private Label authoritySequenceLabel;
	@FXML
	private Label speedSequenceLabel;
	@FXML
	private Label depatureTimeLabel;
	
	@FXML
    private  TextField departStationText;
	@FXML
    private  TextField destStationText;
	@FXML
    private  TextField trainNumText;
	@FXML
    private  TextField authorityText;
	@FXML
    private  TextField authoritySequenceText;
	@FXML
    private  TextField speedSequenceText;
	@FXML
    private  TextField depaTimeText;
	@FXML
    private  ComboBox lineCombox;
	
	@FXML
	private  Button submitBtn;
	@FXML
	private  Button cancelBtn;
	
	@FXML
	private  ListView optTrainsListView;
	
	private ObservableList<OptTrainBean> trainlist;
	
    @FXML
    protected void submitButtonAction(ActionEvent event) {
    	String line =  lineCombox.getSelectionModel().getSelectedItem().toString();   
    	
    	try {
			OptTrainBean opTrain = new OptTrainBean(0, 0, new Double(0), new Double(0), 0);
		
			opTrain.setDepatureStation(departStationText.getText());
			opTrain.setDestination(destStationText.getText());
			opTrain.setTrainNo(trainNumText.getText());
			opTrain.setLine(line);
			opTrain.setAuthority(new Integer(authorityText.getText()).intValue());
			
			String authoritys = authoritySequenceText.getText();
			String speeds = speedSequenceText.getText();
			
			String[] authorityArray = authoritys.split(",");
			String[] speedArray = speeds.split(",");
			
			List<String> authorityList = new ArrayList<String>();
			List<String> speedList = new ArrayList<String>();
			for(String astr : authorityArray)
			{
				authorityList.add(astr);
			}
			
			for(String bstr : speedArray)
			{
				speedList.add(bstr);
			}
			
			opTrain.setAuthoritySequence(authorityList);
			opTrain.setSpeedSequence(speedList);
			opTrain.setDepatureTime(depaTimeText.getText());
			
			// 增加到已发车列表
			trainlist.add(opTrain);
			
			DispatchEventUtil.sentDispatchEvent(opTrain);
			
			// 然后清空
	    	departStationText.setText("");
	    	destStationText.setText("");
	    	trainNumText.setText("");
	    	authorityText.setText("");
	    	authoritySequenceText.setText("");
	    	speedSequenceText.setText("");
	    	depaTimeText.setText("");			
    	} catch (IOException e) {
			e.printStackTrace();
		}
   	
  
    	
    }
    
    @FXML
    protected void cancelButtonAction(ActionEvent event) {
    	departStationText.setText("");
    	destStationText.setText("");
    	trainNumText.setText("");
    	authorityText.setText("");
    	authoritySequenceText.setText("");
    	speedSequenceText.setText("");
    	depaTimeText.setText("");
    }

    
    @SuppressWarnings("unchecked")
	public void showList(){
    	trainlist = FXCollections.observableArrayList();
    	// list.addAll(OptTrainsOffer.offerOptTrains());
    	optTrainsListView.setItems(trainlist);
    	optTrainsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() 
    	{
			@Override
			public void changed(ObservableValue observable,	Object oldValue, Object newValue) 
			{
				// TODO
				OptTrainBean optTrainBean = (OptTrainBean) optTrainsListView.getSelectionModel().getSelectedItem();
				// 点击火车列表
				trainNumLabel.setText(optTrainBean.getTrainNo());
				lineLabel.setText(optTrainBean.getLine());
				deptStationLabel.setText(optTrainBean.getDepatureStation());
				destinationLabel.setText(optTrainBean.getDestination());
				authorityLabel.setText("" + optTrainBean.getAuthority());
				authoritySequenceLabel.setText(optTrainBean.getAuthoritySequence().toString());
				speedSequenceLabel.setText(optTrainBean.getSpeedSequence().toString());
				depatureTimeLabel.setText(optTrainBean.getDepatureTime());
				
				// System.out.println(optTrainBean.toString());
			}
		});
    }
    
    
	@Override
	@FXML
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		DispatchEventUtil.init();   // init Event
		
		lineCombox.getItems().addAll("Green","Red");
		
		showList();
	}
}
