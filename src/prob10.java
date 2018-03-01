import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Alex Chiang
 *
 */
public class prob10 {

	public static void main(String[] args) throws IOException {
		BufferedReader scan;
		try {
			scan = new BufferedReader(new FileReader(prob10.class.getSimpleName() + "-in.txt"));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		int T = Integer.parseInt(scan.readLine());
		while (T-- > 0) {
			String line = scan.readLine();
			String repl = "";
			List<Integer> ind = new ArrayList<>();
			for (int i = 0; i < line.length(); i++) {
				if (isLetter(line.charAt(i))) {
					repl += line.charAt(i);
					ind.add(i);
				}
			}
			String pali = "";
			int s = 0, e = 0;
			for (int a = 0; a <= repl.length() - 2; a++) {
				String longe = "";
				int ss = 0, ee = 0;
				for (int b = a + 2; b <= repl.length(); b++) {
					String cur = repl.substring(a, b);
					if (isPalidrome(cur) && (longe.length() < cur.length())) {
						longe = cur;
						ss = ind.get(a);
						ee = ind.get(b - 1);
					}
				}
				if (pali.length() < longe.length()) {
					pali = longe;
					s = ss;
					e = ee;
				}
			}
			if (pali.isEmpty()) {
				printLine("NO PALINDROME");
			} else {
				printLine(line.substring(s, e + 1));
			}
		}
		scan.close();
	}
	
	public static boolean isLetter(char c) {
		return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
	}
	
	public static boolean isPalidrome(String s) {
		return (new StringBuilder(s).reverse().toString().equalsIgnoreCase(s));
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