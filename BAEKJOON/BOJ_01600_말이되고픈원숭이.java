/*
 * BAEKJOON 01600. 말이 되고픈 원숭이
 * Hint: BFS
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_01600_말이되고픈원숭이 {
	
	static class Position {
		int r;
		int c;
		int cnt;
		int k;
		
		public Position(int r, int c, int cnt, int k) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.k = k;
		}
	}
	
	// 원숭이가 이동할 수 있는 4방 + 말처럼 이동할 수 있는 8방
	static int[] dr = {-1,  1,  0,  0, -2, -1,  1,  2,  2,  1, -1, -2};
	static int[] dc = { 0,  0, -1,  1,  1,  2,  2,  1, -1, -2, -2, -1};
	
	// 말처럼 이동할 수 있는 8가지 방향
//	static int[] horse_r = {-1, -2, -2, -1,  1,  2,  2,  1};
//	static int[] horse_c = {-2, -1,  1,  2, -2, -1,  1,  2};
	
	static int K;
	static int W;
	static int H;
	static int Ans;

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_01600.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			K = Integer.parseInt(br.readLine()); // 말 처럼 뛸 수 있는 제한 횟수
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			W = Integer.parseInt(st.nextToken()); // 가로 길이
			H = Integer.parseInt(st.nextToken()); // 세로 길이
			int[][] map = new int[H+1][W+1];
			Ans = 987654321;
			
			// 2차원 배열에 지도 입력
			for(int r = 1; r <= H; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 1; c <= W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 최단경로이므로 BFS 이용
			BFS(map, new Position(1, 1, 0, K));
			
			// 결과 출력
			bw.write(String.format("#%d %d\n", test_case, (Ans == 987654321 ? -1 : Ans)));
			bw.flush();
		}
		
		// 자원 반환
		br.close();
		bw.close();
	}
	
	public static void BFS(int[][] map, Position start) {
		Queue<Position> queue = new LinkedList<Position>();
		boolean[][][] v = new boolean[H+1][W+1][K+1];
		queue.offer(start);
		v[start.r][start.c][0] = true;
		
		while(!queue.isEmpty()) {
			Position cur = queue.poll();
			
			if(cur.r == H && cur.c == W) {
				Ans = Math.min(Ans, cur.cnt);
				break;
			}
			
			for(int d = 0; d < (cur.k > 0 ? 12 : 4); d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				// 원숭이 이동 방식 -> k는 그대로, 말 이동 방식 -> k를 1 감소
				int nk = d < 4 ? cur.k : cur.k-1;
				
				if(checkRange(nr, nc) && !v[nr][nc][nk]) {
					if(map[nr][nc] == 0) {
						v[nr][nc][nk] = true;
						queue.offer(new Position(nr, nc, cur.cnt+1, nk));
					}
				}
			}
		}
	}
	
	public static boolean checkRange(int nr, int nc) {
		if(nr >= 1 && nr <= H && nc >= 1 && nc <= W) return true;
		else return false;
	}
}
