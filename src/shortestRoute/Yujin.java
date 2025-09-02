package shortestRoute;

import java.io.*;
import java.util.*;

// 방향그래프 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램
// 단, 모든 간선의 가중치는 10 이하의 자연수
// V: 정점 개수, E: 간선 개수, 정점 번호: 1~V
// K: 시작 정점 번호
// ~E (u, v, w) u에서 v로 가는 가중치 w
// (u와 v는 서로 다르며 w는 10 이하의 자연수)
// 1 <= V <= 20,000, 1 <= K <= V

public class Yujin {
	static final int INF = Integer.MAX_VALUE;
	static int V, E, K; // 정점 수, 간선 수, 시작점
	
	// 그래프의 간선 정보 저장
	static class Edge {
		int v, w; // 목적지 정점 번호, 가중치
		
		Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	
	// 다익스트라 알고리즘: 시작점 start에서 모든 정점까지의 최단거리 계산
	static int[] dijkstra(List<List<Edge>> adj, int start) {
		int[] dist = new int[V+1]; // 최단 거리 배열
		Arrays.fill(dist, INF); // 초기값 세팅
		dist[start] = 0; // 시작점은 거리 0
		
		// 우선순위 큐 [현재까지의 거리, 정점 번호] 저장, 거리가 작은 순서로 정렬
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
		pq.offer(new int[] {0, start});
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int curDist = cur[0];
			int node = cur[1];
			
			// 이미 처리된 노드면 패스
			if (curDist > dist[node]) continue;
			
			// 헌재 노드와 연결된 모든 인접 간선 확인
			for (Edge e : adj.get(node)) {
				int nextNode = e.v;
				int weight = e.w;
				
				// 현재 경로를 거쳐서 nextNode로 가는 비용 계산
				if (dist[nextNode] > dist[node] + weight) {
					dist[nextNode] = dist[node] + weight; // 최단거리 갱신
					pq.offer(new int[] {dist[nextNode], nextNode}); // 큐에 추가
				}
			}
		}
		return dist;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		List<List<Edge>> adj = new ArrayList<>(V+1);
		for (int i = 0; i <= V; i++) {
			adj.add(new ArrayList<>());
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj.get(u).add(new Edge(v, w));
		}
		
		// 다익스트라 실행
		int[] dist = dijkstra(adj, K);
		
		// 결과 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if (dist[i] == INF) sb.append("INF\n");
			else sb.append(dist[i]).append("\n");
		}
		System.out.print(sb);
	}
}
