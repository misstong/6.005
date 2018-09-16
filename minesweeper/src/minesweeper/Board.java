/* Copyright (c) 2007-2017 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Random;

/**
 * TODO: Specification
 */
public class Board {
    
 // in boardState, 0 rep UNTOUCHED, 1 rep FLAGGED, 2 rep DUG
    private static final int UNTOUCHED = 0;
    private static final int FLAGGED = 1;
    private static final int DUG = 2;
    int[][] boardState;
    boolean[][] hasBomb;
    int[][] neighborBombs;
    int x,y;
    public static void main(String[] args) {
        Optional<File> file=Optional.of(new File("D:\\ThunderDownload\\6.005\\2\\1.txt"));
        Board b=new Board(file,2,2);
        System.out.println(b.toString());
        for(int i=0;i<b.x;i++) {
            for(int j=0;j<b.y;j++) {
                System.out.print(b.hasBomb[i][j]+" ");
                //System.out.print(b.boardState[i][j]+" ");
            }
            System.out.println();
        }
        b.dig(0, 0);
        System.out.println(b.toString());
    }
    public Board( Optional<File> file,int x,int y) {
        if(file.isPresent()) {
            try {
                BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file.get())));
                String[] sizes=br.readLine().split(" ");
                this.x=Integer.valueOf(sizes[0]);
                this.y=Integer.valueOf(sizes[1]);
                //System.out.println(x+" "+y);
                boardState=new int[this.x][this.y];
                hasBomb=new boolean[this.x][this.y];
                neighborBombs=new int[this.x][this.y];
                
                for(int i=0;i<this.x;i++) {
                    String[] lines=br.readLine().split(" ");
                    for(int j=0;j<this.y;j++) {
                        if(Integer.valueOf(lines[j])==1)
                        {
                            hasBomb[i][j]=true;
                            //System.out.print(lines[j]+" ");
                        }
                          
                    }
                   // System.out.println();
                    
                }
                updateNeiborBombs();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            this.x=x;
            this.y=y;
            boardState=new int[x][y];
            hasBomb=new boolean[x][y];
            neighborBombs=new int[x][y];
            
            Random ran=new Random();
            for(int i=0;i<x;i++) {
                for(int j=0;j<y;j++) {
                    int t=ran.nextInt(4);
                    if(t==0) {
                        hasBomb[i][j]=true;
                        
                    }
                }
            }
            updateNeiborBombs();
        }
    }

    
    private synchronized void updateNeiborBombs() {
        // TODO Auto-generated method stub
        for(int i=0;i<x;i++) {
            for(int j=0;j<y;j++) {
                neighborBombs[i][j]=countNeighborBombs(i,j);
            }
        }
        
    }
    
    private synchronized int countNeighborBombs(int i,int j) {
        int count=0;
        if(hasBomb(i-1,j-1))
            count++;
        if(hasBomb(i-1,j))
            count++;
        if(hasBomb(i-1,j+1))
            count++;
        if(hasBomb(i+1,j-1))
            count++;
        if(hasBomb(i+1,j))
            count++;
        if(hasBomb(i+1,j+1))
            count++;
        if(hasBomb(i,j-1))
            count++;
        if(hasBomb(i,j+1))
            count++;
        
        return count;
        
    }

    public synchronized boolean hasBomb(int i, int j) {
        // TODO Auto-generated method stub
        
            if(isValid(i,j))
                return hasBomb[i][j];
            return false;
    }


    private synchronized boolean isValid(int i, int j) {
        // TODO Auto-generated method stub
        if(i<0||j<0||i>=x||j>=y)
            return false;
        return true;
    }


    public synchronized String toString() {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                switch (boardState[i][j]) {
                case UNTOUCHED:
                    sb.append("- ");
                    break;
                case FLAGGED:
                    sb.append("F ");
                    break;
                case DUG:
                     sb.append(neighborBombs[i][j] == 0 ? "  "
                     : neighborBombs[i][j] + " ");
//                    sb.append(neighborBombs[i][j]);
                    break;
                }
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
    
    // TODO: Abstraction function, rep invariant, rep exposure, thread safety
    
    // TODO: Specify, test, and implement in problem 2
    
    public synchronized void flag(int row,int col) {
        if(isValid(row,col)&&boardState[row][col]==UNTOUCHED)
            boardState[row][col]=FLAGGED;
    }
    
    public synchronized void deflag(int row, int col) {
        if(isValid(row,col)&&boardState[row][col]==FLAGGED){
            boardState[row][col]=UNTOUCHED;
        }
    }
    
    public synchronized boolean dig(int row, int col) {
        if(!isValid(row,col)||boardState[row][col]!=UNTOUCHED)
            return false;
        boolean ret=false;
        if(hasBomb(row,col)) {
            hasBomb[row][col]=false;
            ret=true;
            updateNeighborBombs(row,col);
        }
        setNeighborGrids(row,col);
        return ret;
    }


    private synchronized void setNeighborGrids(int row, int col) {
        // TODO Auto-generated method stub
        if(!isValid(row,col)||boardState[row][col]!=UNTOUCHED)
            return;
        if (!hasBomb[row][col]) {
            boardState[row][col] = DUG;
            if (neighborBombs[row][col] != 0)
                return;
            else {
                setNeighborGrids(row - 1, col - 1);
                setNeighborGrids(row - 1, col);
                setNeighborGrids(row - 1, col + 1);
                setNeighborGrids(row, col - 1);
                setNeighborGrids(row, col + 1);
                setNeighborGrids(row + 1, col - 1);
                setNeighborGrids(row + 1, col);
                setNeighborGrids(row + 1, col + 1);
            }
        }
        return;
        
    }


    private synchronized void updateNeighborBombs(int row, int col) {
        // TODO Auto-generated method stub
        if (isValid(row - 1, col - 1))
            neighborBombs[row - 1][col - 1] = countNeighborBombs(row - 1,
                    col - 1);
        if (isValid(row - 1, col))
            neighborBombs[row - 1][col] = countNeighborBombs(row - 1, col);
        if (isValid(row - 1, col + 1))
            neighborBombs[row - 1][col + 1] = countNeighborBombs(row - 1,
                    col + 1);
        if (isValid(row, col - 1))
            neighborBombs[row][col - 1] = countNeighborBombs(row, col - 1);
        if (isValid(row, col + 1))
            neighborBombs[row][col + 1] = countNeighborBombs(row, col + 1);
        if (isValid(row + 1, col - 1))
            neighborBombs[row + 1][col - 1] = countNeighborBombs(row + 1,
                    col - 1);
        if (isValid(row + 1, col))
            neighborBombs[row + 1][col] = countNeighborBombs(row + 1, col);
        if (isValid(row + 1, col + 1))
            neighborBombs[row + 1][col + 1] = countNeighborBombs(row + 1,
                    col + 1);
    }
    
}
