import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.TreeSet;

public class GameState implements Serializable {
	private static final long serialVersionUID = 1;

	protected boolean[][][] dex;
	protected long ownerID;
	protected ArrayList<TreeSet<Frog>> megabox;
	protected int money;

	public GameState() {
		ownerID = System.currentTimeMillis();
		megabox = new ArrayList<>(10);
		for (int i = 0; i < 10; i++) {
			megabox.add(new TreeSet<>());
		}
		dex = new boolean[9][9][10];
		money = 0;
	}

	public String save() {
		String serializedString = "";
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);
			oos.flush();
			serializedString = Base64.getEncoder().encodeToString(baos.toByteArray());
		} catch (IOException e) {
			System.out.println("Error: IOException while saving game\n\t" + e.getMessage());
		}
		return serializedString;
	}

	public GameState(String serializedString) {
		try {
			byte[] data = Base64.getDecoder().decode(serializedString);
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			ObjectInputStream ois = new ObjectInputStream(bais);
			GameState gs = (GameState) ois.readObject();
			ownerID = gs.ownerID;
			megabox = gs.megabox;
			dex = gs.dex;
			money = gs.money;
		} catch (ClassNotFoundException e) {
			System.out.println("Error: ClassNotFoundException while loading game\n\t" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error: IOException while loading game\n\t" + e.getMessage());
		}
	}

}
