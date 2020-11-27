import java.util.TreeMap;

class Frog implements FrogConstants, Comparable<Frog> {
	private TreeMap<String, int[]> genome;
	private int age;
	private String nick;
	private long originalOwner;
	private long uniqueID;

	//constructor
	public Frog(long owner) {
		originalOwner = owner;
		uniqueID = System.nanoTime() % originalOwner;
		age = 0;
		genome = new TreeMap<>();
		nick = NAMES[(int) (Math.random() * NAMES.length)];
	}

	//setter method for nickname
	public void setNick(String newNick) {
		nick = newNick;
	}

	//method to add a gene
	//pre: genes is a 2-length int[]
	public void addGene(String trait, int[] genes) {
		genome.put(trait, genes);
	}

	// returns the genes for the given trait.
	// pre: trait is a valid key
	public int[] getAlleles(String trait) {
		return genome.get(trait);
	}

	// returns a random gene for the given trait (one of the two)
	public int getRandomAllele(String trait) {
		return genome.get(trait)[(int) (Math.random() * 2)];
	}

	//getter for nickname
	public String getNick() {
		return nick;
	}

	//compareTo, this compares by nickname, not consistent with equals
	public int compareTo(Frog otherFrog) {
		return nick.compareTo(otherFrog.nick);
	}

	//override equals to compare only uniqueID
	public boolean equals(Object other) {
		if(other == null || !(other instanceof Frog)) {
			return false;
		}

		Frog otherFrog = (Frog) other;
		return otherFrog.uniqueID == uniqueID;
	}
}