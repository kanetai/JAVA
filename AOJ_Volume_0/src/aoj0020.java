import java.util.*;
public class aoj0020 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		String line = stdin.nextLine();
		for(int i=0; i<line.length(); ++i) System.out.print( Character.toUpperCase( line.charAt(i) ) );
		System.out.println("");
	}
}
