import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
/**
 * @author CYT
 */
public class Util {
	
	/**
	 * @return Array of the square location
	 */
	public static int[][] setSquareSiteTable() {
		int[][] site = new int[16][2];
		
		site[0][0] = 0; 
		site[0][1] = 70;
		site[1][0] = 0; 
		site[1][1] = 0;
		site[2][0] = 70; 
		site[2][1] = 0;
		site[3][0] = 70;
		site[3][1] = 70;
		site[4][0] = 140; 
		site[4][1] = 70;
		site[5][0] = 140;
		site[5][1] = 140;
		site[6][0] = 70;
		site[6][1] = 140;
		site[7][0] = 0; 
		site[7][1] = 140;
		site[8][0] = 0; 
		site[8][1] = 210;
		site[9][0] = 70; 
		site[9][1] = 210;
		site[10][0] = 140; 
		site[10][1] = 210;
		site[11][0] = 210; 
		site[11][1] = 210;
		site[12][0] = 210;
		site[12][1] = 140;
		site[13][0] = 210; 
		site[13][1] = 70;
		site[14][0] = 210;
		site[14][1] = 0;
		site[15][0] = 140;
		site[15][1] = 0;
		
		return site;
	}
	
	/**
	 * @return color array make the square change color
	 */
	public static int[][] setSquareColorTable() {
		int[][] colorTable = new int[3][3];

		colorTable[0][0] = 152;
		colorTable[0][1] = 251;
		colorTable[0][2] = 152;

		colorTable[1][0] = 187;
		colorTable[1][1] = 255;
		colorTable[1][2] = 250;

		colorTable[2][0] = 255;
		colorTable[2][1] = 165;
		colorTable[2][2] = 0;

		return colorTable;
	}
	
