import java.util.*;
public class aoj0085 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(stdin.hasNext()){
			int n = stdin.nextInt(), m = stdin.nextInt();
			if(n==0 && m==0) break;
			ArrayList<Integer> l = new ArrayList<Integer>();
			for(int i = 0; i < n; ++i) l.add(i+1);
			int idx = 0;
			while(l.size() > 1){
				idx = (idx + m-1)%l.size();
				l.remove(idx);
				idx %= l.size();
			}
			System.out.println(l.get(0));
		}
	}
}
