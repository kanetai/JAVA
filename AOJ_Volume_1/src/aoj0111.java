import java.util.*;
public class aoj0111 {
	static final Scanner stdin = new Scanner(System.in);
	static final HashMap<Character, String> atoc = new HashMap<Character, String>();
	@SuppressWarnings("serial") static final HashMap<String, Character> ctoa = new HashMap<String, Character>(){
		{
			put("101", 		' ');	put("0101",		'C');	put("0110", 	'K');	put("00110",	'S');
			put("000000", 	'\'');	put("0001", 	'D');	put("00100", 	'L');	put("00111",	'T');
			put("000011", 	',');	put("110",		'E');	put("10011001",	'M');	put("10011100",	'U');
			put("10010001", '-');	put("01001", 	'F');	put("10011110",	'N');	put("10011101",	'V');
			put("010001", 	'.');	put("10011011",	'G');	put("00101",	'O');	put("000010",	'W');
			put("000001", 	'?');	put("010000",	'H');	put("111",		'P');	put("10010010",	'X');
			put("100101", 	'A');	put("0111",		'I');	put("10011111",	'Q');	put("10010011",	'Y');
			put("10011010", 'B');	put("10011000",	'J');	put("1000",		'R');	put("10010000",	'Z');
		}
	};
	public static void main(String[] args) {
		for(int i = 0; i <= 31; ++i){
			char c;
			switch(i){
			case 26: c = ' '; break;
			case 27: c = '.'; break;
			case 28: c = ','; break;
			case 29: c = '-'; break;
			case 30: c = '\''; break;
			case 31: c = '?'; break;
			default: c = (char)('A'+i);
			}
			StringBuilder str = new StringBuilder();
			for(int j = 0; j < 5; ++j) str.append( (char)(((i>>j) & 1) + '0') );
			atoc.put(c, str.reverse().toString());
		}
		while(stdin.hasNext()){
			char[] in = stdin.nextLine().toCharArray();
			StringBuilder code = new StringBuilder();
			for(char ch: in) code.append(atoc.get(ch));
			LOOP: while(3<=code.length()){
				for(int i=3; i <= 8; ++i){
					if(i > code.length()) break LOOP;
					String k = code.substring(0,i);
					if(ctoa.containsKey(k)){
						System.out.print(ctoa.get(k));
						code.delete(0, i);
						continue LOOP;
					}
				}
				break;
			}
			System.out.println("");
		}
	}
}
