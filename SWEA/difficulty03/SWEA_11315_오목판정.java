/*
 * SWEA 11315. 오목 판정
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_11315_오목판정 {

	// 탐색을 위한 방향 설정 배열
	// 8방을 모두 탐색할 필요는 없음
	//                 우상  우  우하   하
	static int[] dr = {-1, 0, 1, 1};
	static int[] dc = { 1, 1, 1, 0};
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty03/input_11315.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 오목판의 크기
			char[][] omok = new char[N][];
			boolean[][][] isChecked = new boolean[N][N][4];
			boolean isOmok = false;
			
			// 2차원 배열에 오목판 입력
			for(int r = 0; r < omok.length; r++) {
				omok[r] = br.readLine().toCharArray();
			}
			
			// 오목판 탐색
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					// 현재 위치에 돌이 높여있지 않으면 다음 위치 탐색
					if(omok[r][c] == '.') continue;
					
					// 시작점으로부터 다른 방향을 델타 방식의 탐색 진행
					for(int d = 0; d < dr.length; d++) {
						
						if(isChecked[r][c][d]) continue;
						
						int dist = 1;
						
						while(true) {
							int nr = r + dr[d] * dist;
							int nc = c + dc[d] * dist;

							// 유효 범위이면서 다음에 탐색할 위치에 바둑알이 놓여있으는 경우 
							if(nr >= 0 && nr < N && nc >= 0 && nc < N && omok[nr][nc] == 'o') dist++;
							else break;
							
							// 바둑알이 5개 이상 연속으로 놓여있으면 탐색 종료
							if(dist >= 5) {
								isOmok = true;
								break;
							}
						}
					}
				}
			}
			
			sb.append(String.format("#%d %s\n", test_case, isOmok ? "YES" : "NO"));
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
