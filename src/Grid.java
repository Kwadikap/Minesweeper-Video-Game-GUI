



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Grid {
  private boolean[][] bombGrid;
  private int[][] countGrid;
  private int numRows;
  private int numColumns;
  private int numBombs;
  
  public Grid() {
	  numRows = 10;
	  numColumns = 10;
	  numBombs = 25;
	  countGrid = new int[numRows][numColumns];
	  bombGrid = new boolean[numRows][numColumns];
	  createBombGrid();
	  createCountGrid();
  }
  	
  public Grid(int rows, int columns) {
	  numRows = rows;
	  numColumns = columns;
	  numBombs = 25;
	  countGrid = new int[numRows][numColumns];
	  bombGrid = new boolean[numRows][numColumns];
	  createBombGrid();
	  createCountGrid();
  }
  
  public Grid(int rows, int columns, int numBombs) {
	  numRows = rows;
	  numColumns = columns;
	  this.numBombs = numBombs;
	  countGrid = new int[numRows][numColumns];
	  bombGrid = new boolean[numRows][numColumns];
	  createBombGrid();
	  createCountGrid();
  }
  
  public int getNumRows() {return this.numRows;};
  
  public int getNumColumns() {return this.numColumns;};
  
  public int getNumBombs() {return this.numBombs;};
  
  public boolean[][] getBombGrid() {
	  boolean[][] clone = new boolean[numRows][numColumns];
	
	  for(int r = 0; r<bombGrid.length; r++) {
		  for(int c = 0; c< bombGrid[r].length; c++) {
			  clone[r][c] = this.bombGrid[r][c];
		  }
	  }
	  
	  return clone;
		  
  }
  
  public int[][] getCountGrid(){
	 int[][] clone = new int[numRows][numColumns];
	 for(int r = 0; r < countGrid.length; r++) {
		 for(int c = 0; c < countGrid[r].length; c++) {
			 clone[r][c] = this.countGrid[r][c];
		 }
	 }
	 return clone;
	}
  
  public boolean isBombAtLocation(int row, int column) {
	
	 if((row >= 0) && (row < numRows) && (column >= 0) && (column < numColumns)) {
	  if(bombGrid[row][column] == true) {
		  return true;
	  }
	 }
	  return false;
  }
  
  public int getCountAtLocation(int row, int col) {
	  return this.countGrid[row][col];
	  }
  
  private void createBombGrid() {
	 Random rand = new Random();
	 bombGrid = new boolean [numRows][numColumns];
	 int bombCount = 1;
	
	 
	 while(bombCount <= numBombs) {
			
			int x = rand.nextInt(numRows);
			int y = rand.nextInt(numColumns);
			if(bombGrid[x][y]) {
				continue;
			}
			
			bombGrid[x][y] = true;
			bombCount++;
			
		}
  }
  
  private void createCountGrid() {
	  countGrid = new int[numRows][numColumns];
	  int count = 0;
	  for(int row = 0; row < countGrid.length; row++) {
		  for(int col = 0; col < countGrid[row].length; col++) {
			  if((row == 0) && (col == 0)) {
				  if(isBombAtLocation(row, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row, col+1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row+1, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row+1, col+1) == true) {
					  count++;
				  }
				  countGrid[row][col] = count;
				  count = 0;
			  }
			  else if((row == 0) && (col == 9)) {
				  if(isBombAtLocation(row, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row, col-1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row+1, col-1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row+1, col) == true) {
					  count++;
				  }
				  countGrid[row][col] = count;
				  count = 0;
			  }
			  else if((row == 9) && (col == 0)) {
				  if(isBombAtLocation(row, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row-1, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row, col+1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row-1, col+1) == true) {
					  count++;
				  }
				  countGrid[row][col] = count;
				  count = 0;
			  }
			  else if((row == 9) && (col == 9)) {
				  if(isBombAtLocation(row, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row-1, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row, col-1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row-1, col-1) == true) {
					  count++;
				  }
				  countGrid[row][col] = count;
				  count = 0;
			  }
			  else if((row == 0)) {
				  if(isBombAtLocation(row, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row, col-1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row, col+1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row+1, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row+1, col-1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row+1, col+1) == true) {
					  count++;
				  }
				  countGrid[row][col] = count;
				  count = 0;
			  }
			  else if((row == 9)) {
				  if(isBombAtLocation(row, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row, col+1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row, col-1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row-1, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row-1, col-1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row-1, col+1) == true) {
					  count++;
				  }
				  countGrid[row][col] = count;
				  count = 0;
			  }
			  else if((col == 0)) {
				  if(isBombAtLocation(row, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row-1, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row+1, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row, col+1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row-1, col+1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row+1, col+1) == true) {
					  count++;
				  }
				  countGrid[row][col] = count;
				  count = 0;
			  }
			  else if((col == 9)) {
				  if(isBombAtLocation(row, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row-1, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row+1, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row, col-1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row-1, col-1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row+1, col-1) == true) {
					  count++;
				  }
				  countGrid[row][col] = count;
				  count = 0;
			  }
			  else {
				  if(isBombAtLocation(row, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row-1, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row+1, col) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row, col+1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row, col-1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row-1, col+1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row+1, col+1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row-1, col-1) == true) {
					  count++;
				  }
				  if(isBombAtLocation(row+1, col-1) == true) {
					  count++;
				  }
				  countGrid[row][col] = count;
				  count = 0;
			  }
		  }
	  }
	  
  }
  
  
  
  
  
  
  
  
  
}
