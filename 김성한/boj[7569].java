import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		// 토마토 두번째꺼
		// 값이 1인경우에 큐에 좌표들을 추가하고, 그 좌표들을 이용해 3차원 BFS를 돌리면 될 것 같다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[][][] box = new int[N][M][H];
		
		Deque<int[]> dq = new ArrayDeque<>(); 
		int[][] moves = {{0,0,-1},{0,0,1},{1,0,0},{-1,0,0},{0,1,0},{0,-1,0}};
		
		for(int i = 0; i< H; i++) {
			for(int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < M; k++) {
					int num = Integer.parseInt(st.nextToken());
					box[j][k][i] = num;
					if(num == 1) {
						dq.add(new int[]{j,k,i,0});
					}
				}
			}
		}
		int ans = 0;
		while(dq.size() != 0) {
			int[] point = dq.pop();
			int dx = point[0];
			int dy = point[1];
			int dz = point[2];
			int day = point[3];
			
			if(day > ans) {
				ans = day;
			}
			
			for(int[] move : moves) {
				int nx = dx + move[0];
				int ny = dy + move[1];
				int nz = dz + move[2];
				
				if((nx < 0 || nx >= N || ny < 0 || ny >= M || nz < 0 || nz >= H) || box[nx][ny][nz] == -1) {
					continue;
				}
				
				if(box[nx][ny][nz] == 1) {
					continue;
				}
				
				box[nx][ny][nz] = 1;
				
				dq.add(new int[]{nx,ny,nz,day+1});
			}
		}
		boolean flag = false;
		for(int i = 0; i< H; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < M; k++) {
					if(box[j][k][i] == 0) {
						flag = true;
						break;
					}
				}
				if(flag) break;
			}
			if(flag) break;
		}
		
		if(flag) System.out.println(-1);
		else System.out.println(ans);
		
	}
}