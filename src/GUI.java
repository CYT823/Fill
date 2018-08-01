import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GUI {

	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.setSize(500, 700);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			Image img = ImageIO.read(new File("gameIcon.png"));
			frame.setIconImage(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setVisible(true);
	}

}
