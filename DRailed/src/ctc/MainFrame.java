package ctc;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;



import javax.swing.ButtonGroup;
//import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import ctc.bean.Schedule;
import ctc.bean.Trace;
import ctc.util.ExcelUtil;
import ctc.util.ScheduleUtil;

import java.awt.Font;


public class MainFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 404422147759689411L;

	private JPanel contentPane;
	
	private JButton btnDispatch;
	
	private JButton btnUpdateAuthority;
	
	private JButton btnCloseopenTrack;
	
	private JButton btnUpdateSpeed;
	
	private JButton btnReadInSchedule;
	
	private JRadioButton radioButton1;
	
	private JRadioButton radioButton2;
	
	List<Schedule> resultList = new ArrayList<Schedule>();
	public JLabel label;
	
	List<Trace> redTraces = new ArrayList<Trace>();
	List<Trace> greenTraces = new ArrayList<Trace>();
	private boolean importBl=false;
	
	public JTextArea textArea;
	private JLabel lblCtcMonitoringSystem;
	//private JLabel lblGreen;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					deleteFile();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void deleteFile(){
		File scheduleFile = new File("schedule.txt");
		scheduleFile.delete();
		
		File eventFile = new File("newEvent.txt");
		eventFile.delete();
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 0, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnDispatch = new JButton("Dispatch");
		btnDispatch.setBounds(0, 551, 201, 31);
		contentPane.add(btnDispatch);
		
		btnUpdateAuthority = new JButton("Update Authority");
		btnUpdateAuthority.setBounds(0, 581, 201, 31);
		contentPane.add(btnUpdateAuthority);
		
		btnCloseopenTrack = new JButton("Close/Open Track");
		btnCloseopenTrack.setBounds(0, 611, 201, 31);
		contentPane.add(btnCloseopenTrack);
		
		btnUpdateSpeed = new JButton("Update Speed");
		btnUpdateSpeed.setBounds(0, 640, 201, 31);
		contentPane.add(btnUpdateSpeed);
		
		// zyx     
		radioButton1 = new JRadioButton("Manual"); // Radio Button
		radioButton1.setBounds(20, 520, 83, 31);
		contentPane.add(radioButton1); // 
        
        radioButton2 = new JRadioButton("Automatic");// 
        radioButton2.setBounds(120, 520, 83, 31);
        contentPane.add(radioButton2);// 
        
        
        ButtonGroup group = new ButtonGroup();// 
        group.add(radioButton1); // 
        group.add(radioButton2); // Radio Button Group
		
		
		btnReadInSchedule = new JButton("Read in Schedule");
		btnReadInSchedule.setBounds(0, 551, 201, 31);
		contentPane.add(btnReadInSchedule);
		
		btnDispatch.addActionListener(this);
		btnUpdateAuthority.addActionListener(this);
		btnCloseopenTrack.addActionListener(this);
		btnUpdateSpeed.addActionListener(this);
		btnReadInSchedule.addActionListener(this);
		
		
		textArea = new JTextArea();
		//contentPane.add(textArea);
		//contentPane.add(textArea);
		textArea.setBounds(205, 523, 786, 148);
		
		JScrollPane scroll = new JScrollPane(textArea); 
		scroll.setSize(786, 148);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		scroll.setLocation(205, 523);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		contentPane.add(scroll);
		
		//lblGreen = new JLabel("Green -------------------- Red ------------------- Switches");
		//scroll.setColumnHeaderView(lblGreen);
		
		label = new JLabel("00:00:00");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Arial", Font.PLAIN, 15));
		//Border border = BorderFactory.createLineBorder(Color.red);
		label.setBorder(null);
		label.setBounds(894, 12, 85, 23);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(label);
		
		lblCtcMonitoringSystem = new JLabel("CTC Monitoring System");
		lblCtcMonitoringSystem.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCtcMonitoringSystem.setForeground(Color.WHITE);
		lblCtcMonitoringSystem.setBounds(14, 13, 232, 18);
		contentPane.add(lblCtcMonitoringSystem);
		
		JTextArea trackImg = new JTextArea();
		trackImg.setBackground(Color.DARK_GRAY);
		trackImg.setBounds(0, 2, 994, 516);
		contentPane.add(trackImg);
		Timer timer = new Timer(label);//compute Timer
		timer.start();
		
		Departure departure = new Departure(this);
		departure.start();
		
		
		this.redTraces = ExcelUtil.readTrace("Track_Layout_Vehicle_Data_vF1.xlsx", "red");//Red Trace
		this.greenTraces = ExcelUtil.readTrace("Track_Layout_Vehicle_Data_vF1.xlsx", "green");//Green Trace
		
		
		//  
		btnReadInSchedule.setVisible(false);
		radioButton1.setSelected(true);
		class ActionTry implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == radioButton1) {
					textArea.setText("");
					btnReadInSchedule.setVisible(false);
					btnDispatch.setVisible(true);
					btnUpdateAuthority.setVisible(true);
					btnCloseopenTrack.setVisible(true);
					btnUpdateSpeed.setVisible(true);
				}
				if (e.getSource() == radioButton2) {
					textArea.setText("");
					btnReadInSchedule.setVisible(true);
					btnDispatch.setVisible(false);
					btnUpdateAuthority.setVisible(false);
					btnCloseopenTrack.setVisible(false);
					btnUpdateSpeed.setVisible(false);
				}
			}
		}

        ActionTry select=new ActionTry();
        radioButton1.addActionListener(select);
        radioButton2.addActionListener(select);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		if(resultList!=null && resultList.size()>0){
			for(Schedule s:resultList){
				System.out.println(s.toString());
			}
		}*/
		
		if(e.getSource()==this.btnReadInSchedule){
			int m = JOptionPane.showConfirmDialog(null, "Loading Schedule from file: schedule.xlsx","Confirm",JOptionPane.OK_CANCEL_OPTION);
			//System.out.println(m);
			if(m==0){
				if(importBl){
					JOptionPane.showMessageDialog(null, "schedule.xlsx Was Imported Successfully", "INFORMATION",JOptionPane.INFORMATION_MESSAGE);
				    return;
				}
				List<Schedule> list = ExcelUtil.readSchedule("schedule.xlsx");
				//int i = 1;
				for(Schedule s :list){
					String content = s.getLine()+" "+s.getAuthority()+" "+s.getAuthsequence().replaceAll(",", " ")
							+" "+ s.getSpeedsequence().replaceAll(",", " ");
					ScheduleUtil.saveTxt(content);
					//System.out.println(s.toString());
					//i++;
					this.resultList.add(s);
					
				}
				importBl = true;
				JOptionPane.showMessageDialog(null, "Import schedule.xlsx Successfully", "INFORMATION",JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getSource()==this.btnDispatch){
			DispatchFrame frame = new  DispatchFrame(resultList);
			frame.setVisible(true);			
			
		}else if(e.getSource()==this.btnUpdateAuthority){
			//this.dispose();
			UpdateAuthorityFrame frame = new  UpdateAuthorityFrame(resultList);
			frame.setVisible(true);
		}else if(e.getSource()==this.btnCloseopenTrack){
			CloseopenTrackFrame  frame = new CloseopenTrackFrame(this.redTraces,this.greenTraces);
			frame.setVisible(true);
		}else if(e.getSource()==this.btnUpdateSpeed){
			UpdateSpeedFrame  frame = new UpdateSpeedFrame(this.redTraces,this.greenTraces);
			frame.setVisible(true);
		}
		
	}
}
