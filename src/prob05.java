import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * @author Alex Chiang
 *
 */
public class prob05 {

	public static void main(String[] args) throws IOException {
		BufferedReader scan;
		try {
			scan = new BufferedReader(new FileReader(prob05.class.getSimpleName() + "-1-in.txt"));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		HashMap<String, Double> race = new HashMap<>();
		int T = Integer.parseInt(scan.readLine());
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(scan.readLine());
			race.put(st.nextToken(), Double.parseDouble(st.nextToken()) / Double.parseDouble(st.nextToken()));
		}
		double low = Double.MAX_VALUE;
		String team = "";
		for (String t : race.keySet()) {
			if (race.get(t) < low) {
				team = t;
				low = race.get(t);
			}
		}
		printLine(team + " " + low);
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