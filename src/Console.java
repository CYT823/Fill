
public class Console {

	public static void main(String[] args) {
		int size = 5;
		int startX = 0;
		int startY = 0;
		int[][] path = new int[size - 3][2];
		String[] direction = { "up", "down", "left", "right" };
		int[] lastPoint = new int[2];

		startX = (int) Math.random() * 100 % 5;
		startY = (int) Math.random() * 100 % 5;
		lastPoint[0] = startX;
		lastPoint[1] = startY;

		for (int i = 0; i < size * size - 3; i++) {
			while(true) {
				switch (direction[(int) Math.random() * 10 % 4]) {
				case "up":
					if()
				case "down":
				case "left":
				case "right":

				}
			}
		}
	}

}
