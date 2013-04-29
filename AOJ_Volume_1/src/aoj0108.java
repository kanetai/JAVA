import java.util.*;
public class aoj0108 {
	static final Scanner stdin = new Scanner(System.in);
	static void add(HashMap<Integer, Integer> h, int x){ h.put(x, h.containsKey(x) ? h.get(x)+1 : 1); }
	public static void main(String[] args) {
		int n;
		while((n = stdin.nextInt()) > 0){
			int[][] l = new int[2][n];
			HashMap<Integer, Integer> h = new HashMap<Integer, Integer>(n);
			for(int i = 0; i < n; ++i){
				l[0][i] = stdin.nextInt();
				add(h, l[0][i]);
			}
			int count = 0;
			int cur = 1, pre = 0;
			while(true){
				for(int i = 0; i < n; ++i) l[cur][i] = h.get(l[pre][i]);
				h.clear();
				boolean flag = true;
				for(int i = 0; i < n; ++i){
					add(h,l[cur][i]);
					if(l[cur][i] != l[pre][i]) flag = false;
				}
				if(flag) break;
				cur = (cur + 1) % 2;
				pre = (pre + 1) % 2;
				count++;
			}
			System.out.println(count);
			for(int i = 0; i < n-1; ++i) System.out.print(l[cur][i] + " ");
			System.out.println(l[cur][n-1]);
		}
	}
}
