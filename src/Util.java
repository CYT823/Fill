import java.util.Arrays;

public class Util {
	
	// Returns true if this collection contains this position.
	final static boolean isExist(int x, int y, int[][] path) {
		for (int i = 0; i < path.length; i++) {
			if (path[i][0] == x && path[i][1] == y) {
				return true;
			}
		}
		return false;
	}
	
	// set the game path
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
}
