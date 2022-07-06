/*
 * BAEKJOON 16927. 배열 돌리기 1
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16926_배열돌리기1 {

	// 우 -> 하 -> 좌 -> 상
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_16926.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int[][] matrix = new int[N][M];
			
			// 배열 입력
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 0; c < M; c++) {
					matrix[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 배열을 R번 회전
			int rept = Math.min(N, M) / 2;
			for(int rot = 0; rot < R; rot++) {
				rotate(matrix, rept);
			}
			
			printMatrix(matrix);
		}
		br.close();
	}
	
	public static void rotate(int[][] matrix, int rept) {
		// 지도의 크기에 따라 min(n, m) / 2 횟수만큼 반복
		for(int i = 0; i < rept; i++) {
			// 이동 방향 변수
			int dir = 0;
			// 시작점
			int sr = i;
			int sc = i;
			// 기준점에 있는 값을 임시 저장
			int temp = matrix[sr][sc];
			
			// 방향을 4번 바꾸어 줌
			while(dir < 4) {
				int nr = sr + dr[dir];
				int nc = sc + dc[dir];
				
				// 경계선 체크
				if(nr >= i && nr < matrix.length - i && nc >= i && nc < matrix[0].length - i) {
					matrix[sr][sc] = matrix[nr][nc];
					sr = nr;
					sc = nc;
				}
				
				// 만약 경계선 밖으로 벗어나면 방향을 전환
				else dir++;
			}
			matrix[i+1][i] = temp;
		}
	}
	
	public static void printMatrix(int[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
