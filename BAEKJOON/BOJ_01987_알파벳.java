/*
 * BAEKJOON 01987. 알파벳
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_01987_알파벳 {

	static int maxVal;
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_01987.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			char[][] board = new char[R][];
			boolean[] alphabet = new boolean[26];
			maxVal = 0;
			
			// 2차원 배열에 주어진 알파벳 입력
			for(int r = 0; r < board.length; r++) {
				board[r] = br.readLine().toCharArray();
			}
			
			alphabet[board[0][0] - 'A'] = true;
			DFS(board, 0, 0, alphabet, 1); // 시작점도 cnt에 포함
			
			System.out.printf("#%d %d\n", test_case, maxVal);
		}
		br.close();
	}
	
	//                우       하      좌       상
	static int[] dr = {0,  1,  0, -1};
	static int[] dc = {1,  0, -1,  0};
	
	public static void DFS(char[][] board, int r, int c, boolean[] alphabet, int cnt) {
		
		maxVal = Math.max(maxVal, cnt);
		
		// 26개의 알파벳이 모두 탐색되면 더 진행하는 것이 의미가 없으므로 return
		if(cnt == alphabet.length) return;
		
		// 4방 탐색
		for(int d = 0; d < dr.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 범위 체크
			if(nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length) {
				// 방문한 적이 없는 알파벳이면
				if(!alphabet[board[nr][nc] - 'A']) {
					alphabet[board[nr][nc] - 'A'] = true;
					DFS(board, nr, nc, alphabet, cnt+1);
					alphabet[board[nr][nc] - 'A'] = false;
				}
			}
		}
		
	}

	public static void printMap(char[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
