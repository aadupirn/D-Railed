package ctc.controller.help.paint.bean;

public class Line {
	private Dot startDot;
	private Dot endDot;
	private double distance;
	private String name;
	public Dot getStartDot() {
		return startDot;
	}
	public void setStartDot(Dot startDot) {
		this.startDot = startDot;
	}
	public Dot getEndDot() {
		return endDot;
	}
	public void setEndDot(Dot endDot) {
		this.endDot = endDot;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Line(Dot startDot, Dot endDot, double distance, String name) {
		super();
		this.startDot = startDot;
		this.endDot = endDot;
		this.distance = distance;
		this.name = name;
	}
		
}
