package request_repository;

import java.io.IOException;
import java.util.ArrayList;

import common_method.get_data;

public class post_request_repository {
	
	public static String baseuri()
	{
		String baseuri="https://reqres.in/";
		return baseuri;
	}
	public static String resource()
	{
		String resource ="api/users";
		return resource;
	}

	
	public static String post_request_Tc1() throws IOException
	{
		ArrayList<String> data = get_data.getdataexcel("post_data", "tc1");
		String Name = data.get(2);
		String Job = data.get(3);
	  String requestbody = "{\r\n"
	  		+ "    \"name\": \""+Name+"\",\r\n"
	  		+ "    \"job\": \""+Job+"\"\r\n"
	  		+ "}";	
	  return requestbody;
	}
/*	public static String post_request_Tc2()
	{
	  String requestbody = "{\r\n"
	  		+ "    \"name\": \"Revan\",\r\n"
	  		+ "    \"job\": \"QA\"\r\n"
	  		+ "}";	
	  return requestbody;
	}*/
}
