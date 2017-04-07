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
	int node85Seen = 0;
	int node100Seen = 0;
	int node77Seen = 0;
	boolean dir = true;


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
		blockLocation += iSpeed;

		if(block == null){
			System.out.println("ERROR");
		}

		while(block.getLength() < blockLocation)
		{
			blockLocation = blockLocation - block.getLength();

			//track.suggestTrainDirection(block, dir, block.getSwitch());

			if(node77Seen >= 1 && block.getBlockNumber() == 101) {
				block = track.getNextBlock(block.getLine(), block, false, train);
				node85Seen = -1;
				node100Seen = -1;
			}else if(node85Seen >= 1 && block.getBlockNumber() == 100){
				block = track.getNextBlock(block.getLine(), block, false, train);
				node100Seen++;
			}else if(node85Seen >= 1 && node100Seen >= 1 && node77Seen != 2){
				block = track.getNextBlock(block.getLine(), block, true, train);
			}else if(node85Seen <= 1 && block.getBlockNumber() != 100) {
				block = track.getNextBlock(block.getLine(), block, false, train);
				node85Seen++;
			}else if(block.canMoveToBlock(false)){
				block = track.getNextBlock(block.getLine(), block, false, train);
				if(block.getBlockNumber() == 77){
					node77Seen++;
				}
			}else if(block.canMoveToBlock(true)){
				block = track.getNextBlock(block.getLine(), block, true, train);
			}

		}
		mbo.setLocation(trainID, "Block: " + block);
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
