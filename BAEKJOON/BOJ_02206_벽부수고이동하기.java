/*
 * BAEKJOON 02206. 벽 부수고 이동하기
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

public class BOJ_02206_벽부수고이동하기 {
	
	static class Position {
		int r;
		int c;
		int cnt;
		int br; // 0: false, 1: true(부수지 않음)
		
		public Position(int r, int c, int cnt, int br) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.br = br;
		}
	}

	static int ans;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_02206.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 치즈의 세로 길이
			int M = Integer.parseInt(st.nextToken()); // 치즈의 가로 길이
			int[][] map = new int[N + 1][M + 1];
			cnt = 0;
			ans = 987654321;
			
			// 2차원 배열에 치즈의 상태 입력
			for(int r = 1; r <= N; r++) {
				String row = br.readLine();
				for(int c = 1; c <= M; c++) {
					map[r][c] = row.charAt(c - 1) - '0';
				}
			}
			
			BFS(map, new Position(1, 1, 1, 0));
			
			// 결과 출력
			bw.write(String.format("#%d %d\n", test_case, (ans == 987654321 ? -1 : ans)));
			bw.flush();
		}
		
		// 자원 반환
		br.close();
		bw.close();
	}
	
	static int cnt;
	
	public static void BFS(int[][] map, Position start) {
		Queue<Position> queue = new LinkedList<Position>();
		boolean[][][] v = new boolean[map.length][map[0].length][2]; // 벽을 부순 상태와 부수지 않은 상태의 방문 배열로 나눠야 함
		queue.offer(start);
		v[start.r][start.c][0] = true;
		
		while(!queue.isEmpty()) {
			Position p = queue.poll();
			
			if(p.r == map.length - 1 && p.c == map[0].length - 1) {
				// 도착
				// System.out.println(p.cnt);
				ans = Math.min(ans,  p.cnt);
				break;
			}
			
			for(int d = 0; d < dr.length; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				if(p.r == map.length - 1 && p.c == map[0].length - 1) {
					break;
				}
				
				if(nr >= 1 && nr < map.length && nc >= 1 && nc < map[0].length && !v[nr][nc][p.br]) {
					// 벽을 부수든, 부수지 않든 그대로 전진
					if(map[nr][nc] == 0 && !v[nr][nc][p.br]) {
						v[nr][nc][p.br] = true;
						queue.offer(new Position(nr, nc, p.cnt+1, p.br)); // 그대로 연결해서 감
					}
					else if(map[nr][nc] == 1 && p.br == 0) { // 벽인데 아직 부수지 않았으면
						v[nr][nc][1] = true;
						queue.add(new Position(nr, nc, p.cnt+1, 1));
					}
				}
			}
		}
	}
}
