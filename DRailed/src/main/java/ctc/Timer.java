package ctc;

import javax.swing.JLabel;
import java.sql.Time;

public class Timer extends Thread {

	private JLabel label;

	int hour = 0;
	int min = 0;
	int sec = 0;

	public Timer(JLabel label) {
		super();
		this.label = label;
	}

	Time getTime() { return Time.valueOf(hour + ":" + min + ":" + sec); }
	int getHour() { return this.hour; }
	int getMin() {	return this.min; }
	int getSec() { return this.sec; }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			sec += 1;
			if (sec >= 60) {
				sec = 0;
				min += 1;
			}
			if (min >= 60) {
				min = 0;
				hour += 1;
			}
			showTime();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void showTime() {
		String strTime = "";
		if (hour < 10)
			strTime = "0" + hour + ":";
		else
			strTime = "" + hour + ":";

		if (min < 10)
			strTime = strTime + "0" + min + ":";
		else
			strTime = strTime + "" + min + ":";

		if (sec < 10)
			strTime = strTime + "0" + sec;
		else
			strTime = strTime + "" + sec;

		// display
		label.setText(strTime);
	}

}
