package ctc.panels;

import java.io.IOException;

import ctc.CTCMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class DispatchMgr {
	private static BorderPane borderPane = null;
	
    /**
     * 
     * @return
     */
    public static BorderPane getDispatchBorderPanel(){
    	if(borderPane != null)
    	{
    		return borderPane;
    	}
    	
		try {
			borderPane= FXMLLoader.load(CTCMain.class.getResource("panels/DispatchPanel.fxml"));
			borderPane.setStyle("-fx-background-color: white");
	        return borderPane;
		} catch (IOException e) {
			e.printStackTrace();
		};

        return new BorderPane();
    }
}
