//
// Editor: Raley Wilkin
// Finished: 3/4/2025
// Does: All functions for anti pawn piece, including moving and capturing
//



import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Piece {
    private final boolean color;
    private BufferedImage img;
    
    public Piece(boolean isWhite, String img_file) {
        this.color = isWhite;
        
        try {
            if (this.img == null) {
              this.img = ImageIO.read(getClass().getResource(img_file));
            }
          } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
          }
    }
    
    

    
    public boolean getColor() {
        return color;
    }
    
    public Image getImage() {
        return img;
    }
    
    public void draw(Graphics g, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();
        
        g.drawImage(this.img, x, y, null);
    }
    
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.

    // Pre: Gets a square array array that is not null and a square which is not null
    // Post: Gives an array of all available captures for the piece at start
    public ArrayList<Square> getControlledSquares(Square[][] b, Square start) {
      ArrayList <Square> ans = new ArrayList <Square> ();
      boolean isWhite = start.getOccupyingPiece().getColor();
      int row = 0;
      if (isWhite)
      {
        row = start.getRow() + 1;
      }
      else
      {
        row = start.getRow() - 1;
      }
     
      int col = start.getCol();


      if (row>7 || row<0 || col>7 || col<0)
      {
        return ans;
      }

      // Finds if there is a piece available for capture in capture spot and then adds to capture array
      if ((b[row][col].isOccupied() != false && b[row][col].getOccupyingPiece().getColor() != isWhite))
      {
        ans.add (b[row][col]);
      }

      return ans;
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.


    // Pre: Gets a square array array that is not null and a square which is not null
    // Post: Gives an array of all available moves for the piece at start
    public ArrayList<Square> getLegalMoves(Square[][] b, Square start){

      ArrayList <Square> ans = new ArrayList <Square> ();
      boolean isWhite = start.getOccupyingPiece().getColor();
      int row = 0;

      if (isWhite)
      {
        row = start.getRow() + 1;
      }
      else
      {
        row = start.getRow() - 1;
      }

      if ((start.getRow() + 1)>7 || (start.getRow() - 1) < 0)
      {
        return ans;
      }


      int col = start.getCol();

      // Finds if there is a square available for moving in move spot and then adds to move array
        if ((col + 1 < 8 ) && (b[row][col + 1].isOccupied() == false))
        {
          ans.add (b[row][col + 1]);
        }
      

      // Finds if there is a square available for moving in move spot and then adds to move array
        if ((col - 1 != -1) && (b[row][col - 1].isOccupied() == false))
        {
          ans.add (b[row][col -1]);
        }

      return ans;
    
    }
  }