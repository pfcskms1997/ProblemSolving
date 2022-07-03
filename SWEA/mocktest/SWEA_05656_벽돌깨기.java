/*
 * SWEA 05656. 벽돌 깨기
 */

package mocktest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_05656_벽돌깨기 {
	
	static int[][] board;
	static int T, N, W, H, ans;
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/mocktest/input_05656.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 슈팅 횟수
			W = Integer.parseInt(st.nextToken()); // 보드의 가로 길이
			H = Integer.parseInt(st.nextToken()); // 보드의 세로 길이
			board = new int[H][W];
			ans = Integer.MAX_VALUE;
			
			// 2차원 배열에 벽돌 정보 입력
			for(int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 0; c < W; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			recursive(board, 0);
			
			sb.append(String.format("#%d %d\n", test_case, ans));
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void recursive(int[][] board, int cnt) {
		if(cnt == N) {
			int res = 0;
			for(int r = 0; r < board.length; r++) {
				for(int c = 0; c < board[r].length; c++) {
					if(board[r][c] != 0) res++;
				}
			}
			ans = Math.min(ans, res);
			return ;
		}
		
		// 구슬을 던져 터뜨린다.(inductive part 경우의 수)
		for(int c = 0; c < W; c++) {
			// 여러 번 시뮬레이션을 위해 지도 복사(지도 복원이 가능하다면 backtracking을 사용해도 됨)
			int[][] cboard = copyBoard(board);
			
			for(int r = 0; r < board.length; r++) {
				if(cboard[r][c] != 0) {
					shot(cboard, r, c);
					break;
				}
			}
			pullDown(cboard);
			recursive(cboard, cnt+1);
		}
	}
	
	public static void shot(int[][] board, int r, int c) {
		// 지도 범위 안에서
		if(r >= 0 && r < H && c >= 0 && c < W) {
			int len = board[r][c];
			board[r][c] = 0;
			
			// 값 만큼 반복
			for(int i = 1; i < len; i++) {
				shot(board, r-i, c);
				shot(board, r, c+i);
				shot(board, r+i, c);
				shot(board, r, c-i);
			}
		}
	}
	
	private static void pullDown(int[][] board) {
		for(int r = board.length-1; r >= 0; r--) {
			for(int c = board[r].length-1; c >=0 ; c--) {
				if(board[r][c] == 0) {
					// 공란을 만나게 되면 위로 0 이 아닌값까지 올라가서 복사한다
					for(int k = r; k >= 0; k--) {
						if(board[k][c] != 0) {
							board[r][c] = board[k][c];
							board[k][c] = 0;
							break;
						}
					}
				}
			}
		}
	}

	public static int[][] copyBoard(int[][] board){
		int[][] cmap = new int[H][W];
		for(int r = 0; r < H; r++) {
			for(int c = 0; c < W; c++) {
				cmap[r][c] = board[r][c];
			}
		}
		return cmap;
	}
}