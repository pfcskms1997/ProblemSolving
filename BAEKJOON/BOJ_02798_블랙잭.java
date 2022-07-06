/*
 * BAEKJOON 02798. 블랙잭
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_02798_블랙잭 {

	static int maxSum;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_02798.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 카드 수
			int M = Integer.parseInt(st.nextToken()); // 카드 합의 최대
			int[] card = new int[N];
			maxSum = 0;
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				card[i] = Integer.parseInt(st.nextToken());
			}
			
			// 카드 조합을 계산
			combination(card, new int[3], 0, 0, M);
			
			System.out.println(maxSum);
		}
		br.close();
	}
	
	public static void combination(int[] card, int[] sel, int cnt, int start, int limit) {
		if(cnt == sel.length) {
			//System.out.println(Arrays.toString(sel));
			int sum = 0;
			
			for(int i = 0; i < sel.length; i++) {
				sum += sel[i];
			}
			
			if(sum <= limit) maxSum = Math.max(maxSum, sum);
			return;
		}
		
		for(int i = start; i < card.length; i++) {
			sel[cnt] = card[i];
			combination(card, sel, cnt+1, i+1, limit);
		}
	}
}
