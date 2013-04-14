import java.util.*;
public class aoj0032 {
	static final Scanner stdin = new Scanner(System.in);;
	public static void main(String[] args) {
		int rect_count = 0, lozenge_count = 0;
		while(stdin.hasNext()){
			String e[] = stdin.nextLine().split(",");
			int e1 = Integer.parseInt(e[0]), e2 = Integer.parseInt(e[1]), ed = Integer.parseInt(e[2]);
			if( is_rect(e1,e2,ed) ) rect_count++;
			if( is_lozenge(e1,e2,ed) ) lozenge_count++;
		}
		System.out.println(rect_count+"\n"+lozenge_count);
	}
	static boolean is_rect(int e1, int e2, int ed){ return e1*e1+e2*e2 == ed*ed; }
	static boolean is_lozenge(int e1, int e2, int ed){ return e1==e2; }
}
