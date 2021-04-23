import java.io.*;
import java.util.*;

public class Test {
	public static void main(String[] args) throws IOException{
		GameState gs = new GameState();
		String save = gs.save();
		//System.out.println(save);
		gs = new GameState(save);
		TreeMap<String, int[]> map = new TreeMap<>();
		map.put("hi", new int[]{1, 2, 3});
		map.get("hi")[1] = 7;
		System.out.println(Arrays.toString(map.get("hi")));
	}
}
