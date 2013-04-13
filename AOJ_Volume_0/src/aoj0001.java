import java.util.*;
public class aoj0001 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		ArrayList<Integer> l = new ArrayList<Integer>();
		while( stdin.hasNext() ) l.add( stdin.nextInt() );
		Collections.sort(l);
		for(int i=1; i<=3; ++i) System.out.println( l.get(l.size()-i) );
	}
}