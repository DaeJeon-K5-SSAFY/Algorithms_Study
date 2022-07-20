package backjoon7576;

import java.io.*;
import java.util.*;

/*
창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만, 아직 익지 않은 토마토들도 있을 수 있다. 
보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다. 
하나의 토마토의 인접한 곳은 왼쪽, 오른쪽, 앞, 뒤 네 방향에 있는 토마토를 의미한다. 
*/

/* 
1주변에 사방으로 확장되듯이 0을 탐색하는 알고리즘 -> BFS

sol)
입력 - 토마토맵 초기화
토마토 위치를 Queue에 넣어서 하나씩 빼면서 전체 영역을 모두 탐색할 때까지 진행

최소 몇 번만에 완료되었는지 출력
토마토 맵에 0이 있다면 모든 탐색이 되지 않았으므로 -1 출력
*/
//
public class Tomato {
   public static void main(String[] args) throws NumberFormatException, IOException {
       // 입력단
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer stk = new StringTokenizer(br.readLine());

       int col = Integer.parseInt(stk.nextToken());
       int row = Integer.parseInt(stk.nextToken());
       int[][] tomatoMap = new int[row][col];

       // 맵초기화
       for(int i = 0; i < row; i++){
           stk = new StringTokenizer(br.readLine());
           for(int j = 0; j < col; j++){
               tomatoMap[i][j] = Integer.parseInt(stk.nextToken());
               // System.out.print(tomatoMap[i][j]);
           }
           // System.out.println();
       }

       //토마토 위치 특정
       Queue<int[]> que = new LinkedList<int[]>();
       for(int i = 0; i < row; i++){
           for(int j = 0; j < col; j++){
               if(tomatoMap[i][j]==1){
                   que.add(new int[] {j,i,0}); // y,x point 
               }
           }
       }

       //특정 위치로부터 bfs 진행
       boolean[][] visited = new boolean[row][col]; // 방문 여부를 검사할 배열
       int res = 0;
       res = bfsMap(que, tomatoMap, visited);
       
       System.out.print(res);
   }

   public static int bfsMap(Queue<int[]> queue,int[][] tomatoMap,boolean[][] visited){
       int[][] delta = {{1,0}, {0,-1}, {-1,0}, {0,1}};

       int x,y,dx,dy;
       int[] p = new int[3];

       int row = tomatoMap.length;
       int col = tomatoMap[0].length;
       
       int minRank = 0;
       int rank = 0;
       while(!queue.isEmpty()){
           p = queue.poll();
           x = p[0];
           y = p[1];
           rank = p[2];
           if(rank > minRank) minRank = rank;
           visited[y][x] = true;
           
           tomatoMap[y][x] = 1; // 익음

           for(int i = 0; i < 4; i++){
               dx = x + delta[i][0];
               dy = y + delta[i][1];
               if((dx>=0 && dx<col) && (dy>=0 && dy<row) && (tomatoMap[dy][dx]==0) && !visited[dy][dx]){
                   queue.add(new int[] {dx, dy, rank+1});
                   // visited[dy][dx] = true;
               }
           }
           
       }
       for(int i = 0; i < row; i++){
           for(int j = 0; j < col; j++){
           	if(tomatoMap[i][j] == 0) return -1;
//                System.out.print(tomatoMap[i][j]);
           }
//            System.out.println();
       }
       return minRank;
   }
}


////version2
//import java.io.*;
//import java.util.*;
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.util.LinkedList;
//import java.util.Queue;
// 
//public class Tomato {
//    static int[] dx = { -1, 1, 0, 0 };
//    static int[] dy = { 0, 0, -1, 1 };
// 
//    public static void main(String args[]) throws Exception {
////        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
//    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String[] str = br.readLine().split(" ");
//        int M = Integer.parseInt(str[0]);
//        int N = Integer.parseInt(str[1]);
// 
//        int[][] arr = new int[N][M];
// 
//        for (int i = 0; i < N; i++) {
//            str = br.readLine().split(" ");
//            for (int j = 0; j < M; j++) {
//                arr[i][j] = Integer.parseInt(str[j]);
// 
//            }
//        }
//        //----------------- 입력 부 ------------------
//        BFS(arr, N, M);
//    }
// 
//    public static void BFS(int[][] arr, int N, int M) {
//        Queue<DOT> q = new LinkedList<DOT>();
// 
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                if (arr[i][j] == 1)
//                    //익은 토마토가 있는 모든 위치를 큐에 담는다.
//                    q.add(new DOT(i, j));
//            }
//        }
// 
//        while (!q.isEmpty()) {
//            //익은 토마토의 상하좌우는 다음에 익기 때문에 큐에 담아야한다.
//            DOT dot = q.poll();
//            for (int i = 0; i < 4; i++) {
//                int nextX = dot.x + dx[i];
//                int nextY = dot.y + dy[i];
// 
//                //범위 밖 패스
//                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
//                    continue;
//                }
//                //다음 위치가 익지 않은 토마토가 아니면 패스
//                if (arr[nextX][nextY] != 0) {
//                    continue;
//                }
//                //최대 일수를 구하기 때문에 1로 바꾸는 것이 아니라 현재 일수 +1 을 해줘야한다.
//                arr[nextX][nextY] = arr[dot.x][dot.y] + 1;
//                q.add(new DOT(nextX, nextY));
//            }
//            //print(arr, N, M); // 농장 전체 출력
//            //System.out.println();
//        }
//        int max = 0;
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                if (arr[i][j] == 0) {
//                    //토마토가 모두 익지 못한 상황이라면 -1 을 출력한다.
//                    System.out.println(-1);
//                    return;
//                }
//                max = Math.max(max, arr[i][j]);
//            }
//        }
//        //그렇지 않다면 최대값을 출력한다.
//        System.out.println(max - 1);
// 
//    }
//    //농장을 전체 보여주는 함수
//    public static void print(int[][] arr, int N, int M) {
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }
//}
// 
//class DOT {
//    int x;
//    int y;
// 
//    DOT(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//}
