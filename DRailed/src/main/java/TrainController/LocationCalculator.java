package TrainController;

import TrackModel.Model.Block;
import TrackModel.Track;
import MBO.java.MBO;

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
	private MBO mbo;
	int trainID;

	//endregion

	//region Constructors

	public LocationCalculator(Track iTrack, String iLine, int startingBlock, int iTrainID)
	{
		track = iTrack;
		line = iLine;
		block = track.getFromYardBlock(line);
		trainID = iTrainID;
		mbo = new MBO(1);
	}

	//endregion

	//region methods
	public void ComputeNextLocation(double iSpeed)
	{
		blockLocation += iSpeed;
		while(block.getLength() < blockLocation)
		{
			blockLocation = blockLocation - block.getLength();
			block = track.getNextBlock(block.getLine(), block, false);

		}
		mbo.setLocation(trainID, "Block: " + block);
		System.out.println("We are on block " + block.getBlockNumber()+"\n" +
				"Meters we have traveled along block: " + blockLocation);
	}

	public void setMBO(MBO imbo)
	{
		mbo = imbo;
	}
	//endregion

}
