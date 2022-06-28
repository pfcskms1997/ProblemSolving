/*
 * SWEA 01979. 어디에 단어가 들어갈 수 있을까
 */

package difficulty02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_01979_어디에단어가들어갈수있을까 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty02/input_01979.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 퍼즐의 가로, 세로 길이
			int K = Integer.parseInt(st.nextToken()); // 단어의 길이
			int[][] puzzle = new int[N][N];
			int cnt = 0;
			
			// 2차원 배열에 퍼즐 입력
			for(int r = 0; r < puzzle.length; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 0; c < puzzle[r].length; c++) {
					puzzle[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			//         우      하
			int[] dr = {0, 1};
			int[] dc = {1, 0};
			// 4방탐색 중 우/하 방향으로만 K 길이의 글자가 들어갈 수 있는 공간 탐색
			for(int r = 0; r < puzzle.length; r++) {
				for(int c = 0; c < puzzle[r].length; c++) {
					// 현재 탐색할 위치가 빈칸이 아니면 다음 위치로 시작점을 옮김
					if(puzzle[r][c] != 1) continue;
					
					for(int d = 0; d < dr.length; d++) {
						int dir = 0;
						while(true) {
							int nr = r + dr[d] * dir;
							int nc = c + dc[d] * dir;
							
							// 범위 체크
							if(nr >= 0 && nr < puzzle.length && nc >= 0 && nc < puzzle[0].length) {
								if(puzzle[nr][nc] == 1) dir++;
								else break; // 다음 방향 탐색
							}
							else break;
						}
						// 현재 위치부터 앞으로 탐색할 수 있는 수가 주어진 수와 같을 때
						if(dir == K) {
							// 반대방향으로 1칸 탐색
							int nr = r - dr[d];
							int nc = c - dc[d];
							
							if(nr < 0 || nc < 0 || puzzle[nr][nc] != 1) cnt++;
						}
					}
				}
			}
			sb.append(String.format("#%d %d\n", test_case, cnt));
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
