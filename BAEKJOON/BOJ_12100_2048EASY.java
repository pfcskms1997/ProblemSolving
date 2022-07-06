/*
 * BAEKJOON 12100. 2048(EASY)
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_12100_2048EASY {

	static int N, ans;
	static int[][] board, cboard;
	
	static final int LEFT = 0;
	static final int RIGHT = 1;
	static final int UP = 2;
	static final int DOWN = 3; 
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_12100.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine()); // 보드의 크기
		board = new int[N][N];
		cboard = new int[N][N];
		ans = 0;
		
		StringTokenizer st = null;
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		permutationRept(board, new int[5], 0);
		
		// 결과 출력
		bw.write(String.format("%d", ans));
		bw.flush();
		br.close();
		bw.close();
	}
	
	// 중복 순열로 5개의 이동 방향 경우의 수를 연산하는 함수
	public static void permutationRept(int[][] board, int[] sel, int cnt) {
		if(sel.length == cnt) {
			copyboard(board, cboard);
			
			for(int i = 0; i < sel.length; i++) {
				moveBlock(cboard, sel[i]);
			}
			
			ans = Math.max(ans, findMaxBlock(cboard));
			
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			sel[cnt] = i;
			permutationRept(board, sel, cnt+1);
		}
	}
	
	// 2차원 배열 board의 복사본을 만드는 함수
	private static void copyboard(int[][] board, int[][] cboard) {
		for(int r = 0; r < cboard.length; r++) {
			for(int c = 0; c < cboard.length; c++) {
				cboard[r][c] = board[r][c];
			}
		}
	}
	
	// 주어진 이동방향에 따라 블럭을 이동시키는 함수
	public static void moveBlock(int[][] board, int dir) {
		Stack<Integer> stack = new Stack<Integer>();
		int a = 0;
		int b = 0;
		int idx = 0;
		
		switch(dir) {
		case LEFT:
			for (int r = 0; r < N; r++) {
				// 오른쪽->왼쪽으로 탐색
				for (int c = N-1; c >= 0; c--) {
					// 0이 아닌 블록을 stack에 넣음
					if(board[r][c] != 0) stack.push(board[r][c]);
					board[r][c] = 0;
				}
				
				idx = 0;
				while(stack.size() > 1) {
					a = stack.pop();
					b = stack.pop();
					
					if(a == b) {
						board[r][idx] = a + b;
					} else {
						board[r][idx] = a;
						stack.push(b);
					}
					idx++;
				}
				if(stack.size() > 0) board[r][idx] = stack.pop();
			}
			break;

		case RIGHT:
			for (int r = 0; r < N; r++) {
				// 왼쪽->오른쪽으로 탐색
				for (int c = 0; c < N; c++) {
					// 0이 아닌 블록을 stack에 넣음
					if(board[r][c] != 0) stack.push(board[r][c]);
					board[r][c] = 0;
				}
				
				idx = N-1;
				while(stack.size() > 1) {
					a = stack.pop();
					b = stack.pop();
					
					if(a == b) {
						board[r][idx] = a + b;
					} else {
						board[r][idx] = a;
						stack.push(b);
					}
					idx--;
				}
				if(stack.size() > 0) board[r][idx] = stack.pop();
			}
			break;
			
		case UP:
			for (int c = 0; c < N; c++) {
				// 아래->위로 탐색
				for (int r = N-1; r >= 0; r--) {
					// 0이 아닌 블록을 stack에 넣음
					if(board[r][c] > 1) stack.push(board[r][c]);
					board[r][c] = 0;
				}
				
				idx = 0;
				while(stack.size() > 1) {
					a = stack.pop();
					b = stack.pop();
					
					if(a == b) {
						board[idx][c] = a + b;
					} else {
						board[idx][c] = a;
						stack.push(b);
					}
					idx++;
				}
				if(stack.size() > 0) board[idx][c] = stack.pop();
			}
			break;
			
		case DOWN:
			for (int c = 0; c < N; c++) {
				// 위->아래로 탐색
				for (int r = 0; r < N; r++) {
					// 0이 아닌 블록을 stack에 넣음
					if(board[r][c] != 0) stack.push(board[r][c]);
					board[r][c] = 0;
				}
				
				idx = N-1;
				while(stack.size() > 1) {
					a = stack.pop();
					b = stack.pop();
					
					if(a == b) {
						board[idx][c] = a + b;
					} else {
						board[idx][c] = a;
						stack.push(b);
					}
					idx--;
				}
				if(stack.size() > 0) board[idx][c] = stack.pop();
			}
			break;
		}
	}

	public static int findMaxBlock(int[][] board) {
		int maxVal = 0;
		
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board.length; c++) {
				maxVal = Math.max(maxVal, board[r][c]);
			}
		}
		
		return maxVal;
	}
}
