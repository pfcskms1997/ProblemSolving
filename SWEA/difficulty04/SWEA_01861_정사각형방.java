/*
 * SWEA 01861. 정사각형 방
 */

package difficulty04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA_01861_정사각형방 {
	
	// 어떤 시점에서 거쳐간 방의 수가 최대가 되었을 때의 결과를 객체로 저장하기 위한 클래스 선언
	// 정렬을 위해 Comparable을 implements
	static class Result implements Comparable<Result>{
		int roomNum; // 방 번호
		int count;	 // 거쳐간 방의 수
		
		// 생성자
		public Result(int roomNum, int count) {
			super();
			this.roomNum = roomNum;
			this.count = count;
		}

		@Override
		public int compareTo(Result o) {
			// 비교순위 2. count값이 같을 경우에는 방 번호 값으로 오름차순 정렬(소->대)
			if(this.count == o.count) {
				return this.roomNum - o.roomNum;
			}
			
			// 비교순위 1. count 값으로 내림차순 정렬(대->소)
			return o.count - this.count;
		}
	}

	// 상하좌우 이동을 위한 델타 배열
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty04/input_01861.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		// TEST CASE 별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 배열의 크기
			int[][] rooms = new int[N][N];
			boolean[][] isChecked = new boolean[N][N];
			
			// 2차원 배열의 탐색 중 어떤 시점에서 거쳐간 방의 수가 최대일 때의 결과를 객체로 저장하기 위한 linked list
			LinkedList<Result> maxList = new LinkedList<Result>();
			int maxCnt = 0;
			StringTokenizer st;
			
			// 2차원 배열에 방의 정보 입력
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					rooms[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 2중 for문은 배열을 순차적으로 탐색
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					int tmp_r = r; // while 루프에서 움직이기 위한 r의 값
					int tmp_c = c; // while 루프에서 움직이기 위한 c의 값
					int cnt = 1;   // 거쳐간 방의 수
					int d = 0;     // delta 배열의 index
					
					// 이미 탐색된 경로의 일부일 경우는 다시 볼 필요가 없으므로 pass
					if(isChecked[r][c]) continue;
					
					while(true) {
						for(d = 0; d < dr.length; d++) {
							int nr = tmp_r + dr[d];
							int nc = tmp_c + dc[d];
							
							// 2차원 배열의 유효 범위 체크
							if(nr >= 0 && nr < rooms.length && nc >= 0 && nc < rooms[0].length) {
								// 인접한 방의 번호가 현재 방의 번호보다 1 큰 수인지 확인
								if(rooms[tmp_r][tmp_c] + 1 != rooms[nr][nc]) continue;
								
								isChecked[nr][nc] = true;
								tmp_r = nr;
								tmp_c = nc;
								cnt++;
								break;
							}
						}
						// 4방향 모두 갈 수 없는 경우
						if(d == dr.length) break;
					}
					// 현 시점에서 최대값인 경우 객체를 linked list에 저장
					if(maxCnt <= cnt) {
						maxCnt = Math.max(maxCnt, cnt);
						maxList.add(new Result(rooms[r][c], cnt));
					}
				}
			}
			
//			System.out.println("#" + test_case);
//			for(int i = 0; i < maxList.size(); i++) {
//				System.out.printf("N: %d, CNT: %d\n", maxList.get(i).roomNum, maxList.get(i).count);
//			}
			
			// 클래스에 정의한 우선순위로 list 정렬
			Collections.sort(maxList);
			
			// 결과 출력
			//System.out.printf("#%d %d %d\n", test_case, maxList.get(0).roomNum, maxList.get(0).count);
			sb.append(String.format("#%d %d %d\n", test_case, maxList.get(0).roomNum, maxList.get(0).count));
		}
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
}
