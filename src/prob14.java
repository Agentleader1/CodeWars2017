import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 
 * @author Alex Chiang
 *
 */
public class prob14 {

	public static void main(String[] args) throws IOException {
		BufferedReader scan;
		try {
			scan = new BufferedReader(new FileReader(prob14.class.getSimpleName() + "-in.txt"));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		String nextLine = scan.readLine();
		while (!nextLine.startsWith("0 0")) {
			StringTokenizer st = new StringTokenizer(nextLine);
			int rs = Integer.parseInt(st.nextToken());
			int cs = Integer.parseInt(st.nextToken());
			char[][] array = new char[rs][cs];
			for (int r = 0; r < rs; r++) {
				String s = scan.readLine();
				for (int c = 0; c < cs; c++)
					array[r][c] = s.charAt(c);
			}
			printLine(paths(array));
			nextLine = scan.readLine();
		}
		scan.close();
	}
	
	public static long paths(char[][] array) {
		long[][] mem = new long[array.length][array[0].length];
		mem[array.length - 1][array[0].length - 1] = 1;
		path(array, mem, 0, 0);
		return mem[0][0];
	}
	
	public static long path(char[][] array, long[][] mem, int r, int c) {
		if (!inBounds(array, r, c) || array[r][c] == '#')
			return 0;
		if (mem[r][c] == 0)
			mem[r][c] = path(array, mem, r + 1, c) + path(array, mem, r, c + 1);
		return mem[r][c];
	}
	
	public static boolean inBounds(char[][] array, int r, int c) {
		return (Math.min(r, c) >= 0) && (r < array.length && c < array[r].length);
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