package minesweeper.consoleui;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import minesweeper.UserInterface;
import minesweeper.core.Clue;
import minesweeper.core.Field;
import minesweeper.core.Mine;
import minesweeper.core.Tile;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
    /** Playing field. */
    private Field field;
    
    /** Input reader. */
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    /**
     * Reads line of text from the reader.
     * @return line as a string
     */
    private String readLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }
    
    /**
     * Starts the game.
     * @param field field of mines and clues
     * @throws IOException 
     */
    @Override
	public void newGameStarted(Field field)  {
        this.field = field;
        do {
            update();
            processInput();
            throw new UnsupportedOperationException("Resolve the game state - winning or loosing condition.");
        } while(true);
    }
    
    /**
     * Updates user interface - prints the field.
     */
    @Override
	public void update() {
    	
    	System.out.print("   ");
    	for (int i = 0; i < field.getColumnCount(); i++) {
    		System.out.printf("%d ", 0 + i );
    		
		}
    	System.out.println();
    	
    	
    	for (int i = 0; i < field.getRowCount(); i++) {
    		System.out.printf("%c ", 'A' + i );
			for (int j = 0; j < field.getColumnCount(); j++) {
				if( field.getTile(i,j).getState()  == Tile.State.CLOSED) {
					System.out.print(" -");
				}
				else if(field.getTile(i, j).getState() == Tile.State.MARKED) {
					System.out.print(" M");
				}
				else {
					if(field.getTile(i, j) instanceof Mine) {
					System.out.print(" X");
				}else if( field.getTile(i, j) instanceof Clue) {
					Clue clues = (Clue) field.getTile(i, j);
					System.out.print(" " + clues.getValue());
				}
				}
			}	
			System.out.println();
		}
    	
    	
    	
    	
    	
        //throw new UnsupportedOperationException("Method update not yet implemented");
    }
    
    /**
     * Processes user input.
     * Reads line from console and does the action on a playing field according to input string.
     * @throws IOException 
     */
    private void processInput()  {
    	
    	System.out.println("Please enter your selection <X> Exit, <MA1> Mark, <OB4> OPEN: ");
    	String read;
		try {
			read = input.readLine();
			Pattern pat = Pattern.compile("O([A-I])([0-8])");
	    	Matcher m = pat.matcher(read);
	    	if(m.matches()) {
	    		m.group(0);
	    	}
	    	
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
   //throw new UnsupportedOperationException("Method processInput not yet implemented");
    }
}
