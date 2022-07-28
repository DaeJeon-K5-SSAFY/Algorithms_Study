import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] w = new int[n];
        int[] v = new int[n];
        int[][] bag = new int[n + 1][k + 1];
        int max = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (w[i - 1] > j)
                    bag[i][j] = bag[i - 1][j];
                else
                    bag[i][j] = Math.max(v[i - 1] + bag[i - 1][j - w[i - 1]], bag[i - 1][j]);
                max = Math.max(bag[i][j], max);
            }
        }
        System.out.println(max);
    }
}
