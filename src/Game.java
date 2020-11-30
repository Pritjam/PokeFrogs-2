import java.io.*;

public class Game implements FrogConstants {
	private static GameState gs;

	public static void main(String[] args) {
		gs = new GameState();
		System.out.println("Hi!");

	}

	private void load(String path) throws ClassNotFoundException, IOException {
		try {
			FileInputStream fileIn = new FileInputStream("saves/" + path.toUpperCase() + ".sav");
			ObjectInputStream ois = new ObjectInputStream(fileIn);
			int versionID = (Integer) ois.readObject();
			if(versionID != VERSION_ID) {
				System.out.println("Not a valid save file!");
				ois.close();
				return;
			}
			gs = (GameState) ois.readObject();
			ois.close();
			System.out.println("Loaded Successfully!");
		} catch (FileNotFoundException e) {
			System.out.println("No such save exists! Check the \"saves\" folder in the game directory.");
		}
	}

	private void save(String path) throws FileNotFoundException, IOException {
		FileOutputStream fileStream = new FileOutputStream("saves/" + path.toUpperCase() + ".sav");
		ObjectOutputStream oos = new ObjectOutputStream(fileStream);
		oos.writeObject(VERSION_ID);
		oos.writeObject(gs);
		oos.close();
		System.out.println("Saved Successfully as \"" + path + ".sav\"");
	}
}
