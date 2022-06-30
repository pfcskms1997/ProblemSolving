/*
 * SWEA 09229. 한빈이와 Spot Mart
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_09229_한빈이와SpotMart {

	public static int N;
	public static int M;
	public static int maxWeight;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("./input/difficulty03/input_09229.txt")));
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 과자 봉지의 개수
			M = Integer.parseInt(st.nextToken()); // 제한 무게
			int[] snack = new int[N];
			maxWeight = -1;
			
			// 1차원 배열에 과자의 무게 입력
			StringTokenizer st1 = new StringTokenizer(in.readLine(), " ");
			for(int i = 0; i < N; i++) {
				snack[i] = Integer.parseInt(st1.nextToken());
			}
			
			// 과자 2봉지의 조합으로 해결 가능
			bestCombination(snack, new int[2], 0, 0);
			
			System.out.printf("#%d %d\n", test_case, maxWeight);
		}
		in.close();
	}
	
	// 과자 두 봉지의 조합을 구하는 함수
	public static void bestCombination(int[] snack, int[] sel, int cnt, int start) {
		// 무조건 두 봉지를 골라야 함
		if(cnt == sel.length) {
			int weightSum = sel[0] + sel[1];
			if(weightSum > M) weightSum = -1; // 들고 갈 수 있는 2개의 조합이 없으면 -1을 출력해야 함
			maxWeight = Math.max(maxWeight, weightSum);
			return;
		}
		
		for(int i = start; i < snack.length; i++) {
			sel[cnt] = snack[i];
			bestCombination(snack, sel, cnt+1, i+1);
		}
	}
}
