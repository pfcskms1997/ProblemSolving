/*
 * SWEA 01210. Ladder1
 */

package difficulty04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_01210_Ladder1 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty04/input_01210.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int tc = Integer.parseInt(br.readLine());
			int[][] map = new int[100][100];
			int start = -1;
			int end = -1;
			
			// 2차원 배열에 사다리판 입력을 하며 도착점의 위치를 찾음
			StringTokenizer st;
			for(int r = 0; r < map.length; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 0; c < map[r].length; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if(map[r][c] == 2){
						end = c;
					}
				}
			}
			
			start = ladderGame(map, end);
			
			System.out.printf("#%d %d\n", tc, start);
		}
		br.close();
	}
	
	// delta 방식 탐색(좌, 우, 상)
	// 좌/우 방향을 우선 탐색하기 위해 상을 마지막에 배치함
	static int[] dr = {0, 0, -1};
	static int[] dc = {-1, 1, 0};
	
	public static int ladderGame(int[][] map, int end) {
		// 탐색을 시작할 열의 위치
		int sc = end;
		int start = 0;
		
		// end point에 도달할 수 있는 시작점까지 탐색
		for(int i = map.length - 1; i >= 0; i--) {
			if(i == 0) {
				start = sc;
				break;
			}
			
			for(int d = 0; d < dr.length; d++) {
				boolean endSearch = false;
				int nr = i + dr[d];
				int nc = sc + dc[d];
				
				// 유효 범위 체크
				if(nc >= 0 && nc < map[0].length) {
					// 다음 좌표가 1이면 갈 수 있는 길
					if(map[nr][nc] == 1) {
						// 갈 수 있는 길이 좌 또는 우측인 경우
						if(d == 0 || d == 1) {
							// 가는 방향에서 마지막 1인 위치까지 반복
							while(true) {
								nc += dc[d];
								
								 // col idx가 유효하고, 가는 방향의 원소가 1이면 while loop를 계속 돌며 col idx를 갱신함
								if((nc >= 0 && nc < map[0].length) && map[nr][nc] == 1) {
									continue;
								}
								sc = nc - dc[d]; // 하나 더 갔으므로 -1해서 가던 방향의 끝의 위치를 찾음
								endSearch = true;
								break;
							}
						}
						else { // 갈 수 있는 길이 상 방향인 경우
							sc = nc;
						}
					}
				}
				// 길을 찾았으므로 delta 탐색 종료
				if(endSearch) break;
			}
		}
		return start;
	}
}
