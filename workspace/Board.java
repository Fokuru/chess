//
// Editor: Raley Wilkin
// Finished: 3/4/2025
// Does: All functions for the board, including moving and capturing for pieces
//


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

//You will be implmenting a part of a function and a whole function in this document. Please follow the directions for the 
//suggested order of completion that should make testing easier.
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
	// Resource location constants for piece images
    private static final String RESOURCES_WBISHOP_PNG = "wbishop.png";
	private static final String RESOURCES_BBISHOP_PNG = "bbishop.png";
	private static final String RESOURCES_WKNIGHT_PNG = "wknight.png";
	private static final String RESOURCES_BKNIGHT_PNG = "bknight.png";
	private static final String RESOURCES_WROOK_PNG = "wrook.png";
	private static final String RESOURCES_BROOK_PNG = "brook.png";
	private static final String RESOURCES_WKING_PNG = "wking.png";
	private static final String RESOURCES_BKING_PNG = "bking.png";
	private static final String RESOURCES_BQUEEN_PNG = "bqueen.png";
	private static final String RESOURCES_WQUEEN_PNG = "wqueen.png";
	private static final String RESOURCES_WPAWN_PNG = "wpawn.png";
	private static final String RESOURCES_BPAWN_PNG = "bpawn.png";
    // Added anti pawn pictures
    private static final String RESOURCES_WANTIPAWN_PNG = "wantipawn.png";
	private static final String RESOURCES_BANTIPAWN_PNG = "bantipawn.png";
    // Added slime pictures
    private static final String RESOURCES_WSLIME_PNG = "whiteSlime.png";
	private static final String RESOURCES_BSLIME_PNG = "blackSlime.png";
    // Added slime pictures
    private static final String RESOURCES_WASSASSIN_PNG = "WhiteAssassin.png";
	private static final String RESOURCES_BASSASSIN_PNG = "BlackAssassin.png";
    // Added Joker Pictures
    private static final String RESOURCES_WJOKER_PNG = "WJoker.png";
	private static final String RESOURCES_BJOKER_PNG = "BJoker.png";
	
	// Logical and graphical representations of board
	private final Square[][] board;
    private final GameWindow g;
 
    //contains true if it's white's turn.
    private boolean whiteTurn;

    //if the player is currently dragging a piece this variable contains it.
    private Piece currPiece;
    private Square fromMoveSquare;
    private Square wKingSquare;
    private Square bKingSquare;
    
    //used to keep track of the x/y coordinates of the mouse.
    private int currX;
    private int currY;

    // Move Type for Joker:
    int moveType = (int) (Math.random() * (3 - 0 + 1) + 0);
    
    public Square[][] getArrayArray()
    {
        return board;
    }

    
    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //TO BE IMPLEMENTED FIRST
     
      //for (.....)  
