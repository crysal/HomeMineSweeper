import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Minesweeper extends Applet implements MouseListener
{
        public Minesweeper() {
        }
    Button table[] [];      //Buttons
    boolean mine[] [];      //true = mine is present on button x,y
    boolean flag[] [];      //ture = flag is present at button x,y
    boolean exposed[] [];   //used for exposing 0. If true then a 0 has been exposed
    boolean checkwinbool[] [];  // if x,y = true then the button has a number on it or it is a mine (used for checking if game is over)
    int count = 0, minesRemaining;          //counting the number of mines placed
    String surmines;        //number of mines surrounding button x,y (is a string so that we can setLabel for the button)
    int randx, randy;       //random ints for mines
    int row = 16, col = 30, numberMines = 99, Setup = 3; //number of rows, columns, and mines
    int sizex = 780, sizey = 492;
    Font font;
    Panel P, P2, PB, P3;
    Label mines, TimeRemaning, NG;
    Button RestartE, RestartM, RestartH;
    GridLayout gl;
    public void init ()
    {
        setLayout (new BorderLayout ());
        gl = new GridLayout (row, col);
        P = new Panel (gl);
        font = new Font ("Arial Black", Font.BOLD, 17);
        setFont (font);
        P2 = new Panel (new BorderLayout ());
        P3 = new Panel ();
        TimeRemaning = new Label ("");
        NG = new Label ("New Game");
        RestartH = new Button ("Hard");
        RestartH.addMouseListener (this);
        RestartM = new Button ("Medium");
        RestartM.addMouseListener (this);
        RestartE = new Button ("Easy");
        RestartE.addMouseListener (this);
        PB = new Panel ();
        minesRemaining = numberMines;
        mines = new Label (Integer.toString (minesRemaining));
        table = new Button [row] [col];
        mine = new boolean [row] [col];
        flag = new boolean [row] [col];
        exposed = new boolean [row] [col];
        checkwinbool = new boolean [row] [col];
        for (int x = 0 ; x < row ; x++)
        {
            for (int y = 0 ; y < col ; y++)
            {
                table [x] [y] = new Button ();
                table [x] [y].addMouseListener (this);
                P.add (table [x] [y]);
            }
        }
        //these for loops set up the buttons and sets all the boolean arrays to = false
        add (P2, "North");
        add (P, "Center");
        P2.add (mines, "West");
        P2.add (P3, "North");
        P3.add (NG);
        P2.add (PB, "Center");
        PB.add (RestartE);
        PB.add (RestartM);
        PB.add (RestartH);
        P2.setBackground(Color.lightGray);
        NG.setBackground(Color.lightGray);
        NG.setForeground(Color.black);
        mines.setBackground(Color.lightGray);
        mines.setForeground(Color.white);
        Restart_Game (row, col, numberMines, row, col, sizex, sizey);
    }


    public void Restart_Game (int row, int col, int numberMines, int prow, int pcol, int sizex, int sizey)
    {
        //mBar.Restart (table, row, col);
        //mBar.SetupMenu ();
        setSize (sizex, sizey);
        invalidate();
        validate();
        gl.setRows (row);
        gl.setColumns (col);
        int count = 0;
        minesRemaining = numberMines;
        mines.setText (Integer.toString (minesRemaining));
        for (int x = 0 ; x < prow ; x++)
        {
            for (int y = 0 ; y < pcol ; y++)
            {
                P.remove (table [x] [y]);
            }
        }
        for (int x = 0 ; x < row ; x++)
        {
            for (int y = 0 ; y < col ; y++)
            {

                table [x] [y].setEnabled (true);
                table [x] [y].setLabel ("");
                table [x] [y].setBackground (Color.CYAN);
                table [x] [y].setForeground (Color.white);
                P.add (table [x] [y]);
                mine [x] [y] = false;
                flag [x] [y] = false;
                exposed [x] [y] = false;
                checkwinbool [x] [y] = false;
            }
        }
        setSize (sizex, sizey);
        invalidate();
        validate();
        //adds the mines to random places on the grid
        while (count < numberMines)
        {
            randx = (int) (Math.random () * (row));
            randy = (int) (Math.random () * (col));
            if (mine [randx] [randy] == false)
            {
                mine [randx] [randy] = true;
                checkwinbool [randx] [randy] = true;
                count++;
            }
        }
    }
    
    
    public void keyTyped(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_1){
            System.out.println("1");
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
        	System.out.println("2");
        }
    	if (e.getKeyCode() == KeyEvent.VK_3) {
    		System.out.println("3");
    		}
   }
    Random rand = new Random();
    
    float r = rand.nextFloat();
    float g = rand.nextFloat();
    float b = rand.nextFloat();
    
    Color randomColor = new Color(r, g, b);
   

      

    public void mouseClicked (MouseEvent e)
    {
        int prow = 0, pcol = 0;
        if (e.getSource () == RestartE)
        {
            row = 10;
            col = 10;
            numberMines = 10;
            sizex = 300;
            sizey = 346;
            if (Setup == 2)
            {
                prow = 16;
                pcol = 16;
                Setup = 1;
            }
            else if (Setup == 3)
            {
                prow = 16;
                pcol = 30;
                Setup = 1;
            }
        }
        if (e.getSource () == RestartM)
        {
            row = 16;
            col = 16;
            numberMines = 40;
            sizex = 496;
            sizey = 540;
            if (Setup == 1)
            {
                prow = 10;
                pcol = 10;
                Setup = 2;
            }
            else if (Setup == 3)
            {
                prow = 16;
                pcol = 30;
                Setup = 2;
            }
        }
        if (e.getSource () == RestartH)
        {
            row = 16;
            col = 30;
            numberMines = 99;
            sizex = 780;
            sizey = 492;
            if (Setup == 1)
            {
                prow = 10;
                pcol = 10;
                Setup = 3;
            }
            else if (Setup == 2)
            {
                prow = 16;
                pcol = 16;
                Setup = 3;
            }
        }
        if (e.getSource () == RestartE || e.getSource () == RestartM || e.getSource () == RestartH)
            Restart_Game (row, col, numberMines, prow, pcol, sizex, sizey);
        boolean gameover = false;
        for (int x = 0 ; x < row ; x++)
        {
            for (int y = 0 ; y < col ; y++)
            {
                if (e.getSource () == table [x] [y])
                {
                    if (e.getButton () == MouseEvent.BUTTON1 && flag [x] [y] == false) //if left click, and there is no flag on the button
                    {
                        if (mine [x] [y] == true)  // if you you click on a mine, results in game over
                        {
                            table [x] [y].setLabel ("*");
                            gameover ();
                            table [x] [y].setBackground (Color.DARK_GRAY);
                            gameover = true;
                            break;

                        }
                        exposed [x] [y] = true;
                        checkwinbool [x] [y] = true; // these set to true mean that the button has been clicked
                        surmines = Integer.toString (surroundingmines (x, y)); //gets the number of surrounding buttons with mines and sets it to a string so that it is possible to setLabel
                        table [x] [y].setLabel (surmines); // sets the label to be the number of mines in the 8 surrounding buttons
                        if (surroundingmines (x, y) == 0)
                        {
                            check (x, y);          //calls a recursive method so that if a "0" is there the surrounding 8 buttins must be exposed and if one of those is "0" it too must be exposed and so on
                        }
                    }
                    else if (e.getButton () == MouseEvent.BUTTON3)  // if it is a right click
                    {
                        if (flag [x] [y] == true) //if there is a flag already present set it so that there is no flag
                        {
                            table [x] [y].setLabel ("");
                            table [x] [y].setForeground (Color.black);
                            flag [x] [y] = false;
                            checkwinbool [x] [y] = false;
                            minesRemaining++;
                        }
                        else if (checkwinbool [x] [y] == false || mine [x] [y] == true) //if there is no flag, set it so there is a flag
                        {
                            table [x] [y].setLabel ("|>");
                            table [x] [y].setForeground (Color.RED);
                            flag [x] [y] = true;
                            checkwinbool [x] [y] = true;
                            minesRemaining--;
                        }
                        mines.setText (Integer.toString (minesRemaining));

                    }
                    else if (e.getButton () == MouseEvent.BUTTON2 && flag [x] [y] == false && checkwinbool [x] [y] == true && mine [x] [y] == false) //if both left and right click at the same time
                    { //only executes if there is no flag, it has been exposed, and no mine
                        if (flags (x, y) == surroundingmines (x, y)) //checks that the number of flags around x,y = the number of mines around x,y
                        {
                            for (int q = x - 1 ; q <= x + 1 ; q++)
                            {
                                for (int w = y - 1 ; w <= y + 1 ; w++)
                                {
                                    if (q < 0 || w < 0 || q >= row || w >= col) // makes sure that it wont have an error for buttons next to the wall
                                        break;
                                    if (mine [q] [w] == false && flag [q] [w] == true) //if there is no mine, but there is a flag its game over
                                    {
                                        gameover ();
                                        gameover = true;
                                        break;
                                    }
                                }
                            }
                            if (gameover == false)
                            {
                                expose (x, y);     //eposes the surrounding 8 buttons
                                check (x, y);      //checks if any of those are "0" and calls the recursive method
                            }
                        }
                    }
                    if (gameover == false) //this just calls the method for changing the colors of the buttons if they have been clicked
                    											//this in my opinion is the most impotent function in this code
                        clicked ();
                }

            }
        }
        checkwin ();
    }


    public void clicked ()     //changes the color of the buttons and if x,y is "0" set the label to nothing
    {
        for (int x = 0 ; x < row ; x++)
        {
            for (int y = 0 ; y < col ; y++)
            {
                if (checkwinbool [x] [y] == true && flag [x] [y] == false && mine [x] [y] == false)
                    table [x] [y].setBackground (Color.darkGray);
                if (flag [x] [y] == false && surroundingmines (x, y) == 0)
                    table [x] [y].setLabel ("");
            }
        }
    }


    public int flags (int x, int y)  // get the number of surrounding flags
    {
        int surFlags = 0;
        for (int q = x - 1 ; q <= x + 1 ; q++)
        {
            for (int w = y - 1 ; w <= y + 1 ; w++)
            {
                while (true)
                {
                    if (q < 0 || w < 0 || q >= row || w >= col) // makes sure that it wont have an error for buttons next to the wall
                        break;

                    if (flag [q] [w] == true)
                    {
                        surFlags++;
                    }
                    break;
                }
            }
        }
        return surFlags;
    }


    public int surroundingmines (int x, int y)  // checks surrounding 8 squares for number of mines (it does include itself, but has already been checked for a mine so it won't matter)
    {
        int surmines = 0;
        for (int q = x - 1 ; q <= x + 1 ; q++)
        {
            for (int w = y - 1 ; w <= y + 1 ; w++)
            {
                while (true)
                {
                    if (q < 0 || w < 0 || q >= row || w >= col) // makes sure that it wont have an error for buttons next to the wall
                        break;
                    if (mine [q] [w] == true)
                        surmines++;
                    break;
                }
            }
        }
        return surmines;
    }


    public void expose (int x, int y)  // exposes the surrounding 8 buttons
    {
        String surrmines;
        exposed [x] [y] = true;
        for (int q = x - 1 ; q <= x + 1 ; q++)
        {
            for (int w = y - 1 ; w <= y + 1 ; w++)
            {
                while (true)
                {
                    if (q < 0 || w < 0 || q >= row || w >= col) // makes sure that it wont have an error for buttons next to the wall
                        break;
                    if (flag [q] [w] == true)
                        break;

                    checkwinbool [q] [w] = true;
                    surrmines = Integer.toString (surroundingmines (q, w));
                    table [q] [w].setLabel (surrmines);
                    break;

                }
            }
        }
    }


    public void surr (int x, int y)  //this is what checks if a surrounding button has "0" is so expose it and check around the exposed buttons until there is no more "0"'s
    {
        String surrmines;
        for (int q = x - 1 ; q <= x + 1 ; q++)
        {
            for (int w = y - 1 ; w <= y + 1 ; w++)
            {
                while (true)
                {
                    if (q < 0 || w < 0 || q >= row || w >= col) // makes sure that it wont have an error for buttons next to the wall
                        break;
                    if (flag [q] [w] == true)
                        break;
                    if (exposed [q] [w] == false && surroundingmines (q, w) == 0)
                    {
                        expose (q, w);
                        check (q, w);
                    }
                    break;
                }
            }
        }
    }


    public void check (int x, int y)  //calls the surr method but is neccesary because of the expose first
    {
        expose (x, y);
        surr (x, y);
    }


    public void checkwin ()    //checks if all the button without mines have been pressed
    {
        boolean allexposed = true;
        for (int x = 0 ; x < row ; x++)
        {
            for (int y = 0 ; y < col ; y++)
            {
                if (flag [x] [y] == true && mine [x] [y] == false)
                    allexposed = false;
                if (checkwinbool [x] [y] == false)
                {
                    allexposed = false;
                    break;
                }
            }
        }
        if (allexposed != false)
        {
        	
       	
        	
            gameover ();
            int x2 = (int) col / 2;
            int y2 = (int) row / 2;
            table [y2] [x2 - 4].setLabel ("Y");
            table [y2] [x2 - 3].setLabel ("O");
            table [y2] [x2 - 2].setLabel ("U");
            table [y2] [x2 - 1].setLabel ("");
            table [y2] [x2 + 0].setLabel ("");
            table [y2] [x2 + 1].setLabel ("W");
            table [y2] [x2 + 2].setLabel ("I");
            table [y2] [x2 + 3].setLabel ("N");
            table [y2] [x2 + 4].setLabel ("!");
            for (int i = -4 ; i < 5 ; i++)
            {

            	
                table [y2] [x2 + i].setBackground (Color.black);
                table [y2] [x2 + i].setForeground (Color.white);
            }
        }
    }


    public void gameover ()  // is called if mine is clicked or on the double click if flag is not on a mine
    {
        for (int x = 0 ; x < row ; x++)
        {
            for (int y = 0 ; y < col ; y++)
            {
                if (mine [x] [y] == true)
                {
                    table [x] [y].setLabel ("*"); //exposes all mines
                    table [x] [y].setBackground (Color.RED);
                }
                table [x] [y].setEnabled (false); //disable all buttons
            }
        }
    	int RCG = 1;
    	int DRC = RCG / 100;
        int x2 = (int) col / 2;
        int y2 = (int) row / 2;
        table [y2] [x2 - 4].setLabel ("G");
        table [y2] [x2 - 3].setLabel ("A");
        table [y2] [x2 - 2].setLabel ("M");
        table [y2] [x2 - 1].setLabel ("E");
        table [y2] [x2].setLabel ("");
        table [y2] [x2 + 1].setLabel ("O");
        table [y2] [x2 + 2].setLabel ("V");
        table [y2] [x2 + 3].setLabel ("E");
        table [y2] [x2 + 4].setLabel ("R");
        table [y2] [x2 + 5].setLabel ("!");
        for (int i = -4 ; i < 6 ; i++)
        {
        	
        	while (RCG > 1000)
        	if (RCG > 1)
        	{
                table [y2] [x2 + i].setBackground (Color.black);
                table [y2] [x2 + i].setForeground (Color.white);
                RCG = RCG+1;
                table [y2] [x2 + i].setBackground (Color.red);
                table [y2] [x2 + i].setForeground (Color.green);
                System.out.println(DRC);
                
        	}
            table [y2] [x2 + i].setBackground (Color.WHITE);
            table [y2] [x2 + i].setForeground (Color.BLACK);
        }
    }


    //These are not used but are nesecary for mouseListener
    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }


    public void mousePressed (MouseEvent e)
    {
    }


    public void mouseReleased (MouseEvent e)
    {
    }
    
    
    //small space for creative use
    {
    	int loading=5,loading2=2;
    	while (loading < 5){
    		loading=loading/loading2;
    		System.out.println(loading+"% Complete");
    		loading=loading*loading2+1;
    	}
    }
    
}


