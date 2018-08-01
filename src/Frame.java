import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame{
	Frame() {
		JPanel panel = new HomePageAnimation();
		add(panel);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent mouseEvent) {
				if ((mouseEvent.getX() >= 90 && mouseEvent.getY() >= 450) && (mouseEvent.getX() <= 390 && mouseEvent.getY() <= 550)) { 
					dispose();
				}
			}
		});
	}
}
//LodingAnimation la = new LodingAnimation();