//        	populate the board with squares here. Note that the board is composed of 64 squares alternating from 
//        	white to black.

        // Makes all the colored squares, makes a checker board pattern
        int count = 0;
        for (int r = 0; r < board.length; r++)
        {
            if (r%2 == 0)
            {
                count = 0;
            }
            else 
            {
                count = 1;
            }

            for (int c = 0; c < board[r].length; c++)
            {
                if (count%2 == 0)
                {
                    board[r][c] = new Square(this, true, r, c);
                }
                else if (count%2 == 1)
                {
                    board[r][c] = new Square(this, false, r, c);
                }
                this.add(board[r][c]);
                count++;
            }
        }

        initializePieces();

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        whiteTurn = true;

    }

    
	//set up the board such that the black pieces are on one side and the white pieces are on the other.
	//since we only have one kind of piece for now you need only set the same number of pieces on either side.
	//it's up to you how you wish to arrange your pieces.
    private void initializePieces() {
    	
        // Places anti pawns where regular pawns would be
        for (int i = 0; i < 8; i++)
        {
            board[6][i].put(new AntiPawn(false, RESOURCES_BANTIPAWN_PNG));
        }

        for (int i = 0; i < 8; i++)
        {
            board[1][i].put(new AntiPawn(true, RESOURCES_WANTIPAWN_PNG));
        }

        board[0][3].put(new King(true, RESOURCES_WKING_PNG));
        board[7][3].put(new King(false, RESOURCES_BKING_PNG));
        board[0][4].put(new Queen(true, RESOURCES_WQUEEN_PNG));
        board[7][4].put(new Queen(false, RESOURCES_BQUEEN_PNG));
        board[0][1].put(new Slime(true, RESOURCES_WSLIME_PNG));
        board[7][6].put(new Slime(false, RESOURCES_BSLIME_PNG));
        board[0][2].put(new Assassin(true, RESOURCES_WASSASSIN_PNG));
        board[7][5].put(new Assassin(false, RESOURCES_BASSASSIN_PNG));
        board[0][6].put(new Slime(true, RESOURCES_WSLIME_PNG));
        board[7][1].put(new Slime(false, RESOURCES_BSLIME_PNG));
        board[0][5].put(new Assassin(true, RESOURCES_WASSASSIN_PNG));
        board[7][2].put(new Assassin(false, RESOURCES_BASSASSIN_PNG));
        board[0][0].put(new Joker(true, RESOURCES_WJOKER_PNG));
        board[7][0].put(new Joker(false, RESOURCES_BJOKER_PNG));
        board[0][7].put(new Joker(true, RESOURCES_WJOKER_PNG));
        board[7][7].put(new Joker(false, RESOURCES_BJOKER_PNG));

    }

    public Square[][] getSquareArray() {
        return this.board;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    public void setCurrPiece(Piece p) {
        this.currPiece = p;
    }

    public Piece getCurrPiece() {
        return this.currPiece;
    }

    @Override
    public void paintComponent(Graphics g) {
     
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square sq = board[x][y];
                if(sq == fromMoveSquare)
                	 sq.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
                sq.paintComponent(g);
                
            }
        }
    	if (currPiece != null) {
            if ((currPiece.getColor() && whiteTurn)
                    || (!currPiece.getColor()&& !whiteTurn)) {
                final Image img = currPiece.getImage();
                g.drawImage(img, currX, currY, null);
            }
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();

        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (sq.isOccupied()) {
            currPiece = sq.getOccupyingPiece();
            fromMoveSquare = sq;
            if (!currPiece.getColor() && whiteTurn)
                return;
            if (currPiece.getColor() && !whiteTurn)
                return;
            sq.setDisplay(false);
        }
        repaint();
    }

    //TO BE IMPLEMENTED!
    //should move the piece to the desired location only if this is a legal move.
    //use the pieces "legal move" function to determine if this move is legal, then complete it by
    //moving the new piece to it's new board location. 
    @Override
    public void mouseReleased(MouseEvent e) {
        Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        // System.out.println(e.getX() + " " + e.getY());
        System.out.println(isInCheck(whiteTurn));

        if (endSquare != null)
        {
                
                if (currPiece != null)
                {

                //using currPiece
                for(Square [] row: board){
                    for(Square s: row){
                    s.setBorder(null);
                    }
                }

                
                
                // Figures out if when the mouse was released that the piece being moved could move and then moves it if new position is legal
                ArrayList <Square> movesAllowed = currPiece.getLegalMoves(this, fromMoveSquare);

                for (int i = 0; i < movesAllowed.size(); i++)
                {
                    if (movesAllowed.get(i) == endSquare && currPiece.getColor() == whiteTurn)
                    {
                        Piece from = endSquare.getOccupyingPiece();
                        fromMoveSquare.removePiece();
                        endSquare.put(currPiece);
                        //System.out.println(isInCheck(whiteTurn));

                        if (isInCheck(whiteTurn) == true)
                        {
                            fromMoveSquare.put(currPiece);
                            endSquare.put(from);
                            for (int n = 0; n < 8; n++)
                            {
                                for (int k = 0; k < 8; k++)
                                {
                                    if (board[n][k].getOccupyingPiece() != null && board[n][k].getOccupyingPiece().getColor() == whiteTurn && board[n][k].getOccupyingPiece() instanceof King)
                                    {
                                        board[n][k].setBorder(BorderFactory.createLineBorder(Color.red, 2));
                                    }
                                }
                            }
                        }
                        else
                        {
                            whiteTurn = !whiteTurn;
                            fromMoveSquare.setBorder(null);
                            moveType = (int) (Math.random() * (3 - 0 + 1) + 0);
                        }   
                    }
                 
                }



            
                
        }
        
      

        }
        fromMoveSquare.setDisplay(true);
        currPiece = null;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (currPiece != null)
        {
            currX = e.getX() - 24;
            currY = e.getY() - 24;


            // Finds all moveable squares using getLegalMoves and puts a red border on them
            ArrayList <Square> moves = currPiece.getLegalMoves(this, fromMoveSquare);
            for(int i = 0; i < moves.size(); i++)
            {
                if (moves.get(i) != null)
                {
                moves.get(i).setBorder(BorderFactory.createLineBorder(Color.red, 2));
                }
            }

            // Finds all capturable squares using getLegalMoves and puts a yellow border on them
            ArrayList <Square> caps = currPiece.getControlledSquares(board, fromMoveSquare);
            for(int i = 0; i < caps.size(); i++)
            {
                if (caps.get(i) != null)
                {
                    caps.get(i).setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
                }
            }
            
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public boolean isInCheck(boolean color)
    {
        ArrayList <Square> contPiece = new ArrayList<Square>();
        
        
        for (int i = 0; i < 8; i++)
        {
            for (int k = 0; k < 8; k++)
            {
                if (board[i][k].getOccupyingPiece() != null && board[i][k].getOccupyingPiece().getColor() != color)
                {
                    ArrayList <Square> place = board[i][k].getOccupyingPiece().getControlledSquares(board, board[i][k]);
                    for (int l = 0; l < place.size(); l++)
                    {
                        contPiece.add(place.get(l));
                    }
                }
            }
        }

        boolean check = false;
        for (int p = 0; p < contPiece.size(); p++)
        {
            if (contPiece.get(p).getOccupyingPiece() != null &&  contPiece.get(p).getOccupyingPiece().getColor() == color && contPiece.get(p).getOccupyingPiece() instanceof King)
            {
                check = true;  
            }
        }
        
        

        return check;
        
    }

}