package ctc.panels;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ctc.beanpnl.ScheduleBean;
import ctc.controller.ScheduleCtrl;

public class ScheduleMgr {
	private  static VBox vblast;
	private static TableView<ScheduleBean> tableview = new TableView<>();
	
	public static VBox getScheduleVBox(){
		return createVBox(ScheduleCtrl.getList());
	}
	
	 /**
     * 树节点1的第三个按钮的页面设置
     * @return 返回一个竖直方向的布局
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private static VBox createVBox(final ObservableList<ScheduleBean> list){
    	if(vblast != null)
    	{
    		tableview.setItems(list);
    		tableview.refresh();
    		return vblast;
    	}
    	
        //中间一行
        VBox centervb = new VBox(5);
        centervb.setPadding(new Insets(15, 12, 5, 12));
       
        Label labelct = new Label("Condition:");  //外面标题
        labelct.setFont(new Font(15));
       
        HBox center_hb = new HBox(5);
        center_hb.setPadding(new Insets(5, 12, 15, 0));
       
        Label label1 = new Label("train No.:");
        Label label2 = new Label("departure station:");
        Label label3 = new Label("destination station:");
       
        TextField tf1= new TextField();
        tf1.setPrefColumnCount(10);  //设置偏爱的文本框宽度
        TextField tf2= new TextField();
        tf2.setPrefColumnCount(10);  //设置偏爱的文本框宽度
        TextField tf3= new TextField();
        tf3.setPrefColumnCount(10);  //设置偏爱的文本框宽度
       
        Button btnquery = new Button("Query");
        btnquery.setPrefSize(60, 20);
      
        center_hb.getChildren().addAll(label1,tf1,label2,tf2,label3,tf3,btnquery);
        centervb.getChildren().addAll(labelct,center_hb);
        
        //下面表格       
        TableColumn clo1 = new TableColumn();
        clo1.setText("Train No.");
        clo1.setCellValueFactory(new PropertyValueFactory("MMQBH"));
        clo1.setMinWidth(100);
//        tableview.getColumns().add(clo1);
        TableColumn clo2 = new TableColumn();
        clo2.setText("Train type");
        clo2.setCellValueFactory(new PropertyValueFactory("SCCS"));
        clo2.setMinWidth(100);
//        tableview.getColumns().add(clo2);
        TableColumn clo3 = new TableColumn();
        clo3.setText("Departure station");
        clo3.setCellValueFactory(new PropertyValueFactory("ZT"));
        clo3.setMinWidth(100);
//        tableview.getColumns().add(clo3);
        TableColumn clo4 = new TableColumn();
        clo4.setText("Destination station");
        clo4.setCellValueFactory(new PropertyValueFactory("KHH"));
        clo4.setMinWidth(100);
//        tableview.getColumns().add(clo4);
        TableColumn clo5 = new TableColumn();
        clo5.setText("Section");
        clo5.setCellValueFactory(new PropertyValueFactory("KHMC"));
        clo5.setMinWidth(100);
//        tableview.getColumns().add(clo5);
        TableColumn clo6 = new TableColumn();
        clo6.setText("Blocks");
        clo6.setCellValueFactory(new PropertyValueFactory("BHFX"));
        clo6.setMinWidth(100);
//        tableview.getColumns().add(clo6);
        TableColumn clo7 = new TableColumn();
        clo7.setText("Speeds");
        clo7.setCellValueFactory(new PropertyValueFactory("FXRQ"));
        clo7.setMinWidth(100);
//        tableview.getColumns().add(clo7);
        TableColumn clo8 = new TableColumn();
        clo8.setText("Departure time");
        clo8.setCellValueFactory(new PropertyValueFactory("CJR"));
        clo8.setMinWidth(100);
//        tableview.getColumns().add(clo8);
     
        tableview.setItems(list);
        tableview.getColumns().addAll(clo1,clo2,clo3,clo4,clo5,clo6,clo7,clo8);
//        tableview.setEditable(true);
        
        VBox tavbox = new VBox();
        tavbox.setSpacing(5);
        tavbox.setPadding(new Insets(10, 10, 10, 10));
        tavbox.getChildren().addAll(tableview);
        
        
        //分割线
        Separator sper1 = new Separator();
        sper1.setOrientation(Orientation.HORIZONTAL);
        Separator sper2 = new Separator();
        sper2.setOrientation(Orientation.HORIZONTAL);
        
        vblast = new VBox();
       // vblast.getChildren().addAll(tophb,sper1,centervb,sper2,tavbox,addbox);
       // vblast.getChildren().addAll(sper1,centervb,sper2,tavbox,addbox);
        vblast.getChildren().addAll(sper1,centervb,sper2,tavbox);
        return vblast;
    }
   
  
  
}
