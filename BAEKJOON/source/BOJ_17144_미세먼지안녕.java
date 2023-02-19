/*
 * BAEKJOON 17144. 미세먼지 안녕!
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17144_미세먼지안녕 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_17144.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int TC = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= TC; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int R = Integer.parseInt(st.nextToken()); // 집의 row 크기
			int C = Integer.parseInt(st.nextToken()); // 집의 column 크기
			int T = Integer.parseInt(st.nextToken()); // 측정 시간(초)
			int[][] room = new int[R][C];
			int purifier = -1; // 공기청정기의 위치(row), 열 번호는 1로 고정
			int dustSum = 0;
			
			// 2차원 배열에 방의 미세먼지 정보 입력
			for(int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 0; c < C; c++) {
					room[r][c] = Integer.parseInt(st.nextToken());
					if(room[r][c] == -1 && purifier == -1) purifier = r;
				}
			}
			
			// 입력 받은 측정 시간만큼 반복
			for(int i = 0; i < T; i++) {
				dustSpread(room);
				circulate(room, purifier);
			}
			
//			System.out.println("#" + test_case);
//			printMap(room);
			
			// 반복 후 방의 미세먼지 상태를 모두 더하여 결과 계산
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					if(room[r][c] == -1) continue;
					dustSum += room[r][c];
				}
			}

			// 결과 출력
			bw.write(String.format("#%d %d\n", test_case, dustSum));
			bw.flush();
		}
		br.close();
	}
	
	public static void dustSpread(int[][] room) {
		int R = room.length;
		int C = room[0].length;
		// 먼지가 동시에 퍼져나가므로, 이전 결과가 다음 연산에 영향을 미치지 않도록 더해줄 값을 임시 저장할 배열
		int[][] temp = new int[R][C];
		
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(room[r][c] < 0 || (room[r][c] / 5) <= 0) continue;
				
				int cnt = 0;
				
				for(int d = 0; d < dr.length; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// 범위 체크
					if(checkRanage(nr, nc, 0, 0, R, C) && room[nr][nc] != -1) {
						temp[nr][nc] += room[r][c] / 5;
						cnt++;
					}
				}
				room[r][c] -= (room[r][c] / 5) * cnt;
			}
		}
		
		// 동시 전파를 위해 임시 저장한 값을 원래의 배열에 더해 줌
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				room[r][c] += temp[r][c];
			}
		}
	}
	
	public static void circulate(int[][] room, int purifier) {
		/* COUNTER-CLOCKWISE CIRCULATE*/
		// 상 -> 우 -> 하 -> 좌
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		int R = room.length;
		int C = room[0].length;
		
		// 이동 방향 변수
		int dir = 0;
		// 시작점
		int sr = purifier;
		int sc = 0;
		
		while(dir < 4) {
			int nr = sr + dr[dir];
			int nc = sc + dc[dir];
			if(checkRanage(nr, nc, 0, 0, purifier+1, C)) {
				room[sr][sc] = room[nr][nc];
				sr = nr;
				sc = nc;
			}
			else dir++;
		}
		room[purifier][0] = -1; // rotate 후 공기 청정기의 위치는 변하지 않으므로 고정시킴
		room[purifier][1] = 0; // 공기 청정기를 지나서 나온 공기는 미세 먼지가 완전히 제거되었으므로 0이 나오게 됨
		
		/* CLOCKWISE CIRCULATE*/
		
		// 하 -> 우 -> 상 -> 좌
		// 위에서 사용한 델타 배열 중 상/하만 서로 바꾸어서 사용
		dr[0] = 1;
		dr[2] = -1;
		
		// 이동 방향 변수
		dir = 0;
		// 시작점
		sr = purifier + 1; // 공기 청정기의 아랫부분
		sc = 0;
		
		while(dir < 4) {
			int nr = sr + dr[dir];
			int nc = sc + dc[dir];
			if(checkRanage(nr, nc, purifier+1, 0, R, C)) {
				room[sr][sc] = room[nr][nc];
				sr = nr;
				sc = nc;
			}
			else dir++;
		}
		room[purifier+1][0] = -1; // rotate 후 공기 청정기의 위치는 변하지 않으므로 고정시킴
		room[purifier+1][1] = 0; // 공기 청정기를 지나서 나온 공기는 미세 먼지가 완전히 제거되었으므로 0이 나오게 됨
	}
	
	public static boolean checkRanage(int nr, int nc, int sr, int sc, int er, int ec) {
		if(nr >= sr && nr < er && nc >= sc && nc < ec) return true;
		else return false;
	}

	public static void printMap(int[][] map) {
		for(int r = 0; r < map.length; r++) {
			System.out.println(Arrays.toString(map[r]).replaceAll("[\\[\\,\\]]", ""));
		}
	}
}
