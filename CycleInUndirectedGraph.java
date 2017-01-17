import java.util.*;
import java.io.*;

public class CycleInUndirectedGraph{
  private int Vertices;
  private LinkedList<Integer> adj[];
  CycleInUndirectedGraph(int v){
    Vertices = v;
    adj = new LinkedList[v];
    for(int i = 0 ; i < v ; i++){
      adj[i] = new LinkedList();
    }
  }

  void addEdge(int start , int end){
    adj[start].add(end);
    adj[end].add(start);
  }

  public boolean isCycleUtil(int v , boolean visited[] , int parent){
    visited[v] = true;
    Iterator<Integer> it = adj[v].iterator();
    while(it.hasNext()){
      Integer n = it.next();
      if(!visited[n]){
        if(isCycleUtil(n,visited,v)){
          return true;
        }
      }
      else if(n != parent){
        return true;
      }
    }
    return false;
  }

  public boolean isCycle(){
    boolean visited[] = new boolean[Vertices];
    for(int i = 0 ; i < Vertices ; i++){
      visited[i] = false;
    }
    for(int i = 0 ; i < Vertices ; i++){
      if(!visited[i]){
        if(isCycleUtil(i,visited,-1)){
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {

    CycleInUndirectedGraph g = new CycleInUndirectedGraph(5);
    g.addEdge(1,0);
    g.addEdge(0,2);
    g.addEdge(2,0);
    g.addEdge(0,3);
    g.addEdge(3,4);

    if(g.isCycle()){
      System.out.println("Graph contains cycle");
    }
    else{
      System.out.println("Graph does not contains cycle");
    }
  }
}
