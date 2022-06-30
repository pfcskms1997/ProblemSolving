/*
 * SWEA 06808. 규영이와 인영이의 카드게임
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_06808_규영이와인영이의카드게임 {

	static int[] GY;
	static int[] IY;
	
	// 결과용 변수
	static int winCase;
	static int loseCase;
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty03/input_06808.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = 9; // 카드의 수
			// boolean[] card = new boolean[19];
			int card = 0; // 인영이가 가질 수 있는 카드를 고르기 위해 비트열 이용(규영이가 가지는 카드를 1로 표시)
			GY = new int[N]; // 규영이가 내는 카드의 순서
			IY = new int[N]; // 인영이가 가질 수 있는 카드
			
			winCase = 0;
			loseCase = 0;
			
			// 1차원 배열에 규영이가 낼 카드 번호를 입력
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < GY.length; i++) {
				GY[i] = Integer.parseInt(st.nextToken());
				
				// 규영이가 가지는 카드를 비트열에 1로 표시
				card |= 1 << GY[i];
			}

			// 1차원 배열에 인영이가 낼 수 있는 카드를 저장
			int idx = 0;
			for(int i = 1; i < 19; i++) {
				if((card & 1 << i) != 0) continue;

				IY[idx] = i;
				idx++;
			}
			
			// 순열
			permutation(new int[N], 0, 0);
			
			sb.append(String.format("#%d %d %d\n", test_case, winCase, loseCase));
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	// 인영이가 낼 수 있는 카드의 순열을 구하여 문제 해결
	public static void permutation(int[] sel, int cnt, int flag) {
		if(cnt == sel.length) {
			int scoreGY = 0;
			int scoreIY = 0;
			
			// 각 턴의 점수를 계산
			for(int i = 0; i < sel.length; i++) {
				if(GY[i] > sel[i]) scoreGY += GY[i] + sel[i];
				else scoreIY += GY[i] + sel[i];
			}
			
			// 이기는 경우와 지는 경우의 수를 구함
			if(scoreGY > scoreIY) winCase++;
			else loseCase++;
			
			return;
		}
		
		for(int i = 0; i < IY.length; i++) {
			if((flag & 1 << i) != 0) continue;
			
			sel[cnt] = IY[i];
			permutation(sel, cnt+1, flag | 1 << i);
		}
	}
}
