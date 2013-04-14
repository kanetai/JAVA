import java.util.*;
public class aoj0018 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		Integer[] a = new Integer[5];
		for(int i=0;i<5;++i) a[i] = stdin.nextInt();
		Arrays.sort(a, new Comparator<Integer>(){
			@Override public int compare(Integer o1, Integer o2) { return o2.compareTo(o1); }
		});
		System.out.println( a[0]+" "+a[1]+" "+a[2]+" "+a[3]+" "+a[4] );
	}
}
