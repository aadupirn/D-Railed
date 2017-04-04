package ctc.events;

import ctc.beanpnl.OptTrainBean;
import TrackController.events.impls.DispatchListenerImpl;

public class DispatchEventUtil {
	private static DispatchEventManager manager = new DispatchEventManager();
	
	public static void init() {
        manager.addDispatchListener(new DispatchListenerImpl());// ���Ӽ�����
    }
	
	public static void sentDispatchEvent(OptTrainBean opTrain){
        // ����
        manager.fireDispatchTrain(opTrain);
	}
}
