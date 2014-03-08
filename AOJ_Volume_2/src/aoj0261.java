import java.util.*;
public class aoj0261 {
	static final Scanner stdin = new Scanner(System.in);
	enum Solver {
		VER1 { @Override long Greg2JulianDay(int y, int m, int d, boolean modflag) { return GregToJulianDay(y, m, d, modflag); } }, 
		VER2 { @Override long Greg2JulianDay(int y, int m, int d, boolean modflag) { return GregToJD(y, m, d, modflag); } };
		long Greg2JulianDay(int y, int m, int d, boolean modflag) { return 0L; }
	}
	static final Solver solver = Solver.VER2;
	static final long base_mod = solver.Greg2JulianDay(2012, 12, 21, true), base = solver.Greg2JulianDay(2012, 12, 21, false);
	static final int maya[] = {20*18*20*20, 20*18*20, 20*18, 20, 1}, rotate = 20*18*20*20*13;
	public static void main(String[] args) {
		while (true) {
			String[] d = stdin.next().split("\\.");
			if (d[0].charAt(0) == '#') break;
			StringBuilder sb = new StringBuilder();
			if (d.length == 3) {
				long jday = solver.Greg2JulianDay(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]), true) - base_mod;
				jday %= rotate;
				for (int unit: maya) {
					sb.append('.').append(jday/unit);
					jday %= unit;
				}
			} else {
				long jday = base;
				for (int i = 0; i < d.length; ++i) jday += maya[i]*Integer.parseInt(d[i]);
				int[] ans = JulianDatyToGreg((int)jday);
				for (int ymd : ans) sb.append('.').append(ymd);
			}
			System.out.println(sb.substring(1));
		}
	}
	public static final int[] JulianDatyToGreg(int jd){
		int temp = jd + 32044; // +0.5
		int y = -4800;

		jd = temp/146097;			temp %= 146097;			y += (jd * 400);
		jd = (temp/36524 + 1)*3/4;	temp -= (jd * 36524);	y += (jd * 100);
		jd = temp/1461;				temp %= 1461;			y += (jd * 4);
		jd = (temp/365 + 1)*3/4;	temp -= (jd * 365);		y += jd;

		int m = (temp*5 + 308)/153 -2;
		int d = temp - (m + 4)*153/5 + 122;
		y += ((m + 2)/12 );

		m = (m+2) % 12 + 1;
		d++;
		return new int[]{ y, m, d };
	}
	public static final long GregToJulianDay(int y, int m, int d, boolean modflag) { 
		y += 4800;
		if (m < 3){ --y; m += 12; }
		return - (modflag ?  2400001L : 0L) + 365L*y + y/4 - y/100 + y/400 + (153L*m - 457)/5 + d-32045;
	}
	public static final long GregToJD(int y, int m, int d, boolean modflag){ // Ver. 2
		int a  = (14-m)/12;
		y = y + 4800 - a;
		m = m + 12*a -3;
		return - (modflag ?  2400001L : 0L) + d + (153L*m + 2)/5 + 365L*y + y/4 - y/100 + y/400 - 32045;
	}
}
