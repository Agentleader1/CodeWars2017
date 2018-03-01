import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author Alex Chiang
 *
 */
public class prob16 {

	public static void main(String[] args) throws IOException {
		BufferedReader scan;
		try {
			scan = new BufferedReader(new FileReader(prob16.class.getSimpleName() + "-in.txt"));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		List<List<Character>> l = new ArrayList<List<Character>>();
		int T = Integer.parseInt(scan.readLine());
		boolean[] used = new boolean[T];
		Arrays.fill(used, false);
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(scan.readLine());
			List<Character> cube = new ArrayList<Character>();
			for (int i = 0; i < 6; i++)
				cube.add(st.nextToken().charAt(0));
			l.add(cube);
		}
		T = Integer.parseInt(scan.readLine());
		while (T-- > 0) {
			String word = scan.readLine();
			boolean form = false;
			if (word.length() > l.size()) {
				printLine(word + " CANNOT be formed");
				continue;
			}
			String current = "";
			for (int i = 0; i < l.size(); i++) {
				List<Character> cube = l.get(i);
				if (cube.contains(word.charAt(0))) {
					current += word.charAt(0);
					used[i] = true;
				} else
					continue;
				for (int len = current.length(); len < word.length(); len++) {
					for (int j = 0; j < l.size(); j++) {
						if (used[j])
							continue;
						if (cube.contains(word.charAt(len))) {
							current += word.charAt(len);
							used[j] = true;
						} else
							continue;
					}
				}
				if (current.equalsIgnoreCase(word)) {
					form = true;
					break;
				}
				current = "";
				used[i] = false;
			}
			printLine(word + " " + (form ? "can" : "CANNOT") + " be formed");
		}
		scan.close();
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