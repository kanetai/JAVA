package algorithm;
public class DateAlgorithm {
	enum WeekDay {
		Sun("Sunday"), Mon("Monday"), Tue("Tuesday"), Wed("Wednesday"), Thu("Thursday"), Fri("Friday"), Sat("Saturday");
		private static final WeekDay[] Table = WeekDay.values();
		public static final WeekDay getWeekDay(int i){ return Table[i]; }
		public final String description;
		private WeekDay(String description){ this.description = description; }
		@Override public String toString(){ return description; }
	}
	public static final boolean isLeapYear(int y){
		//if( y < 0 ) y = -y; //グレゴリオ暦のまま紀元前までさかのぼって考えるとき 1 BD-> y=0, 2 BD-> y=-1, ... ,n BD-> y=1-n
		return y % 4 == 0 && y % 100 != 0 || y % 400 == 0;
	}
	/**
	 * Via Fairfield's congruence, Get the days until m/d/y from 1/1/1<br>
	 * Fairfield(y,m,d) mod 7 = 0->Sun, 2->Mon, 3->Tue, ...<br>
	 * @param y	year > 0
	 * @param m	month
	 * @param d	day
	 * @return	the days until m/d/y from 1/1/1
	 */
	public static final int Fairfield(int y, int m, int d){
		if (m < 3) { --y; m += 12; }
		return 365*y+y/4-y/100+y/400+306*(m+1)/10+d-428;
	}
	/**
	 * calc a day of the week by Zeller's congruence (y,m,d)<br>
	 * @param y	year(>=-4800?) 1 BD-> y=0, 2 BD-> y=-1, ...,  n BD-> y=1-n
	 * @param m	month
	 * @param d	day
	 * @return 	a day of the week
	 */
	public static final WeekDay Zeller(int y, int m, int d){
		y += 4800; //y<=0で使いたい場合
		if (m < 3) { --y; m += 12; }
		return WeekDay.getWeekDay((y + y/4 - y/100 + y/400 + (13*m + 8)/5 + d) % 7);
	}
	/**
	 * calc a day of the week by Zelle's congruence (100J+K, m, d) ※y > 0<br>
	 * @param J	year/100
	 * @param K	year mod 100
	 * @param m	month
	 * @param d	day
	 * @return 	a day of the week
	 */
	public static final WeekDay Zeller(int J, int K, int m, int d){
		if (m < 3) { 
			int y=100*J+K-1;
			J=y/100; K=y%100; m += 12;
		}
		return WeekDay.getWeekDay((5*J+K+K/4+J/4+26*(m+1)/10 + d +6) % 7);
	}
	/**
	 * Get day (decimal representation) from hour, minute, sec.
	 * @param hour
	 * @param minute
	 * @param sec
	 * @return day (decimal representation)
	 */
	public static final double hmsToDay(int hour, int minute, double sec){
		return (hour + (minute + sec/60.0)/60.0)/24.0;
	}
	/**
	 * Gregorian Calendar -> (modified) Julian day<br>
	 * (Julian day + 1)%7 = 0->Sun, 1->Mon, .., 6->Sat<br>
	 * ((modified Julian day)%7 + 10 )% 7 = 0->Sun, 1->Mon, .., 6->Sat<br>
	 * <a href="http://www.prefield.com/algorithm/misc/date.html">consultation</a>
	 * @param y 		year(>=-4800?)  1 BD-> y=0, 2 BD-> y=-1, ...,  n BD-> y=1-n
	 * @param m 		month
	 * @param d 		day
	 * @param modflag 	modified Julian day flag
	 * @return 			(modified) Julian day
	 */
	public static final int GregToJulianDay(int y, int m, int d, boolean modflag) { //Ver. 1
		y += 4800; //※負の数に対する除算や剰余の計算結果は、言語あるいは処理系によって異なるので注意
		if (m < 3){ --y; m += 12; }
		return 365*y + y/4 - y/100 + y/400 + (153*m - 457)/5 + d-32045 - (modflag ?  2400001 : 0);
	}
	/**
	 * Gregorian Calendar -> (modified) Julian day (decimal representation)<br>
	 * (Julian day + 1)%7 = 0->Sun, 1->Mon, .., 6->Sat<br>
	 * ((modified Julian day)%7 + 10 )% 7 = 0->Sun, 1->Mon, .., 6->Sat<br>
	 * <a href="http://www.prefield.com/algorithm/misc/date.html">consultation</a>
	 * @param y 		year(>=-4800?)  1 BD-> y=0, 2 BD-> y=-1, ...,  n BD-> y=1-n
	 * @param m 		month
	 * @param d 		day
	 * @param modflag 	modified Julian Day flag
	 * @param hour
	 * @param minute
	 * @param sec
	 * @return (modified) Julian day
	 */
	public static final double GregToJulianDay(int y, int m, int d, boolean modflag, int hour, int minute, double sec){
		return GregToJulianDay(y, m, d, modflag) + hmsToDay(hour, minute, sec) - (modflag ? 0.0 : 0.5);
	}

