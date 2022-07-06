/*
 * BAEKJOON 03040. 백설 공주와 일곱 난쟁이
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_03040_백설공주와일곱난쟁이 {

	static int[] dwarf;
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_03040.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = 9;
			int[] number = new int[N];
			dwarf = new int[7]; // 진짜 난쟁이가 가지고 있는 번호
			
			// 9명의 난쟁이의 번호를 1차원 배열에 입력
			for(int i = 0; i < N; i++) {
				number[i] = Integer.parseInt(br.readLine());
			}
			
			// 9명의 난쟁이 중 7명을 뽑는 경우의 수 조합
			combination(number, new int[7], 0, 0);
			
			for(int i = 0; i < dwarf.length; i++) {
				sb.append(dwarf[i] + "\n");
			}
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	
	public static void combination(int[] number, int[] sel, int cnt, int start) {
		// Base Part
		if(cnt == sel.length) {
			int sum = 0;
			
			// 7난쟁이 조합이 가지고 있는 번호의 합 계산
			for(int i = 0; i < sel.length; i++) {
				sum += sel[i];
			}
			
			// 7난쟁이 조합이 가지고 있는 번호의 합이 100이면, 모두 진짜 난쟁이이므로 dwarf 배열에 번호를 기록
			if(sum == 100) {
				for(int i = 0; i < sel.length; i++) {
					dwarf[i] = sel[i];
				}
			}
			return;
		}
		
		// Inductive Part
		for(int i = start; i < number.length; i++) {
			sel[cnt] = number[i];
			combination(number, sel, cnt+1, i+1);
		}
	}
}
