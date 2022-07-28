package com.ssafy.study;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/*
 * 별찍기 - boj 2447
 * 
 *	N = 3의 거듭제곱
 *  
 *  배열 생성 -> 3의 거듭제곱 행 열
 *  27 -> 9 -> 3 
 *  
 *  for 0~9, 4일 때마다 공백
 */
public class boj2447 {
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("data/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		char[][] starMap = new char[N][N];
		
		int row = 0, col = 0;
		printStar(N, starMap, row, col);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(starMap[i][j]);
			}
			System.out.println();
		}
		sc.close();
	}
	
	public static void printStar(int num, char[][] map, int row, int col) {
		// 3x3일 때는 최소 행렬로 그려줘야 함
		if(num == 3) {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(i == 1 && j == 1) continue;
					map[row+i][col+j] = '*';
				}
			}
			return;
		}
		// 재귀 형태, row와 col이 축적되는 것에 의미가 있음
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(i == 1 && j == 1) continue;
				printStar(num/3, map, row+(num/3)*i, col+(num/3)*j);
			}
		}
		return;
	}
}
