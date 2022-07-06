/*
 * BAEKJOON 01012. 유기농 배추
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_01012_유기농배추_DFS {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_01012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
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
				
				for(int r = 0; r < N; r++) {
					for(int c = 0; c < M; c++) {
						if(farm[r][c] == 1 && !visited[r][c]) {
							cnt++;
							DFS(farm, r, c, visited, cnt);
						}
					}
				}
				//printMap(farm);
				System.out.printf("  #%d %d\n", small_tc, cnt);
			}
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	//                우       하      좌       상
	static int[] dr = {0,  1,  0, -1};
	static int[] dc = {1,  0, -1,  0};
	
	public static void DFS(int[][] farm, int r, int c, boolean[][] v, int cnt) {
		for(int d = 0; d < dr.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// farm의 범위를 나가지 않으면서 방문하지 않은 위치를 재귀로 탐색
			if(nr >= 0 && nr < farm.length && nc >= 0 && nc < farm[0].length && !v[nr][nc] && farm[nr][nc] == 1) {
				v[nr][nc] = true;
				DFS(farm, nr, nc, v, cnt);
				//v[nr][nc] = false;
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
