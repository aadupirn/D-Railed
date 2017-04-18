package ctc.controller.help.paint.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ctc.controller.help.paint.bean.Dot;
import ctc.controller.help.paint.bean.Line;
import ctc.controller.help.paint.bean.Train;

public class RailwayCache {
	private static List<Dot> green_dots = new LinkedList<Dot>(); 
	private static List<Dot> red_dots = new LinkedList<Dot>();
	
	private static List<Line> green_lines = new LinkedList<Line>(); 
	private static List<Line> red_lines = new LinkedList<Line>();
	
	private static List<Train> green_trains = new LinkedList<Train>(); 
	private static List<Train> red_trains = new LinkedList<Train>();
	
	public static Map<String,String> red_line_occup = new HashMap<String,String>();
	public static Map<String,String> green_line_occup = new HashMap<String,String>();
	
	public static List<Dot> getGreen_dots() {
		return green_dots;
	}
	public static List<Dot> getRed_dots() {
		return red_dots;
	}
	public static List<Line> getGreen_lines() {
		return green_lines;
	}
	public static List<Line> getRed_lines() {
		return red_lines;
	}
	public static List<Train> getGreen_trains() {
		return green_trains;
	}
	public static List<Train> getRed_trains() {
		return red_trains;
	}
}
