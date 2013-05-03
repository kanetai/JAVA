import java.util.*;
public class aoj0128 {
	static final Scanner stdin = new Scanner(System.in);
	static final String[] abacus = {"* = ****", "* =* ***", "* =** **", "* =*** *", "* =**** ", " *= ****", " *=* ***", " *=** **", " *=*** *", " *=**** " };
	public static void main(String[] args) {
		boolean flag = false;
		while(stdin.hasNext()){
			if(flag) System.out.println();
			flag = true;
			String line = String.format("%05d", stdin.nextInt());
			for(int i = 0; i < 8; ++i){
				for(int j = 0; j < 5; ++j)
					System.out.print(abacus[line.charAt(j)-'0'].charAt(i));
				System.out.println();
			}
		}
	}
}
