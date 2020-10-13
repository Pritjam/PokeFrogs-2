import java.io.Serializable;

class Frog implements Serializable{
  private Genome genome;
  private int age;
  private String nick;
  private long originalOwner;
  private long uniqueID;
  
  //constructor
  public Frog(int originalOwner) {
    this.originalOwner = originalOwner;
    this.uniqueID = System.nanoTime() % originalOwner;
    this.age = 0;
    this.genome = new Genome();
  }
  
  //setter method for nickname
  public void setNick(String nick) {
    this.nick = nick;
  }
  
  //method to add a gene
  //pre: genes is a 2-length int[]
  public void addGene(String trait, int[] genes) {
    if(genes.length != 2) {
      throw new IllegalArgumentException("Genes must have 2 elements!");
    }
    
    this.genome.addGene(trait, genes);
  }
  
  //getter for uniqueID
  public long getUniqueID() {
    return this.uniqueID;
  }
}