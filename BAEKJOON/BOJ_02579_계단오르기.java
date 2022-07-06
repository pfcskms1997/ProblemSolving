/*
 * BAEKJOON 02579. 계단 오르기
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_02579_계단오르기 {
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_02579.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine()); // 계단의 개수
		int[] stairs = new int[N+1];

		// 계단의 점수 입력
		for(int i = 1; i <= N; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[N+1];
		switch(N) {
		default:
			dp[3] = Math.max(stairs[1], stairs[2]) + stairs[3]; // 연속한 세 계단을 밟을 수 없으므로 1, 2번째 계단 중 큰 값을 밟고 감
		case 2:
			dp[2] = stairs[1] + stairs[2]; // 두 번째 계단에 도달하는 최대 값은 첫 번째 계단과 두 번째 계단 모두 밟고 간 경우
		case 1:
			dp[1] = stairs[1];
		}
		
		for(int i = 4; i <= N; i++) {
			dp[i] = Math.max(dp[i-3] + stairs[i-1], dp[i-2]) + stairs[i];
		}
		// 결과 출력 및 프로그램 종료
		bw.write(String.format("%d", dp[N]));
		bw.flush();
		br.close();
		bw.close();
	}
}
