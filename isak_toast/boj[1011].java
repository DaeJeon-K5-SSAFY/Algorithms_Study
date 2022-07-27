package com.ssafy.study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * Fly me to the Alpha Centauri
 * 
 *	이동거리가 변하는 알고리즘
 *  
 *  최소한의 작동 횟수를 구할 것이므로 BFS를 사용해본다.
 *  도착지점 조건 : 이동거리 = 1 && 도착지점 도착
 */
// 결과 안 맞음
public class boj1011 {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("data/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;
        
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
        	stk = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(stk.nextToken());
        	int M = Integer.parseInt(stk.nextToken());
        	
        	// 이동거리 -> x-1, x, x+1 
        	Queue<int[]> queue = new LinkedList<int[]>();
        	queue.add(new int[] {0, 0, 0});  // 현재 위치, 이동 거리, 작동 횟수
        	
        	int minMove = 0;
        	int[] cur = new int[3];
        	int move = 0, cx = 0, cnt =0;
        	while(!queue.isEmpty()) {
        		cur = queue.poll();
        		
        		if(cur[0] == M && cur[1] == 1) {  // 도착지점에 도달, 이동거리는 1
    				minMove = cur[2];
    				break;
    			}
        		
        		// 이동가능한 거리로 접근
        		for(int i = -1; i < 2; i++) {
        			move = cur[1] + i; // 이동거리
        			cx = cur[0] + move;// 현재 위치
        			if(cx < 0) continue;
        			cnt = cur[2] + 1;  // 작동 횟수
        			
        			queue.add(new int[] {cx, move, cnt});
        		}
        	}
        	System.out.println(minMove);
        }
        br.close();
	}
}
