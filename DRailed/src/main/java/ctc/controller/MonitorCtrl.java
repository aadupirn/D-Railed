package ctc.controller;

import java.net.URL;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import ctc.bean.pnl.OptTrainBean;
import ctc.controller.help.MonitorMapHelp;
import ctc.controller.help.paint.bean.Dot;
import ctc.controller.help.paint.bean.Line;
import ctc.controller.help.paint.cache.RailwayCache;
import ctc.controller.help.paint.mgr.DotMgr;
import ctc.controller.help.paint.mgr.LineMgr;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class MonitorCtrl implements Initializable {
    @FXML
    private TextField timeText;
	@FXML
    private TextField infoFlowText;
	
	@FXML
    private Canvas mapCanvas;
	
	@FXML
	private ListView monitoredTrainsList;
	@FXML
	private Label currSection;
	@FXML
	private Label currBlock;
	@FXML
	private Label nextBlock;
	
	private static String fix_dot_str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	private static GraphicsContext gc;
	
	private static int time_count = 0;
	
	private ObservableList<OptTrainBean> trainlist;
	
    @FXML
    protected void handleButtonAction(ActionEvent event) {
    	timeText.setText("Hello World, I am JavaFX!");
    }
    
    public void initTrainList(){
    	trainlist = FXCollections.observableArrayList();
    	// list.addAll(OptTrainsOffer.offerOptTrains());
    	monitoredTrainsList.setItems(trainlist);
    	monitoredTrainsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() 
    	{
			@Override
			public void changed(ObservableValue observable,	Object oldValue, Object newValue) 
			{
				// TODO
			}
		});
    }
    
    
    public void init(){
    	initTrainList();
    	// 初始化点和线
    	DotMgr.initDots();
    	LineMgr.initLines();
    }
    

    public static void drawDot(GraphicsContext gc){
    	List<Dot> dots = RailwayCache.getGreen_dots();
    	
    	for(Dot dot : dots)
    	{
    		if(fix_dot_str.contains(dot.getName()))
    		{
    			gc.setLineWidth(6);  
    			gc.setFill(Color.WHITE);  
    	        gc.setStroke(Color.RED);  
    		}
    		else
    		{
    			gc.setLineWidth(3);  
    			gc.setFill(Color.GREEN);  
    	        gc.setStroke(Color.RED);
    		}
    		gc.fillOval(dot.getX(), dot.getY(), 10, 10);
    		gc.fillText(dot.getName(), dot.getX()+3, dot.getY()+3);
    	}
    	
    	
    	List<Dot> r_dots = RailwayCache.getRed_dots();
    	

    	for(Dot dot : r_dots)
    	{    
    		if(fix_dot_str.contains(dot.getName()))
			{
    			gc.setLineWidth(6);  
				gc.setFill(Color.BLUE);  
				gc.setStroke(Color.RED);  
			}
			else
			{
				gc.setLineWidth(3);  
				gc.setFill(Color.RED);  
	        	gc.setStroke(Color.RED);
			}
    		gc.fillOval(dot.getX(), dot.getY(), 10, 10);
    		gc.fillText(dot.getName(), dot.getX()+6, dot.getY()+3);
    	}
    }

    public static void drawLine(GraphicsContext gc){
    	List<Line> lines = RailwayCache.getGreen_lines();
    	
    	gc.setFill(Color.GREEN);  
        gc.setStroke(Color.GREEN);  
        gc.setLineWidth(3);
        
    	for(Line line : lines)
    	{
    		if(fix_dot_str.contains(line.getEndDot().getName()))
    		{
        		MonitorMapHelp.drawAL(line.getStartDot().getX()+6, line.getStartDot().getY()+6, line.getEndDot().getX()+6, line.getEndDot().getY()+6, gc);
    		}
    		else
    		{
        		gc.strokeLine(line.getStartDot().getX()+5, line.getStartDot().getY()+5, line.getEndDot().getX()+5, line.getEndDot().getY()+5);
    		}
    	}
    	
    	
    	List<Line> r_lines = RailwayCache.getRed_lines();
    	gc.setFill(Color.RED);  
        gc.setStroke(Color.RED);  
        gc.setLineWidth(3);
        
    	for(Line line : r_lines)
    	{
    		if(fix_dot_str.contains(line.getEndDot().getName()))
    		{
        		MonitorMapHelp.drawAL(line.getStartDot().getX()+6, line.getStartDot().getY()+6, line.getEndDot().getX()+6, line.getEndDot().getY()+6, gc);
    		}
    		else
    		{
        		gc.strokeLine(line.getStartDot().getX()+5, line.getStartDot().getY()+5, line.getEndDot().getX()+5, line.getEndDot().getY()+5);
    		}    	
    	}
    }
    
    public static void  drawTrainLocal_Green(GraphicsContext gc){
    	List<Line> lines = RailwayCache.getGreen_lines();
    	for(Entry<String,String> green_local : RailwayCache.green_line_occup.entrySet())
    	{
    		String trainId = green_local.getKey();
    		String block = green_local.getValue();
    		for(Line line : lines)
    		{
    			if(line.getName().equals(block))
    			{
    				System.out.println("draw occupy for Monitor Map");
    				gc.setFill(Color.DARKVIOLET);  
    		        gc.setStroke(Color.DARKVIOLET);  
    		        gc.setLineWidth(6);
            		gc.strokeLine(line.getStartDot().getX()+5, line.getStartDot().getY()+5, line.getEndDot().getX()+5, line.getEndDot().getY()+5);
    			}
    		}
    	}
    }
    
    public static void draw(GraphicsContext gc){
    	drawDot(gc);
    	drawLine(gc);
    }
    
    
    public static void update(String line){
    	if((time_count % 7) == 0)
    	{
    		draw(gc); 
    		System.out.println("update Monitor Map");
    		
    		if(line.equals("GREEN"))
    		{
    			// draw green Line train local
    	    	drawTrainLocal_Green(gc);
    		}
    		
    	}
    	time_count++;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		init();
		gc = mapCanvas.getGraphicsContext2D();
        draw(gc); 
	}
}
