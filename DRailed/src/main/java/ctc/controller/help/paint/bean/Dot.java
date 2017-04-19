package ctc.controller.help.paint.bean;

public class Dot {
	private int x;
	private int y;
	private String name;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Dot(int x, int y, String name) {
		super();
		this.x = x;
		this.y = y;
		this.name = name;
	}

}
