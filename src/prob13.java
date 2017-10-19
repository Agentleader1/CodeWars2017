import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * 
 * @author Alex Chiang
 *
 */
public class prob13 {

	public static void main(String[] args) throws IOException {
		BufferedReader scan;
		try {
			scan = new BufferedReader(new FileReader(prob13.class.getSimpleName() + "-in.txt"));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		String line = scan.readLine();
		char[][] array = new char[300][300];
		for (char[] ch : array)
			Arrays.fill(ch, ' ');
		/*
		 * 0 - Right
		 * 1 - Up
		 * 2 - Left
		 * 3 - Down
		 */
		int dir = 0;
		int r = 149, c = 149;
		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			array[r][c] = ch;
			if (ch == 'R' || ch == 'r')
				dir = 0;
			else if (ch == 'U' || ch == 'u')
				dir = 1;
			else if (ch == 'L' || ch == 'l')
				dir = 2;
			else if (ch == 'D' || ch == 'd')
				dir = 3;
			if (dir == 0)
				c++;
			else if (dir == 1)
				r--;
			else if (dir == 2)
				c--;
			else if (dir == 3)
				r++;
		}
		printArray(format(array));
		scan.close();
	}
	
	public static char[][] format(char[][] array) {
		char[][] copy;
		int sr = 0, sc = 0, er = 0, ec = 0;
		for (int r = 0; r < array.length; r++)
			if (isEmpty(array[r]))
				sr++;
			else
				break;
		for (int r = array.length - 1; r >= 0; r--)
			if (isEmpty(array[r]))
				er++;
			else
				break;
		for (int c = 0; c < array[0].length; c++) {
			boolean empty = true;
			for (int r = 0; r < array.length; r++)
				if (array[r][c] != ' ')
					empty = false;
			if (empty)
				sc++;
			else
				break;
		}
		for (int c = array[0].length - 1; c >= 0; c--) {
			boolean empty = true;
			for (int r = 0; r < array.length; r++)
				if (array[r][c] != ' ')
					empty = false;
			if (empty)
				ec++;
			else
				break;
		}
		copy = new char[array.length - sr - er][array[0].length - sc - ec];
		int rr = 0;
		for (int r = sr; r < array.length - er; r++) {
			int cc = 0;
			for (int c = sc; c < array[r].length - ec; c++) {
				copy[rr][cc] = array[r][c];
				cc++;
			}
			rr++;
		}
		return copy;
	}
	
	public static boolean isEmpty(char[] array) {
		for (char c : array)
			if (c != ' ')
				return false;
		return true;
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