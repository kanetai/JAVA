package algorithm.dp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LongestIncreasingSubsequence {
	private LongestIncreasingSubsequence(){}
	/**
	 * Gets LIS(longest increasing subsequence) of x[begin, end)(O(n^2) n:= end - begin)
	 * @param x	sequence
	 * @param begin (inclusive) start index
	 * @param end	(exclusive) end index
	 * @return	LIS of x[begin, end)
	 */
	public static List<Integer> _LIS(int[] x, int begin, int end){
		int n = end - begin;
		int[] DP = new int[n]; Arrays.fill(DP, 1);
		int[] prev = new int[n]; Arrays.fill(prev, -1);
		int lidx = 0;
		for(int i = 1; i < n; ++i){
			for(int j = 0; j < i; ++j)
				if(x[begin+j] < x[begin+i] && DP[i] < DP[j] + 1){ DP[i] = DP[j] + 1; prev[i] = j; }
			if(DP[lidx] < DP[i]) lidx = i;
		}
		LinkedList<Integer> lis = new LinkedList<Integer>();
		for(int i = lidx; i >= 0; i = prev[i]) lis.addFirst(x[begin+i]);
		return lis;
	}
	/**
	 * Gets LIS(longest increasing subsequence) of x (O(n^2) n:= end - begin)<br>
	 * AOJ No. 0158
	 * @param x	sequence
	 * @return	LIS of x
	 */
	public static List<Integer> _LIS(int[] x){ return _LIS(x, 0, x.length); }
}
