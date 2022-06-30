/*
 * SWEA 05215. 햄버거 다이어트
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_05215_햄버거다이어트_DP {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty03/input_05215.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 재료의 수
			int L = Integer.parseInt(st.nextToken()); // 제한 칼로리
			int[] score = new int[N];
			int[] cal = new int[N];
			
			// 각 1차원 배열에 재료의 정보 입력
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				score[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}
			
			// 제한 칼로리까지의 결과를 저장할 배열
			int[][] dp = new int[N+1][L+1];
			
			// 0/1 Knapsack
			for(int i = 1; i < dp.length; i++) {
				for(int j = 1; j < dp[i].length; j++) {
					if(j < cal[i - 1]) {
						dp[i][j] = dp[i-1][j];
					}
					else {
						dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - cal[i-1]] + score[i-1]);
					}
				}
			}
			sb.append(String.format("#%d %d\n", test_case, dp[N][L]));
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
