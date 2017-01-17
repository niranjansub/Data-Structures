import java.util.*;
import java.io.*;

class ArticulationPoints{
  private int Vertices;
  private LinkedList<Integer> adj[];
  int time = 0 ;
  int NIL = -1;
  ArticulationPoints(int v){
    Vertices = v;
    adj = new LinkedList[v];
    for(int i = 0 ; i < Vertices ; i++){
      adj[i] = new LinkedList();
    }
  }

  void APUtil(int v,boolean visited[],int parent[],boolean ap[],int disc[],int low[]){
    int children = 0;
    visited[v] = true;
    disc[v]=low[v]=time++;
    Iterator<Integer> it = adj[v].iterator();
    while(it.hasNext()){
      int n = it.next();
      if(!visited[n]){
        children++;
        parent[n] = v;
        APUtil(n,visited,parent,ap,disc,low);
        low[v] = Math.min(low[v],low[n]);

        if(parent[v] == NIL && children > 1){
          ap[v] = true;
        }

        if(parent[v] != NIL && low[n] >= disc[v]){
          ap[v] = true;
        }
      }
      else if(v != parent[n]){
        low[v] = Math.min(low[v] , disc[n]);
      }
    }
  }

  void AP(){
    boolean visited[] = new boolean[Vertices];
    int parent[] = new int[Vertices];
    int disc[] = new int[Vertices];
    int low[] = new int[Vertices];
    boolean ap[] = new boolean[Vertices];

    for(int i = 0 ; i < Vertices ; i++){
      visited[i] = false;
      ap[i] = false;
      parent[i]  = NIL;
    }

    for(int i = 0 ; i < Vertices ; i++){
      if(!visited[i]){
        APUtil(i,visited,parent,ap,disc,low);
      }
    }

    for(int i = 0 ; i < Vertices ; i++){
      if(ap[i] == true){
        System.out.println(i+" ");
      }
    }
  }

  public void addEdge(int start , int end){
    adj[start].add(end);
    adj[end].add(start);
  }

  public static void main(String[] args) {
    ArticulationPoints g = new ArticulationPoints(5);
    g.addEdge(1,0);
    g.addEdge(0,2);
    g.addEdge(2,1);
    g.addEdge(0,3);
    g.addEdge(3,4);

    g.AP();
  }
}
