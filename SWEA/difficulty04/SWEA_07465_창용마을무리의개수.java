/*
 * SWEA 07465. 창용 마을 무리의 개수
 */

package difficulty04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_07465_창용마을무리의개수 {

	static class Node {
		int vertex;
		Node link;
		
		public Node(int vertex, Node link) {
			this.vertex = vertex;
			this.link = link;
		}
		
		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", link=" + link + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty04/input_07465.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 마을의 사람 수
			int M = Integer.parseInt(st.nextToken()); // 관계의 수
			Node[] graph = new Node[N+1];
			
			// 인접 리스트 입력(관계 설정)
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); // 첫 번째 사람
				int b = Integer.parseInt(st.nextToken()); // 두 번째 사람
				
				graph[a] = new Node(b, graph[a]);
				graph[b] = new Node(a, graph[b]);
			}
			
			//System.out.println("#" + test_case);
			//printGraph(graph);
			
			int group = findGroup(graph, new boolean[N+1]);
			
			sb.append(String.format("#%d %d\n", test_case, group));
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	// 인접 리스트를 BFS 방식으로 탐색하여 무리의 수를 셈
	public static int findGroup(Node[] graph, boolean[] v) {
		int count = 0;
		
		for(int i = 1; i < graph.length; i++) {
			Queue<Integer> q = new LinkedList<Integer>();
			
			// 시작 노드
			q.offer(i);
			if(!v[i]) count++;
			
			while(!q.isEmpty()) {
				int p = q.poll();
				v[p] = true;
				
				for(Node temp = graph[p]; temp != null; temp = temp.link) {
					if(!v[temp.vertex]) {
						q.offer(temp.vertex);
					}
				}
			}
		}
		return count;
	}
	
	public static void printGraph(Node[] graph) {
		for(int i = 1; i < graph.length; i++) {
			System.out.println(i + " " + graph[i].toString());
		}
	}
}
