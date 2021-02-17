import java.io.*;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * CSCU9T4 Java strings and files exercise.
 *
 */
public class FilesInOut {
	
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
	
	public static void main(String[] args)
	{
		final int MAX_NAME_LENGTH = 20; //For date alignment
		
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
				
				StringBuilder name = new StringBuilder();
				int nameCount = 0;
				boolean space = true; //Check for capital letter at start of word
				
				StringBuilder date = new StringBuilder();
				int dateCount = 0; //Counter to insert slashes in right place
				
				//FORMAT
				for (char c : dataArr)
				{
					//Appends all letters
					if (Character.isLetter(c))
					{
						if (args[0].equals("-u"))
						{
							name.append(Character.toUpperCase(c));
						}
						else if (space) //If start of word, give upper case
						{
							name.append(Character.toUpperCase(c));
							space = false;
						}
						else //Append rest of letters
						{
							name.append(c);
						}
						
						nameCount++;
					}
					else if (Character.isSpaceChar(c)) //If space before word
					{
						space = true;
						name.append(" ");
					}
					
					//Appends all numbers
					if (Character.isDigit(c))
					{
						date.append(c);
						dateCount++;
					}
					
					//Add slashes to date
					if (dateCount == 2 /*D/M*/ || dateCount == 4 /*M/Y*/)
					{
						date.append("/");
					}
				}
				
				//System log
				System.out.println(name + "" +date);
				
				//WRITE NAME
				writer.write(String.valueOf(name));
				
				//DATE SPACE FORMAT
				for (int i = MAX_NAME_LENGTH - nameCount; i > 0; i--)
				{
					writer.write(" ");
				}
				
				//WRITE DATE
				writer.write(date + "\n");
				
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
}
