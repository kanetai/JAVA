import java.util.*;
public class aoj0196 {
	static final Scanner stdin = new Scanner(System.in);
	static final int WIN = 0, LOSE = 1, DRAW = 2;
	static class T implements Comparable<T>{
		String name;
		int id;
		int freq[] = {0,0,0};
		T(String[] data, int id){
			name = data[0];
			this.id = id;
			for(int i = 1; i < data.length; ++i) freq[Integer.parseInt(data[i])]++;
		}
		@Override public int compareTo(T o) {
			return	freq[WIN] != o.freq[WIN] ? o.freq[WIN] - freq[WIN] : 
				freq[LOSE] != o.freq[LOSE] ? freq[LOSE] - o.freq[LOSE] : id - o.id;
		}
	}
	public static void main(String[] args) {
		int n;
		while((n = stdin.nextInt()) != 0){
			T[] data = new T[n]; stdin.nextLine();
			for(int i = 0; i < n; ++i) data[i] = new T(stdin.nextLine().split(" "), i);
			Arrays.sort(data);
			for(T t: data) System.out.println(t.name);
		}
	}
}
