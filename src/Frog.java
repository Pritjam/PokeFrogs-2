
class Frog implements Constants, Comparable<Frog> {
	public int genome; //TODO: don't forget to put these back to private
	private int age;
	public boolean shiny;
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
		random = (long) (Math.random() * Long.MAX_VALUE);
		timestamp = System.currentTimeMillis();
		age = 0;
		shiny = (int) (Math.random() * 8192) == 0;
		genome = 0;
		nick = NAMES[(int) (Math.random() * NAMES.length)];
	}

	public Frog(long owner, Frog p1, Frog p2) {
		this(owner);
		genome = getGenome(p1.genome, p2.genome);
	}

	/**
	 * A genome is a 32-bit int. From LSB:
	 * - 6 bits for primary color, broken into 3 + 3 alleles
	 * - 6 bits for secondary color, broken into 3 + 3 alleles
	 * - 8 bits for pattern, broken into 1 + 1 + 3 + 3 (unimplemented, special, pattern allele 1, 2)
	 * @param p1 The integer representing the first frog gamete
	 * @param p2 The integer representing the second frog gamete
	 * @return An integer representing the resulting genome
	 */
	private int getGenome(int p1, int p2) {
		//primary color
		int pColorOne = rand() ? p1 & 0x3 : p2 & 0x3;
		int pColorTwo = rand() ? p1 >> 3 & 0x3 : p2 >> 3 & 0x3;

		//secondary
		int sColorOne = rand() ? p1 >> 6 & 0x3 : p2 >> 6 & 0x3;
		int sColorTwo = rand() ? p1 >> 9 & 0x3 : p2 >> 9 & 0x3;

		//Pattern
		int pOne = rand() ? p1 >> 12 & 0x3 : p2 >> 12 & 0x3;
		int pTwo = rand() ? p1 >> 15 & 0x3 : p2 >> 15 & 0x3;
		int pSpecial = rand() ? p1 >> 16 & 0x3 : p2 >> 16 & 0x3;
		int pOther = 0;

		if(pSpecial == 0 && (int)(Math.random() * 256) == 42) { //small chance of mutation
			pSpecial = 1;
		} else if(pSpecial == 1 && (int)(Math.random() * 2) == 0) { //higher chance of mutation not carrying through
			pSpecial = 0;
		}

		return pColorOne + pColorTwo << 3 + sColorOne << 6 + sColorTwo << 9 + pOne << 12 + pTwo << 15 + pSpecial << 16 + pOther << 17;
	}

	private boolean rand() {
		return (int) (Math.random() * 2) == 0;
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

	private String getGenomeString() {
		String ret = shiny ? "*" : "";
		ret += Constants.getColor(genome & 0x3F);
		ret += " with ";
		ret += Constants.getColor(genome >> 6 & 0x3F) + " ";
		ret += Constants.getPattern(genome >> 12 & 0xFF);
		ret += " Owner ID: " + originalOwner;
		return ret;
	}

	public String toString() {
		return String.format("%s, %s. Age %d.", getNick(), getGenomeString(), getAge());
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