import java.util.*;
public class aoj0047 {
	static final Scanner stdin = new Scanner(System.in);
	public static Object[] swap(Object[] a, int i, int j){	
		Object tmp = a[i]; a[i] = a[j]; a[j] = tmp; return a;
	}
	public static void main(String[] args) {
		Integer[] cup = {1, 0, 0};
		while(stdin.hasNext()){
			String line = stdin.nextLine();
			swap(cup, line.charAt(0)-'A', line.charAt(2)-'A');
		}
		for(int i=0; i<3; ++i){
			if(cup[i]==1){
				System.out.println((char)(i+'A'));
				break;
			}
		}
	}
}
