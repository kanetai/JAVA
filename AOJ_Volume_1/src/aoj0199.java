import java.util.*;
public class aoj0199 {
	static final Scanner stdin = new Scanner(System.in);
	static final void A(char[] Seat){ for(int i = 0; i < Seat.length; ++i) if(Seat[i] == '#'){ Seat[i] = 'A'; return; } }
	static final void B(char[] Seat){
		int n = Seat.length;
		for(int i = n-1; i >= 0; --i){
			if(Seat[i] == '#'){
				if(i+1 < n && Seat[i+1] == 'A' || i-1 >= 0 && Seat[i-1] == 'A') continue;
				Seat[i] = 'B'; return;
			}
		}
		for(int i = 0; i < n; ++i) if(Seat[i] == '#'){ Seat[i] = 'B'; return; }
	}
	static final void C(char[] Seat){
		int n = Seat.length;
		for(int i = 0; i < n; ++i)
			if(Seat[i] != '#'){
				if(i+1 < n && Seat[i+1] == '#'){ Seat[i+1] = 'C'; return; }
				if(i-1 >= 0 && Seat[i-1] == '#'){ Seat[i-1] = 'C'; return; }
			}
		Seat[n/2] = 'C'; //場合分けの必要なし
	}
	static final void D(char[] Seat){
		int n = Seat.length, count = 0, max = -1, mi = -1;
		int[] dist = new int[n];
		for(int i = n - 1; i >= 0; --i) dist[i] = (Seat[i] == '#' ? ++count : (count = 0)); //右の人までの距離を計算
		count = 0;
		for(int i = 0; i < n; ++i){
			count = (Seat[i] == '#' ? count+1 : 0); //左の人までの距離を計算
			dist[i] = (i == 0 || i == n-1) ? 
					Math.max(dist[i], count) : Math.min(dist[i], count); //端っこならmax, そうじゃないときはmin
					if(max < dist[i]){ max = dist[i]; mi = i; }
		}
		Seat[mi] = 'D';
	}
	public static void main(String[] args) {
		while(true){
			int n = stdin.nextInt(), m = stdin.nextInt();
			if((n|m) == 0) break;
			char[] Seat = new char[n]; Arrays.fill(Seat, '#');
			for(int i = 0; i < m; ++i){
				switch(stdin.next().toCharArray()[0]){
				case 'A': A(Seat); break;
				case 'B': B(Seat); break;
				case 'C': C(Seat); break;
				case 'D': D(Seat); break;
				}
			}
			System.out.println(Seat);
		}
	}
}
