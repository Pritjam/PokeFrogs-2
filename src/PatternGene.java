import java.io.Serializable;

public class PatternGene implements Gene, Serializable {
    private int[] alleles;

    // combination table to get a pattern from a given pair of alleles
    // basic options-plain (recessive), spotted, camo, stripes (all dominant)
    // combo of any 2 dominants results in a mix (codominance)
    // homozygous combination of a dominant gene has a small chance to result in
    // a mutation: spotted -> stars, camo -> fractal, and stripes -> spiral
    private static String[][] PATTERN_PHENOTYPES = {
            // spot               camo            stripes            plain
            { "SPOTS",           "SPOTTED CAMO", "SPOTTED STRIPES", "SPOTS" },      // SPOTTED
            { "SPOTTED CAMO",    "CAMO",         "STRIPED CAMO",    "CAMO" },       // CAMO
            { "SPOTTED STRIPES", "STRIPED CAMO", "STRIPES",         "STRIPES" },    // STRIPED
            { "SPOTS",           "CAMO",         "STRIPES",         "PLAIN" } // plain
    };

    public static String[] SPECIALS = {"STARS", "FRACTALS", "SPIRALS", "EASTER_EGGS"};

	private static final long serialVersionUID = 1;

    
    
    public PatternGene(int[] alleles) {
        this.alleles = alleles;
    }

    //let allele 2 be the "mutation" allele.
    public Gene applyGenetics(Gene other) {
        if(!(other instanceof PatternGene)) {
            return null; //TODO: Error?
        }

        PatternGene otherPattern = (PatternGene) other;
        if(otherPattern.alleles.length != alleles.length) {
            return null; //TODO: Error?
        }

        int[] newAlleles = new int[this.alleles.length];

        // For the first two alleles, we perform a simple Mendelian assignment.
        for(int i = 0; i < 2; i++) {
            newAlleles[i] = random.nextBoolean() ? this.alleles[i] : otherPattern.alleles[i];
        }

        // To determine if this pattern is "special", meaning it carries the rare "special" allele, we must do a little more probability work.

        if(this.alleles[2] == 1 && otherPattern.alleles[2] == 1) {
            // If both parent genes are "special", there's a 1/16 chance of passing on the gene
            newAlleles[2] = random.nextInt(16) < 1 ? 1 : 0;
        } else if(this.alleles[2] == 1 ^ otherPattern.alleles[2] == 1) {
            // If only one parent gene is "special", there's a 1/64 chance of passing on the gene.
            newAlleles[2] = random.nextInt(64) < 1 ? 1 : 0;
        } else {
            // If neither parent gene is "special", there's a 1/256 chance of it randomly occurring.
            newAlleles[2] = random.nextInt(256) < 1 ? 1 : 0;
        }

        return new PatternGene(newAlleles);
    }

    public String toString() {
        if (alleles[2] == 0) {
            return PATTERN_PHENOTYPES[alleles[0]][alleles[1]];
        } else {
            return SPECIALS[alleles[0]];
        }
    }
}