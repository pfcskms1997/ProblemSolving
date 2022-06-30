/*
 * SWEA 03234. 준환이의 양팔저울
 */

package difficulty04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_03234_준환이의양팔저울 {

	static int count;
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty04/input_03234.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 무게추의 개수
			int[] weight = new int[N];
			count = 0;
			
			// 1차원 배열에 주어진 무게추 입력
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
			
			// 문제 풀이
			solution(weight, new int[N], 0, 0);
			
			sb.append(String.format("#%d %d\n", test_case, count));
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
	
	public static void solution(int[] weight, int[] sel, int cnt, int flag) {
		// 추의 순열(nPn)을 구하는 함수 호출
		permutation(weight, sel, 0, 0);
	}
	
	public static void permutation(int[] weight, int[] sel, int cnt, int flag) {
		if(cnt == sel.length) {
			// 구해진 추의 순열로 powerSet을 구함
			powerSet(sel, 0, 0, 0, 0);
			return;
		}
		
		for(int i = 0; i < weight.length; i++) {
			if((flag & 1 << i) != 0) continue;
			
			sel[cnt] = weight[i];
			permutation(weight, sel, cnt+1, flag | 1 << i);
		}
	}
	
	public static void powerSet(int[] weight, int cnt, int flag, int leftSum, int rightSum) {
		if(cnt == weight.length) {			
			count++;
			return;
		}
		
		// 왼쪽 저울의 무게가 오른쪽보다 작으면 재귀호출이 의미가 없으므로 조건에 맞을 때에만 비선택 재귀를 수행해야 함
		powerSet(weight, cnt+1, flag | 1 << cnt, leftSum + weight[cnt], rightSum); // 선택
		if(leftSum >= rightSum + weight[cnt]) powerSet(weight, cnt+1, flag, leftSum, rightSum + weight[cnt]); // 비선택
	}
}
