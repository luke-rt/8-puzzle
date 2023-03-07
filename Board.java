public class Board {
    private int[][] board;
    private int moves;
    
    public Board(int[][] blocks){
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
        
    }                 // sum of Manhattan distances between blocks and goal
    public boolean isGoal() {

    }                // is this board the goal board?
    public boolean isSolvable(){

    }            // is this board solvable?
    public boolean equals(Object y) {

    }        // does this board equal y?
    public Iterable<Board> neighbors() {

    }     // all neighboring boards
    public String toString() {

    }               // string representation of this board (in the output format specified below)

    public static void main(String[] args){
        int 
    } // unit tests (not graded)
}
