/*
 * BAEKJOON 02468. 안전 영역
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_02468_안전영역 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_02468.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st= new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 배열의 크기
			int[][] map = new int[N][N];
			int maxHeight = 0; // 최고지대의 높이
			int minHeight = 100;
			int maxCnt = 0;
			
			// 2차원 배열 map에 높이 정보 입력
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					maxHeight = Math.max(maxHeight, map[r][c]);
					minHeight = Math.min(minHeight, map[r][c]);
				}
			}
			
			// 문제 풀이: 침수되는 지대의 높이에 따른 안전 영역 구하기
			for(int h = minHeight; h < maxHeight; h++) {
				boolean[][] visited = new boolean[N][N];
				int cnt = 0;
				
				for(int r = 0; r < N; r++) {
					for(int c = 0; c < N; c++) {
						if(map[r][c] > h && !visited[r][c]) {
							cnt++;
							BFS(map, r, c, visited, cnt, h);
						}
					}
				}
				maxCnt = Math.max(maxCnt, cnt);
			}
			
			System.out.printf("#%d %d\n", test_case, maxCnt);
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
	
	public static void BFS(int[][] map, int r, int c, boolean[][] v, int cnt, int min) {
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(r, c));
		
		v[r][c] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			for(int d = 0; d < dr.length; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				if (nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length && !v[nr][nc] && map[nr][nc] > min) {
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
