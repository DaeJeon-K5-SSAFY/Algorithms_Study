package com.ssafy.study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 적록색약 - 빨간색과 초록색의 차이를 거의 느끼지는 못함
 * 
 * 크기가 NxN 그리드의 각 칸에 RGB 
 * 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다.
 * 
 * 그림이 입력으로 주어질 때, 적록색약과 아닌 사람의 구역의 수를 구하는 프로그램을 작성
 * 
 * sol)
 * BFS로 같은 구역을 searching
 * 중요한 것은 같은 구역이 아닌 다른 구역을 탐색할 때는 어떻게?
 * -> 잘못된 점, 노드가 섞여서 제대로 구획이 나뉘지 않음
 * 
 * DFS로 풀어보자
 * 같은 구역만 정하면서 진행
 */
// 정답
public class boj10026 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("data/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        		
        int N = Integer.parseInt(br.readLine());
        char[][] gridMap = new char[N][];
        
        // 맵 초기화 
        for(int i = 0; i < N; i++) {
        	String line = br.readLine();
        	gridMap[i] = line.toCharArray(); // 문자 형식의 line을 한 번에 Array로 변경하는 API
        }
        
        // dfs 
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[] {0, 0});  // x, y
        int[] xy = new int[2]; 
        int[][] del = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}}; // 상우좌하(시계 방향)
        boolean[][] visited = new boolean[N][N];
        
		// 적록색맹이 아닌 사람
        int x, y;
        int pCnt = 1;
        while(!stack.isEmpty()){
        	xy = stack.pop();
        	x = xy[0];
        	y = xy[1];
        	visited[y][x] = true;
        	
        	for(int i = 0; i < 4; i++) {
        		int dx = x + del[i][0];
        		int dy = y + del[i][1];
        		if(dy < 0 || dy >= N || dx < 0 || dx >= N) continue;
        		if(visited[dy][dx] == true) continue;

        		
        		if(gridMap[y][x] == gridMap[dy][dx]) 
        			stack.push(new int[] {dx, dy});
        	}
        	if(stack.isEmpty()) {
    			for(int j = 0; j < N; j++) {
    				for(int k = 0; k < N; k++) {
    					if(visited[j][k] == false) {
    						stack.push(new int[] {k, j});
    						pCnt++;
    						break;
    					}
    				}
    				if(!stack.isEmpty()) break;
    			}
    		}
        }

        // 적록색맹인 사람
        visited = new boolean[N][N]; // 초기화
        stack.push(new int[] {0, 0});  // x, y
        int rbCnt = 1;
        while(!stack.isEmpty()){
        	xy = stack.pop();
        	x = xy[0];
        	y = xy[1];
        	visited[y][x] = true;
        	
        	for(int i = 0; i < 4; i++) {
        		int dx = x + del[i][0];
        		int dy = y + del[i][1];
        		if(dy < 0 || dy >= N || dx < 0 || dx >= N) continue;
        		if(visited[dy][dx] == true) continue;

        		if(gridMap[y][x] == 'R' || gridMap[y][x] == 'G') {
        			if(gridMap[dy][dx] == 'R' || gridMap[dy][dx] == 'G')
        				stack.push(new int[] {dx, dy});
        		}else if (gridMap[y][x] == gridMap[dy][dx]) {
        			stack.push(new int[] {dx, dy});
        		}
        			
        	}
        	if(stack.isEmpty()) {
    			for(int j = 0; j < N; j++) {
    				for(int k = 0; k < N; k++) {
    					if(visited[j][k] == false) {
    						stack.push(new int[] {k, j});
    						rbCnt++;
    						break;
    					}
    				}
    				if(!stack.isEmpty()) break;
    			}
    		}
        }
        System.out.println(pCnt + " " + rbCnt);
        br.close();
	}
}
