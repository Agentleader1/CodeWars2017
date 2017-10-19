import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author Alex Chiang
 *
 */
public class prob20 {

	public static void main(String[] args) throws IOException {
		BufferedReader scan;
		try {
			scan = new BufferedReader(new FileReader(prob20.class.getSimpleName() + "-in.txt"));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		StringTokenizer st = new StringTokenizer(scan.readLine());
		int cs = Integer.parseInt(st.nextToken());
		int rs = Integer.parseInt(st.nextToken());
		char[][] maze = new char[rs][cs];
		int sr = -1, sc = -1;
		for (int i = 0; i < rs; i++) {
			String line = scan.readLine();
			for (int j = 0; j < cs; j++) {
				maze[i][j] = line.charAt(j);
				if (line.charAt(j) == '@') {
					sr = i;
					sc = j;
				}
			}
		}
		printArray(bfs(maze, sr, sc));
		scan.close();
	}
	
	private static int[][] dir = new int[][] { {-1, -1, -1, 0, 0, 1, 1, 1}, {-1, 0, 1, -1, 1, -1, 0, 1} };
	
	public static char[][] bfs(char[][] maze, int sr, int sc) {
		char[][] draw = new char[maze.length][maze[0].length];
		copyArray(maze, draw);
		int[][] ii = new int[maze.length][maze[0].length];
		fillArray(ii, Integer.MAX_VALUE);
		int er = -1, ec = -1;
		Queue<Integer> q = new LinkedList<>();
		q.add(sr);
		q.add(sc);
		while (!q.isEmpty()) {
			int r = q.poll();
			int c = q.poll();
			for (int i = 0; i < 8; i++) {
				int rr = r + dir[0][i];
				int cc = c + dir[1][i];
				if (inBounds(maze, rr, cc) && ii[r][c] + 1 < ii[rr][cc]) {
					ii[rr][cc] = ii[r][c] + 1;
					if (maze[rr][cc] == 'X') {
						er = rr;
						ec = cc;
					}
					q.add(rr);
					q.add(cc);
				}
			}
		}
		q.add(er);
		q.add(ec);
		while (!q.isEmpty()) {
			int r = q.poll();
			int c = q.poll();
			for (int i = 0; i < 8; i++) {
				int rr = r + dir[0][i];
				int cc = c + dir[1][i];
				if (inBounds(maze, rr, cc) && ii[r][c] - 1 == ii[rr][cc]) {
					draw[rr][cc] = '*';
					q.add(rr);
					q.add(cc);
				}
			}
		}
		return draw;
	}
	
	public static void fillArray(int[][] array, int i) {
		for (int r = 0; r < array.length; r++) 
			for (int c = 0; c < array.length; c++)
				array[r][c] = i;
	}
	
	public static void copyArray(char[][] original, char[][] copy) {
		for (int i = 0; i < original.length; i ++) 
			copy[i] = original[i];
	}
	
	public static boolean inBounds(char[][] array, int r, int c) {
		if (!((Math.min(r, c) >= 0) && (r < array.length && c < array[0].length)))
			return false;
		if (array[r][c] == ' ' || array[r][c] == 'X')
			return true;
		return false;
	}

	public static void print(Object... o) {
		for (Object obj : o) {
			System.out.print(obj);
		}
	}

	public static void printLine(Object... o) {
		if (o.length <= 0) {
			System.out.println();
			return;
		}
		for (Object obj : o) {
			System.out.println(obj);
		}
	}

	public static void printF(boolean newLine, String format, Object... o) {
		System.out.printf(format + ((newLine) ? "\n" : ""), o);
	}

	public static void printArray(char[][] array) {
		for (char[] ch : array) {
			for (char c : ch)
				print(c);
			printLine();
		}
	}
	
}
