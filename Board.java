import java.util.Scanner;

import javax.naming.ldap.ManageReferralControl;

public class Board {
    private int[][] board;
    private int moves;
    
    public Board(int[][] blocks){
        board = new int[blocks.length][blocks[0].length];
        for(int i=0; i<blocks.length; ++i){
            for(int e=0; e<blocks[i].length; ++e){
                board[i][e] = blocks[i][e]; // empty space is 0 
            } 
        } 
    }           // construct a board from an N-by-N array of blocks
                                           // (where blocks[i][j] = block in row i, column j)
    public int size() {
        return board.length;
    }                      // board size N
    public int hamming() {
        // number of blocks out of place
        int incorrect = 0;
        for(int i = 1; i <= this.size(); ++i) {
            for(int j = 1; j <= this.size(); ++j) {
                if(board[i][j] != i*j) ++incorrect;
            }
        }
        return incorrect + this.moves;
    }
    
    public int manhattan() {
        int manhat_dist = 0;
        for(int row=0; row<this.size(); ++row){
            for(int col=0; col<this.size(); ++col){
                if(board[row][col] != 0){
                    int proper_row = board[row][col] / this.size();
                    int proper_col = (board[row][col] % this.size()) - 1;
                    
                    if(board[row][col] == this.size()){
                        proper_row--;
                        proper_col += this.size();
                    }
                    System.out.println("Moving " + board[row][col] + " by " + (Math.abs(row - proper_row) + Math.abs(col - proper_col)));
                    
                    manhat_dist += Math.abs(row - proper_row) + Math.abs(col - proper_col);
                }
            }
        } 
        System.out.println("Dist: " + manhat_dist);
        return manhat_dist;
    }                 // sum of Manhattan distances between blocks and goal
    public boolean isGoal() {
        return false;
    }                // is this board the goal board?
    public boolean isSolvable(){
        return false;
    }            // is this board solvable?
    public boolean equals(Object y) {
        return false;
    }        // does this board equal y?
    public Iterable<Board> neighbors() {
        return null;
    }     // all neighboring boards
    public String toString() {
        return null;
    }               // string representation of this board (in the output format specified below)

    public static void main(String[] args) {

        // create initial board from file
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.nextInt();
        Board initial = new Board(blocks);

        // check if puzzle is solvable; if so, solve it and output solution
        if (initial.isSolvable()) {
            Solver solver = new Solver(initial);
            System.out.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()){
                System.out.println(board);
            }
        }
    
        System.out.println(initial.manhattan());
    }
}
