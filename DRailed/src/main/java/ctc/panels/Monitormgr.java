package ctc.panels;

import java.io.IOException;
import ctc.CTCMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class Monitormgr {
	 private static VBox vblast;
	 
    /**
     * 树节点1的第一个按钮的页面设置
     * @return 返回一个竖直方向的布局
     */
    public static VBox getMonitorMapVBox(){
    	if(vblast != null)
    	{
    		return vblast;
    	}
    	
		try {
			vblast = FXMLLoader.load(CTCMain.class.getResource("panels/MonitorMap.fxml"));
	        vblast.setStyle("-fx-background-color: white");
	        return vblast;
		} catch (IOException e) {
			e.printStackTrace();
		};

        return new VBox();
    }
}
