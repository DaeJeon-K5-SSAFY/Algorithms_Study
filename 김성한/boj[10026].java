import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[][] board1 = new String[N][N];
		String[][] board2 = new String[N][N];
		for(int i = 0; i < N; i++) {
			String[] temp = br.readLine().split("");
			System.arraycopy(temp, 0, board1[i], 0, temp.length);
			System.arraycopy(temp, 0, board2[i], 0, temp.length);
		}
		
		int count1 = 0;
		int count2 = 0;
		
		


		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!(board1[i][j].equals("v"))) {
					dfs1(board1,board1[i][j],i,j,N);
					count1++;
				}
				if(!(board2[i][j].equals("v"))) {
					dfs2(board2,board2[i][j],i,j,N);
					count2++;
				}
			}
		}

		System.out.println(count1+" "+count2);

	}

	public static void dfs1(String[][] tempBoard, String color, int x, int y, int N) { //색약이 없는 사람용 깊이우선 탐색
		if(x < 0 || x >= N || y < 0 || y >= N) {
			return;
		}

		if(!tempBoard[x][y].equals(color)) {
			return;
		}
		else {
			tempBoard[x][y] = "v";
			dfs1(tempBoard, color, x-1, y, N);
			dfs1(tempBoard, color, x+1, y, N);
			dfs1(tempBoard, color, x, y-1, N);
			dfs1(tempBoard, color, x, y+1, N);	
		}
	}

	public static void dfs2(String[][] tempBoard, String color, int x, int y, int N) {
		if(x < 0 || x >= N || y < 0 || y >= N) {
			return;
		}

		if(color.equals("R") || color.equals("G")) {
			if(tempBoard[x][y].equals("R")|| tempBoard[x][y].equals("G")) {
				tempBoard[x][y] = "v";
				dfs2(tempBoard, color, x-1, y, N);
				dfs2(tempBoard, color, x+1, y, N);
				dfs2(tempBoard, color, x, y-1, N);
				dfs2(tempBoard, color, x, y+1, N);	
			}
		}
		else if(color.equals("B")) {
			if(tempBoard[x][y].equals(color)) {
				tempBoard[x][y] = "v";
				dfs2(tempBoard, color, x-1, y, N);
				dfs2(tempBoard, color, x+1, y, N);
				dfs2(tempBoard, color, x, y-1, N);
				dfs2(tempBoard, color, x, y+1, N);	
			}
		}		
	}
}
