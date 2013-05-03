import java.util.*;
public class aoj0121 {
	static final Scanner stdin = new Scanner(System.in);
	static final String INISTR = "01234567";
	static final int N = INISTR.length();
	static final HashMap<String, Integer> map = new HashMap<String, Integer>();
	static final int[] offset = {-1, 1, +4, -4};
	public static void main(String[] args) { 
		BFS();
		while(stdin.hasNext()){
			StringBuilder line = new StringBuilder(N);
			for(int i = 0; i < N; ++i) line.append(stdin.nextInt());
			System.out.println(map.get(line.toString()));
		}
	}
	static String swap(String str, int i, int j){
		StringBuilder chars = new StringBuilder(str);
		char c = chars.charAt(i);
		chars.setCharAt(i, chars.charAt(j));
		chars.setCharAt(j, c);
		return chars.toString();
	}
	static class Node{
		String state;
		int count;
		int pos0;
		Node(String state, int count, int pos0){this.state = state; this.count = count; this.pos0 = pos0;}
	}
	static void BFS(){
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(INISTR, 0, 0));
		map.put(INISTR, 0);
		while(!q.isEmpty()){
			Node n = q.poll();
			for(int d: offset){
				int j = n.pos0 + d;
				if(j >=0 && j < N && !(n.pos0 == 3 && d == 1 || n.pos0 == 4 && d == -1) ){	
					String next = swap(n.state, n.pos0, j);
					if(!map.containsKey(next)){
						map.put(next, n.count + 1);
						q.add(new Node(next, n.count + 1, j));
					}
				}
			}
		}
	}
}
