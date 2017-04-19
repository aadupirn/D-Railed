package ctc.controller.help.paint.mgr;

import java.util.List;

import ctc.controller.help.paint.bean.Dot;
import ctc.controller.help.paint.bean.Line;
import ctc.controller.help.paint.bean.Train;
import ctc.controller.help.paint.cache.RailwayCache;

public class TrainMgr {
	public static void initTrain() {
		RailwayCache.getGreen_trains().clear();
		RailwayCache.getRed_trains().clear();
	}
	
	public static void addSchedule(String whichLine, int num, String traces, String speeds, String depatureTime){
		if (whichLine.equals("green") || whichLine.equals("g")) 
		{
			String[] ttraceSeqs = traces.split(" ");
			String[] tspeeds = speeds.split(" ");
			
			String id = "g_t_" + RailwayCache.getGreen_trains().size();
			Dot localtion = getStartLocaltion(traces, whichLine);
			Dot endDot = getEndDot(traces, whichLine);
			String state = "wait";
			String [] trs = traces.split("--");
			Dot nextDot = getEndDotByLine(trs[0], whichLine);

		}
		
		if (whichLine.equals("red") || whichLine.equals("r")) 
		{
			
		}
	}
	
	
	private static Dot getStartLocaltion(String traces,String whichLine){
		String [] trs = traces.split("--");
		
		Dot dot = null;
		List<Line> lines = null;
		if (whichLine.equals("green")) {
			lines = RailwayCache.getGreen_lines();
		}
		else {
			lines = RailwayCache.getRed_lines();
		}
		

		for (Line l: lines) 
		{
			if(l.getName().equals(trs[0]))
			{
				dot = l.getStartDot();
			}
		}
		return dot;
	}
	
	private static Dot getEndDot(String traces,String whichLine){
		String [] trs = traces.split("--");
		
		Dot dot = null;
		List<Line> lines = null;
		if (whichLine.equals("green")) {
			lines = RailwayCache.getGreen_lines();
		}
		else {
			lines = RailwayCache.getRed_lines();
		}
		

		for (Line l: lines) 
		{
			if(l.getName().equals(trs[trs.length-1]))
			{
				dot = l.getEndDot();
			}
		}
		return dot;
	}
	
	private static Dot getEndDotByLine(String line,String whichLine){
		Dot dot = null;
		List<Line> lines = null;
		if (whichLine.equals("green")) {
			lines = RailwayCache.getGreen_lines();
		}
		else {
			lines = RailwayCache.getRed_lines();
		}
		

		for (Line l: lines) 
		{
			if(l.getName().equals(line))
			{
				dot = l.getEndDot();
			}
		}
		return dot;
	}

	public static void changeTrainState(String nowTime) {
		List<Train> g_trains = RailwayCache.getGreen_trains();
		List<Train> r_trains = RailwayCache.getRed_trains();
		
		for (Train g_tTrain : g_trains) 
		{
			if (g_tTrain.getState().equals("wait")) {
				if(nowTime.equals(g_tTrain.getDepatureTime()) )
				{
					g_tTrain.setState("toStart");
				}
			}
		}
		
		for (Train r_tTrain : r_trains) 
		{
			if (r_tTrain.getState().equals("wait")) {
				if(nowTime.equals(r_tTrain.getDepatureTime()))
				{
					r_tTrain.setState("toStart");
				}
			}
		}
	}
}
