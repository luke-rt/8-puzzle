# 8-puzzle

 Output 1:
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

0  1  3  
4  2  5  
7  8  6  

1  0  3  
4  2  5  
7  8  6  

1  2  3  
4  0  5  
7  8  6  

1  2  3  
4  5  0  
7  8  6  

1  2  3  
4  5  6  
7  8  0  

Move made: 5

============

Output 2:
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

1  2  3  4  
5  0  6  8  
9  10  7  11  
13  14  15  12  

1  2  3  4  
5  6  0  8  
9  10  7  11  
13  14  15  12  

1  2  3  4  
5  6  7  8  
9  10  0  11  
13  14  15  12  

1  2  3  4  
5  6  7  8  
9  10  11  0  
13  14  15  12  

1  2  3  4  
5  6  7  8  
9  10  11  12  
13  14  15  0  

Move made: 5

============

Output 3:
3
1
2
3
4
5
6
8
7
0

Unsolvable

============

How our program works:
Using a priority queue, we wrap a Board object inside of a Node class. This Node class provides us
the ability to combine Board's manhattan method with the current amount of moves made for a solution branch
in Solver, which is ultimately our priority function. This priority function will thus be used for our 
Comparator function when using a PQ. We will utilize a recursive function that generates and explores
the neighbors of each minimum element popped from the PQ, and ideally, this minimum branch will become
our solution set.