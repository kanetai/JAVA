import java.util.*;
public class aoj0181 {
	static final Scanner stdin = new Scanner(System.in);
	static final int MAX = 1500000;
	static int m, n;
	static int[] a;
	public static void main(String[] args) {
		while(true){
			m = stdin.nextInt(); n = stdin.nextInt();
			if((m|n) == 0) break;
			a = new int[n];
			for(int i = 0; i < n; ++i) a[i] = stdin.nextInt();
			System.out.println(lowerBound());
		}
	}
	public static boolean func(int v){
		int c = 0, d = 1;
		for(int i = 0; i < n; ++i){
			if((c+=a[i]) > v){
				if((c = a[i]) > v) return false;
				if(++d > m) return false;
			}
		}
		return true;
	}
	public static int lowerBound(){
		int lb = 0, ub = MAX;
		//解の存在範囲が１より大きい間、反復
		while(ub - lb > 1){
			int mid = (lb + ub) / 2;
			if( func(mid) ) 	//midが条件を満たせば、解の存在範囲は(lb,mid]
				ub = mid;
			else		//midが条件を満たさなければ、解の存在範囲は(mid,ub]
				lb = mid;
		}
		return ub; 		//(lb, ub], ub = lb + 1 → [ub, ub+1)
	}
}
