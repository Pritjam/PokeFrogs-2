class Frog implements Constants, Comparable<Frog> {
	public Genome genome; //TODO: don't forget to put these back to private
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
		random = (long) (Math.random() * 10_000_000_000_000L);
		timestamp = System.currentTimeMillis();
		age = 0;
		shiny = (int)(Math.random() * 8192) == 0;
		genome = new Genome();
		nick = NAMES[(int) (Math.random() * NAMES.length)];
	}

	public Frog(long owner, Frog parentOne, Frog parentTwo) {
		this(owner);
		genome = Genome.cross(parentOne.genome, parentTwo.genome);
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
		ret += Constants.getColor(genome.getInt("base"));
		ret += " with ";
		ret += Constants.getColor(genome.getInt("accent")) + " ";
		ret += Constants.getPattern(genome.getInt("pattern"));
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