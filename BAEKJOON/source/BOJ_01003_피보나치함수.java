/*
 * BAEKJOON 01003. 피보나치 함수
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_01003_피보나치함수 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_01003.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int tc = Integer.parseInt(br.readLine()); // 테스트케이스의 수
			StringBuilder res = new StringBuilder();
			
			for(int tcase = 1; tcase <= tc; tcase++) {
				int N = Integer.parseInt(br.readLine()); // 각 케이스의 입력값
				int[] dpZero = new int[N+1];
				int[] dpOne = new int[N+1];
				
				dpZero[0] = 1;
				dpOne[0] = 0;
				
				if(N > 0) {
					dpZero[1] = 0;
					dpOne[1] = 1;
				}
				
				for(int i = 2; i <= N; i++) {
					dpZero[i] = dpZero[i-1] + dpZero[i-2];
					dpOne[i] = dpOne[i-1] + dpOne[i-2];
				}
				res.append(String.format("%d %d\n", dpZero[N], dpOne[N]));
			}
			
			// 결과 출력
			bw.write(String.format("#%d\n%s", test_case, res.toString()));
			bw.flush();
		}
		
		// 자원 반환
		br.close();
		bw.close();
	}
}
