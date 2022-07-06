/*
 * BAEKJOON 11727. 2xn 타일링 2
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_11727_2xn타일링2 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_11727.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] dp = new int[N+1];
			
			switch (N) {
			default:
				dp[3] = 5;
			case 2:
				dp[2] = 3;
			case 1:
				dp[1] = 1;
			}
			
			// 점화식
			for(int i = 4; i <= N; i++) {
				dp[i] = (dp[i-1] + 2 * dp[i-2]) % 10007;
			}
			
			bw.write(String.format("#%d %d\n", test_case, dp[N]));
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
}
