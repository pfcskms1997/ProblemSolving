/*
 * BAEKJOON 01194. 달이 차오른다, 가자.
 * Hint: BFS, BitMasking
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_01194_달이차오른다가자 {
	
	static class Position {
		int r;
		int c;
		int cnt;
		int key;
		
		public Position(int r, int c, int cnt, int key) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.key = key;
		}
	}
	
	static int N;
	static int M;
	static int Ans;
	
	static int[] dr = {-1,  1,  0,  0};
	static int[] dc = { 0,  0, -1,  1};

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_01194.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 세로 길이
			M = Integer.parseInt(st.nextToken()); // 가로 길이
			char[][] maze = new char[N][M];
			int sr = -1;
			int sc = -1;
			Ans = 987654321;
			
			// 2차원 배열에 미로의 상태 입력
			for(int r = 0; r < N; r++) {
				String line = br.readLine();
				for(int c = 0; c < M; c++) {
					maze[r][c] = line.charAt(c);
					
					// 출발지점 기록
					if(maze[r][c] == '0') {
						sr = r;
						sc = c;
					}
				}
			}
			
			BFS(maze, new Position(sr, sc, 0, 0));
			
			// 결과 출력
			bw.write(String.format("#%d %d\n", test_case, (Ans == 987654321) ? -1 : Ans));
			bw.flush();
		}
		
		// 자원 반환
		br.close();
		bw.close();
	}
	
	public static void BFS(char[][] maze, Position start) {
		Queue<Position> queue = new LinkedList<Position>();
		boolean[][][] v = new boolean[N][M][1<<6];
		
		queue.offer(start);
		v[start.r][start.c][0] = true;
		
		while(!queue.isEmpty()) {
			Position p = queue.poll();
			
			// 목적지에 도착
			if(maze[p.r][p.c] == '1') {
				Ans = Math.min(Ans, p.cnt);
				break;
			}
			
			for(int d = 0; d < dr.length; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				int nk = p.key;
				
				if(checkRange(nr, nc) && maze[nr][nc] != '#' && !v[nr][nc][nk]) {
					// 문을 만났을 때
					if('A' <= maze[nr][nc] && maze[nr][nc] <= 'F') {
						// 키가 없으면
						if((nk & (1 << maze[nr][nc] - 'A')) == 0) continue;
					}
					
					// 키를 만났을 때
					if('a' <= maze[nr][nc] && maze[nr][nc] <= 'f') {
						nk = nk | (1 << maze[nr][nc] - 'a'); // 키 획득
					}
					
					v[nr][nc][nk] = true;
					queue.offer(new Position(nr, nc, p.cnt+1, nk));
				}
			}
		}
	}
	
	public static boolean checkRange(int nr, int nc) {
		if(nr >= 0 && nr < N && nc >= 0 && nc < M) return true;
		else return false;
	}
}
