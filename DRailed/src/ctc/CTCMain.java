package ctc;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ctc.panels.DispatchMgr;
import ctc.panels.InstructionsMgr;
import ctc.panels.Monitormgr;
import ctc.panels.ScheduleMgr;

/**
 *
 * @author 
 */
public class CTCMain extends Application {   
    public TitledPane node1;
    public TitledPane node2;
    public TitledPane node3;
    public TitledPane node4;
    public TitledPane node5;
    public Accordion accordion;
    
    /**
     * 设置根节点布局
     * @return 返回布局
     */
    public BorderPane setBorder(){
        BorderPane layout = new BorderPane();
        return layout;
    }
    /**
     * 设置顶部
     * @param layout 传入布局
     */
    public void settop(BorderPane layout){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5, 6, 5, 6));
        hbox.setSpacing(3);
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-background-color: #336699");
        Label toplabel = new Label(" CTC System");
        toplabel.setFont(new Font(15));
        toplabel.setTextFill(Color.BLACK);
        hbox.getChildren().add(toplabel);
        
        VBox vbox = new VBox();
        Separator sper = new Separator();
        sper.setOrientation(Orientation.HORIZONTAL);
        sper.setStyle(" -fx-background-color: #e79423;-fx-background-radius: 2;");
        vbox.getChildren().addAll(hbox,sper);
        
        layout.setTop(vbox);
    }
    /**
     * 设置左边部分
     * @param layout 传入的布局
     */
    public void setleft(BorderPane layout){
        //第一个根节点
        VBox vb1 = new VBox(5);
        vb1.setPadding(new Insets(15, 12, 15, 12));
        node1 = new TitledPane("Monitor Map",vb1);
        
        //第二个根节点
        VBox vb2 = new VBox(5);
        vb2.setPadding(new Insets(15, 12, 15, 12));
        node2 = new TitledPane("Dispatch System",vb2);
        
        //第三个根节点
        VBox vb3 = new VBox(5);
        vb3.setPadding(new Insets(15, 12, 15, 12));
        node3 = new TitledPane("Schedule Manager",vb3);
        
        //第四个根节点
        VBox vb4 = new VBox(5);
        vb4.setPadding(new Insets(15, 12, 15, 12));
        node4 = new TitledPane("Instruction management",vb4);
        
        //第四个根节点
        VBox vb5 = new VBox(5);
        vb5.setPadding(new Insets(15, 12, 15, 12));
        node5 = new TitledPane("Information record",vb5);
        
        //设置树形根节点
        accordion = new Accordion();
        accordion.getPanes().add(node1);
        accordion.getPanes().add(node2);
        accordion.getPanes().add(node3);
        accordion.getPanes().add(node4);
        accordion.getPanes().add(node5);
        
        HBox hb = new HBox();
        Separator sper = new Separator();
        sper.setOrientation(Orientation.VERTICAL);
        sper.setStyle(" -fx-background-color: #e79423;-fx-background-radius: 2;");
        hb.getChildren().addAll(accordion,sper);
        
        //将树形节点加如布局的左边
        layout.setLeft(hb);    
    }
    
    /**
     * 设置中间部分
     * @param layout 传入的布局 
     */
    public void setcenter(final BorderPane layout){
        // Label testt = new Label("Default Panel");
        layout.setCenter(Monitormgr.getMonitorMapVBox());
        
        //树节点1的第一个按钮功能页面的设置
		accordion.expandedPaneProperty().addListener(
			new ChangeListener<TitledPane>() 
			{
				public void changed(ObservableValue<? extends TitledPane> ov, TitledPane old_val, 
						TitledPane new_val) 
				{
					if (new_val == null) 
					{
						return;
					}
					
					if(new_val.getText().equals("Monitor Map"))
					{
						layout.setCenter(Monitormgr.getMonitorMapVBox());
					}
					
					if(new_val.getText().equals("Dispatch System"))
					{
						layout.setCenter(DispatchMgr.getDispatchBorderPanel());
					}
					
					if(new_val.getText().equals("Schedule Manager"))
					{
						 layout.setCenter(ScheduleMgr.getScheduleVBox());
					}
					
					if(new_val.getText().equals("Instruction management"))
					{
						 layout.setCenter(InstructionsMgr.getInstructionBorderPanel());
					}
				}
			}
		);
    }

    
   
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = setBorder();
       
        settop(root);
        setleft(root);
        setcenter(root);
        
        Scene scene = new Scene(root,1280,680);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CTC System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
