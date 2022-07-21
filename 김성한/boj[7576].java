import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N][M];
		Deque<int[]> points = new ArrayDeque<int[]>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				board[i][j] = num;
				if(num == 1) {
					int[] temp = {i,j, 0};
					points.add(temp);
				}
			}
		}
		
		int count = 0;
		
		int[][] moves = {{-1,0},{1,0},{0,-1},{0,1}};
		
		while(points.size() != 0) {
			int[] point = points.remove();
			int x = point[0];
			int y = point[1];
			int order = point[2];
			
			for(int d = 0; d < 4; d++) {
				int nx = x + moves[d][0];
				int ny = y + moves[d][1];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}
				
				if(board[nx][ny] == -1 ) {
					continue;
				}
				
				if(board[nx][ny] == 0) {
					board[nx][ny] = 1;
					int[] temp = {nx, ny, order+1};
					points.add(temp);
				}
			}
			if(count != order) {
				count += 1;
			}
			
		}
		
		if(checker(board, N, M)) {
			System.out.println(count);
		}else {
			System.out.println(-1);
		}
	}
	
	public static boolean checker(int[][] board,int N,int M) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(board[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
}
