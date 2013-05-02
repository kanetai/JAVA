import java.util.*;
public class aoj0118 {
	static final Scanner stdin = new Scanner(System.in);
	static class Point{ int i,j; Point(int i, int j){ this.i = i; this.j = j; }}
	public static void main(String[] args) {
		while(true){
			int H = stdin.nextInt(), W = stdin.nextInt(); 
			if(H == 0 && W == 0) break;
			stdin.nextLine();
			char[][] map = new char[H+2][W+2];
			for(int i = 0; i < H+2; ++i) Arrays.fill(map[i], ' ');
			for(int i = 0; i < H; ++i){
				char[] line = stdin.nextLine().toCharArray();
				for(int j = 0; j < W; ++j) map[i+1][j+1] = line[j];
			}
			int ans = 0;
			for(int i = 1; i <= H; ++i){
				for(int j = 1; j <= W; ++j){
					if(map[i][j] != ' '){
						BFS(i,j, map);
						ans++;
					}
				}
			}
			System.out.println(ans);
		}
	}
	static void BFS(int i, int j, char[][] map){
		char c = map[i][j];
		LinkedList<Point> q = new LinkedList<Point>();
		q.add(new Point(i,j));
		while(!q.isEmpty()){
			Point p = q.poll();
			map[p.i][p.j] = ' ';
			if(map[p.i+1][p.j] == c) q.push(new Point(p.i+1, p.j));
			if(map[p.i-1][p.j] == c) q.push(new Point(p.i-1, p.j));
			if(map[p.i][p.j+1] == c) q.push(new Point(p.i, p.j+1));
			if(map[p.i][p.j-1] == c) q.push(new Point(p.i, p.j-1));
		}
	}
}
