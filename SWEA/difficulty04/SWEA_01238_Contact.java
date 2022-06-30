/*
 * SWEA 01238. Contact
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

public class SWEA_01238_Contact {

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
		System.setIn(new FileInputStream("./input/difficulty04/input_01238.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		// TestCase input 여부에 따라 변경
		int T = 10;
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int branch = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			// 인접리스트 기반의 그래프 생성
			Node[] graph = new Node[101];
			int maxVertex = 0; // 그래프를 효율적으로 탐색하기 위해 가장 큰 번호의 정점을 찾아서 저장
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0 ; i < branch / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				maxVertex = Math.max(maxVertex, Math.max(from, to));
				
				graph[from] = new Node(to, graph[from]);
			}
			
			// 그래프 출력
//			for(int i = 1; i < maxVertex; i++) {
//				if(graph[i] == null) {
//					System.out.println(i);
//					continue;
//				}
//				System.out.println(i + " " + graph[i].toString());
//			}
			
			int maxVal = BFS(graph, start, maxVertex);
			
			sb.append(String.format("#%d %d\n", test_case, maxVal));
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static int BFS(Node[] graph, int start, int size) {
		Queue<Integer> q = new LinkedList<Integer>();
		int[] visited = new int[size + 1];
		int cnt = 1;
		int depth = 0;
		int maxVal = 0;

		// 시작 노드
		q.offer(start);
		visited[start] = cnt++;
		
		// BFS 탐색
		while(!q.isEmpty()) {
			int qSize = q.size();
			while(--qSize >= 0) {
				int cur = q.poll();
				
				for(Node temp = graph[cur]; temp != null; temp = temp.link) {
					if(visited[temp.vertex] == 0) {
						q.offer(temp.vertex);
						visited[temp.vertex] = cnt;
						depth = cnt;
					}
				}
			}
			cnt++;
		}
		
		// 방문배열의 값이 그래프의 depth와 같은 경우만 뽑아서, 그 중에 가장 큰 인덱스 값을 찾음
		for(int i = 1; i < visited.length; i++) {
			if(visited[i] == depth) {
				maxVal = Math.max(maxVal, i);
			}
		}
		return maxVal;
	}
}
