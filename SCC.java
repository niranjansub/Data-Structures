//PROGRAM TO FIND STRONGLY CONNECTED COMPONENTS USING (TARJAN'S ALGORITHM)

import java.util.*;
import java.io.*;

public class SCC{
  private int Vertices;
  private LinkedList<Integer> adj[];
  private int time = 0;
  private int NIL = -1;
  SCC(int v){
    Vertices = v;
    adj = new LinkedList[v];
    for(int i = 0 ; i < Vertices ; i++){
      adj[i] = new LinkedList();
    }
  }
  void addEdge(int start , int end){
    adj[start].add(end);
  }
  void SCCUtil(int v , int disc[] , int low[] , boolean stackMember[] , Stack theStack , boolean visited[]){
    visited[v] = true;
    disc[v] = low[v] = time++;
    stackMember[v] = true;
    theStack.push(v);
    Iterator<Integer> it = adj[v].iterator();
    while(it.hasNext()){
      int n = it.next();
      if(!visited[n]){
        SCCUtil(n,disc,low,stackMember,theStack,visited);
        low[v] = Math.min(low[v],low[n]);
      }
      else if (stackMember[v] == true){
        low[v] = Math.min(low[n],disc[v]);
      }
    }
    int w = 0 ;
    w = (int)theStack.peek();
    while(w != v){
      w = (int)theStack.peek();
      System.out.print(w+" ");
      theStack.pop();
    }
    w = (int)theStack.peek();
    System.out.println(w);
    theStack.pop();
  }

  void SCC(){
    int disc[] = new int[Vertices];
    int low[] = new int[Vertices];
    boolean stackMember[] = new boolean[Vertices];
    boolean visited[] = new boolean[Vertices];
    Stack theStack = new Stack();

    for(int i = 0 ; i < Vertices ; i++){
      low[i] = NIL;
      disc[i] = NIL;
      stackMember[i] = false;
      visited[i] = false;
    }

    for(int i = 0 ; i < Vertices ; i++){
      if(!visited[i]){
        SCCUtil(i,disc,low,stackMember,theStack,visited);
      }
    }
  }
  public static void main(String[] args) {
    SCC g = new SCC(7);
    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(2, 0);
    g.addEdge(1, 3);
    g.addEdge(1, 4);
    g.addEdge(1, 6);
    g.addEdge(3, 5);
    g.addEdge(4, 5);
    g.SCC();
  }
}
