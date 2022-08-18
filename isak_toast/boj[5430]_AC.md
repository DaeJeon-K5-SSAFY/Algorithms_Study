## BOJ_5430_AC

---

<br />

### 코드

```java
package com.ssafy.problem2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_5430_AC {
	public static void main(String[] args) {
		try {
			version1();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static final int MAX_SIZE = 100000;
	private static void version1() throws IOException {
		StringBuilder sb = new StringBuilder();
		System.setIn(new FileInputStream("data2/5430.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		List<Integer> list = new ArrayList<>();

		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
//			char[] comd = br.readLine().toCharArray(); // 시간 초과
			String comd = br.readLine();  // atChar로 접근하는게 더 빠름
			int n = Integer.parseInt(br.readLine()); // n
//			int[] arr = Arrays.stream(br.readLine().split("[\\,\\[\\]]"))
//					.filter(w -> !w.equals(""))
//					.mapToInt(Integer::parseInt)
//					.toArray();
			stk = new StringTokenizer(br.readLine(), "[],");  // 시간과 메모리 줄임
			for(int i = 0; i < n; i++) {
				list.add(Integer.parseInt(stk.nextToken()));
			}
			
			// 커맨드에 따라서 배열 값 변경
			int k = 0;
			boolean reverseSwitch = false;
			boolean errorFlag = false;
			char ch;
			for(int i = 0; i < comd.length(); i++) {
				ch = comd.charAt(i);
				if(ch == 'R') {
					k = (k == 0) ? list.size() - 1 : 0;
					reverseSwitch = !reverseSwitch;
				}
				else if(ch == 'D') {
					if(list.size() == 0) {
						errorFlag = true;
						break;
					}
					list.remove(k);
					if(reverseSwitch) k--;
				}
			}
			if(errorFlag) {
				sb.append("error\n");
			}
			else {
				if(reverseSwitch) {
					Collections.reverse(list);
				}
				sb.append("[");
				for(Integer i : list) {
					sb.append(i + ",");
				}
				if(list.size() != 0) sb.deleteCharAt(sb.length()-1);
				sb.append("]\n");
			}
			list.clear();
		}
		System.out.println(sb.deleteCharAt(sb.length()-1));
		br.close();
	}
	static int readInt() throws IOException {
        int n = 0;
        int input;
        while((input = System.in.read()) <= 32 || input=='[');
        while (true) {
            if (input>47 && input<58)
                n = (n<<3) + (n<<1) + (input&15);
            else if(input != ']') return n;
            input = System.in.read();
        }
    }
}
```

<br />


### 결과 : 맞았습니다. 

- 메모리 : 120156KB
- 실행시간 : 2724ms
- 코드길이 : B

<br />

### 풀이 방법

BOJ_5430_AC

입력
T : 최대 100
수행할 함수 p : 1 ~ 100000 길이
배열에 들어있는 수 n : 0 ~ 100000
정수 x : 1 ~ 100

- R,D가 있는데 D는 첫번째 수를 버리는 함수이므로 R을 뒤집을 때 굳이 배열을 뒤집을 필요없이 인덱스만 끝으로 보내면 된다.
- 인덱스가 끝인지 처음인지 -> reverse인지 normal인지만 판단하는 state 변수
- D가 들어오고 배열의 size를 판별해 삭제할 원소가 없으면 'error' 출력

패턴
- flag와 List를 활용해서 앞, 뒤로 옮겨가면서 List의 원소를 삭제하는 방식으로 진행
- List를 역순 출력할 때는 Collections.reverse()를 사용

<br />

<!--추가 내용 있다면 더 적어주시면 됩니다-->
> 33% 틀렸습니다.

!!! note
	Collections.reverse(list) vs Collections.sort(list, Collections.reverseOrder) 의 차이
	딱 봐도 보이지만 하나는 리스트를 역순으로 만드는 것이고 하나는 역순으로 정렬한 것이다. 그러니 정렬된 데이터가 출력되어서 계속 틀린 것

> 시간 초과 오류

!!! note
	시간 초과 오류를 해결하려면 입력단을 고쳐야 한다. 메모리와 입력 데이터가 클수록 많은 처리가 필요하기 때문에 
	기존의 toCharArray() -> charAt 형태로 바로 받아서 사용하는 식으로 바꿨다. 굳이 배열에 넣고 다시 꺼내서 쓰지 않도록
	그리고 int[] arr를 하는 부분에서 stream, filter 등 많은 처리를 하기 때문에 메모리와 시간을 비효율적으로 낭비한다
	그러므로 찾아보니 StringTokenizer(br.readLine(), "[],") 바로 구별자에 따라서 나눠주니 빠르고 혹은 subString(1, t.length-1).split(",")으로 앞 뒤 괄호를 지우고 쉼표를 구분자로 나눌 수도 있다

---
