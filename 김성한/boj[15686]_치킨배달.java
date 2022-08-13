import java.util.*;
import java.io.*;

public class Main {
	static Map<Integer, int[]> map = new HashMap<>();// 치킨집 좌표를 위한 자료구조 
	static int chicken = 0, home = 0;
	static int min, N, M;
	static int[][] numbers;
	static int[][] homes;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] board = new int[N][N]; // 실제 입력 지도
		numbers = new int[M][];
		
		min = Integer.MAX_VALUE;
		List<int[]> temp = new ArrayList<>();
		// 입력 데이터 처리
		// 치킨집좌표를 저장하고 두개의 board 초기화
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 2) {
					map.put(chicken++, new int[] {i,j}); // 치킨집 번호와 좌표를 맵핑
				}else if(num == 1) {
					// 집 위치에 대해서도 좌표를 유지 => 이후 조합을 수행후 마지막 return전에 치킨집까지의 거리를 계산
					//homes[home++] = new int[] {i,j};
					temp.add(new int[] {i,j});
				}
				board[i][j] = num;
			}
		}
		homes = temp.toArray(new int[temp.size()][]);
		home = homes.length;
		
		combination(0, 0);

		// 저장된 좌표에서 M개의 좌표를 골라 그 조합에 대해 모두 탐색한 거리가 가장 작은 경우를 출력하면됨. 
		
		System.out.println(min);
	}

	private static void combination(int cnt, int start) {
		if(cnt == M) {
			int[] temp = new int[home];
			Arrays.fill(temp, Integer.MAX_VALUE);
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < home; j++) {
					int d = Math.abs(numbers[i][0]-homes[j][0]) + Math.abs(numbers[i][1]-homes[j][1]);
					if(d < temp[j]) {
						temp[j] = d;
					}
				}
			}
			int sum = 0;
			for(int n : temp) {
				sum += n;
			}
			if(sum < min) min = sum;
			return;
		}
		
		for(int i = start; i < chicken; i++) {
			numbers[cnt] = map.get(i);
			combination(cnt+1, i+1);
		}
	}

}