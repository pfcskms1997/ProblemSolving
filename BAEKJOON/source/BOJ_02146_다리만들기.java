/*
 * BAEKJOON 02146. 다리 만들기
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_02146_다리만들기 {
	
	static int ans;

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_02146.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 지도의 크기
		int[][] map = new int[N][N];
		boolean[][] v = new boolean[N][N];
		int islandCnt = 0;
		ans = 987654321;
		
		for(int r = 0; r < map.length; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for(int c = 0; c < map.length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬 넘버링
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c] != 0 && !v[r][c]) {
					islandCnt++;
					numberingIsland(map, v, r, c, islandCnt);
				}
			}
		}
		
		// 다리의 최소 길이 찾기
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c] != 0) {
					buildBridge(map, r, c);
				}
			}
		}
		
		//printMap(map);
		System.out.println(ans);
		br.close();
	}

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	// DFS 알고리즘으로 각각의 섬에 번호를 매김
	public static void numberingIsland(int[][] map, boolean[][] v, int r, int c, int cnt) {
		int N = map.length;
		map[r][c] = cnt;
		v[r][c] = true;
		
		for(int d = 0; d < dr.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 방문하지 않은 육지이면 계속해서 탐색
			if(checkRange(nr, nc, N) && map[nr][nc] != 0 && !v[nr][nc]) {
				numberingIsland(map, v, nr, nc, cnt);
			}
		}
	}
	
	// BFS 알고리즘으로 다른 섬까지 놓을 수 있는 다리의 최소 길이를 구함
	private static void buildBridge(int[][] map, int r, int c) {
		int N = map.length;
		Queue<int[]> queue = new LinkedList<int[]>();
		boolean[][] v = new boolean[N][N];
		int dist = 0;
		
		v[r][c] = true;
		queue.offer(new int[] {r, c, dist});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			// 현재 계산이 진행 중인 다리의 길이가 이전에 구한 다리의 길이보다 크거나 같으면 더 계산하는 의미가 없으므로 계산 종료
			if(cur[2] >= ans) break;
			
			for(int d = 0; d < dr.length; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				
				if(checkRange(nr, nc, N) && !v[nr][nc]) {
					// 아직 바다인 경우
					if(map[nr][nc] == 0) {
						v[nr][nc] = true;
						queue.offer(new int[] {nr, nc, cur[2]+1});
					}
					// 다른 섬에 도착한 경우
					else if(map[nr][nc] != map[r][c]) {
						ans = Math.min(ans, cur[2]);
					}
				}
			}
		}		
	}
	
	// 사방 탐색 중 새로운 좌표가 유효한 범위인지 확인하는 함수
	public static boolean checkRange(int nr, int nc, int N) {
		if(nr >= 0 && nr < N && nc >= 0 && nc < N) return true;
		else return false;
	}
	
	public static void printMap(int[][] map) {
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map.length; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
