package ctc.controller.help.paint.mgr;

import java.util.List;

import ctc.controller.help.paint.bean.Dot;
import ctc.controller.help.paint.cache.RailwayCache;
import ctc.controller.help.paint.util.FileUtils;

public class DotMgr {
	public static void initDots() {
		RailwayCache.getGreen_dots().clear();
		RailwayCache.getRed_dots().clear();
		
		List<String> g_dotStrs = FileUtils.readTxtFile("g_dots.txt");
		for (String str : g_dotStrs) {
			Dot dot = getDotByStr(str);
			RailwayCache.getGreen_dots().add(dot);
		}
		
		List<String> r_dotStrs = FileUtils.readTxtFile("r_dots.txt");
		for (String str : r_dotStrs) {
			Dot dot = getDotByStr(str);
			RailwayCache.getRed_dots().add(dot);
		}
	}
	
	private static Dot getDotByStr(String str){
		String [] ss = str.split(",");
		String [] ss_0 = ss[0].split("\\(");
		String [] ss_1 = ss[1].split("\\)");
		Dot dot = new Dot(new Integer(ss_0[1]).intValue()-90 , new Integer(ss_1[0]).intValue(), ss_0[0]);
		return dot;
	}
}
