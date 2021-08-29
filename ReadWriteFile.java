import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;

public class ReadWriteFile
{
    private static final String INPUT_FILE_NAME="vehicles.txt";
    private static final String OUTPUT_FILE_NAME="output.txt";

    public static String readInputFile()
    {
        String fileContents = "";
        try
        {
            FileReader reader = new FileReader(INPUT_FILE_NAME);
            try
            {
                Scanner fileInput = new Scanner(reader);
                while(fileInput.hasNextLine())
                {
                    String lineContent = fileInput.nextLine();
                    fileContents += (lineContent + "|");
                }
                fileInput.close();
            }
            finally
            {
                try
                {
                    reader.close();
                }
                catch (Exception e)
                {
                    System.out.println("Error closing the file!");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error getting a vehicle from the file. Contact the Admin!");
        }
        return fileContents;
    }

    public static void writeFile(String printString)
    {
        try
        {
            FileWriter writer = new FileWriter(OUTPUT_FILE_NAME);
            try
            {
                writer.append(printString);
            }
            catch (Exception e)
            {
                System.out.println("Error in writing to file! Exiting...");
            }
            finally
            {
                try
                {
                    writer.close();
                }
                catch (Exception e)
                {
                    System.out.println("Error closing the file");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error in writing to file! Exiting...");
        }        
    }
}