package ctc.controller.help.paint.bean;

import java.util.List;

public class Train {
	private String id;
	private String name;
	private Dot currLocation;
	private int sequeueIndex;
	private double currSpeed;
	private String state;  // wait,toStart,started,ended
	private List<String> sequeue;
	private List<Double> speeds;
	private String depatureTime;
	private String endTime;
	private String arrayCostTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Dot getCurrLocation() {
		return currLocation;
	}
	public void setCurrLocation(Dot currLocation) {
		this.currLocation = currLocation;
	}
	public int getSequeueIndex() {
		return sequeueIndex;
	}
	public void setSequeueIndex(int sequeueIndex) {
		this.sequeueIndex = sequeueIndex;
	}
	public double getCurrSpeed() {
		return currSpeed;
	}
	public void setCurrSpeed(double currSpeed) {
		this.currSpeed = currSpeed;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<String> getSequeue() {
		return sequeue;
	}
	public void setSequeue(List<String> sequeue) {
		this.sequeue = sequeue;
	}
	public List<Double> getSpeeds() {
		return speeds;
	}
	public void setSpeeds(List<Double> speeds) {
		this.speeds = speeds;
	}
	public String getDepatureTime() {
		return depatureTime;
	}
	public void setDepatureTime(String depatureTime) {
		this.depatureTime = depatureTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return "Train [id=" + id + ", name=" + name + ", currLocation="
				+ currLocation + ", sequeueIndex=" + sequeueIndex
				+ ", currSpeed=" + currSpeed + ", state=" + state
				+ ", sequeue=" + sequeue + ", speeds=" + speeds
				+ ", arrayCostTime=" + arrayCostTime + ", depatureTime="
				+ depatureTime + ", endTime=" + endTime + "]";
	}
}
