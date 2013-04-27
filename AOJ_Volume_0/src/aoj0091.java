import java.util.*;
import java.awt.Point;
public class aoj0091 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 10, M = 12, N_SIZE = 3, blur[][] = new int[N][N], minimul_count[]={13, 9, 5};
	static final Data[] history = new Data[M];
	static int COUNT, TOTAL, total;
	public static void main(String[] args) {
		COUNT = stdin.nextInt();
		for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) {
			blur[i][j] = stdin.nextInt(); TOTAL += blur[i][j];
		}
		total = TOTAL;
		solve(new Data(),0);
		for(int i = 0; i < COUNT; ++i) System.out.println(history[i].toString());
	}
	static final Point delta[][] = {
		{
			new Point(0,0), new Point(-1,0), new Point(0,-1), new Point(1,0), new Point(0,1),
			new Point(-1,-1), new Point(1,-1), new Point(1,1), new Point(-1,1),
			new Point(-2,0), new Point(0,-2), new Point(2,0), new Point(0,2)
		}, {
			new Point(0,0), new Point(-1,0), new Point(0,-1), new Point(1,0), new Point(0,1),
			new Point(-1,-1), new Point(1,-1), new Point(1,1), new Point(-1,1)
		}, {
			new Point(0,0), new Point(-1,0), new Point(0,-1), new Point(1,0), new Point(0,1)
		}
	};
	static final Point target[]={ new Point(0,2), new Point(1,1), new Point(0,1) };
	static class Data {
		int x, y, size;
		Data(){}
		Data(int x, int y, int size){ this.x = x; this.y = y; this.size = size; }
		@Override public String toString(){ return x+" "+y+" "+(N_SIZE-size); }
	};
	static final boolean canSub(Data data){
		for(Point d: delta[data.size]){
			Data p = new Data(data.x+d.x, data.y+d.y, data.size);
			if(!(0 <= p.x && p.y < N && 0 <= p.x && p.x < N && blur[p.y][p.x] != 0)) return false;
		}
		return true;
	}
	static final void add(Data d){
		for(Point p: delta[d.size]){ ++blur[d.y+p.y][d.x+p.x]; ++total;}
	}
	static final void sub(Data d){
		for(Point p: delta[d.size]){ --blur[d.y+p.y][d.x+p.x]; --total;}
	}
	static final boolean solve(Data b, int d){
		if(d == COUNT && total == 0) return true;
		Point p = new Point(-1,-1);
		LOOP: for(int y = 0; y < N; ++y)
			for(int x = 0; x < N; ++x)
				if(blur[y][x] > 0){ p.x = x; p.y = y; break LOOP; }
		if(p.x < N-1 && p.y < N-1 && p.x != -1){
			for(int size = 0; size < N_SIZE; ++size){
				if(minimul_count[size] > total) continue;
				Data data = new Data(p.x + target[size].x, p.y + target[size].y, size);
				if(canSub(data)){
					sub(data);
					history[d] = data;
					if(solve(data, d+1)) return true;
				}
			}
		}
		add(b);
		return false;
	}
}
