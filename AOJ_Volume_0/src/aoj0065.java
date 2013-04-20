import java.util.*;
public class aoj0065 {
	static final Scanner stdin = new Scanner(System.in);
	@SuppressWarnings("serial") public static class TreeFreqTable<K> extends TreeMap<K,Integer>{
		TreeFreqTable(){ super(); }
		public Integer add(K key) { return put(key, containsKey(key) ? get(key) + 1 : 1); }
	}
	public static void main(String[] args) {
		boolean[] flag = new boolean[1001];
		Arrays.fill(flag, false);
		TreeFreqTable<Integer> map = new TreeFreqTable<Integer>();
		while(true){
			String record = stdin.nextLine();
			if(record.isEmpty()) break;
			String[] temp = record.split(",");
			int id = Integer.parseInt(temp[0]);
			map.add(id);
		}
		while(stdin.hasNext()){
			String[] temp = stdin.nextLine().split(",");
			int id = Integer.parseInt(temp[0]);
			if(map.containsKey(id)){
				flag[id] = true;
				map.add(id);
			}
		}
		Iterator<Integer> itr = map.keySet().iterator();
		while(itr.hasNext()){
			int id = itr.next();
			if(flag[id]) System.out.println(id+" "+map.get(id));
		}
	}
}
