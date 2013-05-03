import java.util.*;
public class aoj0138 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		@SuppressWarnings("unchecked")
		TreeMap<Double, Integer>[] in = new TreeMap[4];
		for(int i = 0; i < 4; ++i) in[i] = new TreeMap<Double, Integer>();

		for(int i = 0; i < 24; ++i){
			int no = stdin.nextInt();
			in[i/8].put(stdin.nextDouble(), no);
		}
		for(int i = 0; i < 3; ++i){
			int j = 1;
			for(double t: in[i].keySet()){
				if(j <= 2) System.out.printf("%d %.2f\n", in[i].get(t), t);
				else in[3].put(t, in[i].get(t));
				j++;
			}
		}
		int j = 1;
		for(double t: in[3].keySet()){
			if(j > 2) break;
			System.out.printf("%d %.2f\n", in[3].get(t), t);
			j++;
		}
	}
}
