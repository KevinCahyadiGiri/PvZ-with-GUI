//MapGenerator.java

import java.awt.Graphics2D;

public class MapGenerator{
    public int map[][];
    //public int squareHeight;
    //public int squareWidth;
    public MapGenerator(int row, int col){
        map = new int[row][col];
        for(int i=0; i<map.length;i++){
            for(int j=0; j<map[0].length; j++){
                map[i][j]=0;                //0 artinya tidak terisi
            }
        }
        //squareWidth = 750/col; //ini rata-rata, harusnya 90,80,100,100,100,90,90,100
        //squareHeight = 620/row; //ini rata-rata, harusnya 110,110,120,110,110
    }

    //menaruh tanaman di square
    public void putPlant(int x, int y){
        map[x][y]=1;
    } 

    // public void draw(Graphics2D g){
    //     for(int i=0; i<map.length;i++){
    //         for(int j=0; j<map[0].length; j++){
    //             if(map[i][j]>0){
    //                 g.draw
    //             }
    //         }
    //     }
    // }
}