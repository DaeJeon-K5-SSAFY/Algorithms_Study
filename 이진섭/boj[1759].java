import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
 
public class Main {    
    
    static String[] text;
    static int l, c;
    static boolean[] visited;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        String str = br.readLine();
        text = str.split(" ");
        Arrays.sort(text);
        
        String[] result = new String[l];
        visited = new boolean[c];
        permutation(result, 0, 0, 0);
    }    
    
    public static void permutation(String[] result, int idx, int vowel, int consonant) {
        if(idx == l) {
            if(vowel >= 1 && consonant >= 2) {
                for(int i = 0; i < l; i++) {
                    System.out.print(result[i]);
                }
                System.out.println();
            }
            return;
        }
        
        for(int i = 0; i < c; i++) {
            if(visited[i] == false) {
                //이전 값보다 사전순으로 더 큰값을 뽑아야 하며 처음 뽑는 경우에는 상관없음
                if(idx == 0 || result[idx - 1].compareTo(text[i]) < 0) { 
                    visited[i] = true;
                    result[idx] = text[i];
                    if(isVowel(text[i])) permutation(result, idx + 1, vowel + 1, consonant);
                    else permutation(result, idx + 1, vowel, consonant + 1);
                    visited[i] = false;
                }
            }
        }
    }
    
    //모음 체크
    public static boolean isVowel(String s) {
        if(s.equals("a") || s.equals("e") || s.equals("i") || s.equals("o") || s.equals("u")) {
            return true;
        }
        return false;
    }
}