import java.util.*;
public class aoj0099 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 1000000, A[] = new int[N+1];
	public static void main(String[] args) {
		stdin.nextInt();
		int q = stdin.nextInt();
		PriorityQueue<int[]> Q = new PriorityQueue<int[]>(N, new Comparator<int[]>(){
			@Override public int compare(int[] o1, int[] o2) {
				return o1[1] != o2[1] ? o2[1] - o1[1] : o1[0] - o2[0];
			}
		});
		while(q-- > 0){
			int a = stdin.nextInt(), v = stdin.nextInt();
			Q.add(new int[]{a,(A[a] += v)});
			while(A[Q.peek()[0]] != Q.peek()[1]) Q.poll();
			System.out.println(Q.peek()[0] + " " + Q.peek()[1]);
		}
	}
}
