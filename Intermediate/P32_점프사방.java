package im_problem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class P32_점프사방 {

	static class Player {
		int y;
		int x;
		int trial;
		
		public Player(int y, int x, int trial) {
			super();
			this.y = y;
			this.x = x;
			this.trial = trial;
		}

		@Override
		public String toString() {
			return "Player [y=" + y + ", x=" + x + ", trial=" + trial + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/Solution32.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int Y = Integer.parseInt(st.nextToken()); // 배열의 Y 크기
			int X = Integer.parseInt(st.nextToken()); // 배열의 X 크기
			int N = Integer.parseInt(st.nextToken()); // 참가자 수
			int[][] board = new int[Y+1][X+1];
			int prizeSum = 0;
			
			// 2차원 배열에 게임 보드 정보 입력
			for(int r = 1; r < board.length; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 1; c < board[r].length; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 참가자 정보를 Player 배열로 저장
			Player[] players = new Player[N];
			for(int i = 0; i < players.length; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int trial = Integer.parseInt(st.nextToken());
				
				players[i] = new Player(y, x, trial);
			}
			
			// 2차원 배열 board에 트랩 정보 입력
			st = new StringTokenizer(br.readLine(), " ");
			int trap = Integer.parseInt(st.nextToken()); // 트랩의 수
			for(int i = 0; i < trap; i++) {
				int ty = Integer.parseInt(st.nextToken());
				int tx = Integer.parseInt(st.nextToken());
				board[ty][tx] = 0;
			}
			
			prizeSum = playGame(board, players);
			
			// 결과 출력
			bw.write(String.format("#%d %d\n", test_case, prizeSum));
			bw.flush();
		}
		// 자원 반납
		br.close();
		bw.close();
	}

	//                 X  동      남      서      북
	static int[] dr = {0, 0,  1,  0, -1};
	static int[] dc = {0, 1,  0, -1,  0};
	
	public static int playGame(int[][] board, Player[] players) {
		int prizeMoney = 0;
		
		// 플레이어의 수만큼 진행
		for(int i = 0; i < players.length; i++) {
			prizeMoney -= 1000;
			int r = players[i].y; // 플레이어의 시작 행 좌표
			int c = players[i].x; // 플레이어의 시작 열 좌표
			int start = board[r][c]; // 시작점에 적힌 숫자
			int dir = start / 10; // 방향(숫자의 앞자리)
			int space = start % 10; // 점프 칸 수(숫자의 뒷자리)
			int nr = 0;
			int nc = 0;
			
			// 플레이어에게 주어진 횟수만큼 게임을 진행
			for(int j = 0; j < players[i].trial; j++) {
				// 기존 위치에서 방향과 점프 칸을 고려한 새 위치
				nr = r + dr[dir] * space;
				nc = c + dc[dir] * space;
				
				// 범위와 함정 체크
				if(nr > 0 && nr < board.length && nc > 0 && nc < board[0].length && board[nr][nc] != 0) {
					// 다음 이동을 위해 필요한 값들을 갱신
					r = nr;
					c = nc;
					dir = board[nr][nc] / 10;
					space = board[nr][nc] % 10;
				}
				// 범위를 벗어나거나 함정에 걸리면 다음 플레이어 진행
				else break;
			}
			// 마지막 좌표에 적힌 숫자에 100을 곱한 금액을 상금으로 받을 수 있음
			prizeMoney += board[nr][nc] * 100;
		}
		
		return prizeMoney;
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
