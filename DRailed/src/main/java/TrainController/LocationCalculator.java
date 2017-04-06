package TrainController;

import TrackModel.Model.Block;
import TrackModel.Track;

/**
 * Created by aadu on 4/3/17.
 */
public class LocationCalculator
{
	//region Class Variables

	private Block block;
	private String line;
	private Track track;
	private double blockLocation;

	//endregion

	//region Constructors

	public LocationCalculator(Track iTrack, String iLine, int startingBlock)
	{
		track = iTrack;
		line = iLine;
		block = track.getFromYardBlock(line);
	}

	//endregion

	//region methods
	public void ComputeNextLocation(double iSpeed)
	{
		blockLocation += iSpeed;
		while(block.getLength() < blockLocation)
		{
			blockLocation = blockLocation - block.getLength();
			block = block.getNextBlock(false);
		}
		System.out.println("We are on block " + block.getBlockNumber()+"\n" +
				"Meters we have traveled along block: " + blockLocation);
	}
	//endregion

}
