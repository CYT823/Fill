
public class Console {

	public static void main(String[] args) {
		int size = 5;
		int[][] path = new int[size * size - 3][2];
		String[] direction = { "up", "down", "left", "right" };
		int TTL = 10; //如果走不出來就直接跳出
		int temp = 0; //跳出幾個 要找到上一個
		path[0][0] = (int)(Math.random()*5); // start point
		path[0][1] = (int)(Math.random()*5);
		
		
		for (int i = 1; i < size * size - 3; i++) {
			TTL = 10;
			
			while (true) {
				if (TTL < 0) {
					temp++;
					break;
				}
				TTL --;
				
				switch (direction[(int) (Math.random() * 4)]) {
				case "up":
					if (path[i - 1][1] - 1 < 0) {
						continue;
					} else {
						if (Util.isExist(path[i - 1][0], path[i - 1][1] - 1, path)) {
//							System.out.println("1");
							continue;
						} else {
							path[i][0] = path[i - 1][0];
							path[i][1] = path[i - 1][1] - 1;
						}
					}
					break;
				case "down":
					if (path[i - 1][1] + 1 > size - 1) {
//						System.out.println("2");
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
				case "left":
					if (path[i - 1][0] - 1 < 0) {
						continue;
					} else {
						if (Util.isExist(path[i - 1][0] - 1, path[i - 1][1], path)) {
//							System.out.println("3");
							continue;
						} else {
							path[i][0] = path[i - 1][0] - 1;
							path[i][1] = path[i - 1][1];
						}
					}
					break;
				case "right":
					if (path[i - 1][0] + 1 > size - 1) {
						continue;
					} else {
						if (Util.isExist(path[i - 1][0] + 1, path[i - 1][1], path)) {
//							System.out.println("4");
							continue;
						} else {
							path[i][0] = path[i - 1][0] + 1;
							path[i][1] = path[i - 1][1];
						}
					}
					break;
				}
//				System.out.println("123");
				break;	
			}
		}

		for (int i = 0; i < path.length; i++) {
			System.out.println("(" + path[i][0] + "," + path[i][1] + ")");
		}
	}

}
