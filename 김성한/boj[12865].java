import java.util.*;
import java.io.*;

public class Main {
	static int[][] dp;
	static int[] W;
	static int[] V;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		dp = new int[N+1][K+1];
		W = new int[N+1];
		V = new int[N+1];

		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}

		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= K; j++) {
				if(W[i] > j) {
					dp[i][j] = dp[i-1][j];
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - W[i]] + V[i]);
				}
			}
		}
		System.out.println(dp[N][K]);
	}
}
