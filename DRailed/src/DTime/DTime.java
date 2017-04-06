package DTime;

import TrainController.TrainController;
import javafx.concurrent.Task;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by aadu on 4/3/17.
 */
public class DTime
{
	private int intervalMS = 1000;
	private TrainController trainController;

	Timer timer;

	class DRailedTask extends TimerTask
	{
		private int counter = 0;
		public void run()
		{
			System.out.println("test");
			counter++;
			trainController.Update();
			if(counter == 60)
			{
				timer.cancel();
			}
		}
	}

	public DTime(TrainController iTrainController)
	{
		intervalMS = 1000;
		trainController = iTrainController;
		timer = new Timer();
		timer.schedule(new DRailedTask(), 0, intervalMS);
		/*Task<Void> task = new Task<Void>()
		{
			@Override public Void call() throws InterruptedException {
				int counter = 0;
				while(counter<30)
				{
					counter++;
					Step();
					Thread.sleep(intervalMS);
				}


				return null;
			}
		};

		Thread thread = new Thread(task);*/
	}


	private void Step()
	{
		trainController.Update();
	}
}
