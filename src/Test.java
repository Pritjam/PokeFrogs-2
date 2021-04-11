import java.io.*;
import java.util.ArrayList;

public class Test {
	public static void main(String[] args) throws IOException{
		GameState gs = new GameState();
		String save = gs.save();
		//System.out.println(save);
		gs = new GameState(save);
		System.out.println(gs.dex[0][1][1]);
	}

	public int[] fill(int[] hi) {
		int[] hello = {1, 2, 3};
		hi = hello;
		return hi;
	}
}
