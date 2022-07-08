/*
 * 2022. 02. 08. 보충수업
 * Intermediate - 기지국 문제
 */

package im;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BaseStation {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("BaseStation_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int ans = 0;
			int N = Integer.parseInt(br.readLine());
			char[][] map = new char[N][];
			
			// 2차원 배열에 map 입력
			for(int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			// 문제 해결
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					int range = map[r][c] - 'A' + 1;
					
					if(map[r][c] == 'A' || map[r][c] == 'B' || map[r][c] == 'C') {
						baseStation(map, r, c, range);
					}
					else continue;
				}
			}
			
			// 결과 출력 Part
			for(int r = 0; r < map.length; r++) {
				for(int c = 0; c < map[r].length; c++) {
					if(map[r][c] == 'H') ans++;
				}
			}
			
			bw.write(String.format("#%d %d\n", test_case, ans));
			bw.flush();
		}
		br.close();
		bw.close();
	}
	
	static int[] dr = {-1,  1,  0,  0};
	static int[] dc = { 0,  0, -1,  1};
	
	public static void baseStation(char[][] map, int r, int c, int range) {
		for(int d = 0; d < dr.length; d++) {
			
			// 기지국 범위에 따른 사방 탐색
			for(int i = 1; i <= range; i++) {
				int nr = r + dr[d] * i;
				int nc = c + dc[d] * i;
				
				// 범위 체크
				if(checkRange(map, nr, nc) && map[nr][nc] == 'H') {
					// 기지국의 범위에 닿으면 결과에 필요하지 않으므로 X로 데이터 변경
					map[nr][nc] = 'X';
				}
				else break;
				
				// if문 버전
//				for(int d=1; d <= range; d++) { // 기지국이 커버하는 거리는 1부터 (1or2or3) 까지임.
//					if(i-d >= 0 && map[i-d][j] == 'H') map[i-d][j] = 'X'; // 윗칸 커버
//					if(i+d < N && map[i+d][j] == 'H') map[i+d][j] = 'X'; // 아래칸 커버
//					if(j-d >= 0 && map[i][j-d] == 'H') map[i][j-d] = 'X'; // 왼쪽칸 커버
//					if(j+d < N && map[i][j+d] == 'H') map[i][j+d] = 'X'; // 오른쪽칸 커버
//				}
			}
		}
	}
	
	public static boolean checkRange(char[][] map, int nr, int nc) {
		if(nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length) {
			return true;
		}
		return false;
	}
}
