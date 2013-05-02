import java.util.*;
public class aoj0119 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int m = stdin.nextInt(), n = stdin.nextInt();
		@SuppressWarnings("unchecked") Set<Integer>[] list = new HashSet[m];
		for(int i = 0; i < m; ++i) list[i] = new HashSet<Integer>();
		for(int i = 0; i < n; ++i) {
			Integer dst = stdin.nextInt()-1, src = stdin.nextInt()-1;
			list[src].add(dst);
		}
		boolean flag = true;
		while(flag){
			flag = false;
			for(int i = 0; i < m; ++i){
				if(list[i] == null) continue;
				if(list[i].isEmpty()){
					flag = true;
					list[i] = null;
					System.out.println(i+1);
					for(int j = 0; j < m; ++j)
						if(list[j] != null && list[j].contains(i)) list[j].remove(i);
				}
			}
		}
	}
}
