import java.util.*;

public class aoj0027 {
	static final Scanner stdin = new Scanner(System.in);;
	static final int[] dmap = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	static final int Y = 2004;
	static final Solver solver = Solver.GregCalendar;
	enum WeekDay {
		Sun("Sunday"), Mon("Monday"), Tue("Tuesday"), Wed("Wednesday"), Thu("Thursday"), Fri("Friday"), Sat("Saturday");
		private static final WeekDay[] Table = WeekDay.values();
		public static final WeekDay getWeekDay(int i){ return Table[i]; }
		public final String description;
		private WeekDay(String description){ this.description = description; }
		@Override public String toString(){ return description; }
	}
	public static void main(String[] args) {
		while(true){
			int m = stdin.nextInt(), d = stdin.nextInt();
			if( m==0 && d==0 ) break;
			System.out.println( solver.solve(Y, m, d) );
		}
	}
	enum Solver {
		GregCalendar{ @Override String solve(int y, int m, int d){ //monthが0～11だなんて聞いていないよ, weekは1～7だったお
			return WeekDay.getWeekDay( new GregorianCalendar(y,m-1,d).get( GregorianCalendar.DAY_OF_WEEK ) - 1).toString();
		}}, Naive { @Override String solve(int y, int m, int d){
			int sum = 0;
			for(int i=0; i<m-1; ++i) sum += dmap[i];
			return WeekDay.getWeekDay((sum+d+3) % 7).toString();
		}}, Zeller { @Override String solve(int y, int m, int d){
			return Zeller(y,m,d).toString();
		}};
		String solve(int y, int m, int d){ return ""; }
	}
	public static final WeekDay Zeller(int y, int m, int d){
		y += 4800; //y<=0で使いたい場合
		if (m < 3) { --y; m += 12; }
		return WeekDay.getWeekDay((y + y/4 - y/100 + y/400 + (13*m + 8)/5 + d) % 7);
	}
}
