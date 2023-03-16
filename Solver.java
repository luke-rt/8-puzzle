import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

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
    
    
    private MinPQ<Node> pq;
    private Node initNode;
    public Solver(Board initial){
        pq = new MinPQ<Node>();
        initNode = new Node(initial, 0);
    }           // find a solution to the initial board (using the A* algorithm)

    public int moves(){
        
        return 0;

    }                     // min number of moves to solve initial board
    
    public void helper(Node node) {
        pq.insert(node);
        for(Board b : node.getBoard().neighbors()){
            pq.insert(new Node(b, node.getMoves()+1));
        }
        
        Node min = pq.delMin();
        System.out.println(min.getPriority());
    }

    public Iterable<Board> solution(){
        ArrayList<Board> empty = new ArrayList<>();
        helper(initNode);
        return empty;
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
        
        s.solution();
    } // solve a slider puzzle (given below) 
}
