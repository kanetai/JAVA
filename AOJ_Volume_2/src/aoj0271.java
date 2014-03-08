import java.util.*;
public class aoj0271 {
    static final Scanner stdin = new Scanner(System.in);
    static final int MOD = 1000000007, NMAX = 100001, seq[] = new int[NMAX], input[] = new int[NMAX];
    static final long factMod[] = new long[NMAX];
    static {
        factMod[0] = 1;
        for (int i = 1; i < NMAX; ++i) {
            factMod[i] = (factMod[i-1]*i) % MOD;
            seq[i] = i;
        }
    }
    public static void main(String[] args) {
        while (true) {
            int N = stdin.nextInt();
            if (N == 0) break;
            FenwickTree BIT = new FenwickTree(N+1);
            System.arraycopy(seq, 0, input, 0, N);
            int R = stdin.nextInt();
            while (R-- > 0) {
                int a = stdin.nextInt()-1, b = stdin.nextInt()-1;
                int tmp = input[a]; input[a] = input[b]; input[b] = tmp;
            }
            long ans = 0;
            for (int i = 0; i < N; i++) {
                int cnt = input[i] - BIT.sum(input[i]);
                ans = (ans + cnt * factMod[N-i-1]) % MOD;
                BIT.add(input[i], 1);
            }
            System.out.println(ans);
        }
    }
    public static class FenwickTree {
        private int[] x;
        public FenwickTree(int n) { init(n); }
        public final void init(int n) { x = new int[n]; Arrays.fill(x, 0); }
        public final int sum(int i) {
            int ret = 0;
            for(int j = i; j >= 0; j = ((j & (j+1)) - 1)) ret += x[j];
            return ret;
        }
        public final void add(int i, int a) {
            for(int j = i; j < x.length; j |= j+1) x[j] += a;
        }
    }
}