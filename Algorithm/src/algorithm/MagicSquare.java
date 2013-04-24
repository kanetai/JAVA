package algorithm;
import static algorithm.Utility.swap;
public class MagicSquare {
	static int[][] magicSquare(int n) throws Exception{
		if(n<=0 || n==2) throw new Exception();
		if(n % 2 == 1) return magicSquareOdd(n);
		if(n % 4 == 0) return magicSquareDoubleEven(n);
		return magicSquareSingleEven(n);
	}
	static int[][] magicSquareOdd(int n){
		int[][] M = new int[n][n];
		int k = 1;
		for(int i = 0; i < n; ++i)
			for(int j = 0; j < n; ++j)
				M[(n+2*i-j)%n][(n+n/2-i+j)%n] = k++;
		return M;
	}
	static int[][] magicSquareDoubleEven(int n){
		int[][] M = new int[n][n];
		int x, y, k = 1;
		for(int i = 0; i < n; ++i){
			for(int j = 0; j < n; ++j){
				x = i % 4;
				if(x==1 || x==2) y=(j+2)%4;
				else y = j%4;
				if(y==1 || y==2) M[i][j] = k++;
				else M[n-1-i][n-1-j] = k++;
			}
		}
		return M; 
	}
	static int[][] magicSquareSingleEven(int n){
		int[][] M = new int[n][n];
		int m = n/2;
		int m2 = m*m;
		int k = (m-1)/2;
		int[][] A = magicSquareOdd(m);
		for(int i = 0; i < m; ++i){
			for(int j = 0; j < m; ++j){
				M[i][j] = M[i+m][j] = M[i][j+m] = M[i+m][j+m] = A[i][j];
				M[i+m][j+m] += m2;
				M[i][j+m] += 2*m2;
				M[i+m][j] += 3*m2;
			}
		}
		if(k>1){
			for(int i = 0; i < m; ++i){
				for(int j=1; j < k; ++j){
					swap(M, i,j, i+m,j);
					swap(M, i,n-k+j, i+m,n-k+j);
				}
			}
		}
		for(int i=0; i < k; ++i){
			swap(M, i,0, i+m,0);
			swap(M, i+k+1,0, i+k+1+m,0);
		}
		swap(M, k,k, k+m,k);
		return M;
	}
}
