/*
 * BAEKJOON 01197. 최소 스패닝 트리
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_01197_최소스패닝트리_Kruskal_인접리스트 {
	
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
		
		// 인접리스트 생성
		Node[] adjList = new Node[V+1];
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Node(to, adjList[from], weight);
			adjList[to] = new Node(from, adjList[to], weight);
		}
		
		printList(adjList);
		
		// Union-Find
		boolean[] visited = new boolean[V+1];
		int[] parents = new int[V+1];
		makeSet(parents);

		int weightSum = 0;
		int selEdgeCnt = 0;

		for(int cnt = 0; cnt < E; cnt++) {
			int minWeight = Integer.MAX_VALUE;
			int edgeFrom = 0;
			int edgeTo = 0;

			// 가중치가 가장 작은 좌표의 정보를 읽음
			for(int i = 1; i < adjList.length; i++) {
				for(Node temp = adjList[i]; temp != null; temp = temp.link) {
					if(!visited[temp.vertex] && minWeight > temp.weight) {
						minWeight = temp.weight;
						edgeFrom = i;
						edgeTo = temp.vertex;
					}
				}
			}
			System.out.printf("%d %d %d\n", edgeFrom, edgeTo, minWeight);
			
			visited[edgeTo] = true;
			
			if(union(parents, edgeFrom, edgeTo)) {

				weightSum += minWeight;
				if(++selEdgeCnt == V - 1) break;
			}
		}
		
		System.out.println(weightSum);
		br.close();
	}
	
	public static void makeSet(int[] parents) {
		for(int i = 1; i < parents.length; i++) {
			parents[i] = i;
		}
	}
	
	public static int findSet(int[] parents, int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents, parents[a]);
	}
	
	public static boolean union(int[] parents, int a, int b) {
		int aRoot = findSet(parents, a);
		int bRoot = findSet(parents, b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void printList(Node[] list) {
		for(int i = 1; i < list.length; i++) {
			System.out.println(i + " " + list[i].toString());
		}
	}
}
