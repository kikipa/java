package algorithms.structure.graph.undirected;

import algorithms.structure.collection.queue.Queue;
import algorithms.structure.collection.stack.Stack;

public class BreadFirstPaths {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    private Queue<Integer> queue;

    public BreadFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        queue.enqueue(s);
        bfs(G);
    }

    private void bfs(Graph G){
        do{
            int v = queue.dequeue();
            marked[v] = true;
            for(int w : G.adj(v)){
                if(!marked[w]){
                    edgeTo[w] = v;
                    queue.enqueue(w);
                }
            }
        }while(queue.isEmpty());
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)){
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        for(int x = v; x != s; x = edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }

}
