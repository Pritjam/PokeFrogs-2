import java.util.TreeMap;

class Frog implements FrogConstants, Comparable<Frog> {
	private TreeMap<String, int[]> genome;
	private int age;
	private int value;
	private String nick;
	private long originalOwner;
	private long uniqueID;

	/**
	 * Constructor for a frog. Sets the originalOwner to the parameter owner, age to 0,
	 * the uniqueID to the System's nanoTime % originalOwner, and a random name.
	 * @param owner The long integer ID for the original owner.
	 */
	public Frog(long owner) {
		originalOwner = owner;
		uniqueID = System.nanoTime() % originalOwner;
		age = 0;
		value = 0;
		genome = new TreeMap<>();
		nick = NAMES[(int) (Math.random() * NAMES.length)];
	}

	/**
	 * Basic setter method for nickname.
	 * @param newNick The new nickname.
	 */
	public void setNick(String newNick) {
		nick = newNick;
	}

	/**
	 * Method to add a gene to the DNA of this frog.
	 * @param trait The String of the trait to add.
	 * @param genes The int[] representation of the genes.
	 */
	public void addGene(String trait, int[] genes) {
		genome.put(trait, genes);
	}

	/**
	 * Getter method for a given trait.
	 * @param trait The String of the trait to get the genes for.
	 * @return the alleles of the requested trait.
	 */
	public int[] getAlleles(String trait) {
		return genome.get(trait);
	}

	/**
	 * Method to get a random allele (haploid gamete)
	 * @param trait the String of the trait to get an allele for
	 * @return the int representation of the allele.
	 */
	public int getRandomAllele(String trait) {
		return genome.get(trait)[(int) (Math.random() * 2)];
	}

	/**
	 * Simple getter method for the nickname
	 * @return the nickname of this frog
	 */
	public String getNick() {
		return nick;
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
	 * Equals method. Not consistent with 
	 */
	public boolean equals(Object other) {
		if(other == null || !(other instanceof Frog)) {
			return false;
		}

		Frog otherFrog = (Frog) other;
		return otherFrog.uniqueID == uniqueID;
	}
}