	/**
	 * Check the point is in the table or not.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param path the collection
	 * @return true if this collection contains this position
	 */
	final static boolean isExist(int x, int y, int[][] path) {
		for (int i = 0; i < path.length; i++) {
			if (path[i][0] == x && path[i][1] == y) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Using random to choose the next step is up or down or left or right.
	 * After getting the direction, it should make sure it wasn't chosen.
	 * Setting a TTL to avoid the point cannot having another step.
	 * 
	 * @param size the map size (or level of the user)
	 * @return the game table
	 */
	final static int[][] givePath(int size) {
		String[] direction = { "up", "down", "left", "right" };
		int[][] path = new int[size * size - 3][2];
		boolean flag = true; //if any point find no way to go, then set false
		int lastPoint = 21; 
		
		path[0][0] = (int) (Math.random() * 5); // start point
		path[0][1] = (int) (Math.random() * 5);
		
		for (int i = 1; i < size * size - 3; i++) {
			int TTL = 40; // Times to live

			while (flag) {
				if (TTL < 0) {
					lastPoint = i; //get last point
					flag = false; //stop looping
					break;
				}

				TTL--;

				switch (direction[(int) (Math.random() * 4)]) {
				//Next point go North
				case "up":
					if (path[i - 1][1] - 1 < 0) {
						continue;
					} else {
						if (Util.isExist(path[i - 1][0], path[i - 1][1] - 1, path)) {
							continue;
						} else {
							path[i][0] = path[i - 1][0];
							path[i][1] = path[i - 1][1] - 1;
						}
					}
					break;
				//Next point go South
				case "down":
					if (path[i - 1][1] + 1 > size - 1) {
						continue;
					} else {
						if (Util.isExist(path[i - 1][0], path[i - 1][1] + 1, path)) {
							continue;
						} else {
							path[i][0] = path[i - 1][0];
							path[i][1] = path[i - 1][1] + 1;
						}
					}
					break;
				//Next point go West
				case "left":
					if (path[i - 1][0] - 1 < 0) {
						continue;
					} else {
						if (Util.isExist(path[i - 1][0] - 1, path[i - 1][1], path)) {
							continue;
						} else {
							path[i][0] = path[i - 1][0] - 1;
							path[i][1] = path[i - 1][1];
						}
					}
					break;
				//Next point go East
				case "right":
					if (path[i - 1][0] + 1 > size - 1) {
						continue;
					} else {
						if (Util.isExist(path[i - 1][0] + 1, path[i - 1][1], path)) {
							continue;
						} else {
							path[i][0] = path[i - 1][0] + 1;
							path[i][1] = path[i - 1][1];
						}
					}
					break;
				}
				break;
			}
		}
		
		path = Arrays.copyOfRange(path, 0, lastPoint);
		return path;
	}
	
	/**
	 * Draw the map square on the drawPanel.
	 * Light green is startPoint and light red is the end.
	 * 
	 * @param g the graphics of the drawPanel
	 * @param path the map
	 * @param width wideth of each block
	 * @param height height of each block
	 */
	final static void drawMap(Graphics g, int[][] path, int width, int height) {
		g.setColor(new Color(0, 255, 0, 130)); //起點
		g.fillRect(path[0][0] * width + 3, path[0][1] * height + 3, width - 6, height - 6);
		for (int i = 1; i < path.length-1; i++) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(path[i][0] * width + 3, path[i][1] * height + 3, width - 6, height - 6);
		}
		g.setColor(new Color(255, 0, 0, 130)); //終點
		g.fillRect(path[path.length-1][0] * width + 3, path[path.length-1][1] * height + 3, width - 6, height - 6);
	}
	
	/**
	 * Check the next move still in the map.
	 * 
	 * @param currentX the x position of the current step
	 * @param currentY The y position of the current step
	 * @param size The size of the map
	 * @return True means currentX and currentY are still in the map
	 */
	final static boolean isInTheMap(int currentX, int currentY, int size) {
		if (currentX < 0 || currentX > size - 1 || currentY < 0 || currentY > size - 1) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * Check the next move is in in the path or not.
	 * 
	 * @param currentX The x position of the current step
	 * @param currentY The y position of the current step
	 * @param path The path array
	 * @return True means currentX and currentY is in the path
	 */
	final static boolean isInThePath(int currentX, int currentY, int[][] path) {
		for(int i=0;i<path.length;i++) {
			if(path[i][0] == currentX && path[i][1] == currentY) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Draw the current step
	 * 
	 * @param g The graphics of the drawPanel
	 * @param userPath The history of the user's step
	 * @param currentX The x position of the current step
	 * @param currentY The y position of the current step
	 * @param width The width of each block
	 * @param height The height of each block
	 */
	final static void draw(Graphics g, String[][] userPath, int currentX, int currentY, int width, int height) {
		for (int i = 0; i < userPath.length; i++) { 
			if (userPath[i][0] == null) {
				userPath[i][0] = String.valueOf(currentX);
				userPath[i][1] = String.valueOf(currentY);
				break;
			}
		}
		g.setColor(new Color(127, 255, 255, 170));
		g.fillRect(currentX * width + 3, currentY * height + 3, width - 6, height - 6);
	}
	
	/**
	 * Clear all the step behind the current step
	 * 
	 * @param g The graphics of the drawPanel
	 * @param userPath The history of the user's step
	 * @param index The index of the current step.
	 * @param width The width of each block
	 * @param height The height of each block
	 * @param lastPointX The final point's x position
	 * @param lastPointY The final point's y position
	 */
	final static void clear(Graphics g, String[][] userPath, int index, int width, int height, int lastPointX, int lastPointY) { 
		for (int i = index + 1; i < userPath.length; i++) {
			if (userPath[i][0] == null)
				break;
			else {
				if(Integer.parseInt(userPath[i][0]) == lastPointX && Integer.parseInt(userPath[i][1]) == lastPointY) { //最後一點要畫回紅色
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(lastPointX * width + 3, lastPointY * height + 3, width - 6, height - 6);
					g.setColor(new Color(255, 0, 0, 130));
					g.fillRect(lastPointX * width + 3, lastPointY * height + 3, width - 6, height - 6);
				}
				else {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(Integer.parseInt(userPath[i][0]) * width + 3, Integer.parseInt(userPath[i][1]) * height + 3, width - 6, height - 6);
				}
				userPath[i][0] = null;
				userPath[i][1] = null;
			}
		}
	}
	
	/**
	 * 判斷遊戲結束
	 * 
	 * @param userPath The history of the user's step
	 * @param lastPointX The final point's x position
	 * @param lastPointY The final point's y position
	 * @return True means user finish the game successfully
	 */
	final static boolean checkGame(String[][] userPath, int lastPointX, int lastPointY) {
		for (int i = 0; i < userPath.length; i++) {
			if(userPath[i][0] == null) {
				return false;
			}
		}
		//確認最後一點是紅色那塊
		if(userPath[userPath.length-1][0].equals(String.valueOf(lastPointX)) && userPath[userPath.length-1][1].equals(String.valueOf(lastPointY))) {
			return true;
		}
		return false;
	}
	
}
