/*
 * SWEA 02112. 보호 필름
 */

package mocktest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_02112_보호필름 {
	
	static int D, W, K, min;
	static int[][] film;
	static final int A = 0, B = 1;
	static int[] drugA; // drugA: 막 1개에 약품 A의 상태를 표현하는 배열
	static int[] drugB; // drugB: 막 1개에 약품 B의 상태를 표현하는 배열

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/mocktest/input_02112.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken()); // 막 두께
			W = Integer.parseInt(st.nextToken()); // 막 너비
			K = Integer.parseInt(st.nextToken()); // 합격 기준(연속 셀의 개수)
			film = new int[D][W];
			drugA = new int[W];
			drugB = new int[W];
			min = K; // 최소 투약 횟수
			
			// 2차원 배열에 보호 필름 단면의 정보 입력
			for(int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Arrays.fill(drugA, A);
			Arrays.fill(drugB, B);
			
			process(0, 0);
			sb.append(String.format("#%d %d\n", test_case, min));
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	// 각 막에 부분집합으로 약품 비투여, 약품A 투여, 약품B 투여
	private static boolean process(int row, int useCnt) {
		if(row == D) {
			if(check()) { // 성능 검사 
				min = Math.min(min, useCnt);
				return min == 0; // 약품을 하나도 사용하지 않았으면 true, 사용했으면 false
			}
			return false;
		}
		
		if(useCnt >= min) return false; // 기존 임시 최적해의 최소 약품수보다 현재까지 사용한 약품 수가 같거나 크면 의미 없으므로 리턴
		
		int[] backup = film[row];
		
		// 약품 비투여
		if(process(row+1, useCnt)) return true;
		
		// 약품A 투여
		film[row] = drugA;
		if(process(row+1, useCnt+1)) return true;
		
		// 약품B 투여
		film[row] = drugB;
		if(process(row+1, useCnt+1)) return true;
		
		film[row] = backup; // 기존 막의 상태로 되돌려 놓음
		return false;
	}
	
	// 보호필름 성능 검사
	private static boolean check() {
		// 열을 고정 행을 탐색하며 연속된 셀에 같은 특성이 K개 이상인지 검사
		for(int c = 0; c < W; c++) {
			int count = 1;
			int before = film[0][c];
			
			for(int r = 1; r < D; r++) {
				int current = film[r][c];
				if(before == current) {
					if(++count == K) break;
				} else {
					before = current;
					count = 1;
				}
			}
			
			if(count < K) return false;
		}
		return true;
	}
}
