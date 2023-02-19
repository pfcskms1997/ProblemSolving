/*
 * BAEKJOON 01197. 최소 스패닝 트리
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_01197_최소스패닝트리_Prim {
	
	static class Node {
		int vertex;
		Node link;
		int weight;
		
		public Node(int vertex, Node link, int weight) {
			this.vertex = vertex;
			this.link = link;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", link=" + link + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_01197.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수(인덱스는 1부터 V까지)
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수
		
		// 인접 리스트 생성
		Node[] graph = new Node[V+1];
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[from] = new Node(to, graph[from], weight);
			graph[to] = new Node(from, graph[to], weight);
		}
		
		// printGraph(graph);
		
		boolean[] v = new boolean[V+1];
		int[] dist = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[1] = 0; // 출발점의 거리를 0으로 초기화
		
		for(int cnt = 1; cnt < graph.length; cnt++) {
			int min = Integer.MAX_VALUE;
			int minVertex = -1;
			
			// 거리 배열에서 방문하지 않은 최소비용의 정점을 선택
			for(int i = 0; i < dist.length; i++) {
				if(!v[i] && dist[i] < min) {
					min = dist[i];
					minVertex = i;
				}
			}
			
			v[minVertex] = true;
			
			// dist 배열 업데이트
			for(Node temp = graph[minVertex]; temp != null; temp = temp.link) {
				if(!v[temp.vertex] && dist[temp.vertex] > temp.weight) {
					dist[temp.vertex] = temp.weight;
				}
				// System.out.println(Arrays.toString(dist));
			}
		}
		
		int weightSum = 0;
		for(int i = 1; i < dist.length; i++) {
			weightSum += dist[i];
		}
		
		System.out.println(weightSum);
		br.close();
	}
	
	public static void printGraph(Node[] graph) {
		for(int i = 1; i < graph.length; i++) {
			System.out.println(i + " " + graph[i].toString());
		}
	}
}
