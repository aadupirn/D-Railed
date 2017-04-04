package ctc.beanpnl;

public class Dot {
	private String id;
	private float x;
	private float y;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "Dot [id=" + id + ", x=" + x + ", y=" + y + "]";
	}
	public Dot(String id, float x, float y) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public Dot(){}
}
