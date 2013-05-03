import java.util.*;
public class aoj0127 {
	static final Scanner stdin = new Scanner(System.in);
	static char[][] map = { {'a', 'b', 'c', 'd', 'e'}, {'f', 'g', 'h', 'i', 'j'}, {'k', 'l', 'm', 'n', 'o'}, {'p', 'q', 'r', 's', 't'}, {'u', 'v', 'w', 'x', 'y'}, {'z', '.', '?', '!', ' '}};
	public static void main(String[] args) {
		while(stdin.hasNext()){
			char[] line = stdin.nextLine().toCharArray();
			StringBuilder ans = new StringBuilder();
			try{
				for(int i = 0; i < line.length; i+=2) ans.append(map[line[i]-'1'][line[i+1]-'1']);
			}catch(Exception e){
				ans = new StringBuilder("NA");
			}finally{
				System.out.println(ans.toString());
			}
		}
	}
}
