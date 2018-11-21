package myrogue;
import java.util.Map;
import java.util.HashMap;

public final class CharKey {
	private final static Map<String, Character> key = new HashMap<String, Character>();
	
	private final static char[] values = 
		{
			'#',	// CONCRETE (0)
			'.',	// FLOOR (1)
			'-',	// HORIZONTAL WALL (2)
			'|',	// VERTICAL WALL (3)
			
			
			
			' '		//BLANK PLACEHOLDER
		};

	public char showKey(int numcode) {
		return values[numcode];
	}
	
	public CharKey() {
		key.put("BOUND",'#');
		key.put("FLOOR", '.');
		key.put("H_WALL", '-');
		key.put("V_WALL", '|');
		key.put("SPACE", ' ');
		key.put("AVATAR", '@');
		key.put("ERROR", 'E');
	}
	
	public char getChar(String s) {
		if (key.get(s) != null) {
			return key.get(s);
		} else {
			return key.get("ERROR");
		}
		
	}
	
}
