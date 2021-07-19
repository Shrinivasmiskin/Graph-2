class Solution {
    List<List<Integer>> graph;
    List<List<Integer>> result;
    int time;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph = new ArrayList<>();
        result = new ArrayList<>();
        int [] discover = new int [n];
        int [] low = new int [n];
        Arrays.fill(discover, -1);
        buildGraph(connections, n);
        dfs(0, 0, discover, low);
        return result;
    }
    
    private void buildGraph(List<List<Integer>> connections, int n){
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        
        for(List<Integer> edge: connections){
            int in = edge.get(0);
            int out = edge.get(1);
            graph.get(in).add(out);
            graph.get(out).add(in);
        }
    }
    
    private void dfs(int v, int u, int [] discover, int[] low){
        if(discover[v] != -1) return;
        discover[v] = time;
        low[v] = time;
        time++;
        for(int n: graph.get(v)){
            if(n == u) continue;
            dfs(n, v, discover, low);
            if(low[n] > discover[v]){
                result.add(Arrays.asList(n, v));
            }
            low[v] = Math.min(low[v], low[n]);
        }
    }
}
