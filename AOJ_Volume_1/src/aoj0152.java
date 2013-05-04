import java.util.*;
public class aoj0152 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int n;
		while((n=stdin.nextInt()) != 0){
			stdin.nextLine();
			T[] record = new T[n];
			for(int i = 0; i < n; ++i) record[i] = new T(stdin.nextLine().split(" "));
			Arrays.sort(record);
			for(T t: record) System.out.println(t.id + " " + t.sum);
		}
	}
	static class T implements Comparable<T>{
		int sum, id;
		@Override public int compareTo(T o2) { return sum != o2.sum ? o2.sum - sum : id - o2.id; }
		T(String[] in){
			int[] frame = new int[10];
			id = Integer.parseInt(in[0]);
			int[] input = new int[in.length-1];
			for(int i = 0; i < input.length; ++i) input[i] = Integer.parseInt(in[i+1]);
			sum = 0;
			for(int i = 0, p = 0; p < 10; ++p){
				frame[p] = input[i] + input[i+1];
				if(p == 9){ //第10フレーム
					if(frame[p] >= 10) frame[p] += input[i+2]; //スペア or ストライク
				}else if(input[i] == 10){ //ストライク
					frame[p] += input[i+2];
					++i;
				}else{
					if(frame[p] == 10) frame[p] += input[i+2]; //スペア
					i+=2;
				}
				sum += frame[p];
			}
		}
	}
}
