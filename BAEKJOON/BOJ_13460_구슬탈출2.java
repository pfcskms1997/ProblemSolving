/*
 * BAEKJOON 13460. 구슬 탈출 2
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13460_구슬탈출2_X {

	static class Bead {
		int r;
		int c;
		char color;
		
		public Bead(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public Bead(int r, int c, char color) {
			this.r = r;
			this.c = c;
			this.color = color;
		}
		
		@Override
		public String toString() {
			return String.format("%c: (%d, %d)", this.color, this.r, this.c);
		}
	}
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_13460.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			char[][] board = new char[N][];
			Bead red = null;
			Bead blue = null;
			int exit_r = 0;
			int exit_c = 0;
			
			for(int r = 0; r < board.length; r++) {
				board[r] = br.readLine().toCharArray();
				for(int c = 0; c < board[r].length; c++) {
					if(board[r][c] == 'R') red = new Bead(r, c, 'R');
					if(board[r][c] == 'B') blue = new Bead(r, c, 'B');
					if(board[r][c] == 'O') {
						exit_r = r;
						exit_c = c;
					}
				}
			}
			
//			System.out.println("#" + test_case);
//			System.out.println(red);
//			System.out.println(blue);
//			System.out.println(exit_r + " " + exit_c);
			printBoard(board);
			
			beadsEscape(board, red, blue, exit_r, exit_c);
			
			//sb.append(String.format("#%d \n", test_case));
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
	
	public static void beadsEscape(char[][] board, Bead red, Bead blue, int exit_r, int exit_c) {
		int N = board.length;
		int M = board[0].length;
		Queue<Bead> q = new LinkedList<Bead>();
		boolean[][] v = new boolean[N][M];
		int[][] cmap = new int[N][M];
		int cnt = 1;
		boolean flag = false;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(board[r][c] == '#') cmap[r][c] = 0;
				if(board[r][c] == 'R') cmap[r][c] = 77;
				if(board[r][c] == 'B') cmap[r][c] = 99;
			}
		}
		
		q.offer(red);
		
		while(!q.isEmpty() && !flag) {
			int size = q.size();
			
			for(int i = size; i > 0; i--) {
				Bead cur = q.poll();
				v[cur.r][cur.c] = true;
				
				for(int d = 0; d < dr.length; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if(checkRange(nr, nc, N-1, M-1) && !v[nr][nc] && board[nr][nc] != '#') {
						q.offer(new Bead(nr, nc));
						cmap[nr][nc] = cnt;
						
						if(board[nr][nc] == 'O') {
							flag = true;
							cmap[nr][nc] = cnt;
						}
					}
				}
			}
			cnt++;
		}
		printBoard(cmap);
	}
	
	public static boolean checkRange(int nr, int nc, int N, int M) {
		if(nr >= 1 && nr < N && nc >= 1 && nc < M) return true;
		else return false;
	}
	
	public static void printBoard(char[][] board) {
		for(int r = 0; r < board.length; r++) {
			System.out.println(Arrays.toString(board[r]).replaceAll("[\\[\\,\\]]", ""));
		}
	}
	
	public static void printBoard(int[][] board) {
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[r].length; c++) {
				System.out.printf("%3d ", board[r][c]);
			}
			System.out.println();
		}
	}
}
