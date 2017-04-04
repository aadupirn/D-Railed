package ctc.events;

import java.util.EventObject;

import ctc.beanpnl.OptTrainBean;

public class DispatchEvent extends EventObject {
	private static final long serialVersionUID = 6496098798146410884L;
    private OptTrainBean optTrain; // 事件实体参数
    
	public DispatchEvent(Object arg0) {
		super(arg0);
	}

	 public DispatchEvent(Object source, OptTrainBean optTrain) {
	        super(source);
	        this.optTrain = optTrain;
	    }

	    public void setOptTrain(OptTrainBean optTrain) {
	        this.optTrain = optTrain;
	    }

	    public OptTrainBean getOptTrain() {
	        return this.optTrain;
	    }
}
