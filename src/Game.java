import java.util.Scanner;

public class Game implements Constants {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Hi! Welcome to PokeFrogs! Please log in:");
		String username = scan.nextLine();

		//api GET call will happen here
		System.out.println("Welcome back " + username);
		String serializedGameState = "";
		GameState gs = new GameState();


		//TODO: this is dev stuff
		Frog newFrog = new Frog(gs.ownerID);
		// newFrog.genome.put("base", new int[]{0, 1}); //ORN
		// newFrog.genome.put("accent", new int[]{3, 4}); //GLS
		// newFrog.genome.put("pattern", new int[]{0, 0, 1}); //STARS
		newFrog.shiny = true;
		gs.megabox.get(0).add(newFrog);

		System.out.println(newFrog.toString());

		boolean gameRunning = true;
		int box = 0;
		while(gameRunning) {
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
					gameRunning = false;
					System.out.println("one moment...");
					gs.save();
					//TODO: actually have this use an UPDATE API call
					System.out.println("See you next time!");
					break;
				case "switch":
					if(arguments.length < 2) {
						System.out.println("You need tp specify which box!");
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
		scan.close();
	}

}
