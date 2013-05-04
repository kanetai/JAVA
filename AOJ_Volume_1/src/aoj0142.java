import java.util.*;
public class aoj0142 {
	static final Scanner stdin = new Scanner(System.in); 
	public static void main(String[] args) {
		while(true){
			int n = stdin.nextInt();
			if(n == 0) break;
			int[] freq = new int[(n-1)/2+1];
			Arrays.fill(freq, 0);
			Set<Integer> set = new HashSet<Integer>();
			for(int i = 1; i < n; ++i) set.add(i*i%n);
			Integer[] a = set.toArray(new Integer[set.size()]);
			for(int i = 0; i < a.length; ++i){
				for(int j = 0; j < a.length; ++j){
					if(i == j) continue;
					int x = a[i] - a[j];
					if(x < 0) x += n;
					if(x > (n-1)/2) x = n - x;
					freq[x]++;
				}
			}
			for(int i = 1; i < freq.length; ++i) System.out.println(freq[i]);
		}
	}
}
