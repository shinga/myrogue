package myrogue;

public class ShowMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenerateDungeon newdungeon = new GenerateDungeon(5);
		newdungeon.generateRooms();
		newdungeon.printDungeon();
		System.out.print("TEST");

	}

}
