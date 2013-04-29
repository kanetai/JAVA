import java.util.*;
public class aoj0104 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
			int n = stdin.nextInt(), m = stdin.nextInt();
			if(n == 0 && m ==0) break;
			char[][] map = new char[n][];
			for(int i = 0; i < n; ++i) map[i] = stdin.next().toCharArray();
			int i = 0, j = 0;
			END: while(true){
				switch(map[i][j]){
				case '<': map[i][j--] = ' '; break;
				case '>': map[i][j++] = ' '; break;
				case '^': map[i--][j] = ' '; break;
				case 'v': map[i++][j] = ' '; break;
				case ' ': System.out.println("LOOP"); break END;
				case '.': System.out.println(j+" "+i); break END;
				}
			}
		}
	}
}
