import java.io.*;
import java.util.Scanner;

/**
 *
 * CSCU9T4 Java strings and files exercise.
 *
 */
public class FormatNames
{
	//Replace this with statements to set the file name (input) and file name (output).
	//Initially it will be easier to hardcode suitable file names.
	
	//Set up a new Scanner to read the input file.
	//Processing line by line would be sensible here.
	//Initially, echo the text to System.out to check you are reading correctly.
	//Then add code to modify the text to the output format.
	
	//Set up a new PrintWriter to write the output file.
	//Add suitable code into the above processing (because you need to do this line by line also.
	//That is, read a line, write a line, loop.
	
	//Finally, add code to read the filenames as arguments from the command line.
	
	private static final int MAX_NAME_LENGTH = 20; //For date alignment
	private static final int DATE_LENGTH = 8; //Without slash formatting
	
	public static void main(String[] args)
	{
		try
		{
			File inputFile;
			File outputFile;
			
			if (args[0].equals("-u"))
			{
				inputFile = new File(args[1]);
				outputFile = new File(args[2]);
			}
			else
			{
				inputFile = new File(args[0]);
				outputFile = new File(args[1]);
			}
			
			//INPUT
			Scanner reader = new Scanner(inputFile);
			
			//OUTPUT
			outputFile.createNewFile();
			FileWriter writer = new FileWriter(outputFile);
			
			while (reader.hasNextLine())
			{
				//READ
				String data = reader.nextLine();
				char[] dataArr = data.toCharArray();
				
				//Split name and date
				char[] nameArr = sepLetter(dataArr);
				char[] dateArr = sepDigit(dataArr);
				
				if (args[0].equals("-u"))
				{
					formatUpperCase(nameArr);
				}
				else
				{
					formatTitleCase(nameArr);
				}
				
				dateArr = formatDate(dateArr);
				
				//System log
				System.out.println(String.valueOf(nameArr) + String.valueOf(dateArr));
				
				//WRITE NAME
				writer.write(String.valueOf(nameArr));
				
				//DATE SPACE FORMAT
				for (int i = MAX_NAME_LENGTH - nameArr.length; i > 0; i--)
				{
					writer.write(" ");
				}
				
				//WRITE DATE
				writer.write(String.valueOf(dateArr) + "\n");
				
				//END WRITE
				writer.flush();
			}
			
			reader.close();
			writer.close();
		}
		catch (IOException e)
		{
			System.out.println("Error.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Separates the letters (and spaces) in an array and returns them in a new array.
	 * @param arr array to be separated
	 * @return string (StringBuilder)
	 */
	public static char[] sepLetter(char[] arr)
	{
		char[] newArr = new char[arr.length - DATE_LENGTH];
		int count = 0; //Counts new array elements
		
		for (char c : arr)
		{
			if (Character.isLetter(c) || Character.isSpaceChar(c))
			{
				newArr[count] = c;
				count++;
			}
		}
		
		return newArr;
	}
	
	/**
	 * Separates the digits in an array and returns them in a new array.
	 * @param arr array to be separated
	 * @return string (StringBuilder)
	 */
	public static char[] sepDigit(char[] arr)
	{
		char[] newArr = new char[DATE_LENGTH];
		int count = 0; //Counts new array elements
		
		for (char c : arr)
		{
			if (Character.isDigit(c))
			{
				newArr[count] = c;
				count++;
			}
		}
		
		return newArr;
	}
	
	/**
	 * Formats lowercase char array containing a name into title case.
	 * @param arr array to be formatted
	 */
	public static void formatTitleCase(char[] arr)
	{
		boolean space = true; //Check for capital letter at start of word
		
		for (int i = 0; i < arr.length; i++)
		{
			if (Character.isLetter(arr[i]))
			{
				if (space) //If start of word, give upper case
				{
					arr[i] = Character.toUpperCase(arr[i]);
					space = false;
				}
			}
			else if (Character.isSpaceChar(arr[i]))
			{
				space = true;
			}
		}
	}
	
	/**
	 * Formats lowercase char array into uppercase.
	 * @param arr array to be formatted
	 */
	public static void formatUpperCase(char[] arr)
	{
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = Character.toUpperCase(arr[i]);
		}
	}
	
	/**
	 * Formats lowercase char array into title case.
	 * @param arr array to be formatted
	 * @return formatted array
	 */
	public static char[] formatDate(char[] arr)
	{
		char[] newArr = new char[DATE_LENGTH + 2];
		int count = 0;
		
		for (char c : arr)
		{
			if (count == 2  /*D/M*/ || count == 5 /*M/Y*/) //Add slash
			{
				newArr[count] = '/';
				count++;
			}
			
			newArr[count] = c;
			count++;
			
		}
		
		return newArr;
	}
}
