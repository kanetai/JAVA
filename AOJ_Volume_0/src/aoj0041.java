import java.util.*;

public class aoj0041 {
	static final Scanner stdin = new Scanner(System.in);
	static final char[] op = {'+', '-', '*' };
	public static void main(String[] args) {
		Integer[] a = new Integer[4]; 
		while(true){
			for(int i=0; i<4; ++i) a[i] = stdin.nextInt();
			if(a[0] == 0 && a[1] == 0 && a[2] == 0 && a[3] == 0 ) break;
			solve(a);
		}
	}
	static int calc(int a, int b, char o ){
		switch(o){
		case '+': return a+b;
		case '-': return a-b;
		case '*': return a*b;
		}
		return 0; //ここには来ない
	}
	static void solve(Integer[] a){
		Arrays.sort(a);
		do{
			for(int i = 0; i < 3; ++i) for(int j = 0; j < 3; ++j) for(int k = 0; k < 3; ++k){
				if(calc(calc(a[0], a[1], op[i]), calc(a[2], a[3], op[k]), op[j]) == 10 ){
					System.out.printf("(%d%c%d)%c(%d%c%d)\n", a[0], op[i], a[1], op[j], a[2], op[k], a[3] );
					return;
				}else if(calc(calc(calc(a[0], a[1], op[i]), a[2], op[j]), a[3], op[k]) == 10 ){
					System.out.printf("((%d%c%d)%c%d)%c%d\n", a[0], op[i], a[1], op[j], a[2], op[k], a[3] );
					return;
				}else if(calc(calc(a[0], calc(a[1], a[2], op[j]), op[i]), a[3], op[k]) == 10 ){
					System.out.printf("(%d%c(%d%c%d))%c%d\n", a[0], op[i], a[1], op[j], a[2], op[k], a[3] );
					return;
				}else if(calc(a[0],calc(calc(a[1],a[2],op[j]),a[3], op[k]),op[i]) == 10 ){
					System.out.printf("%d%c((%d%c%d)%c%d)\n", a[0], op[i], a[1], op[j], a[2], op[k], a[3] );
					return;
				}else if(calc(a[0], calc(calc(a[1],a[2], op[j]), a[3], op[k]), op[i]) == 10 ){
					System.out.printf("%d%c(%d%c%(d%c%d))\n", a[0], op[i], a[1], op[j], a[2], op[k], a[3] );
					return;
				}
			}
		}while(nextPermutation(a));
		System.out.println(0);
	}
	public static <T> boolean nextPermutation(T[] a, int fromIndex, int toIndex, final Comparator<? super T> c){
		for (int i = toIndex - 1; i > fromIndex; --i) {
			if (c.compare(a[i-1], a[i]) < 0) { //[i,toIndex) is arranged in descending order.
				int swapIndex = toIndex;
				while (c.compare(a[i-1], a[--swapIndex]) >= 0);
				//int swapIndex = lowerBound(a, i, toIndex, a[i - 1], new Comparator<T>(){ @Override public int compare(T o1, T o2) { return -(c.compare(o1, o2)); }}) - 1;
				swap(a, i-1, swapIndex);
				reverse(a, i, toIndex);
				return true;
			}
		}
		reverse(a, fromIndex, toIndex); //turn back
		return false;
	}
	public static <T extends Comparable <? super T>> boolean nextPermutation(T[] a){
		return nextPermutation(a, 0, a.length, new Comparator<T>(){
			@Override public int compare(T o1, T o2) { return o1.compareTo(o2); }
		});
	}
	public static Object[] swap(Object[] a, int i, int j){ Object tmp = a[i]; a[i] = a[j]; a[j] = tmp; return a; }
	public static void reverse(Object[] a, int fromIndex, int toIndex) {
		for(int n = toIndex - fromIndex, mid = n>>1, i = 0, j = n-1; i < mid; ++i, --j)
			swap(a, fromIndex+i, fromIndex+j); 
	}
}
