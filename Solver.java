/*
 * Steve X + Luke T
 * 3.23.23
 * Java 8
 * 
 * 
 * 
 * The problem. The 8-puzzle problemLinks to an external site. is a puzzle invented and popularized by Noyes Palmer Chapman in the 1870s. It is played on a 3-by-3 grid with 8 square blocks labeled 1 through 8 and a blank square. Your goal is to rearrange the blocks so that they are in order, using as few moves as possible. You are permitted to slide blocks horizontally or vertically into the blank square. The following shows a sequence of legal moves from an initial board (left) to the goal board (right).
 */


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import javax.naming.InitialContext;

class Node implements Comparable<Node>{
    private Board b;
    private int moves;
    public Node(Board b, int moves){
        this.b = b;
        this.moves = moves;
    }
    
    public int getMoves(){
        return this.moves;
    }
    public Board getBoard(){
        return this.b;
    }
    public int getPriority(){
        return this.moves + this.b.manhattan();
    }
    public int compareTo(Node a){
        return Integer.compare(this.getPriority(), a.getPriority());
    }
}

public class Solver {
    
    private ArrayList<Node> already_visited;
    private MinPQ<Node> pq;
    private Node initNode;
    private ArrayList<Board> sol_set;
    public Solver(Board initial){
        pq = new MinPQ<Node>();
        initNode = new Node(initial, 0);
        already_visited = new ArrayList<Node>();
        already_visited.add(initNode);
        sol_set = new ArrayList<Board>();
    }           // find a solution to the initial board (using the A* algorithm);

    public int moves(){
        int cnt=0;
        for(Board b : solution()){
            cnt++;
        }
        return cnt;

    }                     // min number of moves to solve initial board
    
    public ArrayList<Board> helper(Node node) {
        pq.insert(node);
        for(Board b : node.getBoard().neighbors()){
            Node neighbor = new Node(b, node.getMoves()+1);
            /* 
            for(Node n : already_visited){
                if(neighbor.getBoard().equals(n.getBoard())) already_ = true;
            }
            */
            ///if(already_ == false){
                pq.insert(neighbor);
                already_visited.add(neighbor);
            //}
        }
        
        Node min = pq.delMin();
        boolean to_add = true;
        for(Board b : sol_set){
            if(b.equals(min.getBoard())) to_add = false;
        }
        if(to_add) sol_set.add(min.getBoard());
        if(min.getBoard().isGoal()){
            return sol_set;
        }
        
        return helper(min);
    }
    
    public void outputHelper(Node min){
        System.out.println(min.getPriority() + " moves: " + min.getMoves());
        Board b = min.getBoard();
        for(int i=0; i<b.getBoard().length; ++i){
            for(int e=0; e<b.getBoard().length; ++e){
                System.out.print(b.getBoard()[i][e]);
            }
            System.out.println();
        }
        System.out.println(pq.size());
    }

    public Iterable<Board> solution(){
        if(!initNode.getBoard().isSolvable()){
            System.out.println("Unsolvable");
            return null;
        }

        return (Iterable)helper(initNode);
    }      // sequence of boards in a shortest solution

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.nextInt();
        Board initial = new Board(blocks);
        Solver s = new Solver(initial);
        System.out.println();
        
        if(s.solution() != null){
            for(Board b : s.solution()) {
                System.out.println(b); 
            }
            System.out.println("Move made: " + s.moves());
        }
    } // solve a slider puzzle (given below) 
}

/*
 3
 0
 1
 3
 4
 2
 5
 7
 8
 6
 */

 
 /*
4
1
2
3
4
5
0
6
8
9
10
7
11
13
14
15
12
  */