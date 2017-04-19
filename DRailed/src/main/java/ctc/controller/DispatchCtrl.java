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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ctc.CTCMain;
import ctc.bean.pnl.OptTrainBean;

public class DispatchCtrl implements Initializable  {
	@FXML
	private Label trainNumLabel;
	@FXML
	private Label lineLabel;
	@FXML
	private Label numOfCartsLabel;
	@FXML
	private Label speedLabel;
	@FXML
	private Label authorityLabel;
	@FXML
	private Label depatureTimeLabel;
	
	@FXML
    private  TextField numOfCartsText;
	@FXML
    private  TextField speedText;
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
	
	@FXML
	private Label dispatchedTrainsNum;
	
	private ObservableList<OptTrainBean> trainlist;
	
    @FXML
    protected void submitButtonAction(ActionEvent event) {
    	String line =  lineCombox.getSelectionModel().getSelectedItem().toString();   

			OptTrainBean opTrain = null;
			try {
				
				
				opTrain = new OptTrainBean(0, 6, new Double(0), new Double(100), 100);
				opTrain.setDepatureBlock(numOfCartsText.getText());
				opTrain.setDestination(speedText.getText());
				opTrain.setTrainNo(trainNumText.getText());
				opTrain.setLine(line);
				opTrain.setAuthority(new Integer(authorityText.getText()).intValue());
				opTrain.setDepatureTime(depaTimeText.getText());
				
				for(OptTrainBean train: trainlist)
				{
					if(train.getTrainNo().equals(trainNumText.getText()))
					{
						Alert _alert = new Alert(Alert.AlertType.CONFIRMATION, "Train Id exit！",
							new ButtonType("cancel"),
			                new ButtonType("confirm"));
					
						_alert.show();
						return;
					}
				}
				
				if(line.equals("Green"))
				{
					if(new Double(speedText.getText()) > 55)
					{
						Alert _alert = new Alert(Alert.AlertType.CONFIRMATION, "Unvalid speed！",
								new ButtonType("cancel"),
				                new ButtonType("confirm"));
						
						_alert.show();
						return;
					}
					
					if(new Double(authorityText.getText()) > 153)
					{
						Alert _alert = new Alert(Alert.AlertType.CONFIRMATION, "Unvalid authority!",
								new ButtonType("cancel"),
				                new ButtonType("confirm"));
						_alert.show();
						return;
					}
				}
				else
				{
					if(new Double(speedText.getText()) > 55)
					{
						Alert _alert = new Alert(Alert.AlertType.CONFIRMATION, "Unvalid speed！",
								new ButtonType("cancel"),
				                new ButtonType("confirm"));
						_alert.show();
						return;
					}
					
					if(new Double(authorityText.getText()) > 78)
					{
						Alert _alert = new Alert(Alert.AlertType.CONFIRMATION, "Unvalid authority!",
								new ButtonType("cancel"),
				                new ButtonType("confirm"));
						_alert.show();
						return;
					}
				}
				
				
				// 增加到已发车列表
				trainlist.add(opTrain);
				
				dispatchedTrainsNum.setText("" + trainlist.size());

			CTCMain.ctc_tc.dispatchTrain(152, 
					Integer.parseInt(numOfCartsText.getText()), 
					new Integer(authorityText.getText()).intValue(),
					new Double(speedText.getText()),
					new Integer(trainNumText.getText()));	
			
          
			// To TrackController
			// DispatchDealOfTrackController.dealDispatchEvent(opTrain);
			
			// 然后清空
			numOfCartsText.setText("");
			speedText.setText("");
	    	trainNumText.setText("");
	    	authorityText.setText("");
	    	depaTimeText.setText("");			
    	} catch (Exception e) {
			e.printStackTrace();
		}
   	
  
    	
    }
    
    @FXML
    protected void cancelButtonAction(ActionEvent event) {
    	numOfCartsText.setText("");
    	speedText.setText("");
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
				numOfCartsLabel.setText(optTrainBean.getDepatureBlock());
				speedLabel.setText(optTrainBean.getDestination());
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
