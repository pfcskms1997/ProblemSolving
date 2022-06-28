/*
 * SWEA 01959. 두 개의 숫자열
 */

package difficulty02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA_01959_두개의숫자열 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty02/input_01959.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 숫자열 Ai의 길이
			int M = Integer.parseInt(st.nextToken()); // 숫자열 Bj의 길이
			LinkedList<Integer> A = new LinkedList<Integer>();
			LinkedList<Integer> B = new LinkedList<Integer>();
			int maxValue = -987654321;
			
			// 숫자열 A 입력
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				A.add(Integer.parseInt(st.nextToken()));
			}
			
			// 숫자열 B 입력
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < M; i++) {
				B.add(Integer.parseInt(st.nextToken()));
			}
			
			for(int i = 0; i < Math.abs(N - M) + 1; i++) {
				int val = 0;
				for(int j = 0; j < Math.min(A.size(), B.size()); j++) {
					val += A.get(j) * B.get(j);
				}
				maxValue = Math.max(maxValue, val);
				
				// 더 긴 숫자열의 앞 부분을 잘라냄
				if(A.size() > B.size()) A.remove(0);
				else if(A.size() < B.size()) B.remove(0);
			}
			
			sb.append(String.format("#%d %d\n", test_case, maxValue));
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
