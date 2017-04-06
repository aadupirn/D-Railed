package TrackController.events;

import ctc.beanpnl.OptTrainBean;

public class DispatchDealOfTrackController {

	public static void dealDispatchEvent(OptTrainBean opTrainBean) {
		// TODO 
		
		// please deal with the dispatch data!!!
		System.out.println("Accept Dispatch Event!!! Info is :");
		System.out.println(opTrainBean.getTrainNo());
		System.out.println(opTrainBean.getLine());
		System.out.println(opTrainBean.getDepatureBlock());
		System.out.println(opTrainBean.getDestination());
		System.out.println(opTrainBean.getDepatureTime());
		System.out.println(opTrainBean.getAuthority());
		System.out.println("----------------------------------");
		
		// Please deal with my Dispatch above!
		
	}
}
