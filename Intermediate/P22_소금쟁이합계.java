package im_problem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P22_소금쟁이합계 {
	
	//                     상    하   좌    우
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/Solution22.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 연못의 크기 = N*N
			int I = Integer.parseInt(st.nextToken()); // 소금쟁이의 개체 수
			boolean[][] pond = new boolean[N][N];
			int[][] striders = new int[I][];
			int alive = 0;
			
			// 연못좌표 초기화
			for(int i = 0; i < striders.length; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				// 소금쟁이 좌표 및 방향 입력
				int row = Integer.parseInt(st.nextToken()); // row
				int col = Integer.parseInt(st.nextToken()); // column
				int dir = Integer.parseInt(st.nextToken()); // direction  - 상(1), 하(2), 좌(3), 우(4)
				
				striders[i] = new int[] {row, col, dir};
				
				pond[row][col] = true; // 소금쟁이가 위치한 좌표 기록
			}			
			
			for(int i = 0; i < I; i++) {
				for(int j = 3; j > 0; j--) {
					// 소금쟁이가 점프했으므로 이전 위치는 빈 칸으로 표시
					pond[striders[i][0]][striders[i][1]] = false;
					
					// 소금쟁이가 점프한 위치
					striders[i][0] = striders[i][0] + dr[striders[i][2]] * j;
					striders[i][1] = striders[i][1] + dc[striders[i][2]] * j;
					
					// 소금쟁이가 연못을 벗어났는지 확인
					if(striders[i][0] >= 0 && striders[i][0] < N &&
							striders[i][1] >= 0 && striders[i][1] < N) { // 소금쟁이가 연못 안에 있으면...
						if(pond[striders[i][0]][striders[i][1]] == false) { // 다른 소금쟁이와 부딫히지 않으면...
							if(j == 1) { // 3번 뛰어 도착점에 왔을 때
								pond[striders[i][0]][striders[i][1]] = true; // 도착점에 소금쟁이가 있음을 기록
								alive++;
							}
							else {
								pond[striders[i][0]][striders[i][1]] = true; // 중간지점에 소금쟁이가 있음을 기록
								continue;
							}
						}
						else break;
					}
					else break;
				}	
			}
			System.out.printf("#%d %d\n", test_case, alive);
		}
		br.close();
	}
}
