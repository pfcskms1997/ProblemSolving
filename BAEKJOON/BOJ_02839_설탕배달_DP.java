/*
 * BAEKJOON 02839. 설탕 배달
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_02839_설탕배달_DP {
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_02839.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 총 설탕의 무게
			int[] dp = new int[N+1];
			
			Arrays.fill(dp, 987654321);
			dp[3] = 1;
			if(N >= 5) {
				dp[5] = 1;
				
				for(int i = 6; i <= N; i++) {
					dp[i] = Math.min(dp[i-3] + 1, dp[i-5] + 1);
				}
			}
			
			bw.write(String.format("#%d %d\n", test_case, dp[N] >= 987654321 ? -1 : dp[N]));
			bw.flush();
		}
		br.close();
		bw.close();
	}
}
