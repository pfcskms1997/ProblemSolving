/*
 * SWEA 01974. 스도쿠 검증
 */

package difficulty02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_01974_스도쿠검증 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty02/input_01974.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
				
		for(int test_case = 1; test_case <= T; test_case++) {
			int[][] sudoku = new int[9][9];
			boolean flag = true;
			
			
			// 2차원 배열에 스도쿠 입력
			for(int i = 0; i < sudoku.length; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < sudoku[i].length; j++) {
					sudoku[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// row & column check
			for(int i = 0; i < sudoku.length; i++) {
				if(!flag) break;
				
				int rowFlag = 0;
				int colFlag = 0;
				
				for(int j = 0; j < sudoku.length; j++) {
					// 사용된 숫자에 해당하는 비트열을 1로 교체
					rowFlag |= 1 << sudoku[i][j];
					colFlag |= 1 << sudoku[j][i];
				}
				
				// 모든 숫자가 한 번 씩 사용되었을 때 => 11 1111 1110는 10진수로 1022임
				if(rowFlag != 1022 || colFlag != 1022) flag = false;
			}
		
			// 3*3 square check
			for(int i = 0; i <= 6; i += 3) {

				if(!flag) break;
				
				for (int j = 0; j <= 6; j += 3) {
					int sqrFlag = 0;
					int nr = i + 3;
					int nc = j + 3;
					
					for(int k = i; k < nr; k++) {
						for(int l = j; l < nc; l++) {
							sqrFlag |= 1 << sudoku[k][l];
						}
					}

					if(sqrFlag != 1022) {
						flag = false;
						break;
					}
				}
			}
			
			sb.append("#" + test_case).append(flag ? " 1\n" : " 0\n");
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
