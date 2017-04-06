package ctc.controller;

import java.net.URL;
import java.util.ResourceBundle;

import ctc.beanpnl.ScheduleBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

/**
 *
 * @author Administrator
 */
public  class ScheduleCtrl implements Initializable  {
    public static ObservableList<ScheduleBean> getList(){
        ObservableList<ScheduleBean> list = FXCollections.observableArrayList();
        
        ScheduleBean msg1 = new ScheduleBean();
        msg1.setMMQBH("D2017");
        msg1.setSCCS("American Train");
        msg1.setZT("A");
        msg1.setKHH("G");
        msg1.setKHMC("6");
        msg1.setBHFX("a1,b5,c8,d1,e3,g5");
        msg1.setFXRQ("60,80,100,120,110,150");
        msg1.setCJR("2017-04-20 09:30:00");
        
        ScheduleBean msg2 = new ScheduleBean();
        msg2.setMMQBH("American Train");
        msg2.setSCCS("High-speed rail");
        msg2.setZT("Z");
        msg2.setKHH("C");
        msg2.setKHMC("5");
        msg2.setBHFX("z1,v2,w1,h1,c2");
        msg2.setFXRQ("80,120,110,100,150");
        msg2.setCJR("2017-04-21 08:09:25");
        
        list.add(msg1);
        list.add(msg2);
        return list;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
