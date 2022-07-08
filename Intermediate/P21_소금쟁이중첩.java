package im_problem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class P21_소금쟁이중첩 {

	//                 X  하    우
	static int[] dr = {0, 1, 0};
	static int[] dc = {0, 0, 1};
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/Solution21.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 연못의 크기
			int strider = Integer.parseInt(st.nextToken()); // 소금쟁이의 수
			boolean[][] pond = new boolean[N][N];
			int res = 0;
			
			for(int i = 0; i < strider; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int sr = Integer.parseInt(st.nextToken()); // 소금쟁이의 시작 행 좌표
				int sc = Integer.parseInt(st.nextToken()); // 소금쟁이의 시작 열 좌표
				int dir = Integer.parseInt(st.nextToken()); // 소금쟁이가 뛰는 방향
				boolean findFlag = false;
				
				// 시작 지점이 이미 다른 소금쟁이가 뛰었던 위치인 경우
				if(pond[sr][sc]) {
					res = i + 1;
					break;
				}
				
				// 3회 점프
				for(int j = 3; j > 0; j--) {
					int nr = sr + dr[dir] * j;
					int nc = sc + dc[dir] * j;
					
					// 뛸 좌표가 연못 범위 내에 있는 경우
					if(nr >= 0 && nr < pond.length && nc >= 0 && nc < pond[0].length) {
						// 이미 다른 소금쟁이가 뛰었던 영역인 경우
						if(pond[nr][nc] && !findFlag) {
							res = i + 1;
							findFlag = true;
						}
						// 아직 아무도 뛰지 않은 영역인 경우
						else {
							pond[nr][nc] = true;
							sr = nr;
							sc = nc;
							continue;
						}
					}
					// 소금쟁이가 연못 밖으로 뛰는 경우
					else break;
				}
				if(findFlag) {
					// 읽지 않은 line은 다음 테스트케이스에 영향을 주므로 제거함
					for(int j = 0; j < strider - i - 1; j++) br.readLine();
					break;
				}
			}
			
			// 결과 출력
			bw.write(String.format("#%d %d\n", test_case, res));
			bw.flush();
		}
		// 자원 반납
		br.close();
		bw.close();
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
