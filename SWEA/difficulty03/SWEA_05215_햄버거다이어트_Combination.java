/*
 * SWEA 05215. 햄버거 다이어트
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_05215_햄버거다이어트_Combination {
	
	static class BestResult implements Comparable<BestResult> {
		int maxScore;
		int maxCalorie;
		
		public BestResult(int maxScore, int maxCalorie) {
			this.maxScore = maxScore;
			this.maxCalorie = maxCalorie;
		}
		
		@Override
		public int compareTo(BestResult o) {
			return o.maxScore - this.maxScore;
		}
	}

	static BestResult best;
	
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
			
			best = new BestResult(0, 0);
			
			// 1부터 N개 만큼의 선택지를 조합하는 것을 고려
			for(int i = 1; i <= N; i++) {
				bestCombination(new int[i], 0, 0);
			}
			
			//System.out.printf("#%d %d\n", test_case, best.maxScore);
			sb.append(String.format("#%d %d\n", test_case, best.maxScore));
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void bestCombination(int[] sel, int cnt, int start) {
		if(cnt == sel.length) {
			int calSum = 0;
			int scoreSum = 0;
			// System.out.println(Arrays.toString(sel));
			
			for(int i = 0; i < sel.length; i++) {
				scoreSum += score[sel[i]];
				calSum += cal[sel[i]];
			}
			
			// 제한 칼로리 이하인 경우에만 수행
			if(calSum <= L) {
				BestResult tmp = new BestResult(scoreSum, calSum);
				
				if(best.compareTo(tmp) > 0) {
					best = tmp;
				}
			}
			return;
		}
		
		// Inductive Part
		for(int i = start; i < N; i++) {
			sel[cnt] = i;
			bestCombination(sel, cnt+1, i+1);
		}
	}
}
