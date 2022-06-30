/*
 * SWEA 03289. 서로소 집합
 */

package difficulty04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_03289_서로소집합 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty04/input_03289.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 초기 집합의 개수 (1부터 n이 각각의 집합을 이루고 있음)
			int M = Integer.parseInt(st.nextToken()); // 연산의 개수
			int[] parents = new int[N+1];
			StringBuilder sb = new StringBuilder();
			
			// 자신의 부모노드를 자신의 값으로 세팅
			for(int i = 1; i < parents.length; i++) {
				parents[i] = i;
			}
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int opt = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				switch(opt) {
				case 0: // 합집합 연산
					union(parents, a, b);
					break;
				case 1: // 포함관계 확인
					if(isInclusive(parents, a, b)) sb.append(1);
					else sb.append(0);
					break;
				}
				
			}

			// 결과 출력
			bw.write(String.format("#%d %s\n", test_case, sb));
			bw.flush();
		}
		br.close();
		bw.close();
	}
	
	// a의 집합(root) 찾기
	public static int findSet(int[] parents, int a) {
		if(a == parents[a]) return a;
		
		return parents[a] = findSet(parents, parents[a]);
	}
	
	// 두 집합 병합
	public static void union(int[] parents, int a, int b) {
		int aRoot = findSet(parents, a);
		int bRoot = findSet(parents, b);
		
		if(aRoot != bRoot) parents[bRoot] = aRoot;
	}
	
	public static boolean isInclusive(int[] parents, int a, int b) {
		int aRoot = findSet(parents, a);
		int bRoot = findSet(parents, b);
		
		if(aRoot == bRoot) return true;
		else return false;
	}
}
