import java.util.*;
public class aoj0124 {
	static final Scanner stdin = new Scanner(System.in);
	static class Record{
		String name;
		int point;
		Record(String name, int point){ this.name = name; this.point = point; }
	}
	public static void main(String[] args) {
		boolean flag = false;
		while(true){
			int n = stdin.nextInt(); 
			if(n == 0) break;
			if(flag) System.out.println("");
			flag = true;
			Record[] a = new Record[n];
			for(int i = 0; i < n; ++i)
				a[i] = new Record(stdin.next(), 3*stdin.nextInt() + 0*stdin.nextInt() + stdin.nextInt());
			Arrays.sort(a, new Comparator<Record>(){
				public int compare(Record o1, Record o2){
					return o1.point > o2.point ? -1 : o1.point == o2.point ? 0 : 1;
				}
			});
			for(Record r: a) System.out.println(r.name + "," + r.point);
		}
	}
}
