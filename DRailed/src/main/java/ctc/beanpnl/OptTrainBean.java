package ctc.beanpnl;

import java.io.IOException;
import java.util.List;

import TrainModel.Train;

public class OptTrainBean extends Train{
	
	public OptTrainBean(int blockLocation, int numberOfCarts,
			Double newAuthority, Double newSpeed, int newID) throws IOException 
	{
		// �����������ֵ,blockLocation�ǵ�ǰλ��ô��newSpeed�ǵ�ǰ�ٶ�ô��numberOfCarts�ǳ�����ô
		super(blockLocation, numberOfCarts, newAuthority, newSpeed, newID);
	}

	// ������������
	private String trainNo;   // �г����
	private String line;   // green����Red
	private int authority;
	private List<String> authoritySequence;  
	private List<String> speedSequence;
	private String depatureTime;
	private String depatureStation;   // 
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



	public List<String> getAuthoritySequence() {
		return authoritySequence;
	}



	public void setAuthoritySequence(List<String> authoritySequence) {
		this.authoritySequence = authoritySequence;
	}



	public List<String> getSpeedSequence() {
		return speedSequence;
	}



	public void setSpeedSequence(List<String> speedSequence) {
		this.speedSequence = speedSequence;
	}



	public String getDepatureTime() {
		return depatureTime;
	}



	public void setDepatureTime(String depatureTime) {
		this.depatureTime = depatureTime;
	}



	public String getDepatureStation() {
		return depatureStation;
	}



	public void setDepatureStation(String depatureStation) {
		this.depatureStation = depatureStation;
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
