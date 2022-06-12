import java.io.Serializable;

/**
 * ColorGene class. ColorGenes are used to represent colors in various ways, including base colors and accent colors.
 * This type of gene uses a basic mendelian monogenetic algorithm to generate new genes. 
 */

public class ColorGene implements Gene, Serializable {
    private int[] alleles;    
    /*
     * combination table to get a color from a given pair of alleles
     * for example, COLOR_PHENOTYPES[1][2] represents the phenotype of a frog
     * with color genotype {1, 2}, meaning a color of green
     */
    private static String[][] PHENOTYPES = {
        //0:red  1:ylw  2:blu  3:blk  4:wht
        { "RED", "ORN", "PRP", "BLK", "RED" }, // red
        { "ORN", "YLW", "GRN", "BLK", "YLW" }, // ylw
        { "PRP", "GRN", "BLU", "BLK", "BLU" }, // blu
        { "BLK", "BLK", "BLK", "BLK", "BLK" }, // blk
        { "RED", "YLW", "BLU", "BLK", "GLS" }  // wht
    };

	private static final long serialVersionUID = 1;


    /**
     * Basic constructor for a ColorGene object.
     * @param alleles The alleles to take in as this Gene's genetic material.
     */
    public ColorGene(int[] alleles) {
        this.alleles = alleles;
    }

    /**
     * Implemented method for the Gene class. This method takes in another gene and applies the gene-specific genetic algorithm to generate a new Gene.
     * For ColorGenes, the algorithm is a basic Mendelian Genetics algorithm. Each allele in the new Gene is picked randomly from the corresponding allele from each of the parent genes.
     * @param other The other gene to draw genetic material from
     * @return A new gene generated randomly from this gene and the provided other gene.
     */
    public Gene applyGenetics(Gene other) {
        if(!(other instanceof ColorGene)) {
            return null; //TODO: Error?
        }

        ColorGene otherColor = (ColorGene) other;
        if(otherColor.alleles.length != alleles.length) {
            return null; //TODO: Error?
        }

        int[] newAlleles = new int[this.alleles.length];
        for(int i = 0; i < this.alleles.length; i++) {
            newAlleles[i] = random.nextBoolean() ? this.alleles[i] : otherColor.alleles[i];
        }

        return new ColorGene(newAlleles);
    }

    /**
     * Returns a String representation of this gene. This is done by referencing the Phenotypes table to get the phenotype color code corresponding to this gene.
     * @return A String that tells the color of this gene, based off of it's 2 alleles.
     */
    public String toString() {
        return PHENOTYPES[alleles[0]][alleles[1]];
    }
}