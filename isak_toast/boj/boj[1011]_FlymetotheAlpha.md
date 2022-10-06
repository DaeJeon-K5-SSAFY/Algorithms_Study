## BOJ_1011 Fly me to the Alpha Centauri


---

<br />

### 코드

```java
package com.ssafy.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1011_FlyAlpha_패턴 {
	public static void main(String[] args) {
        try {
			version1();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void version1() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;
        
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
        	stk = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(stk.nextToken());
        	int M = Integer.parseInt(stk.nextToken());
        	
        	int distance = Math.abs(N-M);
        	
        	System.out.println(getCount(distance));
        }
	}

	// 규칙을 통해 수식으로 이동횟수 구하기
	private static int getCount(int distance) {
		int ret = 0;
		int max = (int)Math.sqrt(distance-1);
		if(distance <= max*max + max) {
			ret = 2*max;
		} else {
			ret = 2*max + 1; 
		}
		return ret;
	}
	// 수열로 순차적으로 distance의 이동횟수 구하기
//	private static int getCount(int distance) {
//		int cnt = 0;
//		int max = 1;
//		int series = 0;
//		int temp = 0;
//		while(distance > series) {
//			if(temp++ == 2) {
//				temp = 0;
//				max++;
//			}
//			series += max;
//			cnt++;
//		}
//		return cnt;
//	}
}
```

<br />


### 결과 : 맞았습니다.

- version1
- 메모리 : 	11676kb
- 시간 : 80ms
<br />

### 풀이 방법

기존 풀이 방식
- BFS 최소 경로를 찾는 문제
  - 하지만 마지막에 있는 조건 (이전에 1로 도달해야 한다)
  - 조건을 만족하지 못해서 오류

규칙성이 있는 문제 (수학 문제)
- 규칙을 찾을 때, 원하는 값에 대한 최소, 최대로 변경을 해서 보라
- 거리에 따라서 이동횟수, 최대 이동거리, 이동 형태를 표현해보자.

특징
- 해당 이동은 수열 표현으로 최대 이동거리의 수열로 표현된다. 

거리 수열
`0 - 1 - 2 - 4 - 6 - 9 - 12 - 16 - 20 - 25 - 30 ...`

횟수
`0 - 1 - 2 - 3 - 4 - 5 - 6  -  7  - 8 -  9 - 10`

이런 식으로 표현되며 거리를 입력으로 줄 때, 표현된 거리 범위의 매핑된 값을 찾는 문제이다. 

```
5라는 거리가 주어지면 해당 범위로부터 횟수각 주어지는 getCount(int distance) 함수를 생성

2번에 한 번씩 등차가 변경되는 수열을 통해서 

수열의 an이 distance를 넘게 되는 지점이 해당하는 범위이므로 그 때의 count값을 넘긴다. 
```
> 수열로 푸는 것도 방법이지만 해당 내용은 규칙이 더 있다.
> 그리고 수열로 풀면 시간 복잡도도 크고 오래 걸림 (거리값이 크기 때문에)

distance의 제곱근은 max(최대 이동거리)표현이 되고
count값은 2*max-1로 표현이 된다.

수열은 순차적으로 범위를 구하지만 수식을 사용하면 distance로부터 빠르게 범위를 구해볼 수 있다. 

---

#### 기존 코드

```java
// BFS 풀이
package com.ssafy.problem;

import java.io.BufferedReader;
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
public class BOJ_1011_FlyAlpha_BFS {
	
	public static void main(String[] args) {
        try {
			version1();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void version1() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;
        
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
        	stk = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(stk.nextToken());
        	int M = Integer.parseInt(stk.nextToken());
        	
        	// 이동거리 -> x-1, x, x+1 
        	Queue<int[]> queue = new LinkedList<int[]>();
        	boolean[] visited = new boolean[M + 1];
        	queue.add(new int[] {N, 0, 0});  // 현재 위치, 이동 거리, 작동 횟수
        	visited[N] = true; 
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
        			if(cx < 0 || M < cx) continue;
        			if(visited[cx]) continue;
        			visited[cx] = true;
        			cnt = cur[2] + 1;  // 작동 횟수
        			queue.add(new int[] {cx, move, cnt});
        		}
        	}
        	System.out.println(minMove);
        }
        br.close();
	}
}
```