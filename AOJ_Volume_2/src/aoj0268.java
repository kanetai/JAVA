import java.math.BigDecimal;
import java.util.*;
public class aoj0268 {
	static final Scanner stdin = new Scanner(System.in);
	static final BigDecimal divisor = new BigDecimal((1<<7));
	public static void main(String[] args) {
		int n = stdin.nextInt();
		while (n-- > 0) {
			long x = Long.parseLong(stdin.next(), 16);
			StringBuilder sb = new StringBuilder((x&0x80000000) == 0 ? "" : "-");
			BigDecimal d = new BigDecimal((x&0x7fffffff));
			sb.append(d.divide(divisor).toString());
			if (sb.indexOf(".") == -1) sb.append(".0");
			System.out.println(sb.toString());
		}
	}
}
