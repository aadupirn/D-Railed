package ctc.events;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import ctc.beanpnl.OptTrainBean;
import TrackController.events.DispatchListener;

public class DispatchEventManager {
	private Collection listeners;

    /**
     * 添加事件
     * 
     * @param listener
     *            DoorListener
     */
    public void addDispatchListener(DispatchListener listener) {
        if (listeners == null) {
            listeners = new HashSet();
        }
        listeners.add(listener);
    }

    /**
     * 移除事件
     * 
     * @param listener
     *            DispatchListener
     */
    public void removeDispatchListener(DispatchListener listener) {
        if (listeners == null)
            return;
        listeners.remove(listener);
    }

    /**
     * 触发发车事件
     */
    protected void fireDispatchTrain(OptTrainBean opTrain) {
        if (listeners == null)
            return;
        DispatchEvent event = new DispatchEvent(this, opTrain);
        notifyListeners(event);
    }

    /**
     * 通知所有的DispatchListener
     */
    private void notifyListeners(DispatchEvent event) {
        Iterator iter = listeners.iterator();
        while (iter.hasNext()) {
        	DispatchListener listener = (DispatchListener) iter.next();
            listener.dealDispatchEvent(event);
        }
    }
}
