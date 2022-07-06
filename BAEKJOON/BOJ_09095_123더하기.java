/*
 * BAEKJOON 09095. 1, 2, 3 더하기
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_09095_123더하기 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_09095.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 테스트 케이스의 입력값
			int[] dp = new int[N+1];
			
			// 기저 조건 설정을 위해 의도적으로 break 걸지 않음
			switch(N) {
			default:
			case 3:
				dp[3] = 4;
			case 2:
				dp[2] = 2;
			case 1:
				dp[1] = 1;
			}
			
			for(int i = 4; i <= N; i++) {
				dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
			}
			
			// 결과 출력
			bw.write(String.format("#%d %d\n", test_case, dp[N]));
			bw.flush();
		}
		
		// 자원 반환
		br.close();
		bw.close();
	}
}
