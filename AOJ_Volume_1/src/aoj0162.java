import java.util.*;
public class aoj0162 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 1000000;
	static final boolean[] isHammingNumber = new boolean[N+1];
	static final int[] LUT = new int[N+1];
	static final void init(){
		Arrays.fill(isHammingNumber, false);
		isHammingNumber[1] = true;
		LUT[0] = 0; LUT[1] = 1;
		for (int i = 2; i <= N; i++) {
			if(i%2 == 0 && isHammingNumber[i/2]) isHammingNumber[i] = true;
			if(i%3 == 0 && isHammingNumber[i/3]) isHammingNumber[i] = true;
			if(i%5 == 0 && isHammingNumber[i/5]) isHammingNumber[i] = true;
			LUT[i] = LUT[i-1] + (isHammingNumber[i] ? 1 : 0);
		}
	}
	public static void main(String[] args) {
		init();
		int n, m;
		while((n = stdin.nextInt()) != 0){
			m = stdin.nextInt();
			System.out.println(LUT[m]-LUT[n-1]);
		}
	}
}
