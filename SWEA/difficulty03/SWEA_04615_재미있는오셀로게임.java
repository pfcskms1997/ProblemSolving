/*
 * SWEA 04615. 재미있는 오셀로 게임
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_04615_재미있는오셀로게임 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("./input/difficulty03/input_04615.txt")));
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 보드 한 변의 길이
			int M = Integer.parseInt(st.nextToken()); // 플레이어가 돌을 놓는 횟수
			int[][] board = new int[N][N];
			int black = 0;
			int white = 0;
			
			// 보드 초기화
			// 흑돌: 1, 백돌: 2
			board[N/2-1][N/2-1] = 2;
			board[N/2-1][N/2] = 1;
			board[N/2][N/2-1] = 1;
			board[N/2][N/2] = 2;			
			
			for(int i = 0; i < M; i++) {
				StringTokenizer st1 = new StringTokenizer(in.readLine(), " ");
				int x = Integer.parseInt(st1.nextToken()) - 1;
				int y = Integer.parseInt(st1.nextToken()) - 1;
				int color = Integer.parseInt(st1.nextToken());
				
				othello(board, y, x, color);
			}
			
			// 결과 출력 Part
			for(int i = 0; i < board.length; i++) {
				for(int j = 0; j < board[i].length; j++) {
					if(board[i][j] == 1) black++;
					else if(board[i][j] == 2) white++;
					else continue;
				}
			}
			System.out.printf("#%d %d %d\n", test_case, black, white);
		}
		in.close();
	}
	
	// 8방탐색을 위한 delta 배열
	public static int[] dr = {-1, -1, -1,  0, 0,  1, 1, 1};
	public static int[] dc = {-1,  0,  1, -1, 1, -1, 0, 1};
	
	public static void othello(int[][] board, int y, int x, int color) {
		//System.out.printf("y: %d, x: %d, color: %d\n", y, x, color);
		
		// 빈 자리이면 돌을 놓는다.
		if(board[y][x] == 0) {
			board[y][x] = color;
		}
		
		// 돌 주변으로 8방 탐색하여 포위한 돌을 뒤집음
		for(int i = 0; i < dr.length; i++) {
			// 탐색 할 좌표
			int nr = y + dr[i];
			int nc = x + dc[i];
			
			// 탐색 후 원점을 찾기 위해 임시로 선언한 변수
			int tmp_y = y;
			int tmp_x = x;
			
			// 포위를 위한 같은 색깔 돌의 좌표
			int stone_y = -1;
			int stone_x = -1;
			
			// 유효범위인지 체크
			if(nr >= 0 && nr < board.length && nc >= 0 && nc < board.length) {
				// 해당 포인트가 빈 공간이거나 같은 색의 돌이면 다음 방향 탐색 진행
				if(board[nr][nc] == 0 || board[nr][nc] == color) continue;

				// 해당 포인트가 빈 공간이 아니면 탐색하던 방향으로 계속 진행하며 다른 색의 돌을 포위할 수 있는지 탐색
				while(true) {
					nr += dr[i];
					nc += dc[i];
					
					//System.out.printf("nr: %d, nc: %d\n", nr, nc);
					// 보드 범위를 벗어나면 루프 종료
					if(nr < 0 || nr >= board.length || nc < 0 || nc >= board.length) break;
					
					// 탐색 도중에 빈 공간이 있으면 루프 종료
					if(board[nr][nc] == 0) break;
					
					// 빈 공간 없이 같은 색의 돌을 발견하여 포위가 가능하면 돌의 좌표를 기록하고 루프 종료
					if(board[nr][nc] == color) {
						stone_y = nr;
						stone_x = nc;
						break;
					}
				}
				
				// 포위를 위한 같은 색깔 돌을 찾지 못하면 다른 방향 탐색 진행
				if(stone_y < 0 || stone_x < 0) continue;
				
				// 포위한 돌을 뒤집기
				while(true) {
					tmp_y += dr[i];
					tmp_x += dc[i];
					
					// 같은 색깔 돌에 도달할 경우 뒤집기 종료
					if(tmp_y == stone_y && tmp_x == stone_x) break;
					
					board[tmp_y][tmp_x] = color;
				}
			}
			// 유효범위 내의 작업 종료
		}
		// 8방 탐색 종료
	}
	
	// 보드판 확인용 출력함수
	public static void printBoard(int[][] board) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == 1) System.out.print("B ");
				else if(board[i][j] == 2) System.out.print("W ");
				else System.out.print("□ ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
