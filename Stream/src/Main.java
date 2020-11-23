import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		String filePath = "/Users/leapkh/Desktop/students.txt";
		//readData(filePath);
		//readDataAsString(filePath);
		//readDataFromKeyboard();
		//writeData(filePath, 65);
		//byte[] korLetter = new byte[] {(byte)225, (byte)158, (byte)128};
		//writeUnicodeData(filePath, korLetter);
		writeDataAsString(filePath, "សួស្តី");
		
	}
	
	private static void readData(String filePath) {
		try {
			InputStream inputStream = new FileInputStream(filePath);
			int dataByte;
			while((dataByte = inputStream.read()) != -1) {
				System.out.println("Data: " + dataByte);				
			}
			inputStream.close();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void readDataAsString(String filePath) {
		try {
			InputStream inputStream = new FileInputStream(filePath);
			Scanner scanner = new Scanner(inputStream);
			while(scanner.hasNext()) {
				String line = scanner.nextLine();
				System.out.println("Line: " + line);
			}
			scanner.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void readDataFromKeyboard() {
		InputStream inputStream = System.in;
		Scanner scanner = new Scanner(inputStream);
		System.out.print("Please input your name: ");
		String inputFromKeyboard = scanner.nextLine();
		System.out.println("The input is: " + inputFromKeyboard);
		scanner.close();
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void writeData(String filePath, int data) {
		try {
			OutputStream outputStream = new FileOutputStream(filePath);
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeUnicodeData(String filePath, byte[] data) {
		try {
			OutputStream outputStream = new FileOutputStream(filePath);
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeDataAsString(String filePath, String data) {
		try {
			OutputStream outputStream = new FileOutputStream(filePath);
			PrintWriter printWriter = new PrintWriter(outputStream);
			printWriter.write(data);
			printWriter.flush();
			printWriter.close();
			outputStream.close();
			System.out.println("The data has been writen!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}


















