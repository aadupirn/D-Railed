package TrainController;

import TrackModel.Track;

/**
 * Created by aadu on 4/3/17.
 */
public class LocationCalculator
{
	//region Class Variables

	private int currentBlockID;
	private Track track;
	private double currentBlockLength;
	private double currentSpeed;
	private int blockLocation;
	private boolean atStation;

	//endregion

	//region Constructors

	public LocationCalculator()
	{
		track = new Track();
	}

	//endregion

	//region methods
	public void ComputeNextLocation(double speed)
	{

	}
	//endregion

}
