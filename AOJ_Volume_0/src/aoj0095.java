import java.util.*;
public class aoj0095 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int n = stdin.nextInt();
		List<int[]> l = new ArrayList<int[]>(n);
		while(n-->0) l.add(new int[]{stdin.nextInt(), stdin.nextInt()});
		Collections.sort(l, new Comparator<int[]>(){
			@Override public int compare(int[] o1, int[] o2) { return o1[1]!=o2[1] ? o2[1]-o1[1] : o1[0]-o2[0]; }
		});
		System.out.println(l.get(0)[0]+" "+l.get(0)[1]);
	}
}
