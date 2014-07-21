import java.util.*;
public class aoj0272 {
    static final Scanner stdin = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            int n = stdin.nextInt();
            if (n == 0) break;
            System.gc();
            Integer[] a = new Integer[n], b = new Integer[n];
            for (int i = 0; i < n; ++i) a[i] = stdin.nextInt();
            for (int i = 0; i < n; ++i) b[i] = stdin.nextInt();
            Arrays.sort(a, Collections.reverseOrder());
            Arrays.sort(b, Collections.reverseOrder());
            int k = n;
            for (int i = 0, bNotFail = 0; i < n; ++i) {
                k = i+1;
                int pos = lowerBound(b, a[i]-1, Collections.reverseOrder())-1;
                if (bNotFail <= pos) ++bNotFail;
                if ((k - bNotFail) > k/2) break;
            }
            System.out.println(k < n ? k : "NA");
        }
    }
    public static <T> int lowerBound(T[] a, int fromIndex, int toIndex, T key, Comparator<? super T> c){
        int lb = fromIndex - 1, ub = toIndex;
        while(ub - lb > 1){          //rage of solution > 1
            int mid = (lb + ub) / 2, cmp = c.compare(a[mid], key);
            if( cmp >= 0 )   ub = mid;   //(lb,mid]
            else            lb = mid;   //(mid,ub]
        }
        return ub;                      //(lb, ub], ub = lb + 1 → [ub, ub+1)
    }
    public static <T> int lowerBound(T[] a, T key, Comparator<? super T> c){
		return lowerBound(a, 0, a.length, key, c);
	}
}