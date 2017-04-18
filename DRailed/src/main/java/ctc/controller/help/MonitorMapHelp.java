package ctc.controller.help;

import java.awt.geom.GeneralPath;

import javafx.scene.canvas.GraphicsContext;

public class MonitorMapHelp {
	
	/************************************************************************/
	public static void drawAL(float f, float g, float i, float j, GraphicsContext g2)
	{

		double H = 8; // 箭头高度
		double L = 3; // 底边的一
		int x3 = 0;
		int y3 = 0;
		int x4 = 0;
		int y4 = 0;
		double awrad = Math.atan(L / H); // 箭头角度
		double arraow_len = Math.sqrt(L * L + H * H); // 箭头的长
		double[] arrXY_1 = rotateVec(i - f, j - g, awrad, true, arraow_len);
		double[] arrXY_2 = rotateVec(i - f, j - g, -awrad, true, arraow_len);
		double x_3 = i - arrXY_1[0]; // (x3,y3)是第
		double y_3 = j - arrXY_1[1];
		double x_4 = i - arrXY_2[0]; // (x4,y4)是第亿
		double y_4 = j - arrXY_2[1];

		Double X3 = new Double(x_3);
		x3 = X3.intValue();
		Double Y3 = new Double(y_3);
		y3 = Y3.intValue();
		Double X4 = new Double(x_4);
		x4 = X4.intValue();
		Double Y4 = new Double(y_4);
		y4 = Y4.intValue();
		// 画
		g2.strokeLine(f, g, i, j);
		//
		GeneralPath triangle = new GeneralPath();
		triangle.moveTo(i, j);
		triangle.lineTo(x3, y3);
		triangle.lineTo(x4, y4);
		triangle.closePath();
		//实心箭头
		// g2.fill(triangle);
		
		// 画箭
		g2.strokeLine(i, j, x3, y3);
		g2.strokeLine(i, j, x4, y4);
		g2.strokeLine(x4, y4, x3, y3);
		
		//非实心箭
		//g2.draw(triangle);

	}

	// 计
	public static double[] rotateVec(float f, float g, double ang,
			boolean isChLen, double newLen) {

		double mathstr[] = new double[2];
		// 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度
		double vx = f * Math.cos(ang) - g * Math.sin(ang);
		double vy = f * Math.sin(ang) + g * Math.cos(ang);
		if (isChLen) {
			double d = Math.sqrt(vx * vx + vy * vy);
			vx = vx / d * newLen;
			vy = vy / d * newLen;
			mathstr[0] = vx;
			mathstr[1] = vy;
		}
		return mathstr;
	}
	
	/*******************************************************************************/

}
