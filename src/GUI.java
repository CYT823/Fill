import java.awt.Dimension;

import javax.swing.JFrame;

public class GUI {

	public static void main(String[] args) {
		FillFrame frame = new FillFrame();
		frame.setSize(500, 500);
		frame.setMinimumSize(new Dimension(400, 400));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}