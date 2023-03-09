import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    private int[][] board;
    private int moves;
    
    public Board(int[][] blocks){
        moves = 0;
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
        int prop_place_counter = 1;
        for(int i = 0; i < this.size(); ++i) {
            for(int j = 0; j < this.size(); ++j) {
                if(prop_place_counter == this.size() * this.size()) prop_place_counter = 0;
                if(board[i][j] != prop_place_counter && board[i][j] != 0){
                    ++incorrect;
                } 
                ++prop_place_counter;
            }
        }
        return incorrect;
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
                    manhat_dist += Math.abs(row - proper_row) + Math.abs(col - proper_col);
                }
            }
        } 
        return manhat_dist;
    }                 // sum of Manhattan distances between blocks and goal

    public boolean isGoal() {
        return manhattan() == 0; // if manhattan is 0, then board is proper goal
    }                // is this board the goal board?

    public boolean isSolvable(){
        int inversions = 0;
        if(this.size() % 2 == 1){
            for(int row1=0; row1<this.size(); ++row1){
                for(int col1=0; col1<this.size(); ++col1){
                    for(int row2=0; row2<this.size(); ++row2){
                        for(int col2=0; col2<this.size(); ++col2){
                            if(board[row1][col1] < board[row2][col2] && (row1 > row2 || col1 > col2)){
                                ++inversions;
                            }
                        }
                    }
                }
            }
            
            if(inversions % 2 == 1) return false;
        }else if(this.size() % 2 == 0){
            
        }
        return false;
    }            // is this board solvable?

    public boolean equals(Object y) {
        return y.equals(board);
    }        // does this board equal y?
    
    public void make_move(int empty_pos_row, int empty_pos_col, Board search_node){
        if(empty_pos_row == 0) {
            this.board[empty_pos_row][empty_pos_col] = this.board[empty_pos_row+1][empty_pos_col];
            copy[empty_pos_row][empty_pos_col] = copy[empty_pos_row+1][empty_pos_col];
            // can only move number on bottom
            neighbor_boards.add();
        }
        if(empty_pos_row == this.size()-1){
            // can only move number on the top 
        }
        if(empty_pos_col == 0) {
            // can only move number on right 
        }
        if(empty_pos_col == this.size()-1){
            // can only move number on the left 
        }
    }

    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbor_boards = new ArrayList<>();
        int empty_pos_row = 0, empty_pos_col = 0;
        for(int i=0; i<this.size(); ++i){
            for(int j=0; j<this.size(); ++j){
                if(board[i][j] == 0){
                    empty_pos_row = i;
                    empty_pos_col = j;
                } 
            }
        }

        return null;
    }     // all neighboring boards

    public String toString() {
        String board_string = "";
        for(int i=0; i<this.size(); ++i){
            for(int e=0; e<this.size(); ++e){
                board_string += board[i][e] + "  ";
            }
            board_string += "\n";
        }
        return board_string;
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
        System.out.println();

        // check if puzzle is solvable; if so, solve it and output solution
        if (initial.isSolvable()) {
            Solver solver = new Solver(initial);
            System.out.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()){
                System.out.println(board);
            }
        }
    
        System.out.println(initial.hamming());
        System.out.println(initial.manhattan());
        System.out.println(initial);
    }
}

/*
3
8
1
3
4
0
2
7
6
5
 */