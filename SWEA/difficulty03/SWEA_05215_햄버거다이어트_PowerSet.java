/*
 * SWEA 05215. 햄버거 다이어트
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_05215_햄버거다이어트_PowerSet {
	
	static int N; // 재료의 수
	static int L; // 제한 칼로리

	static int[] score;
	static int[] cal;
	
	static int maxScore;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty03/input_05215.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			score = new int[N];
			cal = new int[N];
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
				score[i] = Integer.parseInt(st1.nextToken());
				cal[i] = Integer.parseInt(st1.nextToken());
			}
			
			// 햄버거 재료의 모든 조합을 고려하는 경우(부분집합)
			bestPowerSet(score, cal, new boolean[N], 0);
			
			//System.out.printf("#%d %d\n", test_case, maxScore);
			sb.append(String.format("#%d %d\n", test_case, maxScore));
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void bestPowerSet(int[] score, int[] cal, boolean[] isSelected, int cnt) {
		if(cnt == isSelected.length) {
			int scoreSum = 0;
			int calSum = 0;
			for(int i = 0; i < isSelected.length; i++) {
				if(isSelected[i]) {
					scoreSum += score[i];
					calSum += cal[i];
				}
			}

			if(calSum <= L) maxScore = Math.max(maxScore, scoreSum);
			
			return;
		}
		
		// Inductive Part
		isSelected[cnt] = true;
		bestPowerSet(score, cal, isSelected, cnt+1);
		isSelected[cnt] = false;
		bestPowerSet(score, cal, isSelected, cnt+1);
	}
}
