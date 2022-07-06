/*
 * BAEKJOON 17070. 파이프 옮기기 1
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17070_파이프옮기기1 {
	
	static class Pipe {
		int r;
		int c;
		int d; // 0: 가로, 1: 대각선, 2: 세로
		
		public Pipe(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	
	static int N;

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_17070.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());

		// 각 TestCase별 수행
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine()); // 집의 크기
			int[][] house = new int[N+1][N+1];

			// 2차원 배열 입력
			StringTokenizer st = null;
			for (int r = 1; r < house.length; r++) {
				st = new StringTokenizer(br.readLine(), " ");

				for (int c = 1; c < house[r].length; c++) {
					house[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] dp = new int[N+1][N+1];
			for(int i = 0; i < dp.length; i++) {
				Arrays.fill(dp[i], -1);
			}
			
			int ans = DFS(house, dp, new Pipe(1, 2, 0));
			printMap(dp);
			
			// 결과 출력
			bw.write(String.format("#%d %d\n", test_case, ans));
			bw.flush();
		}
		br.close();
		bw.close();
	}
	
	//                가로 대각선 세로
	static int[] dr = {0, 1, 1};
	static int[] dc = {1, 1, 0};
	
	private static int DFS(int[][] house, int[][] dp, Pipe p) {
		// 목적지에 도착하는 경우 1을 반환
		if(p.r == N && p.c == N) return 1;
		
		dp[p.r][p.c] = 0;
		for(int d = 0; d < dr.length; d++) {
			
			// 파이프가 가로로 놓여있을 경우에 세로로 움직이는 경우는 pass
			if(p.d == 0 && d == 2) continue;
			
			// 파이프가 세로로 놓여있을 경우에 가로로 움직이는 경우는 pass
			if(p.d == 2 && d == 0) continue;
			
			int nr = p.r + dr[d];
			int nc = p.c + dc[d];
			
			if(nr >= 1 && nr < N+1 && nc >= 1 && nc < N+1 && house[nr][nc] != 1) {
				if(d == 1) { // 대각선으로 이동하는 경우를 살펴볼 때
					// 움직이기 직전 위치의 아래와 오른쪽에 벽지(1)이면 움직일 수 없으므로 다음 단계로 pass
					if(house[p.r][p.c + 1] == 1 || house[p.r + 1][p.c] == 1) continue;
				}
				
				// Memoization
				dp[p.r][p.c] = dp[p.r][p.c] + DFS(house, dp, new Pipe(nr, nc, d));
			}
		}

		return dp[p.r][p.c];
	}

	public static void printMap(int[][] map) {
		for (int r = 1; r < map.length; r++) {
			for (int c = 1; c < map[r].length; c++) {
				System.out.printf("%2d ", map[r][c]);
			}
			System.out.println();
		}
	}
}
