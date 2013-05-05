import java.util.*;
public class aoj0161 {
	static final Scanner stdin = new Scanner(System.in);
	static class T implements Comparable<T>{
		int id, sec;
		T(int id, int sec){ this.id = id; this.sec = sec; }
		@Override public int compareTo(T o) { return sec - o.sec; }
	}
	public static void main(String[] args) {
		int n;
		while((n = stdin.nextInt()) != 0){
			T[] a = new T[n];
			for (int i = 0; i < n; i++)
				a[i] = new T(stdin.nextInt(), 
						60 * stdin.nextInt() + stdin.nextInt() + 
						60 * stdin.nextInt() + stdin.nextInt() +
						60 * stdin.nextInt() + stdin.nextInt() +
						60 * stdin.nextInt() + stdin.nextInt());
			Arrays.sort(a);
			System.out.println(a[0].id);
			System.out.println(a[1].id);
			System.out.println(a[n-2].id);
		}
	}
}
