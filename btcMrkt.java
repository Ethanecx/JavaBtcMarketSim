import java.io.IOException;
import org.json.JSONException;


public class btcMrkt {

	private static String username;
	private static String[] currencies = {"USD", "GBP", "EUR"};
	
	public static void main(String[] args) throws JSONException, IOException {
		fileHandeler.load();
		System.out.print("Username:");
		username = fileHandeler.input();
		users.loadUser(username);
		printAll();


	}
	
	public static void printAll() throws JSONException, IOException {
	
		while(true) {
			
			float[] prices = btcPrice.getPrices();
			
			for(int i = 0; i < 3; i++) {
				System.out.println(currencies[i] + " price: " + prices[i]);
			}
			
			txHistory.printRecentTx();
			System.out.println("1. Buy\n2. Sell\n3. Get Tx\n4. Show user data\n5. Push\n9. Exit");
			System.out.print("> ");
			String option = fileHandeler.input();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			
			if(option.equals("1"))
				buy();
			
			if(option.equals("2"))
				sell();
			
			if(option.equals("3")) {
				System.out.print("Tx id:");
				txHistory.getTx(fileHandeler.input());
			}
			
			if(option.equals("4"))
				users.printUser(username);
			if(option.equals("5"))
				fileHandeler.push();
				
			if(option.equals("9"))
				System.exit(0);
		
		}
		
	}
	
	public static void buy() throws IOException {
		
		System.out.print("Purchase amount: ");
		double price = btcPrice.getPrices()[users.getCurrency(username)];
		double amount = Double.valueOf(fileHandeler.input());
		
		if(amount/price <= users.getCashBalance(username)) {
			
			System.out.println("Buying " + amount + "BTC at a price of " + price + currencies[users.getCurrency(username)] + " for the cost " + amount/price);
			users.setBtcBalance(username, users.getBtcBalance(username) + amount);
			users.setCashBalance(username, users.getCashBalance(username) - amount/price);
			
		} else {
			
			System.out.println("Cash balance too low");
			
		}
		
		
	}
	
	public static void sell() throws IOException {
		
		System.out.print("Sell amount: ");
		double price = btcPrice.getPrices()[users.getCurrency(username)];
		double amount = Double.valueOf(fileHandeler.input());

		if(amount <= users.getBtcBalance(username)) {
			
			System.out.println("Selling " + amount + "BTC at a price of " + price + currencies[users.getCurrency(username)] + " for the amount " + price*amount);
			users.setBtcBalance(username, users.getBtcBalance(username) - amount);
			users.setCashBalance(username, users.getCashBalance(username) + amount*price);
			
		} else {
			
			System.out.println("BTC balance too low");
			
		}
		
		
	}

}
