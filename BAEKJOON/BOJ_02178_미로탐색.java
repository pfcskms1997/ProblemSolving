/*
 * BAEKJOON 02178. 미로 탐색
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_02178_미로탐색 {

	static class Position {
		int r;
		int c;
		
		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		@Override
		public String toString() {
			return "Position [r=" + r + ", c=" + c + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_02178.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 미로의 행(세로) 길이
			int M = Integer.parseInt(st.nextToken()); // 미로의 열(가로) 길이
			int[][] maze = new int[N+1][M+1];
			
			// 2c차원 배열에 미로 입력
			for(int r = 1; r < maze.length; r++) {
				String temp = br.readLine();
				for(int c = 1; c < maze[r].length; c++) {
					maze[r][c] = temp.charAt(c - 1) - '0';
				}
			}
			
			int shortest = BFS(maze, 1, 1);
			
			// 결과 출력
			System.out.printf("#%d %d\n", test_case, shortest);
		}
		
		br.close();
	}
	
	public static int BFS(int[][] maze, int r, int c) {
		int N = maze.length;
		int M = maze[0].length;
		Queue<Position> q = new LinkedList<Position>();
		boolean[][] visited = new boolean[N][M];
		int cnt = 0;
		
		// 시작 노드
		q.offer(new Position(r, c));
		visited[r][c] = true;
		
		//          우    하      좌     상   
		int[] dr = {0,  1,  0, -1};
		int[] dc = {1,  0, -1,  0};
		
		while(!q.isEmpty()) {
			int qSize = q.size();
			cnt++;

			for(int i = 0; i < qSize; i++) {
				Position p = q.poll();
				
				// 현재 지점에 bfs depth를 입력
				maze[p.r][p.c] = cnt;
				
				// 목표지점 도착 시에 루프 종료
				if(p.r == N && p.c == M) break;
				
				// 4방 탐색
				for(int d = 0; d < dr.length; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];
					
					// 유효 범위 및 방문 여부 확인
					if(nr > 0 && nr < N && nc > 0 && nc < M && maze[nr][nc] != 0 && !visited[nr][nc]) {
						visited[nr][nc] = true;
						q.offer(new Position(nr, nc));
					}
				}
			}
		}
		return maze[N-1][M-1]; // 도착지점의 depth를 반환
	}
	
	public static void printMatrix(int[][] matrix) {
		for(int r = 0; r < matrix.length; r++) {
			for(int c = 0; c < matrix[r].length; c++) {
				System.out.print(matrix[r][c] + " ");
			}
			System.out.println();
		}
	}
}
