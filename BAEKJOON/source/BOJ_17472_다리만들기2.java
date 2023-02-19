/*
 * BAEKJOON 17472. 다리 만들기 2
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17472_다리만들기2 {
	
	static class Bridge {
		int r;
		int c;
		int dist;
		
		public Bridge(int r, int c, int dist) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
	
	static int N, M;
	static int[][] map;
	static int[][] adj;

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_17472.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 지도의 세로 크기
			M = Integer.parseInt(st.nextToken()); // 지도의 가로 크기
			map = new int[N][M];
			boolean[][] v = new boolean[N][M];
			int islandCnt = 0;
			
			// 2차원 배열에 지도 정보 입력
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < M; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// DFS 탐색하며 섬에 넘버링하며 개수를 셈
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					if(map[r][c] != 0 && !v[r][c]) {
						islandCnt++;
						numberingIsland(map, r, c, v, islandCnt);
					}
				}
			}
			
//			System.out.println("섬의 개수: " + islandCnt);
//			printMap(map);
			
//			for(int r = 0; r < N; r++) {
//				for(int c = 0; c < M; c++) {
//					if(map[r][c] > 0) {
//						connectBridge(map, r, c);
//					}
//				}
//			}
			
			// PRIM 알고리즘
			int V = islandCnt;
			adj = new int[V][V];
			for(int r = 0; r < V; r++) {
				Arrays.fill(adj[r], Integer.MAX_VALUE);
			}
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					if(map[r][c] > 1) {
						makeBridge(r, c);
					}
				}
			}
			
			// 무한인 값을 0으로 변경
			for(int r = 0; r < V; r++) {
				for(int c = 0; c < V; c++) {
					if(adj[r][c] == Integer.MAX_VALUE) {
						adj[r][c] = 0;
					}
				}
			}
			
			// printMap(adj);
			
			// MST(Prim)
			int[] node = new int[V];
			boolean[] v1 = new boolean[V];
			Arrays.fill(node, Integer.MAX_VALUE);
			node[0] = 0;
			
			for(int l = 0; l < V-1; l++) {
				// 최소 노드 값 구하기
				int minIdx = -1;
				int minD = Integer.MAX_VALUE;
				
				for(int i = 0; i < V; i++) {
					if(node[i] < minD && !v1[i]) {
						minIdx = i;
						minD = node[i];
						break;
					}
				}
				
				if(minIdx == -1) break; // 연결 되어있지 않은 섬이 경우
				
				v1[minIdx] = true;
				
				for(int i = 0; i < V; i++) {
					if(adj[minIdx][i] > 0 && !v1[i] && adj[minIdx][i] < node[i]) {
						node[i] = adj[minIdx][i];
					}
				}
			}
			
			// System.out.println(Arrays.toString(node));
			
			int ans = 0;
			for(int i = 0; i < node.length; i++) {
				if(node[i] == Integer.MAX_VALUE) {
					ans = -1;
					break;
				}
				ans += node[i];
			}
			
			

//			int[] parents = new int[islandCnt+1];
//			makeSet(parents);
			
//			System.out.printf("#%d\n", test_case);
//			System.out.println("섬의 개수: " + islandCnt);
//			printMap(map);
//			System.out.println(Arrays.toString(parents));
			
			// 다리를 만들 수 있는 경우의 수
			
			// Union-Find로 섬의 연결을 확인
			// 모두 연결할 수 있으면 모든 다리 길이의 합의 최소값, 연결할 수 없으면 -1을 받음
			
			
			// 결과 출력
			bw.write(String.format("#%d %d\n", test_case, ans));
			bw.flush();
		}
		
		// 자원 반환
		br.close();
		bw.close();
	}
	
	//                 우     하     좌     상
	static int[] dr = {0,  1,  0, -1};
	static int[] dc = {1,  0, -1,  0};
	
	// 섬마다 번호를 매김
	public static void numberingIsland(int[][] map, int r, int c, boolean[][] v, int cnt) {
		map[r][c] = cnt;
		v[r][c] = true;
		
		for(int d = 0; d < dr.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(checkRange(nr, nc) && !v[nr][nc] && map[nr][nc] != 0) {
				numberingIsland(map, nr, nc, v, cnt);
			}
		}
	}
	
	private static void makeBridge(int r, int c) {
		// 4방 탐색을 하면서 바다라면 다른 육지를 만날 때까지 전진
		for(int d = 0; d < dr.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			int dist = 0;
			
			while(checkRange(nr, nc) && map[nr][nc] == 0) {
				nr += dr[d];
				nc += dc[d];
				dist++;
				
				if(checkRange(nr, nc) && map[nr][nc] != 0 && map[r][c] != map[nr][nc] && dist > 1) {
					int start = map[r][c] - 1;
					int end = map[nr][nc] - 1;
					
					if(adj[start][end] > dist) {
						adj[start][end] = dist;
						adj[end][start] = dist;
					}
				}
			}
		}
	}
	
	// BFS로 다리 연결??
	public static void connectBridge(int[][] map, int r, int c) {
		Queue<Bridge> queue = new LinkedList<Bridge>();
		boolean[][] v = new boolean[N][M];
		queue.offer(new Bridge(r, c, 0));
		v[r][c] = true;
		
		for(int d = 0; d < dr.length; d++) {
			while(!queue.isEmpty()) {
				Bridge cur = queue.poll();
				
			}
		}
		
	}
	
	public static boolean checkRange(int nr, int nc) {
		if(nr >= 0 && nr < N && nc >= 0 && nc < M) return true;
		else return false;
	}
	
	public static void makeSet(int[] parents) {
		// 자신의 부모노드를 자신의 값으로 세팅
		for(int i = 1; i < parents.length; i++) {
			parents[i] = i;
		}
	}

	public static void printMap(int[][] map) {
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[r].length; c++) {
				System.out.printf("%2d ", map[r][c]);
			}
			System.out.println();
		}
	}
}
