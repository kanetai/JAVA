import java.util.*;
public class aoj0220 {
	static final Scanner stdin = new Scanner(System.in);
	static final int INT = 8, FRAC = 4;
	static final String binary(int i, int num) {
		StringBuilder sb = new StringBuilder(Integer.toBinaryString(i)).reverse();
		while (sb.length() < num) sb.append("0");
		return sb.reverse().toString();
	}
	public static void main(String[] args) {
		while (true) {
			double x = Double.parseDouble(stdin.next());
			if (x < 0) break;
			String[] xx = ((x*(1<<FRAC)) + "").split("\\.");
			String str = binary(Integer.parseInt(xx[0]), FRAC+INT);
			System.out.println(
					(xx.length == 2 && Integer.parseInt(xx[1]) != 0) || Integer.toBinaryString((int)x).length() > INT ? 
					"NA" : str.substring(0, INT) + "." + str.substring(INT));
		}
	}
}
