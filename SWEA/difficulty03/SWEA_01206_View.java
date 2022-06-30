/*
 * SWEA 01206. View
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_01206_View {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty03/input_01206.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = 10;
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 테스트케이스의 길이
			int[] building = new int[N];
			int household = 0;
			
			// 1차원 배열에 빌딩의 높이 입력
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < building.length; i++) {
				building[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 2; i < building.length - 2; i++) {
				int LMax = 0;
				int RMax = 0;
				
				// 양 옆으로 거리 2만큼 탐색
				for(int j = 1; j <= 2; j++) {
					int Lidx = i - j;
					int Ridx = i + j;
					
					// 좌/우로 거리 2 이내의 건물 중 최고층의 높이를 기록 
					LMax = Math.max(LMax, building[Lidx]);
					RMax = Math.max(RMax, building[Ridx]);
				}
				
				// 현재 건물의 높이와 인접 건물의 최고층을 비교하였을 때, 양수(현재 건물의 높이가 더 높은 경우)에만 세대 수를 더해 줌
				int interval = building[i] - Math.max(LMax, RMax);
				household += (interval > 0) ? interval : 0;
			}
			sb.append(String.format("#%d %d\n", test_case, household));
		}
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
