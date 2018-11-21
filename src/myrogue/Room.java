package myrogue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Room {
	
	private int[] center = new int[2];
	private int width = 7;
	private int height = 4;
	private int V_WALL = 1;
	private int H_WALL = 0;
	private List<int[]> edge = new ArrayList<int[]>();
	private int north, south, east, west;
	
	public int[] getBounds() {
		return new int[] {north, south, east, west};
	}
	
	public Room(int x, int y, int w, int h) {
		center[0] = x;
		center[1] = y;
		width = w;
		height = h;
		constructEdge();
		
	}
	public Room() {
	}
	
	private void constructEdge() {
//		int numEdges = (2*width)+(2*height)-4;
		
//		If width is even, east half is smaller
		if (isEven(width)) {
			east = center[1] + (width/2 - 1);
			west = center[1] - (width/2);
		} else {
			east = center[1] + (width/2);
			west = center[1] - (width/2);
		}
//		If height is even, bottom half is smaller [(0,0) is NW corner]
		if (isEven(height)) {
			north = center[1] - (height/2);
			south = center[1] + (height/2 - 1);
		} else {
			north = center[1] - (height/2);
			south = center[1] + (height/2);
		}
		
		for(int i=west; i<east; i++) {
//			Top edge
			edge.add(new int[] {i, north, H_WALL});
//			Bottom edge
			edge.add(new int[] {i, south, H_WALL});
		}
		for(int i=north; i<south; i++) {
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
	
	public void printEdges() {
		for(int[] element : edge) {
			System.out.println(Arrays.toString(element));
		}
	}
	
}
