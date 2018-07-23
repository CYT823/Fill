
public class Util {
	// check the point is in the path
	final static boolean isExist(int x, int y, int[][] path) {
		for (int i = 0; i < path.length; i++) {
			if (path[i][0] == x && path[i][1] == y) {
				return true;
			}
		}
		return false;
	}
}
