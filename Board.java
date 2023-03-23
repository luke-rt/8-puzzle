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
    
    public int moves_made(){
        return moves;
    }
    
    public void increase_moves(){
        this.moves += 1;
    }
    
    public int[][] getBoard(){
        return this.board;
    }

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
                    if(board[row][col] % this.size() == 0){
                        proper_row--;
                        proper_col += this.size();
                    }
                    manhat_dist += Math.abs(row - proper_row) + Math.abs(col - proper_col);
                    //System.out.println(board[row][col] + " is displaced " + manhat_dist);
                }
            }
        } 
        return manhat_dist;
    }                 // sum of Manhattan distances between blocks and goal

    public boolean isGoal() {
        if(manhattan() == 0){
            return true;
        }
        return false;
    }                // is this board the goal board?

    public boolean isSolvable(){
        int inversions = 0;
        if(this.size() % 2 == 1){
            for(int row1=0; row1<this.size(); ++row1){
                for(int col1=0; col1<this.size(); ++col1){
                    for(int row2=0; row2<this.size(); ++row2){
                        for(int col2=0; col2<this.size(); ++col2){
                            if(board[row1][col1] != 0 && board[row2][col2] != 0){
                                if(board[row1][col1] < board[row2][col2]){
                                    if(row1 > row2 || (row1 == row2 && col1 > col2)){
                                        ++inversions;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            System.out.println("Inversions: " + inversions);
            if(inversions % 2 == 1) return false;
            else return true;
        }else if(this.size() % 2 == 0){
            int blank_row = 0;
            for(int i=0; i<this.size(); ++i) {
                for(int j=0; j<this.size(); ++j) {
                    if(board[i][j] == 0){
                        blank_row = i;
                    }
                }
            }
            if(blank_row + inversions % 2 == 1) return true;
            else return false;
        }
        return false;
    }            // is this board solvable?

    public boolean equals(Board y) {
        for(int i=0; i<this.size(); ++i){
            for(int e=0; e<this.size(); ++e){
                if(board[i][e] != y.getBoard()[i][e]){
                    return false;
                }
            }
        }
        return true;
    }        // does this board equal y?
    
    public boolean is_valid_move(int x, int y){
        if(x < 0 || y < 0 || x >= this.size() || y >= this.size())
            return false;
        return true;
    }
    
    public int[][] copy_board(int[][] original){
        int[][] copy = new int[original.length][original.length];
        for(int i=0; i<original.length; ++i){
            for(int e=0; e<original.length; ++e){
                copy[i][e] = original[i][e];
            }
        }
        return copy;
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
        
        if(is_valid_move(empty_pos_row-1, empty_pos_col)){ // then we can move a block from top to bottom
            int[][] copy = copy_board(board);
            copy[empty_pos_row][empty_pos_col] = copy[empty_pos_row-1][empty_pos_col];
            copy[empty_pos_row-1][empty_pos_col] = 0;
            Board newBoard = new Board(copy);
            newBoard.increase_moves();
            neighbor_boards.add(new Board(copy));
        }
        if(is_valid_move(empty_pos_row+1, empty_pos_col)){ // can move block bottom to top
            int[][] copy = copy_board(board);
            copy[empty_pos_row][empty_pos_col] = copy[empty_pos_row+1][empty_pos_col];
            copy[empty_pos_row+1][empty_pos_col] = 0;
            Board newBoard = new Board(copy);
            newBoard.increase_moves();
            neighbor_boards.add(new Board(copy));

        }
        if(is_valid_move(empty_pos_row, empty_pos_col-1)){ // can move block left to right
            int[][] copy = copy_board(board);
            copy[empty_pos_row][empty_pos_col] = copy[empty_pos_row][empty_pos_col-1];
            copy[empty_pos_row][empty_pos_col-1] = 0;
            Board newBoard = new Board(copy);
            newBoard.increase_moves();
            neighbor_boards.add(new Board(copy));
        }
        if(is_valid_move(empty_pos_row, empty_pos_col+1)){ // can move block left to right
            int[][] copy = copy_board(board);
            copy[empty_pos_row][empty_pos_col] = copy[empty_pos_row][empty_pos_col+1];
            copy[empty_pos_row][empty_pos_col+1] = 0;
            Board newBoard = new Board(copy);
            newBoard.increase_moves();
            neighbor_boards.add(new Board(copy));
        }

        return neighbor_boards;
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

        // check if puzzle is solvable; if so, solve it and output solution
        /* 
        if (initial.isSolvable()) {
            Solver solver = new Solver(initial);
            System.out.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()){
                System.out.println(board);
            }
        }*/
    
        System.out.println();
        System.out.println(initial);
        System.out.println("Solvable: " + initial.isSolvable());
        // System.out.println(initial.hamming());
        System.out.println("Manhatt: " + initial.manhattan());
        for(Board b : initial.neighbors()){
            System.out.println("Neightbor:");
            System.out.println(b);
        }
        
        
        // int[][] goal = {{1,2,3}, {4,5,6}, {7,8,0}};
        System.out.println("IS goal: " + initial.isGoal());
        System.out.println(initial.isSolvable());
    }
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
 3
 1
 2
 3
 4
 5
 6
 7
 8
 0
 */

 /*
 3
 1
 2
 3
 4
 5
 0
 7
 8
 6
  */