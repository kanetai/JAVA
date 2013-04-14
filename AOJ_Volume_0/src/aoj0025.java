import java.util.*;
public class aoj0025 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int[] a = new int[4], b = new int[4];
		while(stdin.hasNext()){
			int hit = 0, blow = 0;
			for(int i=0; i<4; ++i) a[i] = stdin.nextInt();
			for(int i=0; i<4; ++i){
				b[i] = stdin.nextInt();
				if(a[i] == b[i]) ++hit;
			}
			Arrays.sort(a); Arrays.sort(b);
			int ai = 0, bi =0;
			while( ai < a.length && bi < b.length ){
				if(a[ai] == b[bi]){ ++blow; ++ai; ++bi; }
				else if(a[ai] > b[bi]) ++bi;
				else ++ai;
			}
			blow -= hit;
			System.out.println(hit + " " + blow);
		}
	}
}
