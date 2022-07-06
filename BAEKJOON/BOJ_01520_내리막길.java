/*
 * BAEKJOON 01520. 내리막 길
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_01520_내리막길 {
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int M, N;
	static int Ans;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_01520.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken()); // 지도의 세로 크기
		N = Integer.parseInt(st.nextToken()); // 지도의 가로 크기
		int[][] map = new int[M][N];
		Ans = 0;
		
		for(int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c = 0; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// printMap(map);
		// DFS(map, 0, 0);
		
		dp = new int[M][N];
		for(int i = 0; i < map.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		Ans = DFS_dp(map, 0, 0);
		
		// 결과 출력 및 프로그램 종료
		bw.write(String.format("%d", Ans));
		bw.flush();
		br.close();
		bw.close();
	}

	// 시간 초과
	public static void DFS(int[][] map, int r, int c) {
		if(r == M-1 && c == N-1) Ans++;
		
		for(int d = 0; d < dr.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr >= 0 && nr < M && nc >= 0 && nc < N && map[r][c] > map[nr][nc]) {
				DFS(map, nr, nc);
			}
		}
	}

	private static int DFS_dp(int[][] map, int r, int c) {
		// 프로그램 실행 중 도착 지점은 한 번만 오게 됨
		if(r == M-1 && c == N-1) return 1;
	
		// 이미 [r][c] 지점에서 목적지까지의 경로 수가 계산된 이력이 있으므로 기록한 값을 반환
		if(dp[r][c] != -1) return dp[r][c];
		
		dp[r][c] = 0;
		for(int d = 0; d < dr.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr >= 0 && nr < M && nc >= 0 && nc < N && map[r][c] > map[nr][nc]) {
				// r과 c까지 오는데 걸리는 시간 + nr, nc에서 목표까지 가는 시간
				dp[r][c] = dp[r][c] + DFS_dp(map, nr, nc);
			}
		}
		return dp[r][c];
	}
	
	public static void printMap(int[][] map) {
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[r].length; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
