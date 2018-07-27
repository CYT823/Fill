import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EndingFrame {
	JFrame frame;
	JButton button;
	JPanel imagePanel;
	BufferedImage img;
	
	public static void main(String[] atgs) {
		new EndingFrame();
	}
	
	
	EndingFrame(){
		frame = new JFrame();
		frame.setSize(400, 300);
		frame.setAlwaysOnTop(true);
		frame.setLocationRelativeTo(null);
		
//		button = new JButton("click");
//		frame.add(button, BorderLayout.NORTH);
//		imagePanel = new JPanel();
//		frame.add(imagePanel);
		
//		button.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent ae) {
//				
//				
//			}
//			
//		});
		
		try {
			img = ImageIO.read(new File("congratulation.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		frame.add(new JPanel() {
			@Override
			protected void paintComponent(Graphics grphcs) {
				super.paintComponent(grphcs);
				grphcs.drawImage(img, 0, 0, this);
			}
		});

		frame.setVisible(true);
	}
}
