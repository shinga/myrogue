/**
 * 
 */
package myrogue;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Arthur
 *
 */
public class GenerateDungeon {
	private CharKey key = new CharKey();
	private Random rand = new Random();

	private int numRooms;
	private static final int DUNGEON_WIDTH = 120;
	private static final int DUNGEON_HEIGHT = 30;
	
	private static final int MAX_SIZE = (DUNGEON_WIDTH * DUNGEON_HEIGHT)/10*4;
	
	private static final int MIN_ROOM_WIDTH = 8;
	private static final int MAX_ROOM_WIDTH = (DUNGEON_WIDTH)/4;
	
	private static final int MIN_ROOM_HEIGHT = 4;
	private static final int MAX_ROOM_HEIGHT = (DUNGEON_HEIGHT)/2;
	private Room[] rooms;
	
	private final char[][] dungeon;

	public GenerateDungeon(int num) {
		numRooms = num;
		dungeon = new char[DUNGEON_HEIGHT][DUNGEON_WIDTH];
		for (int i=0; i<dungeon.length; i++) {
			for (int j=0; j<dungeon[i].length; j++) {
				// NORTHWEST WALLS
				if (i == 0 || i == (dungeon.length - 1)) {
					dungeon[i][j] = key.getChar("BOUND");  // CONCRETE
					// SOUTHEAST WALLS
				} else if (j == 0 || j == (dungeon[0].length - 1)) {
					dungeon[i][j] = key.getChar("BOUND"); // CONCRETE
				} else {
					dungeon[i][j] = key.getChar("FLOOR"); // FLOOR
				}

			}
		}
	}
	
//	private boolean checkIfOccupied(int x, int y) {
//		if(dungeon[y][x] == 0)
//	}
	
	public void generateRooms() {
//		rooms = new Room[numRooms];

		int sizeRemaining = MAX_SIZE;
		int buffer = 2;
		for(int i=0; i<numRooms; i++) {
//			Get center
			Room r = new Room();
			while (roomCheck(r) == false) {
				int x = rand.nextInt(DUNGEON_WIDTH - (buffer * 2)) + buffer; // 2 - 47
				int y = rand.nextInt(DUNGEON_HEIGHT - (buffer * 2)) + buffer;
				
				int w = rand.nextInt(MAX_ROOM_WIDTH - MIN_ROOM_WIDTH) + MIN_ROOM_WIDTH;
				int h = rand.nextInt(MAX_ROOM_HEIGHT - MIN_ROOM_HEIGHT) + MIN_ROOM_HEIGHT;
				int roomSize = w*h;
				while (roomSize > sizeRemaining) {
					w = rand.nextInt(MAX_ROOM_WIDTH - MIN_ROOM_WIDTH) + MIN_ROOM_WIDTH;
					h = rand.nextInt(MAX_ROOM_HEIGHT - MIN_ROOM_HEIGHT) + MIN_ROOM_HEIGHT;
					roomSize = w*h;
				}
				r = new Room(x,y,w,h);
			}
			System.out.println(r);
			for(int[] element : r.getEdge()) {
				if(element[2] == 0) {
					dungeon[element[1]][element[0]] = key.getChar("H_WALL");
				} else if(element[2] == 1){
					dungeon[element[1]][element[0]] = key.getChar("V_WALL");
				} else {
					System.out.println("Something broke");
				}
			}
			
		}
	}
	
	private Boolean roomCheck(Room r) {
		if (r == null) {
			return false;
		}
		int[] bounds = r.getBounds();
		int north = bounds[0];
		int south = bounds[1];
		int east = bounds[2];
		int west = bounds[3];
		if (north <= 0 || south >= DUNGEON_HEIGHT || east >= DUNGEON_WIDTH || west <= 0) {
			return false;
		} else {
			for (int i = north; i < south; i++) {
				for (int j = west; j < east; j++) {
					if (dungeon[i][j] != key.getChar("FLOOR")) {
						return false;
					}
				}
			}
			return true;
		}
		
		
	}

	public void printDungeon(){

		for (int i=0; i<dungeon.length; i++) {
			for (int j=0; j<dungeon[i].length; j++) {
				System.out.print(dungeon[i][j]);
			}
			System.out.printf("%n");
		}
		//      System.out.println(Arrays.deepToString(dungeon));
	}

}
