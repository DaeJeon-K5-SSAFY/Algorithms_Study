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
