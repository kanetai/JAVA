package algorithm;
import static algorithm.Utility.equal;
public final class NumericalComputation {
	private NumericalComputation(){}
	static interface Function {
		public double f(double x);
		public double fp(double x);
	}
	/**
	 * solver of non-linear (unary) equation via Newton-Raphson method. 
	 * AOJ No. 0080(minor alteration)
	 * @param x0			initial value
	 * @param N_MAX			maximum iteration count
	 * @param func			function set = { f(x), f'(x) }
	 * @return				solution
	 * @throws Exception	failure analysis
	 */
	static double Newton(double x0, int N_MAX, Function func) throws Exception{
		double x = x0;
		for(int i = 0; i < N_MAX; ++i){
			double delta = func.f(x)/func.fp(x);
			x -= delta;
			if(equal(delta, 0)) return x; //
		}
		throw new Exception();
	}
}
