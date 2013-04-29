import java.util.*;
import java.math.*;
public class aoj0110 {
	static final Scanner stdin = new Scanner(System.in);
	static final boolean headIsX(String str){ return str.charAt(0) == 'X' && str.length() > 1;}
	public static void main(String[] args) {
		while(stdin.hasNext()){
			String[] l = stdin.next().split("\\+|=");
			boolean flag = false;
			boolean zero = false;
			if(headIsX(l[0]) || headIsX(l[1]) || headIsX(l[2])) zero = true;
			for(int i = 0; i < 10; ++i){
				if(i==0 && zero) continue;
				StringBuilder temp = new StringBuilder(l[0]);
				int idx;
				while((idx = temp.indexOf("X")) >= 0) temp.setCharAt(idx, (char)('0'+i));
				BigInteger left = new BigInteger(temp.toString());
				temp = new StringBuilder(l[1]);
				while((idx = temp.indexOf("X")) >= 0) temp.setCharAt(idx, (char)('0'+i));
				left = left.add(new BigInteger(temp.toString()));
				temp = new StringBuilder(l[2]);
				while((idx = temp.indexOf("X")) >= 0) temp.setCharAt(idx, (char)('0'+i));
				if(left.equals(new BigInteger(temp.toString()))){
					System.out.println(i);
					flag = true;
					break;
				}
			}
			if(!flag) System.out.println("NA");
		}
	}
}
