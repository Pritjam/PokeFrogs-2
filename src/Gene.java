import java.util.Arrays;
import java.util.Random;

public interface Gene {
    public Gene applyGenetics(Gene other); // function returns a new Gene based on the other gene, using the genetics algorithm for this gene.
    public static Random random = new Random();

    public class MendelianMonogenetic implements Gene {
        private int[] alleles; 

        public MendelianMonogenetic(int[] alleles) {
            this.alleles = alleles;
        }

        public Gene applyGenetics(Gene other) {
            if(!(other instanceof MendelianMonogenetic)) {
                return null; //TODO: Error?
            }
            MendelianMonogenetic otherGene = (MendelianMonogenetic) other;
            if(otherGene.alleles.length != this.alleles.length) {
                return null; //TODO: Error?
            }
            int[] newAlleles = new int[this.alleles.length];
            for(int i = 0; i < this.alleles.length; i++) {
                newAlleles[i] = random.nextBoolean() ? this.alleles[i] : otherGene.alleles[i];
            }

            return new MendelianMonogenetic(newAlleles);
        }

        public String toString() {
            return Arrays.toString(alleles);
        }
        
    }

    public class ColorGene implements Gene {
        private int[] alleles;

        
        // combination table to get a color from a given pair of alleles
	    // for example, COLOR_PHENOTYPES[1][2] represents the phenotype of a frog
	    // with color genotype {1, 2}, meaning a color of green
        private static String[][] PHENOTYPES = {
			//0:red  1:ylw  2:blu  3:blk  4:wht
			{ "RED", "ORN", "PRP", "BLK", "RED" }, // red
			{ "ORN", "YLW", "GRN", "BLK", "YLW" }, // ylw
			{ "PRP", "GRN", "BLU", "BLK", "BLU" }, // blu
			{ "BLK", "BLK", "BLK", "BLK", "GLS" }, // blk
			{ "RED", "YLW", "BLU", "GLS", "WHT" }  // wht
	    };

        public ColorGene(int[] alleles) {
            this.alleles = alleles;
        }

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

        public String toString() {
            return PHENOTYPES[alleles[0]][alleles[1]];
        }
    }

    public class PatternGene implements Gene {
        private int[] alleles;

        // combination table to get a pattern from a given pair of alleles
        // basic options-plain (recessive), spotted, camo, stripes (all dominant)
        // combo of any 2 dominants results in a mix (codominance)
        // homozygous combination of a dominant gene has a small chance to result in
        // a mutation: spotted -> stars, camo -> fractal, and stripes -> spiral
        private static String[][] PATTERN_PHENOTYPES = {
                // spot               camo            stripes            plain
                { "STARS",           "SPOTTED CAMO", "SPOTTED STRIPES", "SPOTS" },      // SPOTTED
                { "SPOTTED CAMO",    "FRACTALS",     "STRIPED CAMO",    "CAMO" },       // CAMO
                { "SPOTTED STRIPES", "STRIPED CAMO", "SPIRALS",         "STRIPES" },    // STRIPED
                { "SPOTS",           "CAMO",         "STRIPES",         "EASTER_EGGS" } // plain
        };

        public static String[] PATTERNS = {"SPOTS", "CAMO", "STRIPES", "PLAIN"};
        
        public PatternGene(int[] alleles) {
            this.alleles = alleles;
        }

        public Gene applyGenetics(Gene other) {
            if(!(other instanceof PatternGene)) {
                return null; //TODO: Error?
            }

            PatternGene otherPattern = (PatternGene) other;
            if(otherPattern.alleles.length != alleles.length) {
                return null; //TODO: Error?
            }

            //TODO: Custom logic for mutations.
            int[] newAlleles = new int[this.alleles.length];
            for(int i = 0; i < this.alleles.length; i++) {
                newAlleles[i] = random.nextBoolean() ? this.alleles[i] : otherPattern.alleles[i];
            }

            return new ColorGene(newAlleles);
        }

        //TODO: rework this. Let the 2d array have the normal combinations, let the plain array hold mutations?
        public String toString() {
            if (alleles[0] == alleles[1]) {
                return PATTERNS[alleles[0]];
            } else {
                return PATTERN_PHENOTYPES[alleles[0]][alleles[1]];
            }
        }
    }
}
