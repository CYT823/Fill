import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class HomePageAnimation extends JPanel{
	Thread th;
	int x; //圓x座標
	int y; //圓y座標
	int where = 0; //決定現在位置
	int colorNum = 1; //決定出的顏色
	int[][] SiteTable; //方格位置陣列
	int[][] colorTable;	//顏色陣列
	boolean flag = true; //判斷按下按鈕 停下thread
	
	HomePageAnimation(){
		SiteTable = Util.setSquareSiteTable();
		colorTable = Util.setSquareColorTable();
		
		Graphics g = getGraphics();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (flag) {
					x = 110 + SiteTable[where][0];
					y = 125 + SiteTable[where][1];
					if (where == SiteTable.length - 1) { //從頭開始
						where = 0;
					} else {
						if (where == 0) {
							if (colorNum == colorTable.length - 1) {
								colorNum = 0;
							} else {
								colorNum++;
							}
						}
						where++;
					}

					repaint();

					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		addMouseListener(new MouseAdapter() { //滑鼠事件  點了開啟遊戲視窗
			@Override
			public void mouseReleased(MouseEvent mouseEvent) {
				if ((mouseEvent.getX() >= 90 && mouseEvent.getY() >= 450)
						&& (mouseEvent.getX() <= 390 && mouseEvent.getY() <= 550)) {
					flag = false; //停下thread
					closeFrame(); //關閉視窗
					
					//start the game frame
					FillFrame frame = new FillFrame();
					frame.setSize(500, 600);
					frame.setMinimumSize(new Dimension(400, 500));
					frame.setTitle(" F I L L ");
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					String path = getClass().getResource("gameIcon.png").getFile();
					Image img = new ImageIcon(path).getImage();
					frame.setIconImage(img);
					frame.setVisible(true);
				}
			}
		});

	}

	private void closeFrame() { // close the frame by swingUtilities
		SwingUtilities.getWindowAncestor(this).dispose();
	}

	@Override
	public void paint(Graphics g) { // 利用 repaint 呼叫 paint 方法
		g.setColor(new Color(colorTable[colorNum][0], colorTable[colorNum][1], colorTable[colorNum][2]));
		g.fillRect(x, y, 60, 60);

		g.setColor(Color.BLACK);
		g.setFont(new Font("", Font.BOLD, 100));
		g.drawString("Fill", 172, 295);

		g.setColor(new Color(30, 144, 255));
		g.fillRoundRect(90, 450, 300, 100, 45, 45);

		g.setColor(Color.BLACK);
		g.setFont(new Font("Serif", Font.BOLD, 70));
		g.drawString("START", 125, 525);
	}
	
	
}
