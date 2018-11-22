package myrogue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schema {
	private List<int[]> schema = new ArrayList<int[]>();
	private Map<Integer, String> key = new HashMap<Integer, String>();
	
	public Schema(Map<Integer,String> newKey, List<int[]> scheme) {
		schema = scheme;
		key = newKey;
	}
	
	public Schema(Map<Integer,String> newKey) {
		key = newKey;
	}
	
	public Schema(int northbound, int southbound, int eastbound, int westbound, int value, Map<Integer,String> newKey) {
		for(int i=northbound; i<=southbound; i++) {
			for(int j=westbound; j<=eastbound; j++) {
				schema.add(new int[] {j, i, value});
			}
		}
		key = newKey;
	}
	
	public void append(int northbound, int southbound, int eastbound, int westbound, int value) {
		for(int i=northbound; i<=southbound; i++) {
			for(int j=westbound; j<=eastbound; j++) {
				schema.add(new int[] {j, i, value});
			}
		}
	}
	
	public List<int[]> getSchema(){
		return schema;
	}
	
	public Map<?,?> getKey(){
		return key;
	}
	
	public String checkKey(int v) {
		return key.get(v);
		
	}
	
	
}
