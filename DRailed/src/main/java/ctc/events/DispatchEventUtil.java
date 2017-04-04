package ctc.events;

import ctc.beanpnl.OptTrainBean;
import TrackController.events.impls.DispatchListenerImpl;

public class DispatchEventUtil {
	private static DispatchEventManager manager = new DispatchEventManager();
	
	public static void init() {
        manager.addDispatchListener(new DispatchListenerImpl());// 增加监听器
    }
	
	public static void sentDispatchEvent(OptTrainBean opTrain){
        // 发车
        manager.fireDispatchTrain(opTrain);
	}
}
