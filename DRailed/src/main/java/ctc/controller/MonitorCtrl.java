package ctc.controller;

import java.awt.geom.GeneralPath;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import ctc.beanpnl.Dot;
import ctc.beanpnl.Line;
import ctc.datamoni.RailwayMapOffer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class MonitorCtrl  implements Initializable {
    @FXML
    private TextField timeText;
	@FXML
    private TextField infoFlowText;
	
	@FXML
    private Canvas mapCanvas;

    @FXML
    protected void handleButtonAction(ActionEvent event) {
    	timeText.setText("Hello World, I am JavaFX!");
    }
    
    public void init(){
    	timeText.setText("2017-03-31 09:08:12 \\n 2017-03-31 09:08:15");
    	infoFlowText.setText("--------- \n ---------");
    }
    

    public void drawDot(GraphicsContext gc){
    	List<Dot> dots = RailwayMapOffer.offerDots();
    	
    	gc.setFill(Color.GREEN);  
        gc.setStroke(Color.RED);  
        gc.setLineWidth(5);  
    	
    	for(Dot dot : dots)
    	{
    		gc.fillOval(dot.getX(), dot.getY(), 10, 10);
    	}
    	
    	 
    }

    public void drawLine(GraphicsContext gc){
    	List<Line> lines = RailwayMapOffer.offerLines();
    	
    	gc.setFill(Color.GREEN);  
        gc.setStroke(Color.BLUE);  
        gc.setLineWidth(3);
        
    	for(Line line : lines)
    	{
    		drawAL(line.getStrat().getX(), line.getStrat().getY(), line.getEnd().getX(), line.getEnd().getY(), gc);
    	}
    }
    
    public void draw(GraphicsContext gc){
    	drawDot(gc);
    	drawLine(gc);
    }
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		init();
		
        GraphicsContext gc = mapCanvas.getGraphicsContext2D();  
         
        draw(gc); 
	}
	
	
	/************************************************************************/
	public static void drawAL(float f, float g, float i, float j, GraphicsContext g2)
	{

		double H = 8; // 绠ご楂樺害
		double L = 3; // 搴曡竟鐨勪竴鍗�
		int x3 = 0;
		int y3 = 0;
		int x4 = 0;
		int y4 = 0;
		double awrad = Math.atan(L / H); // 绠ご瑙掑害
		double arraow_len = Math.sqrt(L * L + H * H); // 绠ご鐨勯暱搴�
		double[] arrXY_1 = rotateVec(i - f, j - g, awrad, true, arraow_len);
		double[] arrXY_2 = rotateVec(i - f, j - g, -awrad, true, arraow_len);
		double x_3 = i - arrXY_1[0]; // (x3,y3)鏄涓�绔偣
		double y_3 = j - arrXY_1[1];
		double x_4 = i - arrXY_2[0]; // (x4,y4)鏄浜岀鐐�
		double y_4 = j - arrXY_2[1];

		Double X3 = new Double(x_3);
		x3 = X3.intValue();
		Double Y3 = new Double(y_3);
		y3 = Y3.intValue();
		Double X4 = new Double(x_4);
		x4 = X4.intValue();
		Double Y4 = new Double(y_4);
		y4 = Y4.intValue();
		// 鐢荤嚎
		g2.strokeLine(f, g, i, j);
		//
		GeneralPath triangle = new GeneralPath();
		triangle.moveTo(i, j);
		triangle.lineTo(x3, y3);
		triangle.lineTo(x4, y4);
		triangle.closePath();
		//瀹炲績绠ご
		// g2.fill(triangle);
		
		// 鐢荤澶�
		g2.strokeLine(i, j, x3, y3);
		g2.strokeLine(i, j, x4, y4);
		g2.strokeLine(x4, y4, x3, y3);
		
		//闈炲疄蹇冪澶�
		//g2.draw(triangle);

	}

	// 璁＄畻
	public static double[] rotateVec(float f, float g, double ang,
			boolean isChLen, double newLen) {

		double mathstr[] = new double[2];
		// 鐭㈤噺鏃嬭浆鍑芥暟锛屽弬鏁板惈涔夊垎鍒槸x鍒嗛噺銆亂鍒嗛噺銆佹棆杞銆佹槸鍚︽敼鍙橀暱搴︺�佹柊闀垮害
		double vx = f * Math.cos(ang) - g * Math.sin(ang);
		double vy = f * Math.sin(ang) + g * Math.cos(ang);
		if (isChLen) {
			double d = Math.sqrt(vx * vx + vy * vy);
			vx = vx / d * newLen;
			vy = vy / d * newLen;
			mathstr[0] = vx;
			mathstr[1] = vy;
		}
		return mathstr;
	}
	
	/*******************************************************************************/

}
