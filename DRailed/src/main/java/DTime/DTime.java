package DTime;

import TrainController.TrainController;

import java.util.Timer;

/**
 * Created by aadu on 4/3/17.
 */
public class DTime
{
	private int intervalMS = 1000;
	private TrainController trainController;

	public DTime(TrainController iTrainController)
	{
		intervalMS = 1000;
		trainController = iTrainController;
	}

	public void Run() throws InterruptedException
	{
		while(true)
		{
			Step();
			Thread.sleep(intervalMS);

		}
	}

	private void Step()
	{
		trainController.Update();
	}
}
