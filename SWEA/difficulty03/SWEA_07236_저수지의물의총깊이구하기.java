/*
 * SWEA 07236. 저수지의 물의 총 깊이 구하기
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_07236_저수지의물의총깊이구하기 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty03/input_07236.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 저수지 구획의 크기
			char[][] pond = new char[N][N];
			int maxDepth = 0;
			
			// 2차원 배열 pond에 저수지 정보를 입력
			String temp = null;
			for(int r = 0; r < N; r++) {
				temp = br.readLine().replace(" ", ""); // 공백 제거
				for(int c = 0; c < N; c++) {
					pond[r][c] = temp.charAt(c);
				}
			}
			
			// 8방탐색을 위한 delta 변수
			int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
			int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
			
			// 문제 해결: 각 위치에서 저수지의 깊이 구하기
			for(int r = 1; r < N - 1; r++) {
				for(int c = 1; c < N - 1; c++) {
					// 현재 위치가 땅이면 다음 위치로 이동
					if(pond[r][c] == 'G') continue;
					
					int area = 0;
					int ground = 0;
					int curDepth = 8;
					
					for(int d = 0; d < dr.length; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						area++;
						if(pond[nr][nc] == 'G') ground++;
					}
					curDepth = (ground != area) ? 8 - ground : 1;
					maxDepth = Math.max(maxDepth, curDepth);
				}
			}
			
			sb.append(String.format("#%d %d\n", test_case, maxDepth));
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
