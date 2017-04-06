package ctc.beanpnl;

import java.io.IOException;
import java.util.List;

import TrainModel.Train;

public class OptTrainBean extends Train{
	
	public OptTrainBean(int blockLocation, int numberOfCarts,
			Double newAuthority, Double newSpeed, int newID) throws IOException 
	{
		// 这个几个参数值,blockLocation是当前位置么，newSpeed是当前速度么，numberOfCarts是车节数么
		super(blockLocation, numberOfCarts, newAuthority, newSpeed, newID);
	}

	// 新增以下属性
	private String trainNo;   // 列车编号
	private String line;   // green还是Red
	private int authority;
	private String depatureTime;
	private String depatureBlock;   // 
	private String destination;
	
	
	
	public String getTrainNo() {
		return trainNo;
	}



	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}


	public String getLine() {
		return line;
	}



	public void setLine(String line) {
		this.line = line;
	}



	public int getAuthority() {
		return authority;
	}



	public void setAuthority(int authority) {
		this.authority = authority;
	}


	public String getDepatureTime() {
		return depatureTime;
	}



	public void setDepatureTime(String depatureTime) {
		this.depatureTime = depatureTime;
	}



	public String getDepatureBlock() {
		return depatureBlock;
	}



	public void setDepatureBlock(String depatureBlock) {
		this.depatureBlock = depatureBlock;
	}



	public String getDestination() {
		return destination;
	}



	public void setDestination(String destination) {
		this.destination = destination;
	}



	@Override
	public String toString() {
		return trainNo;
	}
}
