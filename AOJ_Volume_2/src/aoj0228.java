import java.util.*;
public class aoj0228 {
	static final Scanner stdin = new Scanner(System.in);
	static final String[] seg = { "0111111", "0000110", "1011011", "1001111", "1100110", "1101101", "1111101", "0100111", "1111111", "1101111",  };
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt();
			if (n == -1) break;
			int a = 0;
			while (n-- > 0) {
				int b = Integer.valueOf(seg[stdin.nextInt()], 2);
				System.out.println(toString(a^b));
				a = b;
			}
		}
	}
	static String toString(int i) {
		StringBuilder sb = new StringBuilder(Integer.toBinaryString(i)).reverse();
		while (sb.length() < 7) sb.append('0');
        return sb.reverse().toString();
    }
}
