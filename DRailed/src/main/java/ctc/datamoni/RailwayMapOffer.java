package ctc.datamoni;

import java.util.ArrayList;
import java.util.List;

import ctc.beanpnl.Dot;
import ctc.beanpnl.Line;

public class RailwayMapOffer {
	
	public static List<Dot> offerDots(){
		List<Dot> dots = new ArrayList<Dot>();
		
		Dot dot1 = new Dot("E", 380,300);
		Dot dot2 = new Dot("F", 300,460);
		Dot dot3 = new Dot("G", 520,470);
		Dot dot4 = new Dot("H", 640,490);
		Dot dot5 = new Dot("I", 820,110);

		dots.add(dot1);
		dots.add(dot2);
		dots.add(dot3);
		dots.add(dot4);
		dots.add(dot5);
		
		return dots;
	}
	
	
	public static List<Line> offerLines(){
		List<Line> lines = new ArrayList<Line>();
		
		Dot dot1 = new Dot("E", 380,300);
		Dot dot2 = new Dot("F", 300,460);
		Dot dot3 = new Dot("G", 520,470);
		Dot dot4 = new Dot("H", 640,490);
		Dot dot5 = new Dot("I", 820,110);


		Line line1 = new Line("5",dot1, dot2);
		Line line2 = new Line("6",dot2, dot3);
		Line line3 = new Line("7",dot3, dot4);
		Line line4 = new Line("8",dot4, dot5);
		
		lines.add(line1);
		lines.add(line2);
		lines.add(line3);		
		lines.add(line4);
		
		return lines;
	}
}
