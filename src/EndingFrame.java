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

public class EndingFrame {
	JFrame frame;
	JPanel imagePanel;
	BufferedImage img;
	
	EndingFrame(){
		frame = new JFrame();
		frame.setSize(400, 302);
		frame.setAlwaysOnTop(true);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		
		try {
			//img = ImageIO.read(new URL("https://www196.lunapic.com/do-not-link-here-use-hosting-instead/153293646549104584?7628171891"));
			img = ImageIO.read(new File("congratulation.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		frame.add(new JPanel() {
			@Override
			protected void paintComponent(Graphics graphics) {
				super.paintComponent(graphics);
				graphics.drawImage(img, 0, 0, this);
				graphics.setFont(new Font("Skia", Font.BOLD, 16));
				graphics.drawString("[ PRESS ANY KET TO CONTINUE ]", 70, 280);
			}
		});

		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				frame.dispose();
			}
		});

		frame.setVisible(true);
	}
}
