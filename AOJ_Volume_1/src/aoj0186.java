import java.util.*;
public class aoj0186 {
	static final Scanner stdin = new Scanner(System.in);
	static final int[] q = new int[2], c = new int[2], a = new int[2];
	static int b;
	public static void main(String[] args) {
		while((q[0] = stdin.nextInt()) != 0){
			b = stdin.nextInt(); c[0] = stdin.nextInt(); c[1] = stdin.nextInt(); q[1] = stdin.nextInt();
			int i = upperBound(1, q[1]+1)-1;
			System.out.println(check(i) ? i+" "+(b-c[0]*i)/c[1] : "NA");
		}
	}
	static final boolean check(int i){
		int j = (b-c[0]*i)/c[1];
		return b-c[0]*i-c[1]*j >= 0 && i+j >= q[0] && j >= 0 && i > 0 && i <= q[1];
	}
	public static int upperBound(int begin, int end){
		int lb = begin-1, ub = end;
		//解の存在範囲が１より大きい間、反復
		while(ub - lb > 1){
			int mid =(lb + ub) / 2;
			if( check(mid) )	//midが条件を満たせば、解の存在範囲は[mid,ub)
				lb = mid;
			else		//mが条件を満たさなければ、解の存在範囲は[lb,mid)
				ub = mid;
		}
		return ub; 		//ub = lb + 1, [lb, ub)
	}
}
