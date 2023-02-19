/*
 * BAEKJOON 02615. 오목
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_02615_오목 {

	// 탐색을 위한 방향 설정 배열
	// 기준이 왼쪽이므로 8방을 모두 탐색할 필요는 없음
	//                 우상  우  우하   하
	static int[] dr = {-1, 0, 1, 1};
	static int[] dc = { 1, 1, 1, 0};
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_02615.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int N = 19; // 오목판의 크기
		int[][] omok = new int[N][N];
		boolean[][][] isChecked = new boolean[N][N][4];
		int curColor = 0; // 현재 위치의 색깔
		boolean findWinner = false;
		
		// 결과를 저장할 변수
		int winner = 0;
		// 이기게 된 5개의 바둑알 중 가장 왼쪽의 바둑알 좌표
		int win_r = 0;
		int win_c = 0;
		
		// 2차원 배열에 오목판 입력
		StringTokenizer st;
		for(int i = 0; i < omok.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < omok[i].length; j++) {
				omok[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//printMap(omok);
		
		// 오목판 탐색
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				// 현재 위치에 돌이 높여있지 않으면 다음 위치 탐색
				if(omok[r][c] == 0) continue;
				
				curColor = omok[r][c]; // 탐색의 시작점에 놓인 돌 색깔
				// 시작점으로부터 다른 방향을 델타 방식의 탐색 진행
				for(int d = 0; d < dr.length; d++) {
					
					if(isChecked[r][c][d]) continue;
					
					int dist = 1;
					
					while(dist < 6) {
						int nr = r + dr[d] * dist;
						int nc = c + dc[d] * dist;

						// 범위 체크
						if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
							// 다음 바둑알의 색깔이 시작점 바둑알의 색깔과 다르면 다른 방향으로 탐색
							if(omok[nr][nc] != curColor) break;
							
							// 색깔이 같은 경우
							isChecked[nr][nc][d] = true;
							dist++;
						}
						else break;
					}
					
					if(dist == 5) {
						if(r - dr[d] >= 0 && r - dr[d] < N && c - dc[d] >= 0 && c - dc[d] < N) {
							// 탐색하는 방향의 역방향 1회 확인
							if(omok[r - dr[d]][c - dc[d]] == curColor) continue;
						}

						// 오목 완성
						winner = curColor;
						win_r = r + 1;
						win_c = c + 1;
						findWinner = true;
						break;
					}
				}
				if(findWinner) break;
			}
			if(findWinner) break;
		}
		
		//System.out.printf("%d\n%d %d\n", winner, win_r, win_c);
		
		sb.append(findWinner ? String.format("%d\n%d %d\n", winner, win_r, win_c) : 0);
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void printMap(int[][] map) {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
