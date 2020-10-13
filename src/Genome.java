import java.lang.IllegalArgumentException;
import java.util.TreeMap;

class Genome {
  private TreeMap<String, int[]> dna;
  
  public Genome() {
    this.dna = new TreeMap<String, int[]>();
  }
  
  //returns the genes for the given trait.
  //pre: trait is a valid key
  public int[] getPhenotype(String trait) {
    if(!this.dna.containsKey(trait)) {
      throw new IllegalArgumentException("No genes for that trait!");
    }
    
    return this.dna.get(trait);
  }
  
  //adds a new trait-genes pair
  //pre: the integer array has 2 elements
  public void addGene(String trait, int[] genes) {
    this.dna.put(trait, genes);
  }
  
  //returns a random gene for the given trait (one of the two)
  //pre: trait is a valid key
  public int getRand(String trait) {
    if(!this.dna.containsKey(trait)) {
      throw new IllegalArgumentException("No genes for that trait!");
    }
    if(Math.random() * 2 > 1) {
      return this.dna.get(trait)[1];
    } else {
      return this.dna.get(trait)[0];
    }
  }
  
  //override default toString
  public String toString() {
    return this.dna.toString();
  }
}