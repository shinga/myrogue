/**
 * 
 */
package myrogue;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

/**
 * @author Arthur
 *
 */
public class GenerateDungeon {
	private CharKey key = new CharKey();
	private Random rand = new Random();

	private int numRooms;
	private int roomAttempts;
	private static final int DUNGEON_WIDTH = 120;
	private static final int DUNGEON_HEIGHT = 30;
	
	private int sparcity;
	private final int MAX_SIZE;
	
	private final int MIN_ROOM_WIDTH = 8;
	private final int MAX_ROOM_WIDTH = (DUNGEON_WIDTH)/4;
	
	private final int MIN_ROOM_HEIGHT = 4;
	private final int MAX_ROOM_HEIGHT = (DUNGEON_HEIGHT)/2;
	private Room[] rooms;
	
	private final char[][] dungeon;

	
/*
 * Parameters: 	int num (number of rooms)
 * 				int sparce (number from 1-10, sparceness of the dungeon - how much room should be left an empty space)
 * 					- 3 means a maximum of 30% of the dungeon will be filled with rooms
 */
	public GenerateDungeon(int num, int sparce) {
		sparcity = sparce;
		MAX_SIZE = (DUNGEON_WIDTH * DUNGEON_HEIGHT)/10*sparcity;
		numRooms = num;
		roomAttempts = 50;
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
					dungeon[i][j] = key.getChar("SPACE"); // FLOOR
				}

			}
		}
	}

	public void applySchema(Schema schema, Boolean overwrite) {
		if(overwrite) {
			for (int[] element : schema.getSchema()) {
				String s = schema.checkKey(element[2]);
				dungeon[element[1]][element[0]] = key.getChar(s);
			}
		}
	}
	
	public void generateRooms() {
//		rooms = new Room[numRooms];

		int sizeRemaining = MAX_SIZE;
		int buffer = 2;
		for(int i=0; i<numRooms; i++) {

			int attempt = 0;
			int x, y, w, h, roomSize = 0;
			Room r = new Room();
			do {

				x = rand.nextInt(DUNGEON_WIDTH - (buffer * 2)) + buffer; // 2 - 47
				y = rand.nextInt(DUNGEON_HEIGHT - (buffer * 2)) + buffer;
				
				w = rand.nextInt(MAX_ROOM_WIDTH - MIN_ROOM_WIDTH) + MIN_ROOM_WIDTH;
				h = rand.nextInt(MAX_ROOM_HEIGHT - MIN_ROOM_HEIGHT) + MIN_ROOM_HEIGHT;
				roomSize = w*h;
				if ((roomSize > sizeRemaining) || (attempt > roomAttempts)) {
					break;
				}

				r = new Room(x,y,w,h);
				attempt++;
			} while ((roomCheck(r) == false));
			
			if ((roomSize > sizeRemaining) || (attempt > roomAttempts)) {
				continue;
			}
			System.out.println(r);
			applySchema(r.getEdge(), true);
			applySchema(r.getFloor(), true);
			sizeRemaining = sizeRemaining - roomSize;
		}
		
	}
	
	
//	Returns false if invalid room, true if room can exist
	private Boolean roomCheck(Room r) {
		if (r == null) {
			return false;
		}
		int[] bounds = r.getBounds();
		int north = bounds[0];
		int south = bounds[1];
		int east = bounds[2];
		int west = bounds[3];
		if (north <= 0 || south >= (DUNGEON_HEIGHT-1) || east >= (DUNGEON_WIDTH-1) || west <= 0) {
			return false;
		} else {
			for (int i = north; i <= south; i++) {
				for (int j = west; j <= east; j++) {
					if (dungeon[i][j] != key.getChar("SPACE")) {
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
