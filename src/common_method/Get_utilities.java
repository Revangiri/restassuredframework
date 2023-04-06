package common_method;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Get_utilities {
	
	public static void EvidenceFileCreator(String fileName, String response) throws IOException
	{
		File newtxtfile=new File("C:\\restAssuredEvidence\\" +fileName+ ".txt");
		
		if(newtxtfile.createNewFile())
		{
			FileWriter datawriter=new FileWriter(newtxtfile);
			datawriter.write("Response body is: " +response);
			datawriter.close();
			System.out.println("\n\nRequest and Response Body file is created." +newtxtfile.getName());
		}
		else
		{
			System.out.println(newtxtfile.getName()+ "\n\nFile already exists, take backup of it.");
		}
	}

}