/*
 * SWEA 01953. 탈주범 검거
 */

package mocktest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_01953_탈주범검거 {
	
	static class Point {
		int r;
		int c;
		int dist; // 맨홀로부터 이동하는 데에 걸리는 시간
		
		public Point(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
	
	static int N, M, R, C, L;
	static int[][] map;
	
	// 0: 상, 1: 하, 2: 좌, 3: 우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	// 터널 구조물 타입
	static int[][] pipe = {	{0},
							{0, 1, 2, 3},
							{0, 1},
							{2, 3},
							{0, 3},
							{1, 3},
							{1, 2},
							{0, 2} };
		
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/mocktest/input_01953.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 지하 터널의 세로 크기
			M = Integer.parseInt(st.nextToken()); // 지하 터널의 가로 크기
			R = Integer.parseInt(st.nextToken()); // 맨홀의 R좌표
			C = Integer.parseInt(st.nextToken()); // 맨홀의 C좌표
			L = Integer.parseInt(st.nextToken()); // 탈출 후 소요 시간
			map = new int[N][M];
			
			// 2차원 배열에 지하 터널의 정보 입력
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < M; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			int ans = BFS(R, C);
			
			bw.write(String.format("#%d %d\n", test_case, ans));
			bw.flush();
		}
		br.close();
		bw.close();
	}
	
	public static int BFS(int sr, int sc) {
		Queue<Point> queue = new LinkedList<Point>();
		boolean[][] v = new boolean[N][M];
		
		int cnt = 1;
		int dist = 1;
		queue.offer(new Point(sr, sc, dist++));
		v[sr][sc] = true;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int i = size; i > 0; i--) {
				Point cur = queue.poll();
				int ptype = map[cur.r][cur.c]; // 현재 위치의 터널 타입
				
				//System.out.printf("r: %d, c: %d, d: %d\n", cur.r, cur.c, cur.dist);
				
				// 현재 위치의 터널 타입에 따라 4방 중 이동이 가능한 방향으로만 탐색
				for(int d = 0; d < pipe[ptype].length; d++) {
					// 이동할 위치가 어느 방향인지 기록
					int mr = dr[pipe[ptype][d]];
					int mc = dc[pipe[ptype][d]];
					
					// 다음으로 이동할 방향
					int nr = cur.r + mr;
					int nc = cur.c + mc;
					
					// map 내에서 시간 내에 이동할 수 있는 연결된 파이프가 존재하는지 확인
					if(checkRange(nr, nc) && map[nr][nc] != 0 && !v[nr][nc] && dist <= L && isConnected(map[nr][nc], mr, mc)) {
						cnt++;
						queue.offer(new Point(nr, nc, dist));
						v[nr][nc] = true;
					}
				}
			}
			if(++dist > L) break;
		}
		return cnt;
	}
	
	// 다음에 이동할 위치가 이동할 수 있는 파이프로 연결되어 있는지 확인
	public static boolean isConnected(int pn, int mr, int mc) { // pn: 파이프 번호
		int connection = -1;
		
		// 상(0) 방향으로 이동시 다음 파이프의 하(1) 방향과 연결되어 있어야 함
		if(mr < 0 && mc == 0) connection = 1;
		
		// 하(1) 방향으로 이동시 다음 파이프의 상(0) 방향과 연결되어 있어야 함
		else if(mr > 0 && mc == 0) connection = 0;
		
		// 좌(2) 방향으로 이동시 다음 파이프의 우(3) 방향과 연결되어 있어야 함
		else if(mr == 0 && mc < 0) connection = 3;
		
		// 우(3) 방향으로 이동시 다음 파이프의 좌(2) 방향과 연결되어 있어야 함
		else if(mr == 0 && mc > 0) connection = 2;
		
		for(int i = 0; i < pipe[pn].length; i++) {
			if(connection == pipe[pn][i]) return true;
		}
		return false;
	}
	
	// 새로 계산된 좌표가 map 내의 유효한 범위인지 확인
	public static boolean checkRange(int nr, int nc) {
		if(nr >= 0 && nr < N && nc >= 0 && nc < M) return true;
		else return false;
	}
}
