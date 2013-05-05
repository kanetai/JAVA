import java.util.*;
public class aoj0208 {
	static final Scanner stdin = new Scanner(System.in);
	static final int[] LUT = {0,1,2,3,5,7,8,9};
	public static void main(String[] args) {
		int o;
		while((o = stdin.nextInt()) != 0){
			for(char c: Integer.toOctalString(o).toCharArray())
				System.out.print(LUT[c-'0']);
			System.out.println();
		}
	}
}
