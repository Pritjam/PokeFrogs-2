import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

public class GameState implements Serializable {
	private static final long serialVersionUID = 1;

	protected boolean[][][][] dex;
	protected long ownerID;
	protected ArrayList<TreeSet<Frog>> megabox;

	public GameState() {
		ownerID = System.nanoTime();
		megabox = new ArrayList<>(10);
		for(int i = 0; i < 10; i++) {
			megabox.add(new TreeSet<>());
		}
	}
}
