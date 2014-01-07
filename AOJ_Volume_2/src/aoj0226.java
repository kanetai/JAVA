import java.util.*;
public class aoj0226 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
			char[] a = stdin.next().toCharArray(), b = stdin.next().toCharArray();
			if (a[0] == '0' && b[0] == '0') break;
			int hit = 0, blow = 0;
			for(int i=0; i < a.length; ++i) if(a[i] == b[i]) ++hit;
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
