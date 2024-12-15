/*
 * This program is a panel for conway's game of life.
 * Author: Suhas Makineni
 * Date: 11/30/22
 */
package life;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class lifepanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	boolean[][] cells;
	double width;
	double height;
	
	public lifepanel(boolean[][] in) {
		cells = in;
	}
	
	public void setCells(boolean[][] newcells) {
		cells = newcells;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// creates cell coordinates
		width = (double)this.getWidth() / cells[0].length;
		height = (double)this.getHeight() / cells.length;
		
		// paints the cells
		g.setColor(Color.BLUE);
		for (int row = 0; row < cells.length; row++) {
			for (int column = 0; column < cells[0].length; column++) {
				if (cells[row][column] == true) {
					g.fillRect((int)Math.round(column*width),
							(int)Math.round(row*height),
							(int)width + 1, (int)height + 1);
				}
			}
		}
		
		// draws the lines
		g.setColor(Color.BLACK);
		// draws vertical lines
		for (int x = 0; x < cells[0].length + 1; x++) {
			g.drawLine((int)Math.round(x*width),0,(int)Math.round(x*width),this.getHeight());
		}
		// draws horizontal lines
		for (int y = 0; y < cells.length + 1; y++) {
			g.drawLine(0,(int)Math.round(y*height),this.getWidth(),(int)Math.round(y*height));
		}

		
	}
	


}
