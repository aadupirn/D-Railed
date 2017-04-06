package TrackController.events.impls;

import ctc.beanpnl.OptTrainBean;
import ctc.events.DispatchEvent;
import TrackController.events.DispatchListener;

public class DispatchListenerImpl implements DispatchListener{

	@Override
	public void dealDispatchEvent(DispatchEvent event) {
		// TODO 
		// please deal with the dispatch data!!!
		System.out.println("Accept Dispatch Event!!! Info is :");
		OptTrainBean opTrainBean = event.getOptTrain();
		System.out.println(opTrainBean.getTrainNo());
		System.out.println(opTrainBean.getLine());
		System.out.println(opTrainBean.getDepatureStation());
		System.out.println(opTrainBean.getDestination());
		System.out.println(opTrainBean.getDepatureTime());
		System.out.println(opTrainBean.getAuthority());
		System.out.println(opTrainBean.getAuthoritySequence().toString());
		System.out.println(opTrainBean.getSpeedSequence().toString());
		System.out.println("----------------------------------");
		
		// Please deal with my Dispatch above!
		
	}
}
