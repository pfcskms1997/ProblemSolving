/*
 * BAEKJOON 11726. 2xn 타일링
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_11726_2xn타일링 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_11726.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] dp = new int[N+1];
			
			switch (N) {
			default:
				dp[3] = 3;
			case 2:
				dp[2] = 2;
			case 1:
				dp[1] = 1;
			}
			
			// 점화식
			for(int i = 4; i <= N; i++) {
				dp[i] = (dp[i-1] + dp[i-2]) % 10007;
			}
			
			bw.write(String.format("#%d %d\n", test_case, dp[N]));
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
}
