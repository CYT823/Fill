import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GUI {
	
	
	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.setSize(500, 700);
		frame.setTitle(" F I L L ");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String path = GUI.class.getResource("gameIcon.png").getFile();
		Image img = new ImageIcon(path).getImage();
		frame.setIconImage(img);
		frame.setVisible(true);
	}
}
