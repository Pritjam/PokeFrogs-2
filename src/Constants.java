public interface Constants {

	public static final String[] NAMES = { "Jim", "Hank", "Todd" };

	// combination table to get a color from a given pair of alleles
	// for example, COLOR_PHENOTYPES[1][2] represents the phenotype of a frog
	// with color genotype {1, 2}, meaning a color of green
	public static final String[][] COLOR_PHENOTYPES = {
			//0:red  1:ylw  2:blu  3:blk  4:wht
			{ "RED", "ORN", "PRP", "BLK", "RED" }, // red
			{ "ORN", "YLW", "GRN", "BLK", "YLW" }, // ylw
			{ "PRP", "GRN", "BLU", "BLK", "BLU" }, // blu
			{ "BLK", "BLK", "BLK", "BLK", "GLS" }, // blk
			{ "RED", "YLW", "BLU", "GLS", "WHT" }  // wht
	};

	/**
	 * A method to get the color from a given genotype
	 * @param genotype the int[] representation of the color genes
	 * @return the 3-letter String of the phenotype
	 */
	public static String getColor(int[] genotype) {
		return COLOR_PHENOTYPES[genotype[0]][genotype[1]];
	}

	// combination table to get a pattern from a given pair of alleles
	// basic options-plain (recessive), spotted, camo, stripes (all dominant)
	// combo of any 2 dominants results in a mix (codominance)
	// homozygous combination of a dominant gene has a small chance to result in 
	// a mutation: spotted -> stars, camo -> fractal, and stripes -> spiral
	public static final String[][] PATTERN_PHENOTYPES = {
		//spot              camo            strp               pln
		{"STARS",           "SPOTTED CAMO", "SPOTTED STRIPES", "SPOTS"},      //SPOTTED
		{"SPOTTED CAMO",    "FRACTALS",     "STRIPED CAMO",    "CAMO"},       //CAMO
		{"SPOTTED STRIPES", "STRIPED CAMO", "SPIRALS",         "STRIPES"},    //STRIPED
		{"SPOTS",           "CAMO",         "STRIPES",         "EASTER_EGGS"} //plain
	};

	public static final String[] PATTERNS = {"SPOTS", "CAMO", "STRIPES", "PLAIN"};

	public static String getPattern(int[] genotype) {
		if(genotype[0] == genotype[1] && genotype[2] != 1) {
			return PATTERNS[genotype[0]];
		} else {
			return PATTERN_PHENOTYPES[genotype[0]][genotype[1]];
		}
	}
}

// public static final ArrayList<String> colorsList = new ArrayList<String>(Arrays.asList("RED", "ORN", "YLW", "GRN", "BLU", "PRP", "WHT", "BLK", "GLS"));
// // 3 5 3 5 3 5 7 2 9
// public static final int[] prices = {
// 3, 5, 3, 5, 3, 5, 7, 2, 9};