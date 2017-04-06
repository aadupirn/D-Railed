package TrainController;

public class ControlCalculator
{
	//region Class Variables
	private double previousSpeed;
	private double desiredSpeed;
	private double currentSpeed;
	private double previousUK = 0;
	private double previousError = 0;
	private double previousCommand;
	private double currentCommand;
	private double kp;
	private double ki;
	private double authority;
	private double powerLimit;
	//endregion

	//region Constructor

	public ControlCalculator(double iDesiredSpeed, double ikp, double iki)
	{
		powerLimit = 120000;
		desiredSpeed = iDesiredSpeed;
		previousCommand = 0;
		previousError = 0;
		previousUK = 0;
		previousSpeed = 0;
		kp = ikp;
		ki = iki;
	}

	//endregion


	//region Methods
	public double computeNextCommand(double iCurrentSpeed)
	{
		currentSpeed = iCurrentSpeed;
		double difference = desiredSpeed - currentSpeed;
		double UK = previousUK + (.5)*(difference+previousError);
		currentCommand = (kp*difference) + (ki*UK);
		if(currentCommand > powerLimit)
		{
			currentCommand = powerLimit;
		}
		else if(currentCommand < 0)
		{
			currentCommand = 0;
		}

		previousError = difference;
		previousUK = UK;
		return currentCommand;
	}

	public void setKP(double ikp)
	{
		kp = ikp;
	}

	public void setKI(double iki)
	{
		ki = iki;
	}

	public void setDesiredSpeed(double iSpeed)
	{
		desiredSpeed = iSpeed;
	}

	//endregion
}
