package boards;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class frmChessBoard extends JFrame implements MouseListener

{
    private JPanel[][] pnlChessCells = new JPanel[8][8];

    private JPanel pnlMain = new JPanel(new GridLayout(8,8));

    private String[][] strChessBoard = new String[][] { {"RB", "NB", "BB", "QB", "KB", "BB", "NB", "RB" }, {"PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB"}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"PW", "PW", "PW", "PW", "PW", "PW", "PW", "PW"}, {"RW", "NW", "BW", "QW", "KW", "BW", "NW", "RW"} };
    //Altered ny Kim Sandberg
    private Image rookBlack = new ImageIcon(System.getProperty("user.dir") + "/graphics/RookBlack.png").getImage().getScaledInstance(  35,35,Image.SCALE_SMOOTH);
    //Altered ny Kim Sandberg
    private Image rookWhite = new ImageIcon(System.getProperty("user.dir") + "/graphics/RookWhite.png").getImage().getScaledInstance(  35,35,Image.SCALE_SMOOTH);
    //Altered ny Kim Sandberg
    private Image bishopBlack = new ImageIcon(System.getProperty("user.dir") + "/graphics/BishopBlack.png").getImage().getScaledInstance(  35,35,Image.SCALE_SMOOTH);
    //Altered ny Kim Sandberg
    private Image bishopWhite = new ImageIcon(System.getProperty("user.dir") + "/graphics/BishopWhite.png").getImage().getScaledInstance(  35,35,Image.SCALE_SMOOTH);
    //Altered ny Kim Sandberg
    private Image knightBlack = new ImageIcon(System.getProperty("user.dir") + "/graphics/KnightBlack.png").getImage().getScaledInstance(  35,35,Image.SCALE_SMOOTH);
    //Altered ny Kim Sandberg
    private Image knightWhite = new ImageIcon(System.getProperty("user.dir") + "/graphics/KnightWhite.png").getImage().getScaledInstance(  35,35,Image.SCALE_SMOOTH);
    //Altered ny Kim Sandberg
    private Image kingBlack = new ImageIcon(System.getProperty("user.dir") + "/graphics/KingBlack.png").getImage().getScaledInstance(  35,35,Image.SCALE_SMOOTH);
    //Altered ny Kim Sandberg
    private Image kingWhite = new ImageIcon(System.getProperty("user.dir") + "/graphics/KingWhite.png").getImage().getScaledInstance(  35,35,Image.SCALE_SMOOTH);
    //Altered ny Kim Sandberg
    private Image queenBlack = new ImageIcon(System.getProperty("user.dir") + "/graphics/QueenBlack.png").getImage().getScaledInstance(  35,35,Image.SCALE_SMOOTH);
    //Altered ny Kim Sandberg
    private Image queenWhite = new ImageIcon(System.getProperty("user.dir") + "/graphics/QueenWhite.png").getImage().getScaledInstance(  35,35,Image.SCALE_SMOOTH);
    //Altered ny Kim Sandberg
    private Image pawnBlack = new ImageIcon(System.getProperty("user.dir") + "/graphics/PawnBlack.png").getImage().getScaledInstance(  35,35,Image.SCALE_SMOOTH);
    //Altered ny Kim Sandberg
    private Image pawnWhite = new ImageIcon(System.getProperty("user.dir") + "/graphics/PawnWhite.png").getImage().getScaledInstance(  35,35,Image.SCALE_SMOOTH);
    //Altered ny Kim Sandberg
    private boolean boolMoveSelection = false, bWhite = true, bMyTurn = true;

    private Point pntMoveFrom;
    private Point pntMoveTo;

    private Container c;

    public static void main(String[] args)

    {

        final frmChessBoard app = new frmChessBoard();

    }

    // the whole constructor is for setting up the UI of the form

    public frmChessBoard()

    {

        c = getContentPane();

        setBounds(100, 100, 470, 495);

        setBackground(new Color(204, 204, 204));

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle("Tutorial: How to Draw a Chess Board in Java");

        setResizable(false);

        c.setLayout(null);

        pnlMain.setBounds(3, 3, 460, 460);

        pnlMain.setBackground(new Color(255, 255, 255));

        c.add(pnlMain);

        this.drawChessBoard();

        this.arrangeChessPieces();

        show();

    }

    // This method captures the move on the chess board and then make

    // it happen, logically and physically; also, it sends the move to

    // the other client

    public void mouseClicked(MouseEvent e)

    {

        if(bMyTurn)

        {

            Object source = e.getSource();

            JPanel pnlTemp = (JPanel)source;

            int intX = (pnlTemp.getX()/57);

            int intY = (pnlTemp.getY()/57);

            this.boolMoveSelection = !this.boolMoveSelection;

            if(this.boolMoveSelection)

            {

                this.pntMoveFrom = new Point(intX, intY);

                if(this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().trim().equals(""))

                    this.boolMoveSelection = !this.boolMoveSelection;

                if((!this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().trim().equals("")) &&

                        this.bWhite && this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().charAt(1) == 'B')

                    this.boolMoveSelection = !this.boolMoveSelection;

                if((!this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().trim().equals("")) &&

                        !this.bWhite && this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().charAt(1) == 'W')

                    this.boolMoveSelection = !this.boolMoveSelection;

                if(this.boolMoveSelection)

                    this.makeChessPieceDifferent(true);

            }

            else

            {

                this.pntMoveTo = new Point(intX, intY);

                if(!this.pntMoveFrom.equals(this.pntMoveTo))

                {

                    if(      this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().trim() != "")

                        if(this.isMoveValid())

                        {

                            this.strChessBoard[this.pntMoveTo.y][this.pntMoveTo.x] = this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString();

                            this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x] = "  ";

                            this.moveChessPiece();

                        }

                        else

                        {

                            JOptionPane.showMessageDialog(this, "Invalid Move Request.", "Warning", JOptionPane.ERROR_MESSAGE);

                            this.makeChessPieceDifferent(false);

                        }

                }

                else

                    this.makeChessPieceDifferent(false);

            }

        }

    }

    // This method checks if attempted move is valid or not
    // TODO: Kim Sandberg: Needs to couple it to the move generator.

    private boolean isMoveValid()

    {

        boolean isMoveValid = true;

        return isMoveValid;

    }

    // This method makes the selected chess piece looks like selected

    private void makeChessPieceDifferent(boolean bSelected)

    {

        for(int z = 0; z < this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponentCount(); z++)

            if(this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponent(z).getClass().toString().indexOf("JLabel") > -1)

            {

                JLabel lblTemp = (JLabel)this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponent(z);

                lblTemp.setEnabled(!bSelected);

            }

    }

    // If class level variables Point-From and Point-To are set,

    // then this method actually moves a piece, if any exists, from

    // one cell to the other

    private void moveChessPiece()

    {

        for(int z = 0; z < this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].getComponentCount(); z++)

            if(this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].getComponent(z).getClass().toString().indexOf("JLabel") > -1)

            {

                this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].remove(z);

                this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].repaint();

            }

        for(int z = 0; z < this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponentCount(); z++)

            if(this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponent(z).getClass().toString().indexOf("JLabel") > -1)

            {

                this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].remove(z);

                this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].repaint();

            }

        this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].add(this.getPieceObject(this.strChessBoard[this.pntMoveTo.y][this.pntMoveTo.x]), BorderLayout.CENTER);

        this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].validate();

        //calculate board
        //TODO depends on how we represent the board data structure.

    }

    // Given the code of a piece as a string, this method instantiates

    // a label object with the right image inside it

    private JLabel getPieceObject(String strPieceName)
    //Altered ny Kim Sandberg
    {

        JLabel lblTemp;
        ImageIcon icon=null;

        if(strPieceName.equals("RB"))

            icon = new ImageIcon(this.rookBlack);

        else if(strPieceName.equals("BB"))

            icon = new ImageIcon(this.bishopBlack);

        else if(strPieceName.equals("NB"))

            icon = new ImageIcon(this.knightBlack);

        else if(strPieceName.equals("QB"))

            icon = new ImageIcon(this.queenBlack);

        else if(strPieceName.equals("KB"))

            icon = new ImageIcon(this.kingBlack);

        else if(strPieceName.equals("PB"))

            icon = new ImageIcon(this.pawnBlack);

        else if(strPieceName.equals("RW"))

            icon = new ImageIcon(this.rookWhite);

        else if(strPieceName.equals("BW"))

            icon = new ImageIcon(this.bishopWhite);

        else if(strPieceName.equals("NW"))

            icon = new ImageIcon(this.knightWhite);

        else if(strPieceName.equals("QW"))

            icon = new ImageIcon(this.queenWhite);

        else if(strPieceName.equals("KW"))

            icon = new ImageIcon(this.kingWhite);


        else if(strPieceName.equals("PW")) {
            icon = new ImageIcon(this.pawnWhite);
        }

        else {

            lblTemp = new JLabel();
        }
        lblTemp = new JLabel(icon);
        return lblTemp;

    }

    // This method reads strChessBoard two-dimensional array of string

    // and places chess pieces at their right positions

    private void arrangeChessPieces()

    {

        for(int y = 0; y < 8; y++)

            for(int x = 0; x < 8; x++)

            {
                this.pnlChessCells[y][x].add(this.getPieceObject(strChessBoard[y][x]), BorderLayout.CENTER);
                this.pnlChessCells[y][x].validate();
            }

    }

    // This method draws chess board, i.e. black and white cells on the board

    private void drawChessBoard()

    {

        for (int y = 0; y < 8; y++)

            for (int x = 0; x < 8; x++)

            {

                pnlChessCells[y][x] = new JPanel(new BorderLayout());

                pnlChessCells[y][x].addMouseListener(this);

                pnlMain.add(pnlChessCells[y][x]);

                if (y % 2 == 0)

                    if (x % 2 != 0)

                        pnlChessCells[y][x].setBackground(Color.LIGHT_GRAY);

                    else

                        pnlChessCells[y][x].setBackground(Color.WHITE);

                else

                if (x % 2 == 0)

                    pnlChessCells[y][x].setBackground(Color.LIGHT_GRAY);

                else

                    pnlChessCells[y][x].setBackground(Color.WHITE);

            }

    }
    //Kim Sandberg
    public void doMove(String move){
        //Switch on chess lingo
            normalMove(move);
            enPesant(move);
            castling(move);
            convert(move);
    }
    //Kim Sandberg
    private void convert(String move) {
        //ex. g4e6
        String from = move.substring(0,2);
        String to = move.substring(2,4);
        int xCoordinateFrom = getXCoordinate(from);
        int yCoordinateFrom = getYCoordinate(from);
        int xCoordinateTo = getXCoordinate(to);
        int yCoordinateTo = getYCoordinate(to);
        this.pntMoveFrom = new Point(xCoordinateFrom, yCoordinateFrom);
        this.pntMoveTo = new Point(xCoordinateTo, yCoordinateTo);
        moveChessPiece();

    }

    private int getYCoordinate(String coordinate) {
        char yCoord = coordinate.charAt(1);
        return Character.getNumericValue(yCoord);
    }
    //Kim Sandberg
    private int getXCoordinate(String coordinate) {

        switch (coordinate.charAt(0)){
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
        }
        //Never gets here
        return 100;
    }

    private void castling(String move) {
    }

    private void enPesant(String move) {
    }

    private void normalMove(String move) {
    }

    public void mouseEntered(MouseEvent e){}

    public void mouseReleased(MouseEvent e){}

    public void mouseExited(MouseEvent e){}

    public void mousePressed(MouseEvent e){}



}