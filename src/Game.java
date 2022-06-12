import java.util.Scanner;

public class Game implements Constants {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Hi! Welcome to PokeFrogs! Please log in:\nUsername:");
		String username = keyboard.nextLine();
		// System.out.println("Enter your password:");
		// String pass = scan.nextLine();

		//api GET call will happen here
		System.out.println("Welcome back, " + username);
		//String serializedGameState = ""; //API call here
		GameState gs = new GameState();

		//TODO: this is dev stuff, remove it later or make a helper method for it
		Frog newFrog = new Frog(gs.ownerID);
		newFrog.genome.addGene("base", new ColorGene(new int[]{0, 1})); //ORN
		newFrog.genome.addGene("accent", new ColorGene(new int[]{3, 4})); //GLS
		newFrog.genome.addGene("pattern", new PatternGene(new int[]{0, 0, 1})); //STARS
		newFrog.shiny = true;
		gs.box.add(newFrog);

		System.out.println(newFrog.toString());
		while(true) {
			System.out.println("Input command:");
			String inputString = keyboard.nextLine();
			String[] arguments = inputString.split(" ");
			if(arguments.length < 1) {
				System.out.println("Invalid input! Try again.");
				continue;
			}
			switch (arguments[0].toLowerCase()) {
				case "save":
					gs.save();
					//TODO: actually have this use a UPDATE api call. Be sure to check for empty return strings, and to not save in that case.
					break;
				case "view":
					System.out.println(gs.box.toString());
					break;
				case "exit":
					System.out.println("one moment...");
					gs.save();
					//TODO: actually have this use an UPDATE API call
					System.out.println("See you next time!");
					keyboard.close();
					System.exit(0);
					break;
				default:
					System.out.println("Unknown command, try again!");
					break;
			}
		}
	}

}
