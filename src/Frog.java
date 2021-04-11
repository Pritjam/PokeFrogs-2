import java.util.TreeMap;

class Frog implements Constants, Comparable<Frog> {
	private TreeMap<String, int[]> genome;
	private int age;
	private String nick;
	private long originalOwner;
	private long timestamp;
	private long random;

	/**
	 * Constructor for a frog. Sets the originalOwner to the parameter owner, age to 0,
	 * the uniqueID to the System's nanoTime % originalOwner, and a random name.
	 * @param owner The long integer ID for the original owner.
	 */
	public Frog(long owner) {
		originalOwner = owner;
		random = (long) (Math.random() * 10_000_000_000_000L);
		timestamp = System.nanoTime();
		age = 0;
		genome = new TreeMap<>();
		nick = NAMES[(int) (Math.random() * NAMES.length)];
	}

	public Frog(long owner, Frog otherParent) {
		this(owner);
		genome = getGenome(genome, otherParent.genome);
	}

	private TreeMap<String, int[]> getGenome(TreeMap<String, int[]> parentOne, TreeMap<String, int[]> parentTwo) {
		if(parentOne.size() != parentTwo.size()) {
			return null;
		}
		TreeMap<String, int[]> returnGenome = new TreeMap<>();
		for(String key : parentOne.keySet()) {
			int[] parentOneGenes = parentOne.get(key);
			int[] parentTwoGenes = parentTwo.get(key);
			int[] childGenes = new int[parentOneGenes.length];
			for(int i = 0; i < childGenes.length; i++) {
				childGenes[i] = Math.round(Math.random()) == 0 ? parentOneGenes[i] : parentTwoGenes[i];
			}
			returnGenome.put(key, childGenes);
		}
		return returnGenome;
	}

	/**
	 * Basic setter method for nickname.
	 * @param newNick The new nickname.
	 */
	public void setNick(String newNick) {
		nick = newNick;
	}

	/**
	 * Simple getter method for the nickname
	 * @return the nickname of this frog
	 */
	public String getNick() {
		return nick;
	}

	public int getAge() {
		return age;
	}

	/**
	 * CompareTo method override. This just compares by the nickname. NOT CONSISTENT WITH EQUALS
	 * @param otherFrog The frog to compare to
	 * @return the result of this.nick.compareTo(otherFrog.nick)
	 */
	public int compareTo(Frog otherFrog) {
		return nick.compareTo(otherFrog.nick);
	}

	/**
	 * Equals method.
	 */
	public boolean equals(Object other) {
		if(other == null || !(other instanceof Frog)) {
			return false;
		}

		Frog otherFrog = (Frog) other;
		return otherFrog.random == random && otherFrog.timestamp == timestamp;
	}
}