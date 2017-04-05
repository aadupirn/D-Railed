package TrainController;

public class ControlCalculator
{
	//region Class Variables
	private double previousSpeed;
	private double currentSpeed;
	private double previousCommand;
	private double currentCommand;
	private double kp;
	private double ki;
	private double authority;
	private double powerLimit;
	//endregion

	//region Constructor

	public ControlCalculator(double iPowerLimit, double ikp, double iki)
	{
		powerLimit = iPowerLimit;
		kp = ikp;
		ki = iki;
	}

	//endregion


	//region Methods
	public double computeNextCommand()
	{
		return 10;
	}

	public void setKP(double ikp)
	{
		kp = ikp;
	}

	public void setKI(double iki)
	{
		ki = iki;
	}

	//endregion
}
