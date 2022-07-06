/*
 * BAEKJOON 01753. 최단경로
 * Hint: Dijkstra Algorithm
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_01753_최단경로 {
	
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
		System.setIn(new FileInputStream("./input/input_01753.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수(1부터 V)
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수
		int K = Integer.parseInt(br.readLine()); // 시작 정점의 번호
		
		// 인접리스트 생성
		Node[] graph = new Node[V+1]; // 1부터 V까지 인덱싱
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			// u -> v로 가는 간선(유향 그래프)
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken()); // 가중치
			
			graph[u] = new Node(v, graph[u], w);
		}
		
		// printList(graph);
		
		// Dijkstra Algorithm
		int[] distance = new int[V+1];
		boolean[] visited = new boolean[V+1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[K] = 0; // 시작점의 거리를 0으로 세팅
		
		for(int i = 0; i < graph.length; i++) {
			int minDistance = Integer.MAX_VALUE;
			int current = 0;
			
			// 각 정점으로 가는 최소 비용을 저장한 배열에서 최소 비용과 그에 해당하는 정점을 찾음
			for(int j = 0; j < distance.length; j++) {
				if(!visited[j] && distance[j] < minDistance) {
					minDistance = distance[j];
					current = j;
				}
			}
			
			visited[current] = true;
			
			// 현재 정점의 리스트를 방문하며 최소 비용 갱신
			for(Node temp = graph[current]; temp != null; temp = temp.link) {
				if(!visited[temp.vertex] && distance[temp.vertex] > distance[current] + temp.weight) {
					distance[temp.vertex] = distance[current] + temp.weight;
				}
			}
		}
		
		// 결과 출력 및 프로그램 종료
		for(int i = 1; i < distance.length; i++) {
			sb.append(distance[i] != Integer.MAX_VALUE ? distance[i] : "INF").append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void printList(Node[] list) {
		for(int i = 1; i < list.length; i++) {
			if(list[i] == null) {
				System.out.println(i + " NULL");
				continue;
			}
			System.out.println(i + " " + list[i].toString());
		}
	}
}
