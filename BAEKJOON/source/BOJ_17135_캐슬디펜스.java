/*
 * BACKJOON 17135. 캐슬 디펜스
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17135_캐슬디펜스 {
	
	static int Ans;

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_17135.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 행의 수
			int M = Integer.parseInt(st.nextToken()); // 열의 수
			int D = Integer.parseInt(st.nextToken()); // 공격 거리 제한
			int[][] board = new int[N+1][M];
			Ans = 0;
			
			// 2차원 배열 입력
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				for(int c = 0; c < M; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			archerCombination(board, new int[3], 0, 0, D);

			// 결과 출력
			bw.write(String.format("#%d %d\n", test_case, Ans));
			bw.flush();
		}
		br.close();
		bw.close();
	}
	
	static class Target implements Comparable<Target> {
		int r;
		int c;
		
		public Target(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Target o) {
			return Integer.compare(this.c, o.c);
		}
	}
	
	public static void archerCombination(int[][] board, int[] sel, int start, int cnt, int D) {
		// Base Part
		if(cnt == sel.length) {
			int[][] cboard = copyBoard(board);
			int N = cboard.length;
			
			// 궁수 배치
			for(int i = 0; i < sel.length; i++) {
				cboard[N - 1][sel[i]] = 2;
			}

			int killCnt = 0;
			
			while(!allClear(cboard)) {
				// 궁수 3명이 노릴 수 있는 각각의 최적 타켓을 리스트로 관리
				Target[] tlist = new Target[sel.length];
				for(int i = 0; i < sel.length; i++) {
					tlist[i] = findTarget(cboard, N - 1, sel[i], D); // board, r, c
				}
				
				for(int i = 0; i < tlist.length; i++) {
					// 사정거리 내에 적이 없거나, 다른 궁수에 의해 타겟이 제거되었을 경우는 skip
					if(tlist[i] == null || cboard[tlist[i].r][tlist[i].c] == 0) continue;
					
					cboard[tlist[i].r][tlist[i].c] = 0; // 타겟 제거
					killCnt++;
				}
				
				// 한 턴이 끝날 때마다 적이 한 칸씩 성 방향으로 전진
				int[] zeroRow = new int[board[0].length];
				Arrays.fill(zeroRow, 0);
				
				for(int i = 0; N-3-i >= 0; i++) {
					cboard[N-2-i] = cboard[N-3-i];
				}
				cboard[0] = zeroRow; // 첫 번째 행은 0인 1차원 배열에 매핑
			}
			Ans = Math.max(Ans, killCnt);
			return;
		}
		
		// Inductive Part
		for(int i = start; i < board[0].length; i++) {
			sel[cnt] = i;
			archerCombination(board, sel, i+1, cnt+1, D);
		}
	}
	
	// 2차원 배열 복사를 위한 함수
	public static int[][] copyBoard(int[][] board){
		int N = board.length;
		int M = board[0].length;
		int[][] temp = new int[N][M];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				temp[r][c] = board[r][c];
			}
		}
		return temp;
	}
	
	//                  좌     상     우
	static int[] dr = { 0, -1,  0};
	static int[] dc = {-1,  0,  1};
	
	// 한 궁수가 사격할 수 있는 적들 중 가장 가까우면서 왼쪽에 있는 적을 찾는 함수(BFS)
	public static Target findTarget(int[][] board, int sr, int sc, int D) {
		int N = board.length;
		int M = board[0].length;
		
		Queue<Target> q = new LinkedList<Target>();
		boolean[][] v = new boolean[N][M];
		// int[][] cmap = new int[N][M];
		int cnt = 1;
		
		LinkedList<Target> targetList = new LinkedList<Target>();
		
		q.offer(new Target(sr, sc));
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i = size; i > 0; i--) {
				Target cur = q.poll();
				v[cur.r][cur.c] = true;
				
				for(int d = 0; d < dr.length; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if(checkRange(nr, nc, N-1, M) && !v[nr][nc] && cnt <= D) {
						q.offer(new Target(nr, nc));
						// cmap[nr][nc] = cnt;
						if(board[nr][nc] == 1) {
							targetList.add(new Target(nr, nc));
						}
					}
				}
			}
			
			if(!targetList.isEmpty()) {
				Collections.sort(targetList);
				return targetList.get(0);
			}
			cnt++;
		}
		
		return null;
	}
	
	public static boolean allClear(int[][] board) {
		int enemeyCnt = 0;
		
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[r].length; c++) {
				if(board[r][c] == 1) enemeyCnt++;
			}
		}
		
		return enemeyCnt > 0 ? false : true;
	}
	
	public static boolean checkRange(int nr, int nc, int N, int M) {
		if(nr >= 0 && nr < N && nc >= 0 && nc < M) return true;
		else return false;
	}
	
	public static void printMap(int[][] map) {
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[r].length; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
