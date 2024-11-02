package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF topUF;
    private WeightedQuickUnionUF bottomUF;
    private final int VirtualTop;
    private final int VirtualBottom;
    private boolean[][] grids;
    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int numberOfOpenSites = 0;
    private final int N;
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N)  {
        if(N <= 0){
            throw new IllegalArgumentException("N must be positive");
        }
        this.N = N;
        grids = new boolean[N][N];
        topUF = new WeightedQuickUnionUF(N * N + 2);
        bottomUF = new WeightedQuickUnionUF(N * N + 1);
        VirtualTop = N * N;
        VirtualBottom = N * N + 1;
        for(int i = 0; i < N; i++){
            topUF.union(VirtualTop, i);
            bottomUF.union(VirtualTop, i);
        }
    }
    private boolean validPos(int row, int col){
        return row >= 0 && row < N && col >= 0 && col < N;
    }
    private int pos2dTo1d(int row, int col){
        return N * row + col;
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col){
        if(!validPos(row, col)){
            throw new IndexOutOfBoundsException("row or column must be between 0 and N");
        }
        if(grids[col][row]){
            return;
        }
        if (row == N - 1) {
            topUF.union(VirtualBottom, pos2dTo1d(row, col));
        }
        grids[row][col] = true;
        ++numberOfOpenSites;
        for(int i = 0; i < 4; i++){
            int nx = row + directions[i][0];
            int ny = col + directions[i][1];
            if(validPos(nx, ny) && grids[nx][ny]){
                topUF.union(pos2dTo1d(nx, ny),pos2dTo1d(row, col));
                bottomUF.union(pos2dTo1d(nx, ny),pos2dTo1d(row, col));
            }
        }
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if(!validPos(row, col)){
            throw new IndexOutOfBoundsException("row or column must be between 0 and N");
        }
        return grids[row][col];
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if(!validPos(row, col)){
            throw new IndexOutOfBoundsException("row or column must be between 0 and N");
        }
        return grids[row][col] && bottomUF.connected(pos2dTo1d(row, col), VirtualTop);
    }
    // number of open sites
    public int numberOfOpenSites(){
        return numberOfOpenSites;
    }
    // does the system percolate?
    public boolean percolates(){
        return topUF.connected(VirtualTop, VirtualBottom);
    }
    // use for unit testing (not required)
    public static void main(String[] args){}
}
