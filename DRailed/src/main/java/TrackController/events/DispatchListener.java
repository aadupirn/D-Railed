package TrackController.events;

import java.util.EventListener;
import ctc.events.DispatchEvent;

public interface DispatchListener extends EventListener {
	public void dealDispatchEvent(DispatchEvent event);
}
