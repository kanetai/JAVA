import java.util.*;
public class aoj0100 {
	static final Scanner stdin = new Scanner(System.in);
	static final long MAX = 1000000;
	public static void main(String[] args) {
		while(true){
			int n = stdin.nextInt(); stdin.nextLine();
			if(n==0) break;
			ArrayList<String> order = new ArrayList<String>(n);
			HashMap<String, Long> table = new HashMap<String, Long>(n);
			for(int i = 0; i < n; ++i){
				String[] line = stdin.nextLine().split(" ");
				long v = Long.parseLong(line[1]) * Long.parseLong(line[2]);
				if(!table.containsKey(line[0])){
					order.add(line[0]);
					if(v > MAX) v = MAX;
				}else{
					v += table.get(line[0]);
					if(v > MAX) v = MAX;
				}
				table.put(line[0], v);
			}
			boolean flag = false;
			for(String id: order){
				long v = table.get(id);
				if(v == MAX){
					System.out.println(id);
					flag = true;
				}
			}
			if(!flag) System.out.println("NA");
		}
	}
}
