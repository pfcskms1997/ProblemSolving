/*
 * SWEA 01220. Magnetic
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_01220_Magnetic {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty03/input_01220.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(in.readLine());
			int[][] table = new int[N][N];
			int ans = 0;
			
			// 2차원 배열에 테이블의 상태 입력
			// 테이블의 위에 N극, 아래를 S극이 위치한다고 가정
			// 1: N극, 2: S극
			for(int r = 0; r < table.length; r++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				for(int c = 0; c < table[r].length; c++) {
					table[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int c = 0; c < table.length; c++) {
				// edge 물체의 발견 여부를 나타내는 플래그
				boolean findNorthEdge = false;
				boolean findSouthEdge = false;
				
				// edge 물체의 행좌표
				int northEdge = 0;
				int southEdge = 0;
				int idx = 0;
				
				// 교착상태 내의 N극과 S극의 개수를 카운트
				int Ns = 0;
				int Ss = 0;
				
				// 가장 최근에 탐색한 자성물체의 극성
				// N극: true, S극: false
				boolean lastPole = false;
				
				// 현재 열에서 테이블의 양 끝과 같은 극성을 가지고 있는 가장 가까운 자성 물체의 위치를 탐색함
				while(!(findNorthEdge && findSouthEdge)) {
					if(!findNorthEdge) {
						if(table[idx][c] == 1) {
							northEdge = idx;
							findNorthEdge = true;
						}
					}
					
					if(!findSouthEdge) {
						if(table[table.length - 1 - idx][c] == 2) {
							southEdge = table.length - 1 - idx;
							findSouthEdge = true;
						}
					}
					idx++;
					
					// 조건에 맞는 자성물체를 찾지 못한 경우 루프를 빠져나감
					if(idx >= table.length) break;
				}
				
				if(!(findNorthEdge && findSouthEdge)) continue; // 두 극성 물체 중 하나라도 없으면 교착상태가 없으므로 다음 열로 이동
				
				for(int r = northEdge; r <= southEdge; r++) {
					// N극과 S극이 번갈아 나타나야 교착상태이므로 이를 검증하여 N극과 S극의 개수를 셈
					if(table[r][c] == 1 && lastPole == false) {
						Ns++;
					}
					else if(table[r][c] == 2 && lastPole == true) {
						Ss++;
					}
					
					// 다음 연산을 위해 마지막으로 방문한(현재) 위치의 극성을 기록
					if(table[r][c] == 1) lastPole = true;
					else if(table[r][c] == 2) lastPole = false;
				}
				ans += Math.min(Ns, Ss);
			}
			System.out.printf("#%d %d\n", test_case, ans);
		}
		in.close();
	}
}
