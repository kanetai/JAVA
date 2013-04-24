import java.util.*;
public class aoj0088 {
	static final Scanner stdin = new Scanner(System.in);
	static final String strENC = " _101_'_000000_,_000011_-_10010001_._010001_?_000001_A_100101_B_10011010_C_0101_D_0001_E_110_F_01001_G_10011011_H_010000_I_0111_J_10011000_K_0110_L_00100_M_10011001_N_10011110_O_00101_P_111_Q_10011111_R_1000_S_00110_T_00111_U_10011100_V_10011101_W_000010_X_10010010_Y_10010011_Z_10010000";
	static final String strDEC = "00000_A_00001_B_00010_C_00011_D_00100_E_00101_F_00110_G_00111_H_01000_I_01001_J_01010_K_01011_L_01100_M_01101_N_01110_O_01111_P_10000_Q_10001_R_10010_S_10011_T_10100_U_10101_V_10110_W_10111_X_11000_Y_11001_Z_11010_ _11011_._11100_,_11101_-_11110_'_11111_?";
	static public void main(String[] args) {
		HashMap<Character, String> enc = new HashMap<Character, String>();
		HashMap<String, Character> dec = new HashMap<String, Character>();
		String[] e = strENC.split("_");
		String[] d = strDEC.split("_");
		for(int i = 0; i<64; i+=2){
			enc.put(e[i].charAt(0), e[i+1]);
			dec.put(d[i], d[i+1].charAt(0));
		}
		while(stdin.hasNext()){
			char[] line = stdin.nextLine().toCharArray();
			StringBuilder sb = new StringBuilder();
			for(char ch: line) sb.append(enc.get(ch));
			while(sb.length()%5 != 0) sb.append('0');
			for(int i=0; i < sb.length(); i+=5)
				System.out.print(dec.get(sb.substring(i, i+5)));
			System.out.println("");
		}
	}
}
