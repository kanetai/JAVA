import java.awt.geom.Point2D;
import java.util.*;
public class aoj0269 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			int H = stdin.nextInt(), R = stdin.nextInt();
			if ((H|R) == 0) break;
			Point[] house = new Point[H];
			for (int i = 0; i < H; ++i) house[i] = new Point(stdin.nextDouble(), stdin.nextDouble());
			T[][] t = new T[3][];
			t[0] = new T[stdin.nextInt()];
			t[1] = new T[stdin.nextInt()];
			t[2] = new T[stdin.nextInt()];
			int[] ds = {stdin.nextInt(), stdin.nextInt(), stdin.nextInt()};
			for (int i = 0; i < t.length; ++i)
				for (int j = 0; j < t[i].length; ++j) t[i][j] = new T(stdin.nextInt(), stdin.nextInt(), ds[i]);
			T myU = new T(0, 0, ds[0]);
			int freq[][] = new int[H][2];
			for (int i = 0; i < H; ++i) freq[i][1] = i+1;
			for (int k = 0; k < R; ++k) {
				int w = stdin.nextInt(), a = stdin.nextInt();
				myU.input(w, a);
				for (int i = 0; i < t.length; ++i) for (int j = 0; j < t[i].length; ++j) t[i][j].input(w, a);
				LOOP: for (int h = 0; h < H; ++h) {
					if (!myU.contain(house[h])) continue;
					for (int i = 0; i < t.length; ++i) for (int j = 0; j < t[i].length; ++j) 
						if (t[i][j].contain(house[h])) continue LOOP;
					freq[h][0]++;
				}
			}
			Arrays.sort(freq, new Comparator<int[]>(){
				@Override public int compare(int[] o1, int[] o2) {
					return o1[0] != o2[0] ? o2[0] - o1[0] : o1[1] - o2[1];
				}
			});
			StringBuilder sb = new StringBuilder();
			if (freq[0][0] == 0) {
				sb.append("NA");
			} else { 
				sb.append(freq[0][1]);
				int m = freq[0][0];
				for (int i = 1; i < H; ++i) {
					if (freq[i][0] == m) sb.append(" ").append(freq[i][1]);
					else break;
				}
			}
			System.out.println(sb.toString());
		}
	}
	static class T {
		Point[] tri = new Point[3];
		int d, a;
		T(int x, int y, int d) {
			this.d = d;
			tri[0] = new Point(x, y);
		}
		void input(int w, int a) {
			this.a = a;
			double theta = Math.toRadians(w-d/2);
			tri[1] = new Point(Math.cos(theta), Math.sin(theta));
			theta = Math.toRadians(w+d/2);
			tri[2] = new Point(Math.cos(theta), Math.sin(theta));
		}
		boolean contain(Point p) { 
			return leq(p.distance(tri[0]), a) && 
				leq(0, tri[1].cross(p.sub(tri[0]))) && leq(0, p.sub(tri[0]).cross(tri[2]));
		}
	}
	public static final double EPS = 1e-9;
	public static boolean leq(double a, double b){ return a - b < EPS; }			// a <= b
	@SuppressWarnings("serial") public static class Point extends Point2D.Double {
		public Point(double x, double y){ super(x,y); }
		public final Point sub(Point p){ return new Point( x - p.x, y - p.y ); }
		public final double cross(Point p){ return x * p.y - y * p.x; }
	} //class Point
}
