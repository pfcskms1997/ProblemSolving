/*
 * SWEA 03307. 최장 증가 부분 수열
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_03307_최장증가부분수열 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty03/input_03307.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 수열의 길이
			int[] elements = new int[N];
			int ans = 0;
			
			// 1차원 배열에 입력 받은 숫자를 삽입
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				elements[i] = Integer.parseInt(st.nextToken());
			}
			
			// 최장 증가 부분 수열 알고리즘
			int[] LIS = new int[N];
			for(int i = 0; i < elements.length; i++) {
				LIS[i] = 1;
				for(int j = 0; j < i; j++) {
					if(elements[j] < elements[i] && LIS[i] < 1+LIS[j]) {
						LIS[i] = 1 + LIS[j];
					}
				}
				ans = Math.max(ans, LIS[i]);
			}
			
			sb.append(String.format("#%d %d\n", test_case, ans));
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
