package ctc.panels;

import java.io.IOException;

import ctc.CTCMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class InstructionsMgr {
	private static BorderPane borderPane = null;
	
	/**
     * 树节点1的第四个按钮的页面设置
     * @return 返回一个竖直方向的布局
     */
    public static BorderPane getInstructionBorderPanel(){
    	if(borderPane != null)
    	{
    		return borderPane;
    	}
    	
		try {
			borderPane= FXMLLoader.load(CTCMain.class.getResource("panels/Instructions.fxml"));
			borderPane.setStyle("-fx-background-color: white");
	        return borderPane;
		} catch (IOException e) {
			e.printStackTrace();
		};

        return new BorderPane();
    }
    
}
