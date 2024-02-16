package LerosUsefulLibrary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Lerohi
 * @version 24.0216
 */
public abstract class FileEditor {

	private FileEditor() {
		throw new IllegalStateException("This is a static utility class, therefore it can't be instantiated!");
	}

	/**
	 * Appends the given text to the content of the given file
	 * 
	 * @param file An Object of the class "File" that represents the file
	 * @param text A String containing the text that is supposed to be appended
	 */
	public static void append(File file, String text) {
		ensureFileExistence(file);
		try(FileWriter fw = new FileWriter(file, true)) {
			fw.write(text);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Overwrites the content of the given file with the given text
	 * 
	 * @param file An Object of the class "File" that represents the file
	 * @param text A String containing the text that is supposed to be appended
	 */
	public static void overwrite(File file, String text) {
		ensureFileExistence(file);
		try(FileWriter fw = new FileWriter(file, false)) {
			fw.write(text);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads the content of a file and returns it as a String
	 * 
	 * @param file An Object of the class "File" that represents the file
	 * @return A String containing the content of the file that is being read
	 */
	public static String read(File file) {
		String res = "";
		ensureFileExistence(file);
		try(Scanner sc = new Scanner(file)) {
			while (sc.hasNext()) {
				res += sc.next();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Reads the content of a file as lines and returns it as an ArrayList containing the lines as Strings
	 * 
	 * @param file An Object of the class "File" that represents the file
	 * @return An ArrayList containing the lines of the file that is being read as Strings
	 */
	public static List<String> readLines(File file) {
		ArrayList<String> res = new ArrayList<>();
		ensureFileExistence(file);
		try(Scanner sc = new Scanner(file)) {
			while(sc.hasNextLine()) {
				res.add(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Reads the content of a file and returns it as a String
	 * 
	 * @param file An Object of the class "File" that represents the file
	 * @param line The number of the line that is supposed to be read
	 * @return A String containing the content of the line that is being read
	 */
	public static String readSpecificLine(File file, int line) {
		List<String> lines = readLines(file);
		return lines.get(line);
	}

	//Support-Methods
	/**
	 * Ensures that the given file exists, if it doesn't, it will create a new file with the properties defined in the given "File"-Object 
	 * 
	 * @param file An Object of the class "File" that represents the file
	 */
	public static void ensureFileExistence(File file) {
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}