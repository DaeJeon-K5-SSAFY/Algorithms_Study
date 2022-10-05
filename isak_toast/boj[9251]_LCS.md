## BOJ_9251_LCS
---

<br />

### 코드

```java
package com.ssafy.problem3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9251_LCS {
	static int[][] dp;
	static char[] A, B;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("data3/9251.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		A = br.readLine().toCharArray(); // String.a
		B = br.readLine().toCharArray();
		
		System.out.println(getLCS(A.length, B.length));
	}
	private static int getLCS(int N, int M) {
		dp = new int[N+1][M+1]; // 최대 1000글자
		// LCS(Longest Common Subsequence) 점화식
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (A[i-1] == B[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1; 
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		return dp[N][M];
	}
}
```

<br />


### ACK

- 메모리 : 	15808KB
- 실행시간 : 100ms

<br />

### 풀이 방법
LCS 알고리즘

1. 최장 공통 문자열 (Longest Common Substring)

점화식
if(i == 0 || j == 0){
    LCS[i][j] = 0
} else if(stringA[i] == stringB[j]) { // 문자열이 동일하다면
    LCS[i][j] = LCS[i-1][j-1] + 1;
} else {
    LCS[i][j] = 0
}

> 연속된 부분문자열의 최대 길이를 표현할 수 있는 dp의 점화식 표현이 된다.

2. 최장 공통 부분수열(Longest Common Subsequence)

점화식
if(i == 0 || j == 0) {
    LCS[i][j] = 0
} else if(stringA[i] == stringB[j]){
    LCS[i][j] = LCS[i-1][j-1] + 1
} else {
    LCS[i][j] = max(LCS[i-1][j], LCS[i][j-1]) // 직전에 비교했던 값들 (2개) 중에서 최대 길이인 값을 취함.
}

> LCS 문자열을 찾고 싶다면 상향식 흐름으로 끝에서부터 값을 역순으로 찾고 문자열을 뒤집으면 LCS 문자열을 얻을 수 있다. 

참고: [Link](https://velog.io/@emplam27/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-LCS-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Longest-Common-Substring%EC%99%80-Longest-Common-Subsequence)

<br />

<!--추가 내용 있다면 더 적어주시면 됩니다-->

---
