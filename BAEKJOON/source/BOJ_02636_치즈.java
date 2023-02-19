/*
 * BAEKJOON 02636. 치즈
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_02636_치즈 {
	
	static class Position {
		int r;
		int c;
		
		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] dr = {-1, 1,  0,  0};
	static int[] dc = {0,  0, -1,  1};

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_02636.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 치즈의 세로 길이
			int M = Integer.parseInt(st.nextToken()); // 치즈의 가로 길이
			int[][] cheese = new int[N][M];
			int time = 0;
			int cnt = 0;
			
			// 2차원 배열에 치즈의 상태 입력
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 0; c < M; c++) {
					cheese[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 치즈가 없어질 때까지 반복
			while(true) {
				int tmp = 0;
				// 공기와 맞닿은 치즈 부분의 값을 0으로 변경
				// DFS(cheese, new Position(0, 0), new boolean[N][M]);
				BFS(cheese, new Position(0, 0));
				// 1보다 큰 부분은 공기와 맞닿은 부분이므로 0으로 변경
				for(int r = 0; r < N; r++) {
					for(int c = 0; c < M; c++) {
						if(cheese[r][c] > 1) {
							cheese[r][c] = 0;
							tmp++;
						}
					}
				}
				if(tmp != 0) cnt = tmp;
				else break; // 지도 상에 치즈가 없으므로 반복문 탈출
				
				time++;
			}
			
			// 결과 출력
			bw.write(String.format("#%d %d %d\n", test_case, time, cnt));
			bw.flush();
		}
		
		// 자원 반환
		br.close();
		bw.close();
	}
	
	public static void DFS(int[][] cheese, Position p, boolean[][] v) {
		for(int d = 0; d < dr.length; d++) {
			int nr = p.r + dr[d];
			int nc = p.c + dc[d];
			
			if(nr >= 0 && nr < cheese.length && nc >= 0 && nc < cheese[0].length && !v[nr][nc]) {
				// 공기인 경우
				if(cheese[nr][nc] == 0) {
					v[nr][nc] = true;
					DFS(cheese, new Position(nr, nc), v);
				}
				// 치즈인 경우
				else {
					cheese[nr][nc]++;
				}
			}
		}
	}
	
	public static void BFS(int[][] cheese, Position start) {
		Queue<Position> queue = new LinkedList<Position>();
		boolean[][] v = new boolean[cheese.length][cheese[0].length];
		queue.offer(start);
		v[start.r][start.c]= true;
		
		while(!queue.isEmpty()) {
			Position p = queue.poll();
			for(int d = 0; d < dr.length; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				if(nr >= 0 && nr < cheese.length && nc >= 0 && nc < cheese[0].length && !v[nr][nc]) {
					// 공기인 경우
					if(cheese[nr][nc] == 0) {
						v[nr][nc] = true;
						queue.offer(new Position(nr, nc));
					}
					// 치즈인 경우
					else {
						cheese[nr][nc]++;
					}
				}
			}
		}
	}
}
