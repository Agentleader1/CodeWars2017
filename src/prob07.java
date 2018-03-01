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
public class prob07 {

	public static void main(String[] args) throws IOException {
		BufferedReader scan;
		try {
			scan = new BufferedReader(new FileReader(prob07.class.getSimpleName() + "-1-in.txt"));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		int T = Integer.parseInt(scan.readLine());
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(scan.readLine());
			double[] x = new double[101];
			double a, b, c, m, n, e;
			x[0] = Double.parseDouble(st.nextToken());
			a = Double.parseDouble(st.nextToken());
			b = Double.parseDouble(st.nextToken());
			c = Double.parseDouble(st.nextToken());
			m = Double.parseDouble(st.nextToken());
			n = Double.parseDouble(st.nextToken());
			e = Double.parseDouble(st.nextToken());
			try {
				double out = calculate(x, a, b, c, m, n, e);
				System.out.println(out);
			} catch (IOException ex) {
				System.out.println("DIVERGES");
			}
		}
		scan.close();
	}
	
	public static double calculate(double[] x, double a, double b, double c, double m, double n, double e) throws IOException {
		for (int i = 1; i < 101; i++) {
			x[i] = c + (a * x[i - 1] + m) / (b * x[i - 1] + n);
			if (Math.abs(x[i] - x[i - 1]) < e) {
				printLine(i);
				return x[i];
			}
		}
		throw new IOException();
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