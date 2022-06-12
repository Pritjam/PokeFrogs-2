import java.io.*;
import java.util.Base64;
import java.util.TreeSet;

public class GameState implements Serializable {
	private static final long serialVersionUID = 1;

	protected byte[][] dex;
	protected long ownerID;
	protected TreeSet<Frog> box;
	protected int money;

	public GameState() {
		ownerID = System.currentTimeMillis();
		box = new TreeSet<>();
		dex = new byte[11][12];
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
			System.out.println("Error: IOException while saving game");
			System.out.println(e.getMessage());
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
			box = gs.box;
			dex = gs.dex;
			money = gs.money;
		} catch (ClassNotFoundException e) {
			System.out.println("Error: ClassNotFoundException while loading game\n\t" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error: IOException while loading game\n\t" + e.getMessage());
		}
	}

}
