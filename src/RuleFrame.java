import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RuleFrame {
	JFrame frame;
	JPanel imagePanel;
	Image img;
	
	RuleFrame(){
		frame = new JFrame();
		frame.setSize(450, 294);
		frame.setAlwaysOnTop(true);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		
		String path = getClass().getResource("rule.jpg").getFile();
		img = new ImageIcon(path).getImage();
		
		frame.add(new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, null);
				
				g.setFont(new Font("Skia", Font.BOLD, 16));
				g.drawString("The rules are extremely simple.", 40, 90);
				g.drawString("Just fill in all of the blocks using only one line.", 40, 125);
				g.drawString("Go from light green to light red.", 40, 160);
				g.drawString("Using (↑) (↓) (←) (→) to change the current step.", 40, 195);
				g.drawString("Good luck!", 40, 230);
				g.drawString("[ ANY KEY ]", 175, 260);
			}
		});

		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				FillFrame.drawPanel.requestFocus();
				frame.dispose();
			}
		});

		frame.setVisible(true);
	}
}
