import java.util.*;
public class aoj0163 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 7;
	static final int[][] table = new int[][]{
		{0, 300, 500, 600, 700, 1350, 1650}, 
		{6,   0, 350, 450, 600, 1150, 1500}, 
		{13,  7,   0, 250, 400, 1000, 1350}, 
		{18,  12,  5,   0, 250,  850, 1300}, 
		{23,  17, 10,   5,   0,  600, 1150}, 
		{43,  37, 30,  25,  20,    0,  500}, 
		{58,  52, 45,  40,  35,   15,    0}
	};
	static final int length[][] = new int[N][N];
	static final int fee[][] = new int[N][N];
	static final int A = 17*60+30; //17h30m
	static final int B = 19*60+30; //19h30m
	static final void init(){
		for (int i = 0; i < N; i++)
			for (int j = i; j < N; j++) {
				fee[i][j] = fee[j][i] = table[i][j];
				length[i][j] = length[j][i] = table[j][i];
			}
	}
	static final int discount(int fee){
		fee /= 2;
		int temp = fee % 50;
		if(temp > 0) fee += 50 - temp;
		return fee;
	}
	public static void main(String[] args) {
		init();
		int src, dst, stime, dtime;
		while((src = stdin.nextInt()-1) >= 0){
			stime = stdin.nextInt()*60 + stdin.nextInt();
			dst = stdin.nextInt()-1;
			dtime = stdin.nextInt()*60 + stdin.nextInt();
			System.out.println(
					(length[src][dst] <= 40 && (A <= stime && stime <= B) || (A <= dtime && dtime <= B)) ? 
							discount(fee[src][dst]) : fee[src][dst]
					);
		}
	}
}
