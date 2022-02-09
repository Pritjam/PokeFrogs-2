import java.util.HashMap;

public class Genome {

    /**
     * Genome of a frog:
     * 
     * "Base"   : 2-element color array, described in COLOR_PHENOTYPES. 9 colors total.
     * "Accent" : 2-element color array, described in COLOR_PHENOTYPES. 9 colors total.
     * "Pattern": 3-element color array, with first two denoting alleles and third 
     *            adenoting mutation. 11 patterns total.
     */
    private HashMap<String, Gene> genes;

    public Genome() {
        genes = new HashMap<>();
    }

    /**
     * This method creates and returns a new Genome object resulting from a genetic
     * cross between
     * the two provided parent genomes.
     * 
     * @param parentOne The first parent to take genetic data from
     * @param parentTwo The second parent to take genetic data from
     * @return The new genome created as a cross between the two parents
     */
    public static Genome cross(Genome parentOne, Genome parentTwo) {
        Genome ret = new Genome();

        // all of this genome's genes, both in common with the other and ones only this
        // one has
        for (String key : parentOne.genes.keySet()) {
            Gene newGene = parentOne.genes.get(key);
            if (parentTwo.genes.containsKey(key)) {
                newGene = newGene.applyGenetics(parentTwo.genes.get(key));
            }
            ret.genes.put(key, newGene);
        }

        // any genes the other genome has that this one doesn't
        for (String key : parentTwo.genes.keySet()) {
            if (!parentOne.genes.containsKey(key)) {
                ret.genes.put(key, parentTwo.genes.get(key));
            }
        }

        return ret;
    }

    /**
     * Method to get a String representation of whatever gene is provided.
     * 
     * @param key The gene to get the string representation of
     * @return The string representation of the given gene
     */
    public String getString(String key) {
        return genes.get(key).toString();
    }

    // TODO: just for debugging
    public void addGene(String name, Gene gene) {
        genes.put(name, gene);
    }

    // TODO: just for debugging
    public Gene getGene(String name) {
        return genes.get(name);
    }

}
