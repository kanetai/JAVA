import java.util.*;
public class aoj0266 {
	static final Scanner stdin = new Scanner(System.in);
	enum City { 
		A, B, X, Y, Z, W, D;
		static City path[][] = { {X, Y}, {Y, X}, {D, Z}, {X, D}, {W, B}, {B, Y}, {D, D} };
	}
	public static void main(String[] args) {
		while (true) {
			char[] str = stdin.next().toCharArray();
			if (str[0] == '#') break;
			int pos = City.A.ordinal();
			for (char c : str) pos = City.path[pos][c - '0'].ordinal();
			System.out.println(pos == City.B.ordinal() ? "Yes" : "No");
		}
	}
}
