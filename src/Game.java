import java.util.Scanner;

public class Game implements Constants {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Hi! Welcome to PokeFrogs! Please log in:");
		String username = scan.nextLine();
		// System.out.println("Enter your password:");
		// String pass = scan.nextLine();

		//api GET call will happen here
		System.out.println("Welcome back " + username);
		//String serializedGameState = ""; //API call here
		GameState gs = new GameState();

		//TODO: this is dev stuff, remove it later or make a helper method for it
		Frog newFrog = new Frog(gs.ownerID);
		newFrog.genome.addGene("base", new Gene.MendelianMonogenetic(new int[]{0, 1})); //ORN
		newFrog.genome.addGene("accent", new Gene.MendelianMonogenetic(new int[]{3, 4})); //GLS
		newFrog.genome.addGene("pattern", new Gene.MendelianMonogenetic(new int[]{0, 0, 1})); //STARS
		newFrog.shiny = true;
		gs.megabox.get(0).add(newFrog);

		System.out.println(newFrog.toString());

		int box = 0;
		while(true) {
			System.out.println("Input command:");
			String input = scan.nextLine();
			String[] arguments = input.split(" ");
			if(arguments.length < 1) {
				System.out.println("Invalid input! Try again.");
				continue;
			}
			switch (arguments[0].toLowerCase()) {
				case "save":
					gs.save();
					//TODO: actually have this use a UPDATE api call
					break;
				case "view":
					System.out.println(gs.megabox.toString());
					break;
				case "exit":
					System.out.println("one moment...");
					gs.save();
					//TODO: actually have this use an UPDATE API call
					System.out.println("See you next time!");
					scan.close();
					System.exit(0);
					break;
				case "switch":
					if(arguments.length < 2) {
						System.out.println("You need to specify which box!");
						break;
					}
					box = Integer.parseInt(arguments[1]);
					if(box > 9 || box < 0) {
						
					}
					System.out.println(box);
					break;
				default:
					System.out.println("Unknown command, try again!");
					break;
			}
		}
	}

}
