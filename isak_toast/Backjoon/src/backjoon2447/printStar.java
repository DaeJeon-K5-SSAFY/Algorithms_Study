package backjoon2447;

/*
재귀적인 패턴으로 별을 찍어 보자. N이 3의 거듭제곱(3, 9, 27, ...)이라고 할 때, 크기 N의 패턴은 N×N 정사각형 모양이다.
크기 3의 패턴은 가운데에 공백이 있고, 가운데를 제외한 모든 칸에 별이 하나씩 있는 패턴이다.


*/

import java.util.*;
import java.io.*;

public class printStar {
	 public static void main(String args[]) throws Exception {
//       BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   StringTokenizer stk = new StringTokenizer(br.readLine());
//       String[] str = br.readLine().split(" ");
       int M = Integer.parseInt(stk.nextToken());
//       int N = Integer.parseInt(str[1]);

	 }
	 public static void recusStar(int num) {
		 for(int i = 0; i < num; i++) {
			 for(int j = 0; j < num; j++) {
				 
			 }
		 }
	 }
}
