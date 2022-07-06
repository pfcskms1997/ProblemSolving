/*
 * BAEKJOON 04485. 녹색 옷 입은 애가 젤다지?
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_04485_녹색옷입은애가젤다지 {
	
	static class Point implements Comparable<Point>{
		int r; // 행 좌표
		int c; // 열 좌표
		int rupee; // 도둑루피
		
		public Point(int r, int c, int rupee) {
			this.r = r;
			this.c = c;
			this.rupee = rupee;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.rupee, o.rupee);
		}
	}
	
	static int ans;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_04485.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_case = 1; // 테스트케이스 반복 횟수 출력용 변수
		
		while(true) {
			int N = Integer.parseInt(br.readLine()); // 동굴의 크기
			
			// 입력이 0이면 종료
			if(N == 0) break;
			
			int[][] cave = new int[N][N];
			boolean[][] v = new boolean[N][N];
			ans = 987654321;
			
			// 2차원 배열에 동굴의 정보 입력
			StringTokenizer st = null;
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 0; c < N; c++) {
					cave[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 풀이 1. DFS(시간 초과)
//			v[0][0] = true;
//			DFS(cave, v, 0, 0, 0);
			
			// 풀이 2. Dijkstra Algorithm
			int[][] cost = new int[N][N];
			
			// 최소비용을 기록하는 2차원 배열을 무한대로 초기화
			for(int r = 0; r < N; r++) {
				Arrays.fill(cost[r], Integer.MAX_VALUE);
			}
			cost[0][0] = cave[0][0]; // 시작점 초기화
			
			PriorityQueue<Point> pQueue = new PriorityQueue<Point>();
			pQueue.offer(new Point(0, 0, cave[0][0]));
			v[0][0] = true;
			
			while(!pQueue.isEmpty()) {
				Point cur = pQueue.poll();
				
				for(int d = 0; d < dr.length; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if(checkRange(nr, nc, N) && !v[nr][nc]) {
						if(cost[nr][nc] > cost[cur.r][cur.c] + cave[nr][nc]) {
							cost[nr][nc] = cost[cur.r][cur.c] + cave[nr][nc];
							pQueue.offer(new Point(nr, nc, cost[nr][nc]));
							v[nr][nc] = true;
						}
					}
				}
			}
			
			bw.write(String.format("Problem %d: %d\n", test_case++, cost[N-1][N-1]));
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
	
	// DFS는 시간 초과
	public static void DFS(int[][] map, boolean[][] v, int r, int c, int sum) {
		sum += map[r][c];
		
		if(sum >= ans) return;
		
		if(r == map.length-1 && c == map.length-1) {
			ans = Math.min(ans, sum);
			return;
		}
		
		for(int d = 0; d < dr.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(checkRange(nr, nc, map.length) && !v[nr][nc]) {
				v[r][c] = true;
				DFS(map, v, nr, nc, sum);
				v[r][c] = false;
			}
		}
	}
	
	public static boolean checkRange(int nr, int nc, int N) {
		if(nr >= 0 && nr < N && nc >= 0 && nc < N) return true;
		else return false;
	}
	
	public static void printMap(int[][] map) {
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map.length; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
