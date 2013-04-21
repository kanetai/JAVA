import java.util.*;

public class aoj0080 {
	static final Scanner stdin = new Scanner(System.in);
	static int q;
	public static void main(String[] args) throws Exception {
		while(stdin.hasNext()){
			q = stdin.nextInt();
			if(q == -1) break;
			System.out.printf("%.6f\n", Newton(q/2.0, Integer.MAX_VALUE, new Function(){
				@Override public double f(double x) { return x*x*x - q; }
				@Override public double fp(double x) { return 3*x*x; }
			}));
		}
	}
	public static boolean equal(double a, double b){ return Math.abs(a-b) < 1e-5*q; }	// a == b
	static interface Function {
		public double f(double x);
		public double fp(double x);
	}
	static double Newton(double x0, int N_MAX, Function func) throws Exception{
		double x = x0;
		for(int i = 0; i < N_MAX; ++i){
			double delta = func.f(x)/func.fp(x);
			x -= delta;
			if(equal(func.f(x), 0)) return x; //if(equal(delta, 0)) return x;
		}
		throw new Exception();
	}
}
