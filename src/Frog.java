import java.io.Serializable;

class Frog implements Constants, Comparable<Frog>, Serializable {
	public Genome genome; //TODO: don't forget to put these back to private
	private int age;
	public boolean shiny;
	private String nickname;
	private long originalOwner;
	private long timestamp;
	private long id;

	private static final long serialVersionUID = 1;


	/**
	 * Constructor for a frog. Sets the originalOwner to the parameter owner, age to 0,
	 * the uniqueID to the System's nanoTime % originalOwner, and a random name.
	 * @param owner The long integer ID for the original owner.
	 */
	public Frog(long owner) {
		originalOwner = owner;
		id = (long) (Math.random() * Long.MAX_VALUE);
		timestamp = System.currentTimeMillis();
		age = 0;
		shiny = (int)(Math.random() * 8192) == 0;
		genome = new Genome();
		nickname = NAMES[(int) (Math.random() * NAMES.length)];
	}

	public Frog(long owner, Frog p1, Frog p2) {
		this(owner);
		genome = Genome.cross(p1.genome, p2.genome);
	}

	/**
	 * Basic setter method for nickname.
	 * @param newNick The new nickname.
	 */
	public void setNick(String newNick) {
		nickname = newNick;
	}

	/**
	 * Simple getter method for the nickname
	 * @return the nickname of this frog
	 */
	public String getNick() {
		return nickname;
	}

	public int getAge() {
		return age;
	}

	private String getGenomeString() {
		String ret = shiny ? "*" : "";
		ret += genome.getString("base");
		ret += " with ";
		ret += genome.getString("accent") + " ";
		ret += genome.getString("pattern");
		ret += ". Owner ID: " + originalOwner;
		return ret;
	}

	public String toString() {
		return String.format("%s, %s. Age %d.", nickname, getGenomeString(), age);
	}

	/**
	 * CompareTo method override. This just compares by the nickname. NOT CONSISTENT WITH EQUALS
	 * @param otherFrog The frog to compare to
	 * @return the result of this.nick.compareTo(otherFrog.nick)
	 */
	public int compareTo(Frog otherFrog) {
		return nickname.compareTo(otherFrog.nickname);
	}

	/**
	 * Equals method.
	 */
	public boolean equals(Object other) {
		if(other == null || !(other instanceof Frog)) {
			return false;
		}

		Frog otherFrog = (Frog) other;
		return otherFrog.id == id && otherFrog.timestamp == timestamp;
	}
}