/*
 * BAEKJOON 01786. 찾기
 * Hint: KMP(Knuth-Morris-Pratt) Algorithm
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_01786_찾기 {
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_01786.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			char[] text = br.readLine().toCharArray(); // 텍스트 문자열
			char[] pattern = br.readLine().toCharArray(); // 패턴 문자열
			
			ArrayList<Integer> result = KMP(text, pattern);

			// 출력 포맷 설정
			sb.append(String.format("#%d \n", test_case));
			sb.append(result.size() + "\n");
			for(Integer idx : result) {
				sb.append(idx + " ");
			}
			sb.append("\n");
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	// 패턴의 접두사와 접미사가 일치하는 최대 길이를 저장하는 테이블을 int[]로 반환하는 함수
	public static int[] makeTable(char[] pattern) {
		int patternSize = pattern.length;
		int[] table = new int[patternSize];
		
		// i는 1부터, j는 0부터 시작
		for(int i = 1, j = 0; i < patternSize; i++) {
			// 불일치하는 구간을 발견하면 바로 직전 j의 테이블에 있는 값을 j로 재설정
			while(j > 0 && pattern[i] != pattern[j]) j = table[j - 1];
			
			// 일치하면 현재 인덱싱하고 있는 테이블의 위치에 j+1 값을 할당
			if(pattern[i] == pattern[j]) table[i] = ++j;
			else table[i] = 0;
		}
		// System.out.println(Arrays.toString(table));
		return table;
	}
	
	// text 내에서 찾은 pattern의 위치(인덱스 번호를 1부터 함)를 list로 반환하는 함수
	public static ArrayList<Integer> KMP(char[] text, char[] pattern) {
		int[] table = makeTable(pattern);
		int textSize = text.length;
		int patternSize = pattern.length;
		ArrayList<Integer> list = new ArrayList<Integer>(); // 패턴이 일치하는 인덱스를 저장할 리스트
		
		// i: 텍스트 포인터, j: 패턴 포인터
		for(int i = 0, j = 0; i < textSize; i++) {
			// 탐색 도중 일치하지 않는 경우가 발생하면 현재 패턴 포인터(j)에서 1을 뺀 값이 가리키고 있는 테이블의 값을 j에 할당함
			while(j > 0 && text[i] != pattern[j]) j = table[j - 1];
			
			if(text[i] == pattern[j]) { // 두 글자가 일치하는 경우
				if(j == patternSize - 1) { // j가 패턴의 마지막 인덱스라면
					list.add(i - patternSize + 2);
					j = table[j]; // 다음 일치하는 문자열을 찾기 위해 테이블의 값만큼 점프
				}
				else j++;
			}
		}
		
		return list;
	}
}
