package TrainController;

import TrackModel.Model.Block;
import TrackModel.Track;
import MBO.java.MBO;
import TrainModel.Train;

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
	private Train train;
	int trainID;
	boolean dir = true;
	int redirect = 0;

	//endregion

	//region Constructors

	public LocationCalculator(Train iTrain, Track iTrack, String iLine, int startingBlock, int iTrainID)
	{
		track = iTrack;
		train = iTrain;
		line = iLine;
		block = track.getFromYardBlock(line);
		trainID = iTrainID;
		mbo = new MBO(1);
	}

	//endregion

	//region methods
	public Block ComputeNextLocation(double iSpeed)
	{

		//blockLocation += iSpeed;
		// TODO: Remove for non-testing
		blockLocation += iSpeed*20;

		if(block == null){
			System.out.println("ERROR");
		}

		while(block.getLength() < blockLocation)
		{
			blockLocation = blockLocation - block.getLength();

			System.out.println("Direction Before:" + dir);
			if(redirect == 0) {
				dir = block.canMoveToBlock(dir);
			}else{
				redirect = 0;
			}
			System.out.println("Direction After:" + dir);

			int switchNum = block.getNextSwitchBlockNumber();
			boolean redir = block.getNextSwitchRedirect();

			block = track.getNextBlock(block.getLine(), block, dir, train);

			if(block.getBlockNumber() == switchNum && dir != redir){
				dir = redir;
				System.out.println("REDIRECT: " + dir);
				redirect = 1;
			}


		}

		mbo.setLocation(trainID, line, "Block: " + block);

		System.out.println("We are on block " + block.getBlockNumber()+"\n" +
				"Meters we have traveled along block: " + blockLocation);
		return block;
	}

	public void setMBO(MBO imbo)
	{
		mbo = imbo;
	}
	//endregion

}