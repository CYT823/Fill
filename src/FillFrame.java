import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FillFrame extends JFrame{
	int size = 5; //map size
	int[][] path; //game path
	JPanel controlPanel;
	JPanel drawPanel;
	JButton startBtn;
	JButton restartBtn;
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
		controlPanel.setBackground(Color.CYAN);
		drawPanel = new JPanel();
		drawPanel.setBackground(Color.PINK);
		startBtn = new JButton("Start");
		restartBtn = new JButton("Restart");
		QABtn = new JButton("Rules");

		controlPanel.add(startBtn);
		controlPanel.add(restartBtn);
		controlPanel.add(QABtn);
		this.add(controlPanel, bag1);
		this.add(drawPanel, bag2);

		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawPanel.setLayout(new GridLayout(size,size));
				
				do {
					path = Util.givePath(size);
				} while (path.length < size * size - 4); // 如果地圖太小要重載
				
				for (int i = 0; i < path.length; i++) {
					System.out.println("(" + path[i][0] + "," + path[i][1] + ")");
				}
				
				
			}
		});
		
		restartBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		QABtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
}
