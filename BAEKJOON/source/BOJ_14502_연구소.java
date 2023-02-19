/*
 * BAEKJOON 14502. 연구소
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_연구소 {
	
	static class Virus {
		int r;
		int c;
		
		public Virus(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return String.format("r: %d, c: %d", this.r, this.c);
		}
	}

	static int N, M, ans;
	static int[][] map;
	static int[][] cmap;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_14502.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 지도의 세로 크기
			M = Integer.parseInt(st.nextToken()); // 지도의 가로 크기
			map = new int[N][M];
			cmap = new int[N][M];
			ans = 0;
			
			// 2차원 배열에 지도 정보 입력
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 0; c < M; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			setWall(0);

			bw.write(String.format("#%d %d\n", test_case, ans));
			bw.flush();
		}
		br.close();
		bw.close();
	}
	
	// 벽을 세울 수 있는 모든 경우를 계산하는 함수(DFS)
	public static void setWall(int cnt) {
		// 벽이 3개 모두 세워짐
		if(cnt == 3) {
			copyMap(map, cmap);
			spreadVirus(cmap);
			
			ans = Math.max(ans, countSafeZone(cmap));
			return;
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] == 0) {
					map[r][c] = 1;
					setWall(cnt+1);
					map[r][c] = 0;
				}
			}
		}
	}
	
	// 바이러스를 퍼뜨리는 함수(BFS)
	public static void spreadVirus(int[][] map) {
		Queue<Virus> queue = new LinkedList<Virus>();
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] == 2) {
					queue.offer(new Virus(r, c));
				}
			}
		}
		
		while(!queue.isEmpty()) {
			Virus cur = queue.poll();
			
			for(int d = 0; d < dr.length; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(checkRange(nr, nc) && map[nr][nc] == 0) {
					map[nr][nc] = 2;
					queue.offer(new Virus(nr, nc));
				}
			}
		}
	}
	
	// 임시 지도 생성 함수
	public static void copyMap(int[][] map, int[][] cmap){
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				cmap[r][c] = map[r][c];
			}
		}
	}
	
	// 안전 영역의 개수를 세는 함수
	public static int countSafeZone(int[][] map) {
		int cnt = 0;
	
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] == 0) cnt++;
			}
		}
		return cnt;
	}
	
	public static boolean checkRange(int nr, int nc) {
		if(nr >= 0 && nr < N && nc >= 0 && nc < M) return true;
		else return false;
	}
	
	// 지도 출력 함수
	public static void printMap(int[][] map) {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	// 바이러스 리스트 출력 함수
	public static void printList(ArrayList<Virus> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
}
