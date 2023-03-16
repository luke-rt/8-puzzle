public class Solver {
    private MinPQ<Board> pq;
    private Board workingBoard;
    public Solver(Board initial){
        pq = new MinPQ<Board>();
        workingBoard = initial;
    }           // find a solution to the initial board (using the A* algorithm)
    public int moves(){

    }                     // min number of moves to solve initial board
    
    public void helper(Board b) {
        pq.insert(b);
        for(Board b1 : b.neighbors()){
            for(Board b2 : b1.neighbors()) {
                if(!b2.equals(b)){
                    pq.insert(b2);
                    pq.delMin();
                }
            }
        }
    }

    public Iterable<Board> solution(){
        while(!workingBoard.isGoal()){
            helper(workingBoard);
        }
    }      // sequence of boards in a shortest solution
    public static void main(String[] args){

    } // solve a slider puzzle (given below) 
}
