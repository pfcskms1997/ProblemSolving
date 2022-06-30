/*
 * SWEA 03499. 퍼펙트 셔플
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA_03499_퍼펙트셔플 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty03/input_03499.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringBuilder sb = new StringBuilder();
			int N = Integer.parseInt(br.readLine());
			int half = 0;
			
			// 카드 개수의 홀/짝 여부 판별
			if(N % 2 == 0) half = N / 2;
			else half = (N / 2) + 1;

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			LinkedList<String> deck = new LinkedList<String>();
			String[] firstPart = new String[half];
			String[] secondPart = new String[N - half];
			
			// 카드 나누기
			for(int i = 0; i < firstPart.length; i++) {
				firstPart[i] = st.nextToken();
			}
			for(int i = 0; i < secondPart.length; i++) {
				secondPart[i] = st.nextToken();
			}
			
			// 카드 합치기
			for(int i = 0; i < half; i++) {
				deck.add(firstPart[i]);
				sb.append(firstPart[i] + " ");
				
				if(i < secondPart.length) {
					deck.add(secondPart[i]);
					sb.append(secondPart[i] + " ");
				}
			}
			System.out.printf("#%d %s\n", test_case, sb);
		}
		br.close();
	}
}
