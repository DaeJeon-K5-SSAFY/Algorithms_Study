package com.ssafy.study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 준서가 여행에 필요하다고 생각하는 N개의 물건, 각 물건은 무게 W와 가치 V를 가진다.
 * 가치의 최댓값을 알려주자.
 * 
 * 백트래킹 문제
 * 물품 수 - N
 * 버틸 수 있는 무게 K
 */
// 시간 초과
public class boj12865 {
	static int maxValue = 0; 
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("data/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());;
        
        int T = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        List<int[]> stuffList = new ArrayList<>();
        for(int tc = 0; tc < T; tc++) {
        	stk = new StringTokenizer(br.readLine());
        	int W = Integer.parseInt(stk.nextToken());
        	int V = Integer.parseInt(stk.nextToken());
        	stuffList.add(new int[] {W, V});
        }
        boolean[] visited = new boolean[T];
        int value = 0;
        dfs(K, value, stuffList, visited);
        System.out.println(maxValue);
        br.close();
	}
	public static void dfs(int weight, int value, List<int[]> list, boolean[] visited) {
		if(weight < 0) {
			return;
		}
		
		for(int i = 0; i < list.size(); i++) {
			if(visited[i] == true) continue;
			visited[i] = true;
			int w = weight-list.get(i)[0];
			int v = weight-list.get(i)[1];
			if(w < 0) 
				maxValue = Math.max(maxValue, value); 
			dfs(weight-list.get(i)[0], value+list.get(i)[1], list, visited);
			visited[i] = false;
		}
		return;
	}
}
