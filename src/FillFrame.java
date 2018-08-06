import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * 
 * @author CYT
 * @version 4.1
 *
 */
public class FillFrame extends JFrame{
	int size = 5; //map size, also level
	int[][] path; //game path
	String[][] userPath; // The path which the user went
	int currentX;
	int currentY;
	JPanel controlPanel;
	static JPanel drawPanel;
	JButton setBtn;
	JButton clearBtn;
	JButton QABtn;
	
	
	FillFrame(){
		this.setLayout(new GridBagLayout()); // set frame layout to gridbag
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				JOptionPane.showMessageDialog(controlPanel, "Your IQ: "+(size+1)*10);
			}
			
			
		});
		
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
		controlPanel.setBorder(BorderFactory.createEtchedBorder());
		drawPanel = new JPanel();
		setBtn = new JButton("Set");
		clearBtn = new JButton("Clear");
		clearBtn.setEnabled(false);
		QABtn = new JButton("Rules");

		controlPanel.add(setBtn);
		controlPanel.add(new JPanel());
		controlPanel.add(clearBtn);
		controlPanel.add(new JPanel());
		controlPanel.add(QABtn);
		this.add(controlPanel, bag1);
		this.add(drawPanel, bag2);
		
		setBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// giving size, default is 5
				//askingSize();
				
				drawPanel.setLayout(new GridLayout(size, size));

				do {
					path = Util.givePath(size);
				} while (path.length < size * (size - 2) + 3 || size * (size - 2) + 4 < path.length); // 如果地圖太小要重載
				
				userPath = new String[path.length][2]; //define the userPath cuz String array contain null
				userPath[0][0] = String.valueOf(path[0][0]);
				userPath[0][1] = String.valueOf(path[0][1]) ;
				currentX = Integer.parseInt(userPath[0][0]);
				currentY = Integer.parseInt(userPath[0][1]);

				//畫地圖
				Util.drawMap(drawPanel.getGraphics(), path, drawPanel.getWidth() / size, drawPanel.getHeight() / size);
				
				setBtn.setEnabled(false);
				clearBtn.setEnabled(true);
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
							Util.clear(drawPanel.getGraphics(),userPath, i, drawPanel.getWidth() / size, drawPanel.getHeight() / size, path[path.length-1][0], path[path.length-1][1]);
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
					if(Util.checkGame(userPath, path[path.length-1][0], path[path.length-1][1])) {
						new EndingFrame();
						finshGame();
						if(size<12)
							size++;
					}
					break;
				case KeyEvent.VK_DOWN:
					//clear
					for (int i = 0; i < userPath.length; i++) { 
						if (userPath[i][0] != null && Util.isInTheMap(currentX, currentY + 1, size) && currentX == Integer.parseInt(userPath[i][0]) && (currentY + 1) == Integer.parseInt(userPath[i][1])) { //clear all steps which are behind this step
							Util.clear(drawPanel.getGraphics(),userPath, i, drawPanel.getWidth() / size, drawPanel.getHeight() / size, path[path.length-1][0], path[path.length-1][1]);
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
					if(Util.checkGame(userPath, path[path.length-1][0], path[path.length-1][1])) {
						new EndingFrame();
						finshGame();
						if(size<12)
							size++;
					}
					break;
				case KeyEvent.VK_LEFT:
					//clear
					for (int i = 0; i < userPath.length; i++) { 
						if (userPath[i][0] != null && Util.isInTheMap(currentX - 1, currentY, size) && (currentX - 1) == Integer.parseInt(userPath[i][0]) && currentY == Integer.parseInt(userPath[i][1])) { //clear all steps which are behind this step
							Util.clear(drawPanel.getGraphics(),userPath, i, drawPanel.getWidth() / size, drawPanel.getHeight() / size, path[path.length-1][0], path[path.length-1][1]);
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
					if(Util.checkGame(userPath, path[path.length-1][0], path[path.length-1][1])) {
						new EndingFrame();
						finshGame();
						if(size<12)
							size++;
					}
					break;
				case KeyEvent.VK_RIGHT:
					//clear
					for (int i = 0; i < userPath.length; i++) { 
						if (userPath[i][0] != null && Util.isInTheMap(currentX + 1, currentY, size) && (currentX + 1) == Integer.parseInt(userPath[i][0]) && currentY == Integer.parseInt(userPath[i][1])) { //clear all steps which are behind this step
							Util.clear(drawPanel.getGraphics(),userPath, i, drawPanel.getWidth() / size, drawPanel.getHeight() / size, path[path.length-1][0], path[path.length-1][1]);
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
					if(Util.checkGame(userPath, path[path.length-1][0], path[path.length-1][1])) {
						new EndingFrame();
						finshGame();
						if(size<12)
							size++;
					}
					break;
				}
			}
		});
		
		
		
		clearBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				finshGame();
				if (size > 4) //按clear按鈕 等級就會下降
					size--;
			}
		});
		
		QABtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RuleFrame();
			}
		});
	
	}
	
	void askingSize() {
		do {
			try {
				size = Integer.parseInt(JOptionPane.showInputDialog(null, "(ex: 5 means 5x5 map) Suggenst:5-10", "Input size", JOptionPane.INFORMATION_MESSAGE));
			} catch (NumberFormatException nfe) {
				size = 5;
			}
		} while (size < 4 || size > 12);
	}

	// The step after finsh the game
	void finshGame() {
		drawPanel.repaint();
		setBtn.setEnabled(true);
		clearBtn.setEnabled(false);
	}
	
}
