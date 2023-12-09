package life;

/*
 * This program is an implementation of Conway's Game of Life.
 * Author: Suhas Makineni
 * Date: 11/30/22
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class life implements MouseListener, ActionListener, Runnable {
	
	// objects and variables
	boolean[][] cells = new boolean[25][25];
	JFrame frame = new JFrame("Life Simulation");
	lifepanel panel = new lifepanel(cells);
	Container south = new Container();
	JButton step = new JButton("Step");
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	boolean running = false;
	
	// constructor
	public life() {
		frame.setSize(600,600);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		panel.addMouseListener(this);
		// south container
		south.setLayout(new GridLayout(1,3));
		south.add(step);
		step.addActionListener(this);
		south.add(start);
		start.addActionListener(this);
		south.add(stop);
		stop.addActionListener(this);
		frame.add(south, BorderLayout.SOUTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}



	public static void main(String[] args) {
		new life();
	}
	@Override
	public void mouseClicked(MouseEvent event) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// when cell is clicked, cell gets painted and prints the which cell was painted to the console
		double width = (double)panel.getWidth() / cells[0].length;
		double height = (double)panel.getHeight() / cells.length;
		int column = Math.min(cells[0].length - 1, (int)(event.getX() / width));
		int row = Math.min(cells.length - 1, (int)(event.getY() / height));
		System.out.println(column + "," + row);
		cells[row][column] = !cells[row][column];
		frame.repaint();
		
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	// code for the buttons
	public void actionPerformed(ActionEvent event) {
		// steps to next life when pressed
		if (event.getSource().equals(step)) {
			System.out.println("Step");
			step();
		}
		// loops  step class until stopped
		if (event.getSource().equals(start)) {
			System.out.println("Start");
			if (running == false) {
				running = true;
				Thread t = new Thread(this);
				t.start();
			}
		}
		// sets "running" to true and stops code
		if (event.getSource().equals(stop)) {
			System.out.println("Stop");
			if(running == true) {
				running = false;
			}
		}
	}
	
	@Override
	// step class loop
	public void run() {
		while (running == true) {
			step();
			try {
				Thread.sleep(250);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/*
	 * row-1,column-1		row-1,column		row-1, column+1
	 * row, column-1		row, column			row, column+1
	 * row+1, column-1		row+1, column		row+1, column+1
	 */
	
	public void step() {
		// checks for neighbors of the middle cell
		boolean[][] nextCells = new boolean[cells.length][cells[0].length];
		for (int row = 0; row < cells.length; row++) {
			for (int column = 0; column < cells[0].length; column++) {
				int neighborCount = 0;
				// up left
				if (row > 0 && column > 0 && cells[row-1][column-1] == true) {
					neighborCount++;
				}
				// up middle
				if (row > 0 && cells[row-1][column] == true) {
					neighborCount++;
				}
				// up right
				if (row > 0 && column < cells[0].length-1 && cells[row-1][column+1] == true) {
					neighborCount++;
				}
				// middle left
				if (column > 0 && cells[row][column-1] == true) {
					neighborCount++;
				}
				// middle right
				if (column < cells[0].length-1 && cells[row][column+1] == true) {
					neighborCount++;
				}
				// bottom left
				if (row < cells.length-1 && column > 0 && cells[row+1][column-1] == true) {
					neighborCount++;
				}
				// bottom middle
				if (row < cells.length-1 && cells[row+1][column] == true) {
					neighborCount++;
				}
				// bottom right
				if (row < cells.length-1 && column < cells[0].length-1 && cells[row+1][column+1] == true) {
					neighborCount++;
				}
				
				// life or death conditions
				if (cells[row][column] == true) {
					// it's alive
					if (neighborCount == 2 || neighborCount == 3) {
						nextCells[row][column] = true; // stays alive
					}
					// dies
					else {
						nextCells[row][column] = false;
					}
				}
				else {
					// cell resurrects if it has 3 neighbors
					if (neighborCount == 3) {
						nextCells[row][column] = true;
					}
					// stays dead if it doesn't have 3 neighbors
					else {
						nextCells[row][column] = false;
					}
				}
			}
		}
		cells = nextCells;
		panel.setCells(nextCells);
		frame.repaint();
	}
}



