// Name:Adam Wang
// Piece: Joker
// Description: Each turn, the Joker is randomly able to move either like a rook, knight, or bishop
import java.util.ArrayList;

//you will need to implement two functions in this file.
public class Joker extends Piece{

    public Joker(boolean isWhite, String img_file) {
      super(isWhite,img_file);
    }
    
    public String toString(){
      if(color){
        return "A white Joker";
      }
      return "A black Joker"; 
    }

    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    // precondition: row and column of square are greater than or equal to 0 and less than 8
    // postcondition: returns an arraylist of all squares controled by piece
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start, int moveType) {
      ArrayList<Square> moves = new ArrayList<Square>();
      
      if(moveType == 0){ // ROOK-----------------------------------------------------------------------------------
        int startRow = start.getRow();
        int startCol = start.getCol();
        for(int r = startRow+1; r < 8; r++){ // Squares below piece
          Square sq = board[r][startCol];
          if(!sq.isOccupied()){
            moves.add(sq);
          } else if(sq.isOccupied()){
            moves.add(sq);
            break;
          }
        }
        for(int r = startRow-1; r >= 0; r--){ // Squares above piece
          Square sq = board[r][startCol];
          if(!sq.isOccupied()){
            moves.add(sq);
          } else if(sq.isOccupied()){
            moves.add(sq);
            break;
          }
        }
        for(int c = startCol+1; c < 8; c++){ // Squares right of piece
          Square sq = board[startRow][c];
          if(!sq.isOccupied()){
            moves.add(sq);
          } else if(sq.isOccupied()){
            moves.add(sq);
            break;
          }
        }
        for(int c = startCol-1; c >= 0; c--){ // Squares left of piece
          Square sq = board[startRow][c];
          if(!sq.isOccupied()){
            moves.add(sq);
          } else if(sq.isOccupied()){
            moves.add(sq);
            break;
          }
        }
      
      } else if(moveType == 1){ // KNIGHT-----------------------------------------------------------------------------------
        int row = start.getRow();
        int col = start.getCol();
      if(row + 2 < 8 && col - 1 >= 0){ // Down two, left one
        Square sq = board[row+2][col-1]; // Up two, left one
        moves.add(sq);
      }
      if(row + 2 < 8 && col + 1 < 8){
        Square sq = board[row+2][col+1]; // Down two, right one
        moves.add(sq);
      }
      if(row - 2 >= 0 && col - 1 >= 0){
        Square sq = board[row-2][col-1]; // Down two, left one
        moves.add(sq);
      }
      if(row - 2 >= 0 && col + 1 < 8){
        Square sq = board[row-2][col+1]; // Down two, right one
        moves.add(sq);
      }
      if(row - 1 >= 0 && col + 2 < 8){ 
        Square sq = board[row-1][col+2]; // Right two, up one
        moves.add(sq);
      }
      if(row + 1 < 8 && col + 2 < 8){ // Right two, down one
        Square sq = board[row+1][col+2]; // Down two, right one
        moves.add(sq);
      }
      if(row - 1 >= 0 && col - 2 >= 0){ 
        Square sq = board[row-1][col-2]; // Left two, up one
        moves.add(sq);
      }
      if(row + 1 < 8 && col - 2 >= 0){ 
        Square sq = board[row+1][col-2]; // Left two, down one
        moves.add(sq);
      }
    } else if(moveType == 2){ // BISHOP-----------------------------------------------------------------------------------
      int r = start.getRow()+1;
      int c = start.getCol()+1;
      for(int i = start.getRow(); i < 8; i++){ // Down right diagonal
        if(r < 8 && c < 8){
          Square sq = board[r][c];
          if(!sq.isOccupied()){
            moves.add(sq);
          } else if(sq.isOccupied()){
            moves.add(sq);
            break;
          }
            r++;
            c++;
        }
      }
      r = start.getRow()-1;
      c = start.getCol()-1;
      for(int i = start.getRow(); i >= 0; i--){ // Up right diagonal
        if(r >= 0 && c >= 0){
          Square sq = board[r][c];
          if(!sq.isOccupied()){
            moves.add(sq);
          } else if(sq.isOccupied()){
            moves.add(sq);
            break;
          }
            r--;
            c--;
        }
      }
      r = start.getRow()+1;
      c = start.getCol()-1;
      for(int i = start.getRow(); i < 8; i++){ // Down left diagonal
        if(r < 8 && c >= 0){
          Square sq = board[r][c];
          if(!sq.isOccupied()){
            moves.add(sq);
          } else if(sq.isOccupied()){
            moves.add(sq);
            break;
          }
            r++;
            c--;
        }
      }
      r = start.getRow()-1;
      c = start.getCol()+1;
      for(int i = start.getRow(); i >= 0; i--){ // Up right diagonal
        if(r >= 0 && c < 8){
          Square sq = board[r][c];
          if(!sq.isOccupied()){
            moves.add(sq);
          } else if(sq.isOccupied()){
            moves.add(sq);
            break;
          }
            r--;
            c++;
          }
        }
      }
      return moves;
    }

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    // Preconditions: row and column are greater than or equal to 0 and less than 8
    // Postcondition: returns an arraylist of possible moves
    // Rules: Each turn, the piece can move like a randomly-chosen piece on the board as long as it does not move off the board
    // 0 = rook, 1 = knight, 2 = bishop
    public ArrayList<Square> getLegalMoves(Board b, Square start){
      ArrayList<Square> moves = new ArrayList<Square>();
      int moveType = (int) Math.random() * (3 - 0 + 1) + 0;
      if(moveType == 0){ // ROOK-----------------------------------------------------------------------------------
        int startRow = start.getRow();
        int startCol = start.getCol();
        for(int r = startRow+1; r < 8; r++){ // Squares below piece
          Square sq = b.getSquareArray()[r][startCol];
          if(!sq.isOccupied()){
            moves.add(sq);
          } else if(sq.isOccupied() && (color != sq.getOccupyingPiece().getColor())){
            moves.add(sq);
            break;
          } else if(sq.isOccupied () && (color == sq.getOccupyingPiece().getColor())){
            break;
          }
        }
        for(int r = startRow-1; r >= 0; r--){ // Squares above piece
          Square sq = b.getSquareArray()[r][startCol];
          if(!sq.isOccupied()){
            moves.add(sq);
          } else if(sq.isOccupied() && (color != sq.getOccupyingPiece().getColor())){
            moves.add(sq);
            break;
          } else if(sq.isOccupied () && (color == sq.getOccupyingPiece().getColor())){
            break;
          }
        }
        for(int c = startCol+1; c < 8; c++){ // Squares right of piece
          Square sq = b.getSquareArray()[startRow][c];
          if(!sq.isOccupied()){
            moves.add(sq);
          } else if(sq.isOccupied() && (color != sq.getOccupyingPiece().getColor())){
            moves.add(sq);
            break;
          } else if(sq.isOccupied () && (color == sq.getOccupyingPiece().getColor())){
            break;
          }
        }
        for(int c = startCol-1; c >= 0; c--){ // Squares left of piece
          Square sq = b.getSquareArray()[startRow][c];
          if(!sq.isOccupied()){
            moves.add(sq);
          } else if(sq.isOccupied() && (color!= sq.getOccupyingPiece().getColor())){
            moves.add(sq);
            break;
          } else if(sq.isOccupied () && (color == sq.getOccupyingPiece().getColor())){
            break;
          }
        }
      
      } else if(moveType == 1){ // KNIGHT-----------------------------------------------------------------------------------
        int row = start.getRow();
        int col = start.getCol();
      if(row + 2 < 8 && col - 1 >= 0){ // Down two, left one
        Square sq = b.getSquareArray()[row+2][col-1]; // Up two, left one
        if(!sq.isOccupied()){
          moves.add(sq);
        } else if(sq.isOccupied() && (color != sq.getOccupyingPiece().getColor())){
          moves.add(sq);
        }
      }
      if(row + 2 < 8 && col + 1 < 8){
        Square sq = b.getSquareArray()[row+2][col+1]; // Down two, right one
        if(!sq.isOccupied()){
          moves.add(sq);
        } else if(sq.isOccupied() && (color != sq.getOccupyingPiece().getColor())){
          moves.add(sq);
        }
      }
      if(row - 2 >= 0 && col - 1 >= 0){
        Square sq = b.getSquareArray()[row-2][col-1]; // Down two, left one
        if(!sq.isOccupied()){
          moves.add(sq);
        } else if(sq.isOccupied() && (color != sq.getOccupyingPiece().getColor())){
          moves.add(sq);
        }
      }
      if(row - 2 >= 0 && col + 1 < 8){
        Square sq = b.getSquareArray()[row-2][col+1]; // Down two, right one
        if(!sq.isOccupied()){
          moves.add(sq);
        } else if(sq.isOccupied() && (color != sq.getOccupyingPiece().getColor())){
          moves.add(sq);
        }
      }
      if(row - 1 >= 0 && col + 2 < 8){ 
        Square sq = b.getSquareArray()[row-1][col+2]; // Right two, up one
        if(!sq.isOccupied()){
          moves.add(sq);
        } else if(sq.isOccupied() && (color != sq.getOccupyingPiece().getColor())){
          moves.add(sq);
        }
      }
      if(row + 1 < 8 && col + 2 < 8){ // Right two, down one
        Square sq = b.getSquareArray()[row+1][col+2]; // Down two, right one
        if(!sq.isOccupied()){
          moves.add(sq);
        } else if(sq.isOccupied() && (color != sq.getOccupyingPiece().getColor())){
          moves.add(sq);
        }
      }
      if(row - 1 >= 0 && col - 2 >= 0){ 
        Square sq = b.getSquareArray()[row-1][col-2]; // Left two, up one
        if(!sq.isOccupied()){
          moves.add(sq);
        } else if(sq.isOccupied() && (color != sq.getOccupyingPiece().getColor())){
          moves.add(sq);
        }
      }
      if(row + 1 < 8 && col - 2 >= 0){ 
        Square sq = b.getSquareArray()[row+1][col-2]; // Left two, down one
        if(!sq.isOccupied()){
          moves.add(sq);
        } else if(sq.isOccupied() && (color != sq.getOccupyingPiece().getColor())){
          moves.add(sq);
        }
      }
    } else if(moveType == 2){ // BISHOP-----------------------------------------------------------------------------------
      int r = start.getRow()+1;
      int c = start.getCol()+1;
      for(int i = start.getRow(); i < 8; i++){ // Down right diagonal
        if(r < 8 && c < 8){
          Square sq = b.getSquareArray()[r][c];
          if(!sq.isOccupied()){
            moves.add(sq);
          } else if(sq.isOccupied() && (color != sq.getOccupyingPiece().getColor())){
            moves.add(sq);
            break;
          } else if(sq.isOccupied() && (color == sq.getOccupyingPiece().getColor())){
            break;
          }
            r++;
            c++;
        }
      }
      r = start.getRow()-1;
      c = start.getCol()-1;
      for(int i = start.getRow(); i >= 0; i--){ // Up right diagonal
        if(r >= 0 && c >= 0){
          Square sq = b.getSquareArray()[r][c];
          if(!sq.isOccupied()){
            moves.add(sq);
          } else if(sq.isOccupied() && (color != sq.getOccupyingPiece().getColor())){
            moves.add(sq);
            break;
          } else if(sq.isOccupied() && (color == sq.getOccupyingPiece().getColor())){
            break;
          }
            r--;
            c--;
        }
      }
      r = start.getRow()+1;
      c = start.getCol()-1;
      for(int i = start.getRow(); i < 8; i++){ // Down left diagonal
        if(r < 8 && c >= 0){
          Square sq = b.getSquareArray()[r][c];
          if(!sq.isOccupied()){
            moves.add(sq);
          } else if(sq.isOccupied() && (color != sq.getOccupyingPiece().getColor())){
            moves.add(sq);
            break;
          } else if(sq.isOccupied() && (color == sq.getOccupyingPiece().getColor())){
            break;
          }
            r++;
            c--;
        }
      }
      r = start.getRow()-1;
      c = start.getCol()+1;
      for(int i = start.getRow(); i >= 0; i--){ // Up right diagonal
        if(r >= 0 && c < 8){
          Square sq = b.getSquareArray()[r][c];
          if(!sq.isOccupied()){
            moves.add(sq);
          } else if(sq.isOccupied() && (color != sq.getOccupyingPiece().getColor())){
            moves.add(sq);
            break;
          } else if(sq.isOccupied() && (color == sq.getOccupyingPiece().getColor())){
            break;
          }
            r--;
            c++;
          }
        }
      }
    return moves;
  }
}   