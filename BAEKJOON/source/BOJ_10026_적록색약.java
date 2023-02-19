/*
 * BAEKJOON 10026. 적록색약
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_10026_적록색약 {
	
	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_10026.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 그림의 크기
		char[][] image = new char[N][];
		boolean[][] visited = new boolean[N][N];
		int possible = 0;
		int impossible = 0;
		
		for(int r = 0; r < N; r++) {
			image[r] = br.readLine().toCharArray();
		}
		
		// 적록색약이 아닌 사람에 대한 BFS 탐색
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(!visited[r][c]) {
					possible++;
					BFS(image, visited, r, c);
				}
			}
		}
		
		// 방문배열 초기화
		visited = new boolean[N][N];
		
		// 적록색약인 사람은 초록(G)와 빨강(R)을 구분하지 못하므로 G를 R로 변경하여 동일하게 취급
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(image[r][c] == 'G') image[r][c] = 'R';
			}
		}
		
		// 적록색약인 사람에 대한 BFS 탐색
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(!visited[r][c]) {
					impossible++;
					BFS(image, visited, r, c);
				}
			}
		}
		
		System.out.println(possible + " " + impossible);
		br.close();
	}
	
	public static void BFS(char[][] image, boolean[][] v, int r, int c) {
		int N = image.length;
		char color = image[r][c];
		
		Queue<Point> q = new LinkedList<Point>();
		
		// 현재 위치(탐색의 시작 위치)
		q.offer(new Point(r, c));
		v[r][c] = true;
		
		//     	       우      하     좌      상
		int[] dr = {0,  1,  0, -1};
		int[] dc = {1,  0, -1,  0};
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			for(int d = 0; d < dr.length; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc] && image[nr][nc] == color) {
					v[nr][nc] = true;
					q.add(new Point(nr, nc));
				}
			}
		}
	}
}
