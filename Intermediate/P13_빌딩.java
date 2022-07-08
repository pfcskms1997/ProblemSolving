package im_problem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P13_빌딩 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/Solution13.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 배열의 크기
			char[][] map = new char[N][N];
			int maxHeight = 0;
			
			// 2차원 배열에 map 입력
			for(int r = 0; r < N; r++) {
				String temp = br.readLine().replace(" ", "");
				for(int c = 0; c < N; c++) {
					map[r][c] = temp.charAt(c);
				}
			}
			
			// 문제 해결: 가장 높이 세울 수 있는 빌딩의 층수 구하기
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					// 현재 위치가 빌딩 부지인 경우에만 수행
					if(map[r][c] == 'B') {
						// 함수 인자에 map과 현재 좌표를 인자로 주고, 현재 위치에 세울 수 있는 빌딩의 높이를 return 받음
						maxHeight = Math.max(maxHeight, currentHeight(map, r, c));
					}
				}
			}
			
			// 결과 출력
			bw.write(String.format("#%d %d\n", test_case, maxHeight));
			bw.flush();
		}
		// 자원 반납
		br.close();
		bw.close();
	}
	
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	public static int currentHeight(char[][] map, int r, int c) {
		int height = 0;
		
		// 8방 탐색
		for(int d = 0; d < dr.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 범위 체크
			if(nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length) {
				// 빈 공간이 아니면 다음 방향 탐색
				if(map[nr][nc] == 'G') {
					return 2;
				}
			}
		}
		
		// 주변에 공원 부지가 없는 경우 현재 위치의 가로와 세로에 있는 모든 빌딩 부지의 수를 더함
		for(int i = 0; i < map.length; i++) {
			if(map[i][c] == 'B') height++;
			if(map[r][i] == 'B') height++;
		}
		
		return height - 1; // 중복된 값 하나 제거
	}
	
	public static void printMap(char[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
