/*
 * BAEKJOON 01012. 유기농 배추
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_01012_유기농배추_BFS {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_01012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int smallT = Integer.parseInt(br.readLine());
			
			System.out.println("#" + test_case);
			for(int small_tc = 1; small_tc <= smallT; small_tc++) {
				StringTokenizer st= new StringTokenizer(br.readLine(), " ");
				int M = Integer.parseInt(st.nextToken()); // 배추밭의 가로 길이
				int N = Integer.parseInt(st.nextToken()); // 배추밭의 세로 길이
				int K = Integer.parseInt(st.nextToken()); // 배추의 개수
				int[][] farm = new int[N][M];
				boolean[][] visited = new boolean[N][M];
				int cnt = 0;
				
				
				for(int i = 0; i < K; i++) {
					st = new StringTokenizer(br.readLine(), " ");
					int c = Integer.parseInt(st.nextToken()); // 배추의 가로 좌표
					int r = Integer.parseInt(st.nextToken()); // 배추의 세로 좌표
					
					farm[r][c] = 1;
				}

				printMap(farm);
				for(int r = 0; r < N; r++) {
					for(int c = 0; c < M; c++) {
						if(farm[r][c] == 1 && !visited[r][c]) {
							cnt++;
							BFS(farm, r, c, visited, cnt);
						}
					}
				}
				//printMap(farm);
				System.out.printf("  #%d %d\n", small_tc, cnt);
			}
		}
		br.close();
	}

	//                우       하      좌       상
	static int[] dr = {0,  1,  0, -1};
	static int[] dc = {1,  0, -1,  0};
	
	static class Point {
		int r, c;

		Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c +"]";
		}
	}
	
	public static void BFS(int[][] farm, int r, int c, boolean[][] v, int cnt) {
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(r, c));
		
		v[r][c] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			for(int d = 0; d < dr.length; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				if (nr >= 0 && nr < farm.length && nc >= 0 && nc < farm[0].length && !v[nr][nc] && farm[nr][nc] == 1) {
					v[nr][nc] = true;
					q.add(new Point(nr, nc));
				}
			}
		}
	}

	public static void printMap(int[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
