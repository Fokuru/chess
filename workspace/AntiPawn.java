//
// Editor: Raley Wilkin
// Finished: 3/18/2025
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
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class AntiPawn extends Piece
{
    // Construct an Anti Pawn
    public AntiPawn(boolean isWhite, String img_file) {
        super(isWhite, img_file);
    }

    // Pre: No input required
    // Post: Gives a string which contains information about the piece
    public String toString()
    {
        return "The color of this piece is " + super.getColor() + " and it is an Anti Pawn";
    }

    // Pre: Gets a square array array that is not null and a square which is not null
    // Post: Gives an array of all available controlled squares for the piece at start
    public ArrayList<Square> getControlledSquares(Square[][] b, Square start) {
      ArrayList <Square> ans = new ArrayList <Square> ();
      int row = 0;

      if (super.getColor())
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
    
      ans.add (b[row][col]);

      return ans;
    }


    // Pre: Gets a square array array that is not null and a square which is not null
    // Post: Gives an array of all available moves for the piece at start
    // Logic: Can move diagonal one square towards the opposite side of the board from 
    // where piece started and can capture pieces of opposite color to self if they 
    // are placed one square in front of piece towards the opposite side of board from
    // where piece started. Can not move if another piece of any color or the edge of 
    // the board is in the way 
    public ArrayList<Square> getLegalMoves(Square[][] b, Square start){

      ArrayList <Square> ans = new ArrayList <Square> ();
      int row = 0;

      if (super.getColor())
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


      // Finds if there is a square available for moving in move spot and then adds to ans array
        if ((col + 1 < 8 ) && (b[row][col + 1].isOccupied() == false))
        {
          ans.add (b[row][col + 1]);
        }
      

      // Finds if there is a square available for moving in move spot and then adds to ans array
        if ((col - 1 != -1) && (b[row][col - 1].isOccupied() == false))
        {
          ans.add (b[row][col -1]);
        }


        // Finds if there is a piece available for capture in capture spot and then adds to ans array
      if ((b[row][col].isOccupied() != false && b[row][col].getOccupyingPiece().getColor() != super.getColor()))
      {
    
        ans.add (b[row][col]);
      }

      return ans;
    
    }
}
