import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = y - x;

            int max = (int) Math.sqrt(d);
            if (max == Math.sqrt(d)) {
                sb.append(max * 2 - 1).append('\n');
            } else if (d <= max * max + max) {
                sb.append(max * 2).append('\n');
            } else {
                sb.append(max * 2 + 1).append('\n');
            }
        }
        System.out.println(sb);
    }
}