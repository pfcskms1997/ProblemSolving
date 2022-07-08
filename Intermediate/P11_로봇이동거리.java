package im_problem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P11_로봇이동거리 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/Solution11.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 배열의 크기
			char[][] map = new char[N][N];
			int dist = 0;
			
			// 2차원 배열에 map 입력
			for(int r = 0; r < N; r++) {
				String temp = br.readLine().replace(" ", "");
				for(int c = 0; c < N; c++) {
					map[r][c] = temp.charAt(c);
				}
			}
			
			// 문제 해결: 모든 로봇이 이동한 거리 구하기
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					switch(map[r][c]) {
					case 'A': // 로봇 A(우로만 이동 가능)
						dist += move(map, r, c, 1);
						break;
					case 'B': // 로봇 B(좌/우로만 이동 가능)
						dist += move(map, r, c, 2);
						break;
					case 'C': // 로봇 C(상/하/좌/우 이동 가능)
						dist += move(map, r, c, 4);
						break;
					default:
						break;
					}
				}
			}
			
			// 결과 출력
			bw.write(String.format("#%d %d\n", test_case, dist));
			bw.flush();
		}
		// 자원 반납
		br.close();
		bw.close();
	}
	
	//                 우     좌      상       하
	static int[] dr = {0,  0, -1,  1};
	static int[] dc = {1, -1,  0,  0};
	
	public static int move(char[][] map, int r, int c, int dir) {
		int dist = 0;
		
		// 4방 탐색
		for(int d = 0; d < dir; d++) {
			int n = 1;
			while(true) {
				int nr = r + dr[d] * n;
				int nc = c + dc[d] * n;
				
				// 범위 체크
				if(nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length) {
					// 빈 공간이 아니면 다음 방향 탐색
					if(map[nr][nc] != 'S') break;
					n++; // 진행방향의 거리를 늘림
					dist++; // 갈 수 있는 거리 1증가
				}
				else break; // 범위를 벗어난 경우
			}
		}
		return dist;
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
