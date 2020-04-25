package algorithms.structure.graph.undirected;

import algorithms.structure.collection.bag.Bag;

import java.io.IOException;
import java.io.InputStream;

public class Graph {
    //顶点数目
    private final int V;
    //边的数目
    private int E;
    //邻接表
    private Bag<Integer>[] adj;
    //构造函数:创建一个含有V个顶点但不含有边的图
    public Graph(int V){
        this.V = V;
        this.E = 0;
        //创建邻接表
        adj = (Bag<Integer>[]) new Bag[V];
        //将所有链表初始化为空
        for(int v = 0; v < V; v++){
            adj[v] = new Bag<Integer>();
        }
    }
    //构造函数:从标准输入流读入一个图（注意此处InputStream.read返回的整数范围是-1~255）
    public Graph(InputStream in) throws IOException {
        //读取V并将图初始化
        this(in.read());
        //读取E
        int E = in.read();
        for(int i = 0; i < E; i++){
            //添加一条边
            //读取一个顶点
            int v = in.read();
            //读取另一个顶点
            int w = in.read();
            //添加一条连接它们的边
            addEdge(v, w);
        }
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }
    public void addEdge(int v, int w){
        //将w添加到v的链表中
        adj[v].add(w);
        //将v添加到w的链表中
        adj[w].add(v);
        E++;
    }
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
    //图的邻接表的字符串表示
    public String toString(){
        String s = V + " vertices, " + E + " edges\n";
        for(int v = 0; v < V; v++){
            s += v + ": ";
            for(int w : this.adj(v)){
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }
}
