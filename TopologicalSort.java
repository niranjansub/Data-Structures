import java.util.*;
import java.io.*;

public class TopologicalSort{
  private int Vertices;
  private LinkedList<Integer> adj[];
  TopologicalSort(int v){
    Vertices = v;
    adj = new LinkedList[v];
    for(int i = 0 ; i < v ; i++){
      adj[i] = new LinkedList();
    }
  }

  public void addEdge(int start , int end){
    adj[start].add(end);
  }

  void TopoSortUtil(int v , boolean visited[] , Stack theStack){
    visited[v] = true;
    Iterator<Integer> i = adj[v].iterator();
    while(i.hasNext()){
      int n = i.next();
      if(!visited[n]){
        TopoSortUtil(n , visited , theStack);
      }
    }
    theStack.push(new Integer(v));
  }

  void TopoSort(){
    Stack theStack = new Stack();
    boolean visited[] = new boolean[Vertices];
    for(int i = 0 ; i < Vertices ; i++){
      visited[i] = false;
    }
    for(int i = 0 ; i < Vertices ; i++){
      if(!visited[i]){
        TopoSortUtil(i , visited , theStack);
      }
    }
    while(!theStack.empty()){
      System.out.println(theStack.pop()+" ");
    }
  }

  public static void main(String[] args) {
    TopologicalSort g = new TopologicalSort(6);
    g.addEdge(5,2);
    g.addEdge(5,0);
    g.addEdge(4,0);
    g.addEdge(4,1);
    g.addEdge(2,3);
    g.addEdge(3,1);

    g.TopoSort();
  }

}
