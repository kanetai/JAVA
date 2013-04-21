import java.util.*;
import java.awt.Point;
public class aoj0071 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) { 
		int n = stdin.nextInt();
		char[][] map = new char[8][8];
		for(int i = 1; i <= n; ++i){
			for(int j = 0; j < 8; ++j) map[j] = stdin.next().toCharArray();
			BFS(map, stdin.nextInt()-1, stdin.nextInt()-1);
			System.out.println("Data "+i+":");
			for(int j = 0; j < 8; ++j) System.out.println(map[j]);
		}
	}
	static void BFS(char[][] map, int x, int y){
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(x,y));
		while(!q.isEmpty()){
			Point p = q.poll();
			map[p.y][p.x] = '0';
			for(int i = -3; i <= 3; ++i){
				if(0 <= p.y+i && p.y+i < 8 && map[p.y+i][p.x] == '1') q.add(new Point(p.x, p.y+i)); 
				if(0 <= p.x+i && p.x+i < 8 && map[p.y][p.x+i] == '1') q.add(new Point(p.x+i, p.y)); 
			}
		}
	}
}
