package ctc.panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.border.Border;

public class MonitorMapPanel extends JPanel {
	public void paintComponent(Graphics comp) {
		Rectangle2D.Float background = new Rectangle2D.Float(0F, 0F,
				(float) getSize().width, (float) getSize().height);

		Graphics2D comp2D = (Graphics2D) comp;
		comp2D.fill(background);
		Line2D line1=new Line2D.Double(50,50,100,100);  
		Line2D line2=new Line2D.Double(100,100,120,120);
		Line2D line3=new Line2D.Double(120,120,120,140);
		Line2D line4=new Line2D.Double(120,140,140,140);
		Line2D line5=new Line2D.Double(140,140,200,200);
		
		
		Line2D line6=new Line2D.Double(200,200,200,400);
		Line2D line7=new Line2D.Double(200,400,400,450);
		Line2D line8=new Line2D.Double(400,450,500,480);
		Line2D line9=new Line2D.Double(500,480,600,310);
		comp2D.setColor(Color.green);
		comp2D.draw(line1);
		comp2D.draw(line2);
		comp2D.draw(line3);
		comp2D.setColor(Color.red);
		comp2D.draw(line4);
		comp2D.draw(line5);
		comp2D.setColor(Color.red);
		comp2D.draw(line6);
		comp2D.draw(line7);
		comp2D.setColor(Color.green);
		comp2D.draw(line8);
		comp2D.draw(line9);
		comp2D.dispose();  
		comp2D.setColor(Color.red);
	}
}
