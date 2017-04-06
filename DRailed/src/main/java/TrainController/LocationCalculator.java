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
	int node85Seen = 0;
	int node100Seen = 0;
	int node77Seen = 0;

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
	public Block ComputeNextLocation(double iSpeed)
	{
		blockLocation += iSpeed;

		if(block == null){
			System.out.println("ERROR");
		}

		while(block.getLength() < blockLocation)
		{
			blockLocation = blockLocation - block.getLength();

			if(node77Seen >= 1 && block.getBlockNumber() == 101) {
				block = track.getNextBlock(block.getLine(), block, false);
				System.out.println("101 MOVE DOWN");
				node85Seen = -1;
				node100Seen = -1;
			}else if(node85Seen >= 1 && block.getBlockNumber() == 100){
				System.out.println("100 MOVE DOWN ONE");
				block = track.getNextBlock(block.getLine(), block, false);
				node100Seen++;
			}else if(node85Seen >= 1 && node100Seen >= 1 && node77Seen != 2){
				System.out.println("MOVE UP");
				block = track.getNextBlock(block.getLine(), block, true);
			}else if(node85Seen <= 1 && block.getBlockNumber() != 100) {
				System.out.println("85 ONCE MOVE DOWN");
				block = track.getNextBlock(block.getLine(), block, false);
				node85Seen++;
			}else if(block.canMoveToBlock(false)){
				System.out.println("MOVE DOWN");
				block = block.getNextBlock(false);
				if(block.getBlockNumber() == 77){
					node77Seen++;
				}
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
