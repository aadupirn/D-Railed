package ctc.datamoni;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ctc.beanpnl.OptTrainBean;

public class OptTrainsOffer {
	public static ObservableList<OptTrainBean> offerOptTrains(){
		ObservableList<OptTrainBean> optTrains = FXCollections.observableArrayList();
		
		for(int i = 0; i < 20;i++)
		{
			String id = "id_" + i;
			
			int newID = i;
			Double newSpeed = new Double(100);
			Double newAuthority = new Double(220);
			int numberOfCarts = 16;
			int blockLocation = i;
					
			OptTrainBean train;
			try {
				train = new OptTrainBean(blockLocation, numberOfCarts,
						newAuthority, newSpeed, newID);
				
				train.setTrainNo("No._" + i);
				optTrains.add(train);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return optTrains;
	}
}
