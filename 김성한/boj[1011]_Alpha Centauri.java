import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
	
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int l = b - a;
			int maxNum = (int) Math.sqrt(l);
			
			if(maxNum == Math.sqrt(l)){
                System.out.println(2*maxNum-1);
            }else if(l <= maxNum*maxNum+maxNum){
                System.out.println(2*maxNum);
            }else if(l > maxNum*maxNum+maxNum){
                System.out.println(2*maxNum+1);
            }
		}
	}
}
