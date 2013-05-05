import java.util.*;
public class aoj0159 {
	static final Scanner stdin = new Scanner(System.in);
	static final double STANDARD = 22;
	static class T implements Comparable<T>{
		int id;
		double diffBMI;
		T(int id, int h, int w){ this.id = id; diffBMI = Math.abs(STANDARD - w/Math.pow(h/100.0, 2)); }
		@Override public int compareTo(T o) { return (diffBMI == o.diffBMI) ? id - o.id : (diffBMI > o.diffBMI ? 1 : -1); }
	}
	public static void main(String[] args) {
		int n;
		while((n = stdin.nextInt()) != 0){
			T[] a = new T[n];
			for (int i = 0; i < a.length; i++) a[i] = new T(stdin.nextInt(), stdin.nextInt(), stdin.nextInt());
			Arrays.sort(a);
			System.out.println(a[0].id);
		}
	}
}
