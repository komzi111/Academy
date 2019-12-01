package minesweeper.core;

import java.util.Random;
import minesweeper.core.Tile.State;

/**
 * Field represents playing field and game logic.
 */
public class Field {
	/**
	 * Playing field tiles.
	 */
	private final Tile[][] tiles;

	/**
	 * Field row count. Rows are indexed from 0 to (rowCount - 1).
	 */
	private final int rowCount;

	/**
	 * Column count. Columns are indexed from 0 to (columnCount - 1).
	 */
	private final int columnCount;

	/**
	 * Mine count.
	 */
	private final int mineCount;

	/**
	 * Game state.
	 */
	private GameState state = GameState.PLAYING;

	/**
	 * Constructor.
	 *
	 * @param rowCount    row count
	 * @param columnCount column count
	 * @param mineCount   mine count
	 */
	public Field(int rowCount, int columnCount, int mineCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.mineCount = mineCount;
		tiles = new Tile[rowCount][columnCount];

		// generate the field content
		generate();
	}

	/**
	 * Opens tile at specified indeces.
	 *
	 * @param row    row number
	 * @param column column number
	 */
	public void openTile(int row, int column) {
		Tile tile = tiles[row][column];

		if (tile.getState() == Tile.State.CLOSED) {
			tile.setState(Tile.State.OPEN);
			if (tile instanceof Mine) {
				setState(GameState.FAILED);
				return;
			}

			if (isSolved()) {
				setState(GameState.SOLVED);
				return;
			}
		}
	}

	/**
	 * Marks tile at specified indeces.
	 *
	 * @param row    row number
	 * @param column column number
	 */
	public void markTile(int row, int column) {

		if (tiles[row][column].getState() == Tile.State.CLOSED) {
			tiles[row][column].setState(Tile.State.MARKED);
		}

		if (tiles[row][column].getState() == Tile.State.MARKED) {
			tiles[row][column].setState(Tile.State.CLOSED);

		}

		// throw new UnsupportedOperationException("Method markTile not yet
		// implemented");
	}

	/**
	 * Generates playing field.
	 */
	private void generate() {

		Random r = new Random();

		int counter = 0;
		while (counter <  mineCount) {
			int randomRow = r.nextInt(rowCount);
			int randomCount = r.nextInt(columnCount);

			if (tiles[randomRow][randomCount] == null) {
				tiles[randomRow][randomCount] = new Mine();
				counter++;
			}
			
		}

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (tiles[i][j] == null) {
					tiles[i][j] = new Clue(countAdjacentMines(i, j));
				}
			}

		}

		//throw new UnsupportedOperationException("Method generate not yet implemented");
	}

	/**
	 * Returns true if game is solved, false otherwise.
	 *
	 * @return true if game is solved, false otherwise
	 */
	private boolean isSolved() {

		return ((rowCount * columnCount) - getNumberOf(Tile.State.OPEN) == mineCount);

		// throw new UnsupportedOperationException("Method isSolved not yet
		// implemented");
	}

	/**
	 * Returns number of adjacent mines for a tile at specified position in the
	 * field.
	 *
	 * @param row    row number.
	 * @param column column number.
	 * @return number of adjacent mines.
	 */
	private int countAdjacentMines(int row, int column) {
		int count = 0;
		for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
			int actRow = row + rowOffset;
			if (actRow >= 0 && actRow < getRowCount()) {
				for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
					int actColumn = column + columnOffset;
					if (actColumn >= 0 && actColumn < getColumnCount()) {
						if (tiles[actRow][actColumn] instanceof Mine) {
							count++;
						}
					}
				}
			}
		}

		return count;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getMineCount() {
		return mineCount;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	private int getNumberOf(Tile.State state) {
		int counter = 0;

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (tiles[i][j].getState() == state) {
					++counter;
				}
			}
		}

		return counter;
	}
	
	
	 public Tile getTile(int row, int column) {
		 return tiles[row][column];
	 }


}
