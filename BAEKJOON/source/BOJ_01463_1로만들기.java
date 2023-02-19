/*
 * BAEKJOON 01463. 1로 만들기
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_01463_1로만들기 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_01463.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] dp = new int[N+1];
			dp[1] = 0;
			
			for(int i = 2; i <= N; i++) {
				dp[i] = dp[i - 1] + 1;
				
				if(i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
				if(i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
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
