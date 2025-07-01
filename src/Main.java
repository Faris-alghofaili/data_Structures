import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	static Scanner scan = new Scanner(System.in);
	static ADTWordAnalysis adt;

	
	public static String GetMenu() {
		System.out.println("=============================================================");
		System.out.println("1* Total number of words.");
		System.out.println("2* Total number of unique words.");
		System.out.println("3* Total number of occurrences of a particular word.");
		System.out.println("4* Total number of words with a particular lengths.");
		System.out.println("5* Unique words in the file sorted.");
		System.out.println("6* Locations of the occurrences of a word.");
		System.out.println("7* Examine if two words are occurring adjacent to each other.");
		System.out.println("another key to exit:");
		System.out.print("Your choice please -> ");
		return scan.nextLine();
	}

	public static void main(String[] args) throws FileNotFoundException {
		System.out.print("File name: ");
		String file_name = scan.nextLine();
		File input_file = new File(file_name);
		if (input_file.exists() == true) {
			adt = new ADTWordAnalysis(50, input_file);
			while (true) {
				switch (GetMenu()) {
				case "1":
					System.out.println(adt.operation1());
					break;
				case "2":
					System.out.println(adt.operation2());
					break;
				case "3": {
					System.out.print("please enter word: ");
					String word = scan.nextLine();
					
					int num = adt.operation3(word);
					System.out.println(num);
					break;
				}
				case "4": {
					System.out.print("please enter length: ");
					int length = Integer.parseInt(scan.nextLine());
					int num = adt.operation4(length);
					System.out.println(num);
					break;
				}
				case "5":
					adt.operation5();
					break;
				case "6": {
					System.out.print("Please enter word: ");
					String word = scan.nextLine();
					adt.operation6(word);
					break;
				}
				case "7":
					System.out.println("Please enter word1 and word2: ");
					String word1 = scan.nextLine();
					String word2 = scan.nextLine();
					boolean result = adt.operation7(word1, word2);
					System.out.println(result);
					break;
				default:
					System.out.println("Thanks for using WordAnalysis application.");
					System.exit(0);
				}
			}
		}
	}

}
