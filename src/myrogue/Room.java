package myrogue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
	
	private final static Map<Integer, String> roomKey = new HashMap<Integer, String>();
	
	
	private int[] center = new int[2];
	private int width = 7;
	private int height = 4;
	private int V_WALL = 1;
	private int H_WALL = 0;
	private List<int[]> edge = new ArrayList<int[]>();
//	private List<int[]> floor = new ArrayList<int[]>();
	private Schema floor;
	private int north, south, east, west;
	
	public int[] getBounds() {
		return new int[] {north, south, east, west};
	}
	
	static
	{
		roomKey.put(1, "V_WALL");
		roomKey.put(0,"H_WALL");
		roomKey.put(2, "FLOOR");
	}
	
	public Room(int x, int y, int w, int h) {
		center[0] = x;
		center[1] = y;
		width = w;
		height = h;
		constructEdge();
		constructFloor();
		
	}
	
	public Room() {
	}
	
	private void constructFloor() {
		
		floor = new Schema((north+1), (south-1), (east-1), (west+1), 2, roomKey);
		
//		
//		for (int i = (north+1); i < south; i++) {
//			for (int j = (west+1); j < east; j++) {
//				floor.add(new int[] {i, j});
//			}
//		}
	}

	
	private void constructEdge() {
//		int numEdges = (2*width)+(2*height)-4;
//		If width is even, east half is smaller
		if (isEven(width)) {
			east = center[0] + (width/2 - 1);
			west = center[0] - (width/2);
		} else {
			east = center[0] + (width/2);
			west = center[0] - (width/2);
		}
//		If height is even, bottom half is smaller [(0,0) is NW corner]
		if (isEven(height)) {
			north = center[1] - (height/2);
			south = center[1] + (height/2 - 1);
		} else {
			north = center[1] - (height/2);
			south = center[1] + (height/2);
		}
		
		for(int i=west; i<=east; i++) {
//			Top edge
			edge.add(new int[] {i, north, H_WALL});
//			Bottom edge
			edge.add(new int[] {i, south, H_WALL});
		}
		for(int i=(north+1); i<south; i++) {
//			West edge
			edge.add(new int[] {west, i, V_WALL});
//			East edge
			edge.add(new int[] {east, i, V_WALL});			
		}
	}
	
	private Boolean isEven(int i) {
		return (i % 2) == 0;
	}
	
	public List<int[]> getEdge(){
		return edge;
	}
	
	
	public Schema getFloor() {
		return floor;
	}
	
	public void printEdges() {
		for(int[] element : edge) {
			System.out.println(Arrays.toString(element));
		}
	}
	
	@Override
	public String toString() {
		return ("Center: " + Arrays.toString(center) + 
				", Height: " + height + 
				", Width: " + width + 
				", Bounds: " + Arrays.toString(getBounds()));
	}
	
}
