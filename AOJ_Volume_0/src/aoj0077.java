import java.util.*;
public class aoj0077 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(stdin.hasNext()){
			char[] line = stdin.nextLine().toCharArray();
			for(int i = 0; i < line.length; ++i){
				if(line[i]=='@'){
					int n = line[i+1]-'0';
					while(n-->0) System.out.print(line[i+2]);
					i+=2;
				}else{
					System.out.print(line[i]);
				}
			}
			System.out.println("");
		}
	}
}
