package percolation_week_1; /**
 * Created by Ashish.Am.Singh on 01-06-2017.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;

    private WeightedQuickUnionUF grid, auxGrid;
    private boolean[]   state;


    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        N =  n;
        int sitecount = N * N;
        grid = new WeightedQuickUnionUF(sitecount + 2);
        auxGrid = new WeightedQuickUnionUF(sitecount + 1);
        state = new boolean[sitecount + 2];

        for(int i = 1 ;i <= sitecount ;i++ ){
            state[i] = false ;
        }
        state[0] = true ;
        state[sitecount+1] = true ;

    }

    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        checkbounds(row, col);
        int index = convert2dTo1dArray(row, col);
        state[index] = true;


        if (row != 1 && isOpen(row - 1, col)) {
            grid.union(index, convert2dTo1dArray(row - 1, col));
            auxGrid.union(index, convert2dTo1dArray(row - 1, col));
        }
        if (row != N && isOpen(row + 1, col)) {
            grid.union(index, convert2dTo1dArray(row + 1, col));
            auxGrid.union(index, convert2dTo1dArray(row + 1, col));
        }
        if (col != 1 && isOpen(row , col - 1)) {
            grid.union(index, convert2dTo1dArray(row, col - 1));
            auxGrid.union(index, convert2dTo1dArray(row , col - 1));
        }
        if (col != N && isOpen(row, col + 1)) {
            grid.union(index, convert2dTo1dArray(row , col+1));
            auxGrid.union(index, convert2dTo1dArray(row ,col+1));
        }

        if(index <= N){
            grid.union(0,index);
            auxGrid.union(0,index);
        }
        if(index >= (N-1)*(N+1)){
            grid.union(state.length - 1,index);
        }

    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        checkbounds(row,col);

        int index = convert2dTo1dArray(row,col);
        if(state[index]){
            return true ;
        }

        return false;
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        checkbounds(row,col);
        int index = convert2dTo1dArray(row,col);
        return grid.connected(row,col) && auxGrid.connected(0,index);
    }

    public int numberOfOpenSites()       // number of open sites
    {
        int count = 0;
        for(int i = 1 ;i <= N*N ;i++){
           if(state[i]){
               count++;
           }
        }
        return count ;
    }

    public boolean percolates()              // does the system percolate?
    {
        return grid.connected(0, state.length-1);
    }

    public static void main(String[] args)   // test client (optional)
    {

    }

    private void checkbounds(int i ,int j){

        if(i <= 0 || i > N){
            System.out.println("row "+ i + " col " + j);
            throw new java.lang.IllegalArgumentException("index out of bound row") ;
        }
        if( j <= 0 || j > N){
            System.out.println("row "+ i + " col " + j);
            throw new java.lang.IllegalArgumentException("index out of bound col") ;
        }
    }

    private int convert2dTo1dArray(int i , int j) {
        checkbounds(i, j);
        return (i - 1) * N + j;
    }
}
