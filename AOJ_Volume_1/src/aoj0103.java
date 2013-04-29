import java.util.*;
public class aoj0103 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int count = 0, cond = 0, point = 0;
		int p = 1<<3;
		stdin.nextInt(); stdin.nextLine();
		while(stdin.hasNext()){
			String l = stdin.nextLine();
			if(l.equals("OUT")){
				if(++count == 3){
					System.out.println(point);
					cond = point = count = 0;
				}
			}else if(l.equals("HIT")){
				cond <<= 1;
				if((cond & p) == p) point++;
				cond |= 1;
			}else{
				for(int i = 0; i < 3; ++i){
					cond <<= 1;
					if((cond & p) == p) point++;
				}
				point++;
			}
		}
	}
}
