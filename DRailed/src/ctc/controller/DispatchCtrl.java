package ctc.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import TrackController.events.DispatchDealOfTrackController;
import ctc.beanpnl.OptTrainBean;

public class DispatchCtrl implements Initializable  {
	@FXML
	private Label trainNumLabel;
	@FXML
	private Label lineLabel;
	@FXML
	private Label departureLabel;
	@FXML
	private Label destinationLabel;
	@FXML
	private Label authorityLabel;
	@FXML
	private Label depatureTimeLabel;
	
	@FXML
    private  TextField departureText;
	@FXML
    private  TextField destinationText;
	@FXML
    private  TextField trainNumText;
	@FXML
    private  TextField authorityText;
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
			OptTrainBean opTrain = new OptTrainBean(0, 6, new Double(0), new Double(100), 100);
		
			opTrain.setDepatureBlock(departureText.getText());
			opTrain.setDestination(destinationText.getText());
			opTrain.setTrainNo(trainNumText.getText());
			opTrain.setLine(line);
			opTrain.setAuthority(new Integer(authorityText.getText()).intValue());
			
			opTrain.setDepatureTime(depaTimeText.getText());
			
			// 增加到已发车列表
			trainlist.add(opTrain);
			
			// To TrackController
			DispatchDealOfTrackController.dealDispatchEvent(opTrain);
			
			// 然后清空
			departureText.setText("");
			destinationText.setText("");
	    	trainNumText.setText("");
	    	authorityText.setText("");
	    	depaTimeText.setText("");			
    	} catch (IOException e) {
			e.printStackTrace();
		}
   	
  
    	
    }
    
    @FXML
    protected void cancelButtonAction(ActionEvent event) {
    	departureText.setText("");
    	destinationText.setText("");
    	trainNumText.setText("");
    	authorityText.setText("");
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
				departureLabel.setText(optTrainBean.getDepatureBlock());
				destinationLabel.setText(optTrainBean.getDestination());
				authorityLabel.setText("" + optTrainBean.getAuthority());
				depatureTimeLabel.setText(optTrainBean.getDepatureTime());
				
				// System.out.println(optTrainBean.toString());
			}
		});
    }
    
    
	@Override
	@FXML
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		lineCombox.getItems().addAll("Green","Red");
		
		showList();
	}
}
