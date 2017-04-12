package DTime;

import TrackController.TrackController;
import TrackModel.TrackModelGUI;
import TrainController.TrainController;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import MBO.java.MBO;

/**
 * Created by aadu on 4/3/17.
 */
public class DTime
{
	private int multiplier;
	private ArrayList<TrainController> trainControllers;
	private TrackModelGUI trackModelGUI;
	private ArrayList<TrackController> trackControllers;
	private MBO mbo;

	Timer timer;

	class DRailedTask extends TimerTask
	{
		public void run()
		{
			for(TrainController tc: trainControllers)
			{
				tc.update();
			}
			for(TrackController tc: trackControllers)
			{
				tc.Update();
			}
			//if(trackModelGUI!= null)
				//trackModelGUI.update();
		}
	}

	public DTime()
	{
		multiplier = 10;
		int intervalMS = 1000/multiplier;
		trainControllers = new ArrayList<TrainController>();
		trackControllers = new ArrayList<TrackController>();
		timer = new Timer();
		timer.schedule(new DRailedTask(), 0, intervalMS);
	}

	public void addTC(TrainController itc)
	{
		trainControllers.add(itc);
		setMBO(mbo);
	}

	public void addTrackC(TrackController itc)
	{
		trackControllers.add(itc);
	}

	public void addTMGUI(TrackModelGUI itmgui)
	{
		trackModelGUI = itmgui;
	}

	public void setMBO(MBO imbo)
	{
		mbo = imbo;
		for(TrainController tc: trainControllers)
		{
			tc.setMBO(mbo);
		}
	}
}
