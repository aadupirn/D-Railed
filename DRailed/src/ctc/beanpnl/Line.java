package ctc.beanpnl;

public class Line {
	private String id;
	private Dot strat;
	private Dot end;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Dot getStrat() {
		return strat;
	}
	public void setStrat(Dot strat) {
		this.strat = strat;
	}
	public Dot getEnd() {
		return end;
	}
	public void setEnd(Dot end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "Line [id=" + id + ", strat=" + strat.getId() + ", end=" + end.getId() + "]";
	}
	
	public Line(String id, Dot strat, Dot end) {
		super();
		this.id = id;
		this.strat = strat;
		this.end = end;
	}
	
	public Line(){}
}
