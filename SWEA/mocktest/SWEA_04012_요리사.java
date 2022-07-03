/*
 * SWEA 04012. 요리사
 */

package mocktest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_04012_요리사 {

	static int minVal;
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/mocktest/input_04012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 식재료의 수
			int[][] synergy = new int[N+1][N+1];
			minVal = 987654321;
			
			for(int r = 1; r < synergy.length; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int c = 1; c < synergy[r].length; c++) {
					synergy[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// (n)C(n/2) 조합으로 재료를 선택하여 문제 풀이
			combination(synergy, new int[N/2], 0, 1, 0);
			
			sb.append(String.format("#%d %d\n", test_case, minVal));
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	// 기본 조합에서는 flag가 필요하지 않지만, 이 문제에서는 뽑히지 않은 재료(foodB)를 골라내기 위해 flag를 사용
	public static void combination(int[][] synergy, int[] sel, int cnt, int start, int flag) {
		if(cnt == sel.length) {
			int[] insel = new int[sel.length]; // 선택되지 않은 재료의 번호(즉, B음식의 재료)
			int idx = 0;
			int foodA = 0;
			int foodB = 0;
			
			for(int i = 1; i < synergy.length; i++) {
				if((flag & 1 << i) != 0) continue;
				
				insel[idx++] = i;
			}
			
			// foodA와 foodB의 시너지 합 계산
			for(int i = 0; i < sel.length; i++) {
				for(int j = i; j < sel.length; j++) {
					foodA += synergy[sel[i]][sel[j]] + synergy[sel[j]][sel[i]];
					foodB += synergy[insel[i]][insel[j]] + synergy[insel[j]][insel[i]];
				}
			}
			
			minVal = Math.min(minVal, Math.abs(foodA - foodB));
			return;
		}
		
		for(int i = start; i < synergy.length; i++) {
			sel[cnt] = i;
			combination(synergy, sel, cnt+1, i+1, flag | 1 << i);
		}
	}
	
	public static void printMatrix(int[][] matrix) {
		for(int i = 1; i < matrix.length; i++) {
			for(int j = 1; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
