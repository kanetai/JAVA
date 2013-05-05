import java.util.*;
public class aoj0172 {
	static final Scanner stdin = new Scanner(System.in);
	enum Data{ POS, STATE, COMMAND } enum Command{ MOVE, ON, OFF } enum Result{ PERFECT, NOT_PERFECT, HELP }
	public static void main(String[] args) {
		while(true){
			int n = stdin.nextInt(), m = stdin.nextInt();
			if((n|m) == 0) break;
			@SuppressWarnings("unchecked") 
			ArrayList<Integer>[] adjList = new ArrayList[n], light = new ArrayList[n];
			for(int i = 0; i < n; ++i){
				adjList[i] = new ArrayList<Integer>();
				light[i] = new ArrayList<Integer>();
			}
			for(int i = 0; i < m; ++i){
				int s = stdin.nextInt()-1, t = stdin.nextInt()-1;
				adjList[s].add(t); adjList[t].add(s);
			}
			int S = 0;
			for(int i = 0; i < n; ++i) if(stdin.nextInt() == 1) S |= (1<<i);
			for(int i = 0; i < n; ++i){
				int k = stdin.nextInt();
				while(k-- > 0) light[i].add(stdin.nextInt()-1);
				Collections.sort(light[i]);
			}
			switch(BFS(adjList, light, S)){
			case PERFECT:
				System.out.println(String.format("You can go home in %d steps.", 
						(command.length-Data.COMMAND.ordinal())/2));
				for(int i = Data.COMMAND.ordinal(); i < command.length; i+=2)
					System.out.println((String.format("%s room %d.",
							(command[i] == Command.MOVE.ordinal()) ? "Move to" : 
								(command[i] == Command.ON.ordinal()) ? "Switch on" : "Switch off", 
										command[i+1]+1)));
				break;
			case NOT_PERFECT:
				System.out.println("You can not switch off all lights."); break;
			case HELP:
				System.out.println("Help me!");
			}
		}
	}
	static int[] command;
	static boolean[][] visited;
	static Result BFS(ArrayList<Integer>[] adjList, ArrayList<Integer>[] light, int S){
		Result ret = Result.HELP;
		int n = adjList.length;
		visited = new boolean[n][1<<n];
		for(int i = 0; i < n; ++i) Arrays.fill(visited[i], false);
		visited[0][S] = true;
		Queue<int[]> q = new LinkedList<int[]>(); q.add(new int[]{0, S});
		while(!q.isEmpty()){
			int[] e = q.poll(); int pos = e[Data.POS.ordinal()], state = e[Data.STATE.ordinal()];
			if((state & (1<<pos)) == 0) continue; //今いる部屋の明かりが消えている
			if(pos == n-1 && check(n, state)){ command = e; return Result.PERFECT; }
			if(pos == n-1) ret = Result.NOT_PERFECT;
			for(Integer i: adjList[pos]){
				int nextData[] = new int[e.length+2]; System.arraycopy(e, 0, nextData, 0, e.length);
				nextData[e.length] = Command.MOVE.ordinal();
				nextData[e.length+1] = i;
				nextData[Data.POS.ordinal()] = i;
				if(visited[i][state]) continue;
				visited[i][state] = true;
				q.add(nextData);
			}
			for(Integer i: light[pos]){
				int nextData[] = new int[e.length+2]; System.arraycopy(e, 0, nextData, 0, e.length);
				int bit = (1<<i);
				nextData[e.length] = (state & bit) != 0 ? Command.OFF.ordinal() : Command.ON.ordinal();
				nextData[e.length+1] = i;
				nextData[Data.STATE.ordinal()] = (state & bit) != 0 ? (state & (~bit)) : (state | bit);
				if(visited[pos][nextData[Data.STATE.ordinal()]]) continue;
				visited[pos][nextData[Data.STATE.ordinal()]] = true;
				q.add(nextData);
			}
		}
		return ret;
	}
	static final boolean check(int n, int state){
		for(int i = 0; i < n-1; ++i) if(((state>>i)&1) == 1) return false;
		return true;
	}
}
