import java.io.IOException;

public class users {
	
	public static void loadUser(String username) throws IOException {
		
		if(!checkUser(username)) {
			System.out.println("That user does not exist, would you like to create a new user with that name?(y/n)");
			if(fileHandeler.input().toLowerCase().charAt(0) == 'y') {
				newUser(username);
			}
			else {
				System.exit(0);
			}
		}
		
	}
	public static boolean checkUser(String username) throws IOException {
		
		String line = fileHandeler.getLine("src/users.txt", username);
		return !(line == null || line.equals(""));
		
	}
	public static void printUser(String username) throws IOException {
		
		String[] items = fileHandeler.getLine("src/users.txt", username).split(":");
		System.out.println("Name: " + items[0] + " | Currency: " + items[1] + " | BTC Balance: " + items[2] + " | CashBalance: " + items[3]);

	
	}
	public static double getCashBalance(String username) throws NumberFormatException, IOException {

		return Double.valueOf(fileHandeler.getItem("src/users.txt", 3, username));
		
	}
	public static double getBtcBalance(String username) throws NumberFormatException, IOException {

		return Double.valueOf(fileHandeler.getItem("src/users.txt", 2, username));
	}
	
	public static int getCurrency(String username) throws IOException {
		//0 = USD, 1 = GBP, 2 = EUR, defaults to USD
		
		String[] items = fileHandeler.getLine("src/users.txt", username).split(":");
		
		if(items[1].equals("GBP"))
			return 1;
		else if(items[1].equals("EUR"))
			return 2;
		else
			return 0;
			
	}
	public static void setBtcBalance(String username, double balance) throws IOException {
		fileHandeler.replace("src/users.txt", 2, username, "" + balance);
	}
	public static void setCashBalance(String username, double balance) throws IOException {
		fileHandeler.replace("src/users.txt", 2, username, "" + balance);
	}

	public static void newUser(String username) throws IOException {
		
		System.out.println("Whats is your preffered currency?(USD, GBP, EUR):");
		String curr = fileHandeler.input();
		String newUser = username + ":" + curr + ":" + 5 + ":" + 0;
		fileHandeler.append("src/users.txt", newUser);
		


	}
}