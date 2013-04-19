import java.util.*;

public class aoj0043 {
	static final Scanner stdin =  new Scanner(System.in);
	static final ArrayList<String> pat = new ArrayList<String>(); //順子、刻子のパターン
	public static void main(String[] args) { 
		for(int i=0;i<2;++i)for(int j=0;j<2;++j)for(int k=0;k<2;++k)for(int l=0;l<2;++l)
			pat.add(""+i+j+k+l);
		while(stdin.hasNext()){
			TreeFreqTable<Integer> hand = new TreeFreqTable<Integer>();
			String line = stdin.nextLine();
			for(int i=0; i<13; ++i) hand.add(Integer.parseInt(""+line.charAt(i)));
			System.out.println( solve(hand) );
		}
	}
	@SuppressWarnings("serial") public static class TreeFreqTable<K> extends TreeMap<K,Integer>{
		TreeFreqTable(){ super(); }
		TreeFreqTable(TreeFreqTable<K> f){ super((TreeMap<K, Integer>)f); }
		public Integer add(K key) { return put(key, containsKey(key) ? get(key) + 1 : 1); }
	}
	static private boolean remove(TreeFreqTable<Integer> hand, int k){ //頻度減算 減算できなかったらfalse
		if(!hand.containsKey(k)) return false;
		int n = hand.get(k);
		if(n==1) hand.remove(k);
		else hand.put(k, n-1); //n>0
		return true;
	}
	static String solve(TreeFreqTable<Integer> hand){
		StringBuilder res = new StringBuilder();
		for(int i=1; i<10; ++i){ //ツモ　i
			if(hand.containsKey(i) && hand.get(i) >= 4) continue;
			hand.add(i);
			FOUND:
				for(int j: hand.keySet()){ //雀頭 j
					if(hand.get(j)<2) continue;
					for(int k=0; k<pat.size(); ++k){ //順子/刻子のパターン
						if( check(pat.get(k), hand, j)){
							res.append(' '); res.append(i);
							break FOUND;
						}
					}
				}
			remove(hand, i);
		}
		if(res.length()==0) return "0";
		return res.substring(1);
	}
	static boolean check(String p, TreeFreqTable<Integer> hand, int j){
		TreeFreqTable<Integer>  temp = new TreeFreqTable<Integer>(hand);
		remove(temp, j); remove(temp, j);
		for(int k=0; k<p.length(); ++k){
			int key = temp.firstKey();
			switch(p.charAt(k)){
			case '0': //刻子
				if(temp.get(key)<3) return false;
				remove(temp,key);remove(temp, key);remove(temp, key);
				break;
			case '1': //順子
				if(!remove(temp, key) || !remove(temp, key+1) || !remove(temp,key+2)) return false;
				break;
			}
		}
		return true;
	}
}