	/**
	 * Gregorian Calendar -> (modified) Julian day<br>
	 * (Julian day + 1)%7 = 0->Sun, 1->Mon, .., 6->Sat<br>
	 * ((modified Julian day)%7 + 10 )% 7 = 0->Sun, 1->Mon, .., 6->Sat<br>
	 * <a href="http://en.wikipedia.org/wiki/Julian_day">consultation</a>
	 * @param y 		year(>=-4800?)  1 BD-> y=0, 2 BD-> y=-1, ...,  n BD-> y=1-n
	 * @param m 		month
	 * @param d 		day
	 * @param modflag 	modified Julian day flag
	 * @return			(modified) Julian day
	 */
	public static final int GregToJD(int y, int m, int d, boolean modflag){ // Ver. 2
		int a  = (14-m)/12;
		y = y + 4800 - a;
		m = m + 12*a -3;
		return d + (153*m+2)/5 + 365 * y + y/4 - y/100 + y/400 - 32045 - (modflag ?  2400001 : 0);
	}

	/**
	 * Gregorian Calendar -> (modified) Julian day (decimal representation)<br>
	 * (Julian day + 1)%7 = 0->Sun, 1->Mon, .., 6->Sat<br>
	 * ((modified Julian day)%7 + 10 )% 7 = 0->Sun, 1->Mon, .., 6->Sat<br>
	 * <a href="http://en.wikipedia.org/wiki/Julian_day">consultation</a>
	 * @param y 		year(>=-4800)  1 BD-> y=0, 2 BD-> y=-1, ...,  n BD-> y=1-n
	 * @param m 		month
	 * @param d 		day
	 * @param modflag 	modified Julian day flag
	 * @param hour
	 * @param minute
	 * @param sec
	 * @return 			(modified) Julian day
	 */
	public static final double GregToJD(int y, int m, int d, boolean modflag, int hour, int minute, double sec){
		return GregToJD(y,m,d, modflag)  + hmsToDay(hour, minute, sec) - (modflag ? 0.0 : 0.5);
	}

	/**
	 * Julian day -> Gregorian Calendar<br>
	 * <a href="http://en.wikipedia.org/wiki/Julian_day">consultation</a>
	 * @param jd Julian day
	 * @param y year(>=4800?)
	 * @param m month
	 * @param d day
	 * @return	Gregorian Calendar {y,m,d}
	 */
	public static final int[] JulianDatyToGreg(int jd, int y, int m, int d){
		int temp = jd + 32044; // +0.5
		y = -4800;

		jd = temp/146097;			temp %= 146097;			y += (jd * 400);
		jd = (temp/36524 + 1)*3/4;	temp -= (jd * 36524);	y += (jd * 100);
		jd = temp/1461;				temp %= 1461;			y += (jd * 4);
		jd = (temp/365 + 1)*3/4;	temp -= (jd * 365);		y += jd;

		m = (temp*5 + 308)/153 -2;
		d = temp - (m + 4)*153/5 + 122;
		y += ((m + 2)/12 );

		m = (m+2) % 12 + 1;
		d++;
		return new int[]{ y, m, d };
	}
	/**
	 * Julian Calendar -> (modified) Julian day<br>
	 * <a href="http://www.prefield.com/algorithm/misc/date.html">consultation</a>
	 * @param y			year(>=4800?)
	 * @param m 		month
	 * @param d 		day
	 * @param modflag	modified Julian day flag
	 * @return 			(modified) Julian day
	 */
	long JulianToJulianDay(int y, int m, int d, boolean modflag) {
		y += 4716;
		if (m < 3){ --y; m += 12; }
		return 365L*y + y/4 + (153*m - 457)/5 + d-1402 - (modflag ? 2400001L : 0L);
	}
	/**
	 * Julian Calendar -> (modified) Julian day<br>
	 * <a href="http://www.prefield.com/algorithm/misc/date.html">consultation</a>
	 * @param y 		year
	 * @param m 		month
	 * @param d 		day
	 * @param modflag 	modified Julian day flag
	 * @param hour
	 * @param minute
	 * @param sec
	 * @return 			(modified) Julian day
	 */
	double JulianToJulianDay(int y, int m, int d, boolean modflag, int hour, int minute, double sec){
		return JulianToJulianDay(y, m, d, modflag) + hmsToDay(hour, minute, sec);
	}
}
