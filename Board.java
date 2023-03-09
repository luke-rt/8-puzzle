import java.util.Scanner;

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
                if(board[i][j] != i+j) ++incorrect;
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

    public Iterable<Board> neighbors() {
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

        // check if puzzle is solvable; if so, solve it and output solution
        if (initial.isSolvable()) {
            Solver solver = new Solver(initial);
            System.out.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()){
                System.out.println(board);
            }
        }
    
        System.out.println(initial.manhattan());
        System.out.println(initial);
    }
}
