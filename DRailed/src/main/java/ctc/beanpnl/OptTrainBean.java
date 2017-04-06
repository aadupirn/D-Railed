package ctc.beanpnl;

import java.io.IOException;
import java.util.List;

import TrainModel.Train;

public class OptTrainBean extends Train{
	
	public OptTrainBean(int blockLocation, int numberOfCarts,
			int newAuthority, Double newSpeed, int newID) throws Exception
	{
		// �����������ֵ,blockLocation�ǵ�ǰλ��ô��newSpeed�ǵ�ǰ�ٶ�ô��numberOfCarts�ǳ�����ô
		super(blockLocation, numberOfCarts, newAuthority, newSpeed, newID);
	}

	// ������������
	private String trainNo;   // �г����
	private String line;   // green����Red
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
