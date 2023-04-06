package common_method;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Commonmethodutilities {
	public static void evidencefilecreator(String filename, String request, String response) throws IOException
	{
		File newTextFile = new File("C:\\restAssuredevidence\\"
				+ filename + ".txt");
		if(newTextFile.createNewFile())
		{
		FileWriter dataWriter =new FileWriter(newTextFile);
		dataWriter.write("Requestbody is :\n" + request + "\n\n");
		dataWriter.write("Responsebody is :\n" + response);
		dataWriter.close();
		System.out.println("request and responsebody saved in :" +newTextFile.getName());
		
	}
		else
		{
			System.out.println(newTextFile.getName() + " Already exits take a back up of it :");
			}
		}

}
