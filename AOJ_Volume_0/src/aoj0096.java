import java.util.*;
public class aoj0096 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 4000, M = 1000, AB[] = new int[N+1], LUT[] = new int[N+1];
	public static void main(String[] args) {
		for(int a = 0; a <= M; ++a) for(int b = 0; b <= M; ++b) AB[a+b]++;
		for(int i = 0; i <= N; ++i)
			for(int cpd = 0; cpd <= M+M; ++cpd){
				int apb = i - cpd;
				if(0 <= apb && apb <= N && AB[apb]> 0 && AB[cpd] > 0) LUT[i] += (AB[apb]*AB[cpd]);
			}
		while(stdin.hasNext()) System.out.println(LUT[stdin.nextInt()]);
	}
}
