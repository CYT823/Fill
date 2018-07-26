import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FillFrame extends JFrame{
	int size = 5; //map size
	int[][] path; //game path
	String[][] userPath; // The path which the user went
	int currentX;
	int currentY;
	JPanel controlPanel;
	JPanel drawPanel;
	JButton setBtn;
	JButton resetBtn;
	JButton QABtn;
	
	FillFrame(){
		this.setLayout(new GridBagLayout()); // set frame layout to gridbag
		
		GridBagConstraints bag1 = new GridBagConstraints();
		bag1.gridx = 0;
        bag1.gridy = 0;
        bag1.weightx = 1;
        bag1.weighty = 1;
        bag1.fill = GridBagConstraints.BOTH;
        bag1.anchor = GridBagConstraints.NORTH;
        
        GridBagConstraints bag2 = new GridBagConstraints();
        bag2.gridx = 0;
        bag2.gridy = 1;
        bag2.weightx = 1;
        bag2.weighty = 9;
        bag2.fill = GridBagConstraints.BOTH;
        bag2.anchor = GridBagConstraints.NORTH;
        
		controlPanel = new JPanel(new GridBagLayout()); //make vertical center
		controlPanel.setBackground(new Color(127, 255, 255, 130));
		drawPanel = new JPanel();
		setBtn = new JButton("Set");
		resetBtn = new JButton("Reset");
		resetBtn.setEnabled(false);
		QABtn = new JButton("Rules");

		controlPanel.add(setBtn);
		controlPanel.add(resetBtn);
		controlPanel.add(QABtn);
		this.add(controlPanel, bag1);
		this.add(drawPanel, bag2);

		setBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawPanel.setLayout(new GridLayout(size, size));

				do {
					path = Util.givePath(size);
				} while (path.length < size * size - 4); // 如果地圖太小要重載

				for (int i = 0; i < path.length; i++) {
					System.out.println("(" + path[i][0] + "," + path[i][1] + ")");
				}
				
				userPath = new String[path.length][2]; //define the userPath cuz String array contain null
				userPath[0][0] = String.valueOf(path[0][0]);
				userPath[0][1] = String.valueOf(path[0][1]) ;
				currentX = Integer.parseInt(userPath[0][0]);
				currentY = Integer.parseInt(userPath[0][1]);

				//畫地圖
				Util.drawMap(drawPanel.getGraphics(), path, drawPanel.getWidth() / size, drawPanel.getHeight() / size);
				
				setBtn.setEnabled(false);
				resetBtn.setEnabled(true);
				drawPanel.requestFocus();
			}
		});
		
		
		drawPanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				boolean flag = true; // "true" means this step is not in the path
				
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					//clear
					for (int i = 0; i < userPath.length; i++) { 
						if (userPath[i][0] != null && Util.isInTheMap(currentX, currentY - 1, size) && currentX == Integer.parseInt(userPath[i][0]) && (currentY - 1) == Integer.parseInt(userPath[i][1])) { //clear all steps which are behind this step
							Util.clear(drawPanel.getGraphics(),userPath, i, drawPanel.getWidth() / size, drawPanel.getHeight() / size);
							currentY--;
							flag = false;
							break;
						}
					}

					//draw
					if (flag) { 
						currentY --;
						if(Util.isInTheMap(currentX, currentY, size) && Util.isInThePath(currentX, currentY, path))
							Util.draw(drawPanel.getGraphics(), userPath, currentX, currentY, drawPanel.getWidth() / size, drawPanel.getHeight() / size);
						else
							currentY++;
					}
					System.out.println(currentX + " " + currentY);
					break;
				case KeyEvent.VK_DOWN:
					//clear
					for (int i = 0; i < userPath.length; i++) { 
						if (userPath[i][0] != null && Util.isInTheMap(currentX, currentY + 1, size) && currentX == Integer.parseInt(userPath[i][0]) && (currentY + 1) == Integer.parseInt(userPath[i][1])) { //clear all steps which are behind this step
							Util.clear(drawPanel.getGraphics(),userPath, i, drawPanel.getWidth() / size, drawPanel.getHeight() / size);
							currentY++;
							flag = false;
							break;
						}
					}

					//draw
					if (flag) { 
						currentY++;
						if(Util.isInTheMap(currentX, currentY, size) && Util.isInThePath(currentX, currentY, path))
							Util.draw(drawPanel.getGraphics(), userPath, currentX, currentY, drawPanel.getWidth() / size, drawPanel.getHeight() / size);
						else
							currentY--;
					}
					System.out.println(currentX + " " + currentY);
					break;
				case KeyEvent.VK_LEFT:
					//clear
					for (int i = 0; i < userPath.length; i++) { 
						if (userPath[i][0] != null && Util.isInTheMap(currentX - 1, currentY, size) && (currentX - 1) == Integer.parseInt(userPath[i][0]) && currentY == Integer.parseInt(userPath[i][1])) { //clear all steps which are behind this step
							Util.clear(drawPanel.getGraphics(),userPath, i, drawPanel.getWidth() / size, drawPanel.getHeight() / size);
							currentX--;
							flag = false;
							break;
						}
					}

					//draw
					if (flag) { 
						currentX--;
						if(Util.isInTheMap(currentX, currentY, size) && Util.isInThePath(currentX, currentY, path))
							Util.draw(drawPanel.getGraphics(), userPath, currentX, currentY, drawPanel.getWidth() / size, drawPanel.getHeight() / size);
						else
							currentX++;
					}
					System.out.println(currentX + " " + currentY);
					break;
				case KeyEvent.VK_RIGHT:
					//clear
					for (int i = 0; i < userPath.length; i++) { 
						if (userPath[i][0] != null && Util.isInTheMap(currentX + 1, currentY, size) && (currentX + 1) == Integer.parseInt(userPath[i][0]) && currentY == Integer.parseInt(userPath[i][1])) { //clear all steps which are behind this step
							Util.clear(drawPanel.getGraphics(),userPath, i, drawPanel.getWidth() / size, drawPanel.getHeight() / size);
							currentX++;
							flag = false;
							break;
						}
					}

					//draw
					if (flag) { 
						currentX++;
						if(Util.isInTheMap(currentX, currentY, size) && Util.isInThePath(currentX, currentY, path))
							Util.draw(drawPanel.getGraphics(), userPath, currentX, currentY, drawPanel.getWidth() / size, drawPanel.getHeight() / size);
						else
							currentX--;
					}
					System.out.println(currentX + " " + currentY);
					break;
				}
			}
		});
		
		
		
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawPanel.repaint();
				setBtn.setEnabled(true);
				resetBtn.setEnabled(false);
			}
		});
		
		QABtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
}
