import java.util.*;
public class aoj0061 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		TreeMap<Integer, Integer>rmap = new TreeMap<Integer, Integer>(Collections.reverseOrder());
		while(true){
			String[] record = stdin.nextLine().split(",");
			if(record[0].equals("0") && record[1].equals("0")) break;
			int num = Integer.parseInt(record[1]);
			map.put(record[0], num);
			rmap.put(num, 1);
		}
		Iterator<Integer> itr = rmap.keySet().iterator();
		int rank = 1;
		while(itr.hasNext()) rmap.put(itr.next(), rank++);
		while(stdin.hasNext()) System.out.println( rmap.get( map.get(stdin.nextLine()) ) );
	}
}
