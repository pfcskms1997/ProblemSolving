/*
 * SWEA 02382. 미생물 격리
 */

package mocktest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_02382_미생물격리 {
	
	static class Micro implements Comparable<Micro> {
		int r; // 행
		int c; // 열
		int cnt; // 군집 크기
		int dir; // 이동 방향
		
		public Micro(int r, int c, int cnt, int dir) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.dir = dir;
		}

		@Override
		public int compareTo(Micro o) {
			return o.cnt - this.cnt; // 내림차순, 최대힙
		}
	}

	static int N, M, K;
	static Micro[][] map;
	
	static int[] dr = {0, -1, 1, 0, 0}; // 0: 사용하지 않음, 상: 1, 하: 2, 좌: 3, 우: 4
	static int[] dc = {0, 0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/mocktest/input_02382.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 구역 크기
			M = Integer.parseInt(st.nextToken()); // 처리 시간
			K = Integer.parseInt(st.nextToken()); // 초기 군집 개수
			map = new Micro[N][N]; // 매 시간마다 각 셀에 이동한 미생물 정보
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int row = Integer.parseInt(st.nextToken()); // 세로 위치
				int col = Integer.parseInt(st.nextToken()); // 가로 위치
				int cnt = Integer.parseInt(st.nextToken()); // 미생물 수
				int dir = Integer.parseInt(st.nextToken()); // 이동방향
				
				map[row][col] = new Micro(row, col, cnt, dir);
			}
			
			int ans = simulation(map);
			
			bw.write(String.format("#%d %d\n", test_case, ans));
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
	
	private static int simulation(Micro[][] map) {
		PriorityQueue<Micro> pQueue = new PriorityQueue<Micro>();
		int remains = 0;
		int time = M;
		
		while(time-- > 0) {
			// 미생물 군집을 queue에 넣고 map을 비움
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					if(map[r][c] != null) {
						pQueue.offer(map[r][c]);
						map[r][c] = null;
					}
				}
			}
			
			while(!pQueue.isEmpty()) {
				Micro m = pQueue.poll();
				m.r += dr[m.dir];
				m.c += dc[m.dir];
				
				if(m.r == 0 || m.r == N-1 || m.c == 0 || m.c == N-1) { // 가장자리 약품이 칠해진 셀
					if((m.cnt = m.cnt / 2) == 0) continue; // 크기를 줄인 후 0이면 소멸
					
					// 방향 반대로 턴
					if(m.dir % 2 == 1) m.dir++;
					else m.dir--;
				}
				
				if(map[m.r][m.c] == null) { // 해당 자리에 처음 이동한 미생물 군집이면 그 자리에 세팅
					map[m.r][m.c] = m;
				} else { // 해당 자리에 처음 이동한 미생물 군집이 아니면 기존 군집에 합치기
					map[m.r][m.c].cnt += m.cnt;
				}
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c] != null) {
					remains += map[r][c].cnt; // 살아 있는 미생물 군집의 크기 누적
				}
			}
		}
		
		return remains;
	}
}
