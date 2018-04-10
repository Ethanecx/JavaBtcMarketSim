import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class fileHandeler {
	


    static String txBuffer;
    static String userBuffer;
    
    
    
    
    public static void push() throws IOException {
    	
        BufferedWriter bwU = new BufferedWriter(new FileWriter("src/user.txt"));
        BufferedWriter bwX = new BufferedWriter(new FileWriter("src/transactions.txt"));
        bwU.write(userBuffer);
        bwX.write(txBuffer);
    }
    public static void load() throws IOException {
    	
    	BufferedReader brU = new BufferedReader(new FileReader("src/users.txt"));
    	BufferedReader brTX = new BufferedReader(new FileReader("src/transactions.txt"));
    	String line;
    	while ((line = brU.readLine()) != null) {
    		userBuffer = userBuffer + line + "\n";
    	}
    	System.out.println("Filling Buffers...(1/2)");
    	while ((line = brU.readLine()) != null) {
    		txBuffer = txBuffer + line + "\n";
    	}
    	System.out.println("Filling Buffers...(2/2)");
    }
	
	public static void replace(String path, int col, String row, String newVal) throws IOException {
		
		String bUserBuffer = "";
		
		for(String line : userBuffer.split("\n")) {
			String[] split = line.split(":");
			if (split[0].equals(row)) {
				line = "";
			   	for(int i = 0; i < split.length;i++) {
		       		if(i == col) {
	          			line = line + newVal + ":";
	           			continue;
		            }
		            line = line + split[i] + ":";
		     	}
			}
			               
		    bUserBuffer += line;
		}
			
		userBuffer = bUserBuffer;

	}
	public static String getItem(String path, int col, String row) throws IOException {
		
		String buffer = "";
		if(path.equals("src/transactions.txt")) {
			buffer = txBuffer;
		} else if(path.equals("src/users.txt")) {
			buffer = userBuffer;
		}

		for(String line : buffer.split("\n")) {
			String[] split = line.split(":");
			if (split[0].equals(row))
				return split[col];
		}
		System.out.println("Could not find item at row["+row+"] and column["+col+"] at path[" + path + "]");
		return "";
	}
	public static void append(String path, String newLine) throws IOException {
		if(path.equals("src/transactions.txt")) {
			txBuffer += newLine + "\n";
		} else if(path.equals("src/users.txt")) {
			userBuffer += newLine + "\n";
		}
	}
	public static String getLine(String path, String row) throws IOException {
		
		String buffer = "";
		if(path.equals("src/transactions.txt")) {
			buffer = txBuffer;
		} else if(path.equals("src/users.txt")) {
			buffer = userBuffer;
		}
		if(buffer == null || buffer.equals(""))
			return buffer;

		for(String line : buffer.split("\n")) {
			String[] split = line.split(":");
			if (split[0].equals(row))
				return line;
		}
		System.out.println("Could not find row[" + row + "]");
		return "";
	}
	public static String input() {
		Scanner user = new Scanner(System.in);
		String input = user.nextLine();
		return input;
	}
	public static String getLastLine(String path) throws IOException {
		
		String buffer = "";
		if(path.equals("src/transactions.txt")) {
			buffer += txBuffer;
		} else if(path.equals("src/users.txt")) {
			buffer += userBuffer;
		}
		String lastLine = "";
		System.out.println(buffer);
		if(!buffer.equals("")) {
			for(String line : buffer.split("\n")) {
				lastLine = line;
			}
		}
		return lastLine;
	}

}
