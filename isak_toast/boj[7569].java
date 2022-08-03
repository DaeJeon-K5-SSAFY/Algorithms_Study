package com.ssafy.recur;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 토마토 6방위 버전 (3차원)
 * 
 * 마찬가지로 BFS 알고리즘을 통해서 풀어본다.
 * 
 */
/*
*
/* 
1주변에 사방으로 확장되듯이 0을 탐색하는 알고리즘 -> BFS
sol)
입력 - 토마토맵 초기화
토마토 위치를 Queue에 넣어서 하나씩 빼면서 전체 영역을 모두 탐색할 때까지 진행
최소 몇 번만에 완료되었는지 출력
토마토 맵에 0이 있다면 모든 탐색이 되지 않았으므로 -1 출력
*/
//

public class BOJ_7569_토마토 {
	public static void main(String[] args) {
		try {
			version1();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void version1() throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(stk.nextToken());
        int N = Integer.parseInt(stk.nextToken());
	    int H = Integer.parseInt(stk.nextToken());
	    int[][][] tomatoMap = new int[H][N][M];
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < N; j++) {
				tomatoMap[i][j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();				
			}
		}
		//알고리즘
		//토마토 (1) 위치 특정
		Queue<List<Integer>> que = new LinkedList<>();
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < M; k++) {
					if(tomatoMap[i][j][k] == 1) {
						que.add(new ArrayList<>(Arrays.asList(i, j, k, 0))); // x,y,z,point	
					}
				}
           }
       }

       //특정 위치로부터 bfs 진행
       boolean[][][] visited = new boolean[H][N][M]; // 방문 여부를 검사할 배열
       
       int[][] delta = {
    		   { 1, 0, 0}, {-1, 0, 0}, 
    		   { 0, 1, 0}, { 0,-1, 0},
    		   { 0, 0, 1}, { 0, 0,-1}
       }; // 6방위 (위, 아래, 왼쪽, 오른쪽, 앞, 뒤)
       

       int x,y,z,dx,dy,dz;
       List<Integer> p = new ArrayList<Integer>();

       int ans = 0;       
       int rank = 0;
       while(!que.isEmpty()){
           p = que.poll();
           z = p.get(0);
           y = p.get(1);
           x = p.get(2);
           rank = p.get(3);
           
           ans = Math.max(ans, rank);
           
           visited[z][y][x] = true;
           tomatoMap[z][y][x] = 1; // 익음

           // 인접한 토마토 찾기
           for(int k = 0; k < 6; k++){
               dx = x + delta[k][2];
               dy = y + delta[k][1];
               dz = z + delta[k][0];
               
               // 맵을 벗어났는가?
               if(dz < 0 || dz >= H || dy < 0 || dy >= N || dx < 0 || dx >= M) continue;
               
        	   if (tomatoMap[dz][dy][dx] == 0 && !visited[dz][dy][dx]){
                   que.add(new ArrayList<>(Arrays.asList(dz, dy, dx, rank+1)));
                   // visited[dy][dx] = true;
               }
           }
           
       }
       for(int i = 0; i < H; i++){
           for(int j = 0; j < N; j++){
        	   for(int k = 0; k < M; k++) {
        		   if(tomatoMap[i][j][k] == 0) {
        			   ans = -1;
        		   }
        	   }
        	   if(ans == -1) break;
           }
           if(ans == -1) break;
       }
	
		// 출력
		System.out.println(ans);
		br.close();
	}
}
