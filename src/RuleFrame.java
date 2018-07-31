import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RuleFrame {
	JFrame frame;
	JPanel imagePanel;
	BufferedImage img;
	
	RuleFrame(){
		frame = new JFrame();
		frame.setSize(450, 294);
		frame.setAlwaysOnTop(true);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		
		try {
			//img = ImageIO.read(new URL("https://www269.lunapic.com/do-not-link-here-use-hosting-instead/153300311320191605?6349266302"));
			img = ImageIO.read(new File("rule.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
				
//				g.drawString("those boxes which were gone through behind this box", 50, 95);
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
