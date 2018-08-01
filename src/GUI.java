import javax.swing.JFrame;

public class GUI {

	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.setSize(500, 700);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
