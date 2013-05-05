import java.util.*;
public class aoj0205 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 3, M = 5, WIN = 1, LOSE = 2, DRAW = 3;
	public static void main(String[] args) {
		int[] freq = new int[N], data = new int[M];
		while((data[0] = stdin.nextInt()-1) != -1){
			Arrays.fill(freq, 0);
			freq[data[0]]++;
			for(int i = 1; i < M; ++i){
				data[i] = stdin.nextInt()-1;
				freq[data[i]]++;
			}
			int temp = 0, a = -1, b = -1;
			for(int i = 0; i < N; ++i){
				if(freq[i] > 0){
					temp++;
					if(a == -1) a = i;
					else b = i;
				}
			}
			if(temp == 2) if((a+1) != b){ temp = a; a = b; temp = b; }
			for(int d: data)
				System.out.println(temp == 2 ? (d == a ? WIN : LOSE) : DRAW);
		}
	}
}
