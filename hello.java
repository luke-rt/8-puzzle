class hello{
    public static void main(String[] args) {
        
        MinPQ<Board> pq = new MinPQ<Board>();
        int[][] b1 =  {{0,
        1,
        3},
        {4,
        2,
        5},
        {7,
        8,
        6}};

        int[][] b2 =  {{1,
        0,
        3},
        {4,
        2,
        5},
        {7,
        8,
        6}};
        
        Board board1 = new Board(b1);
        Board board2 = new Board(b2);
            
        System.out.println("B1 manh: " + board1.manhattan());
        System.out.println("B2 manh: " + board2.manhattan());
        
        pq.insert(board1);
        pq.insert(board2);
        
        Board out = pq.delMin();
        System.out.println(out);
        

    }
}