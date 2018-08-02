import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EndingFrame {
	JFrame frame;
	JPanel imagePanel;
	Image img;
	
	EndingFrame(){
		frame = new JFrame();
		frame.setSize(400, 302);
		frame.setAlwaysOnTop(true);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		
		String path = getClass().getResource("congratulation.jpg").getFile();
		img = new ImageIcon(path).getImage();

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
