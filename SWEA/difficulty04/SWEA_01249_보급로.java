/*
 * SWEA 01249. 보급로
 */

package difficulty04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_01249_보급로 {
	
	static int N, ans;
	static int[][] map;
	static int[][] dp;
	
	// 하 우 상 좌
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	final static int INF = Integer.MAX_VALUE;

	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty04/input_01249.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			dp = new int[N][N];
			ans = 987654321;
			
			// 2차원 배열 map에 지도 정보 입력
			for(int r = 0; r < N; r++) {
				String line = br.readLine();
				for(int c = 0; c < N; c++) {
					map[r][c] = line.charAt(c) - '0';
				}
			}
			
			// memoization 용도의 2차원 배열의 원소를 -1로 초기화
			for(int r = 0; r < N; r++) {
				Arrays.fill(dp[r], -1);
			}
			
//			ans = DFS(0, 0, new boolean[N][N], 0);
			ans = dijkstra(0, 0);
			
			bw.write(String.format("#%d %d\n", test_case, ans));
			bw.flush();
		}
		
		// 결과 출력 및 프로그램 종료
		br.close();
		bw.close();
	}
	
	// 일반 DFS + 최소값 백트래킹으로는 시간초과 되었음
	private static int DFS(int r, int c, boolean[][] v, int sum) {
		if(dp[r][c] != -1) return dp[r][c];
		
		// 목적지에 도착
		if(r == N-1 && c == N-1) {
			dp[r][c] = Math.min(dp[r][c], sum);
			return 0;
		}
		
		dp[r][c] = 0;
		for(int d = 0; d < dr.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(checkRange(nr, nc)) {
				dp[r][c] = DFS(nr, nc, v, sum + map[r][c]);
			}
		}
		
		return dp[r][c];
	}
	
	private static int dijkstra(int sr, int sc) {
		Queue<int[]> queue = new LinkedList<int[]>(); // int[] {r, c, cost}
		int[][] cost = new int[N][N];
		
		// 2차원 배열 cost를 무한대로 채움
		for(int r = 0; r < N; r++) {
			Arrays.fill(cost[r], INF);
		}
		
		queue.offer(new int[] {sr, sc, 0});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int d = 0; d < dr.length; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				
				if(checkRange(nr, nc) && cost[nr][nc] > cur[2] + map[nr][nc]) {
					cost[nr][nc] = cur[2] + map[nr][nc];
					queue.offer(new int[] {nr, nc, cost[nr][nc]});
				}
			}
		}
		
		return cost[N-1][N-1];
	}
	
	private static boolean checkRange(int nr, int nc) {
		if(nr >= 0 && nr < N && nc >= 0 && nc < N) return true;
		else return false;
	}

	public static void printMap(int[][] map) {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
