package ctc.controller.help.paint.mgr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ctc.controller.help.paint.bean.Dot;
import ctc.controller.help.paint.bean.Line;
import ctc.controller.help.paint.cache.RailwayCache;
import ctc.controller.help.paint.util.FileUtils;

public class LineMgr {
	public static void initLines() {
		RailwayCache.getGreen_lines().clear();
		RailwayCache.getRed_lines().clear();
		
		Map<String,Dot> g_dotsMap = new HashMap<String,Dot>();
		for (Dot dot : RailwayCache.getGreen_dots()) {
			g_dotsMap.put(dot.getName(), dot);
		}
		
		Map<String,Dot> r_dotsMap = new HashMap<String,Dot>();
		for (Dot dot : RailwayCache.getRed_dots()) {
			r_dotsMap.put(dot.getName(), dot);
		}
		
		List<String> g_lineStrs = FileUtils.readTxtFile("g_lines.txt");
		for (String str : g_lineStrs) {
			Line line = getLineByStr(str, g_dotsMap);
			RailwayCache.getGreen_lines().add(line);
		}
		
		List<String> r_lineStrs = FileUtils.readTxtFile("r_lines.txt");
		for (String str : r_lineStrs) {
			Line line = getLineByStr(str, r_dotsMap);
			RailwayCache.getRed_lines().add(line);
		}
	}
	
	private static Line getLineByStr(String str, Map<String,Dot>dotsMap){
		String [] s = str.split("\\|");
		String [] ss = s[0].split("-->");
		Dot begin = dotsMap.get(ss[0]);
		Dot end = dotsMap.get(ss[1]);
		
		double dis = countDistance(begin,end);
		Line line = new Line(begin, end, dis, s[1]);
		return line;
	}
	
	private static double countDistance(Dot begin, Dot end){
		double d = (end.getX()-begin.getX())*(end.getX()-begin.getX()) - (end.getY()-begin.getY())*(end.getY()-begin.getY());
		return Math.sqrt(d);
	}
}
