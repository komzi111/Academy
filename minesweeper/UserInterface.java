package minesweeper;

import java.io.IOException;

import minesweeper.core.Field;

public interface UserInterface {

	/**
	 * Starts the game.
	 * @param field field of mines and clues
	 * @throws IOException 
	 */
	void newGameStarted(Field field);

	/**
	 * Updates user interface - prints the field.
	 */
	void update();

}