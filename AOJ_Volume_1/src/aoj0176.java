import java.util.*;
public class aoj0176 {
	static final Scanner stdin = new Scanner(System.in);
	static final int color[][] = { {0, 0, 0}, {0, 0, 255}, {0, 255,0}, {0, 255,255}, {255, 0, 0}, {255, 0,255}, {255,255,0}, {255,255,255} };
	static final String name[] = {"black", "blue", "lime", "aqua", "red", "fuchsia", "yellow", "white"};
	static final int dist(int[] a, int[] b){
		int ret = 0;
		for(int i = 0; i < 3; ++i) ret += (a[i]-b[i])*(a[i]-b[i]);
		return ret;
	}
	public static void main(String[] args) {
		String s;
		while(!(s = stdin.nextLine()).equals("0")){
			int c = -1, m = Integer.MAX_VALUE;
			for(int i = 0; i < color.length; ++i){
				int d = dist(color[i], new int[]{ Integer.parseInt(s.substring(1, 3), 16), Integer.parseInt(s.substring(3, 5), 16), Integer.parseInt(s.substring(5, 7), 16) });
				if(d < m){ c = i; m = d; }
			}
			System.out.println(name[c]);
		}
	}
}
