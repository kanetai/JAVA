import java.util.*;
public class aoj0140 {
	static final Scanner stdin = new Scanner(System.in);
	static final char[] pat = "012345678954321".toCharArray();
	static void print(ArrayList<Integer> l){
		for(int i = 0; i < l.size(); ++i){
			System.out.print(l.get(i));
			System.out.print(i < l.size()-1 ? " " : "\n");
		}
	}
	public static void main(String[] args) {
		int n = stdin.nextInt();
		while(n-- > 0){
			int start = stdin.nextInt(), goal = stdin.nextInt();
			int cur = start;
			ArrayList<Integer> r = new ArrayList<Integer>(), l = new ArrayList<Integer>();
			while(Integer.parseInt(""+pat[cur]) != goal){
				r.add(Integer.parseInt(""+pat[cur]));
				cur = (cur + 1) % pat.length;
			}
			r.add(goal);
			if(start <= 5 && start != 0){
				cur = 15-start;
				while(Integer.parseInt(""+pat[cur]) != goal){
					l.add(Integer.parseInt(""+pat[cur]));
					cur = (cur + 1) % pat.length;
				}
				l.add(goal);
				print(r.size() < l.size() ? r : l);
			}else{
				print(r);
			}
		}
	}
}
