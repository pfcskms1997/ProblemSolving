/*
 * BAEKJOON 15683. 감시
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15683_감시_X {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_15683.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			System.out.println("#" + test_case);
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 세로의 크기
			int M = Integer.parseInt(st.nextToken()); // 가로의 크기
			int[][] zone = new int[N][M];
			ArrayList<Camera> camera = new ArrayList<Camera>();
			
			// 2차원 배열에 구역 정보 입력
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 0; c < M; c++) {
					zone[r][c] = Integer.parseInt(st.nextToken());
					if(zone[r][c] > 0 && zone[r][c] < 6) {
						camera.add(new Camera(r, c, zone[r][c]));
					}
				}
			}
			
			// 재귀
			// 모든 카메라를 순회하면서 감시 영역 체크
			minBlindSpot(zone, camera, 0);
			
			sb.append(String.format("#%d \n", test_case));
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	static class Camera {
		int r;
		int c;
		int type;
		
		public Camera(int r, int c, int type) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
		}

		@Override
		public String toString() {
			return "Camera [r=" + r + ", c=" + c + ", type=" + type + "]";
		}
	}
	
	//    0
	//  3   1
	//    2
	
	static int[] dr = {-1, 0,  1,  0};
	static int[] dc = {0,  1,  0, -1};
	static int[][] type = {{}, {1}, {1, 3}, {0, 1, 3}, {0, 1, 2, 3}};
	
	public static void minBlindSpot(int[][] zone, ArrayList<Camera> camera, int cnt) {
		// Base Part
		if(cnt == camera.size()) {
			// 사각지대를 검사해서 최소값을 구한다.
			for(int i = 0; i < zone.length; i++) {
				System.out.println(Arrays.toString(zone[i]));
			}
			System.out.println("------------------------");
			return;
		}
		
		// Logic Part
		Camera cam = camera.get(cnt);
		
		
		// Inductive Part
		// 4번 회전
		for(int d = 0; d < 4; d++) {
			int[][] tmpMap = copyMap(zone);
			// 카메라가 바라보는 모든 방향을 감시
			for(int i = 0; i < type[cam.type].length; i++) {
				int nr = cam.r;
				int nc = cam.c;
				int nd = (type[cam.type][i] + d) % 4;
				
				// 벽이 나오기 전까지 
				while(true) {
					nr += dr[nd];
					nc += dc[nd];
					
					if(!check(zone.length, zone[0].length, nr, nc) || tmpMap[nr][nc] == 6) {
						break;
					}
					tmpMap[nr][nc] = 9;
				}
			}
			minBlindSpot(zone, camera, cnt+1);
		}
		
	}
	
	public static int[][] copyMap(int[][] map) {
		int[][] temp = new int[map.length][map[0].length];
		
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[r].length; c++) {
				temp[r][c] = map[r][c];
			}
		}
		
		return temp;
	}
	
	public static boolean check(int N, int M, int nr, int nc) {
		if(nr >= 0 && nr < N && nc >= 0 && nc < M) {
			return true;
		}
		
		return false;
	}

	public static void printMap(int[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
