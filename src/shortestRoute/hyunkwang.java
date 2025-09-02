package shortestRoute;
import java.io.*;
import java.util.*;

// 인접리스트 그래프 구현
// 최단 거리 배열 초기화
// 값이 가장 작은 노드 고르기
// 최단 거리 배열 업데이터

public class hyunkwang {
	static final int INF = Integer.MAX_VALUE;
	static int N, M;
	static int[] dist;
	
	static class Edge{
		int num, cost;
		Edge(int num, int cost){
			this.num = num;
			this.cost = cost;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		
		List<List<Edge>> adjList = new ArrayList<>(N+1); 
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to =  Integer.parseInt(st.nextToken());
			int cost =  Integer.parseInt(st.nextToken());
			adjList.get(from).add(new Edge(to, cost));
		}
		
		dist = dijkstra(N, adjList, K);
		
		for (int i = 1; i <= N; i++) {
			if(dist[i] >= INF) {
				bw.write("INF");
			}else {
				bw.write(Integer.toString(dist[i]));
			}
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static int[] dijkstra(int N, List<List<Edge>> adjList, int start) {
		int[] dist = new int[N+1];
		
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
		pq.offer(new int[] {0,start});
		
		while (!pq.isEmpty()) {
			int[] entry = pq.poll();
			int currentDist = entry[0];
			int currentNode = entry[1];
			
			if (currentDist != dist[currentNode]) continue;
			
			for (Edge edge : adjList.get(currentNode)) {
				int next = edge.num;
				int  newDist = currentDist + edge.cost;
				if (newDist < dist[next]) {
					dist[next] = newDist;
					pq.offer(new int[] {newDist, next});
					
				}
			}
		}
			return dist;
	}
}
