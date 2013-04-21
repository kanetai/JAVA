import java.util.*;
public class aoj0083 {
	static final Scanner stdin = new Scanner(System.in);
	static Solver solver = Solver.GregToJulianDay;
	static final int premeiji = solver.solve(1868, 9, 8);
	static final int meiji = solver.solve(1912, 7, 29);
	static final int taisho = solver.solve(1926, 12, 24);
	static final int showa = solver.solve(1989, 1, 7);
	public static void main(String[] args) {
		while(stdin.hasNext()){
			int y = stdin.nextInt(), m = stdin.nextInt(), d = stdin.nextInt();
			int jd = solver.solve(y,m,d);
			if(jd < premeiji) System.out.println("pre-meiji");
			else if(jd <= meiji) System.out.println("meiji "+(y-1868+1) + " " + m + " " + d);
			else if(jd <= taisho) System.out.println("taisho "+(y-1912+1) + " " + m + " " + d);
			else if(jd <= showa) System.out.println("showa "+(y-1926+1) + " " + m + " " + d);
			else System.out.println("heisei "+(y-1989+1) + " " + m + " " + d);
		}
	}
	static enum Solver {
		GregToJulianDay{ @Override int solve(int y, int m, int d){ return GregToJulianDay(y,m,d,true); }}, 
		GregToJD{ @Override int solve(int y, int m, int d){ return GregToJD(y,m,d,true); }};
		int solve(int y, int m, int d){ return 0; }
	}
	public static final int GregToJulianDay(int y, int m, int d, boolean modflag) { //Ver. 1
		y += 4800; //※負の数に対する除算や剰余の計算結果は、言語あるいは処理系によって異なるので注意
		if (m < 3){ --y; m += 12; }
		return 365*y + y/4 - y/100 + y/400 + (153*m - 457)/5 + d-32045 - (modflag ?  2400001 : 0);
	}
	public static final int GregToJD(int y, int m, int d, boolean modflag){ // Ver. 2
		int a  = (14-m)/12;
		y = y + 4800 - a;
		m = m + 12*a -3;
		return d + (153*m+2)/5 + 365 * y + y/4 - y/100 + y/400 - 32045 - (modflag ?  2400001 : 0);
	}
}
