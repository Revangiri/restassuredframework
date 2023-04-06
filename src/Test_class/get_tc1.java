package Test_class;

import java.io.IOException;

import org.testng.Assert;

import common_method.Get_utilities;
import common_method.Get_common_method;
import io.restassured.path.json.JsonPath;
import request_repository.get_request_repository;

public class get_tc1 {
	
	public static void orchestrator() throws IOException
	{
		int res_StatusCode=0;
		String responseBody="";
		String baseURI=get_request_repository.baseuri();
		String resource=get_request_repository.resource();
		
		for(int i=0; i<5; i++)
		{
			res_StatusCode=Get_common_method.responseStatusCode_Extractor(baseURI, resource);
			if(res_StatusCode==200)
			{
				responseBody=Get_common_method.responseBody_Extractor(baseURI, resource);
				responseBodyValidator(responseBody);
				break;
			}
			else
			{
				System.out.println("Correct Status Code is not found");
			}

		}
		Get_utilities.EvidenceFileCreator("get_TC1", responseBody);
		Assert.assertEquals(res_StatusCode, 200);
		}

	private static void responseBodyValidator(String responseBody) {
		// TODO Auto-generated method stub
		
		//Create json Path object to extract response body
				JsonPath obj = new JsonPath(responseBody);
				
				int data_array_length=obj.getInt("data.size()");
				System.out.println("Data: " +data_array_length);
		//Extract Response body parameters to validate
				int exp_Id[] = {7,8,9,10,11,12};
				String exp_email[]= {"michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in", "byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in"};
				String exp_fname[]= {"Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel"};
				String exp_lname[]= {"Lawson", "Ferguson", "Funke", "Fields", "Edwards", "Howell"};
				String exp_Avatar[]= {"https://reqres.in/img/faces/7-image.jpg", "https://reqres.in/img/faces/8-image.jpg", "https://reqres.in/img/faces/9-image.jpg", "https://reqres.in/img/faces/10-image.jpg", "https://reqres.in/img/faces/11-image.jpg", "https://reqres.in/img/faces/12-image.jpg"};
				
				for(int i=0; i<data_array_length; i++)
				{
				//extract response body parameters from array
				int res_Id=obj.getInt("data["+i+"].id");
				String res_email=obj.get("data["+i+"].email");
				String res_fname=obj.getString("data["+i+"].first_name");
				String res_lname=obj.getString("data["+i+"].last_name");
				String res_Avatar=obj.getString("data["+i+"].avatar");
				
				//Print the Result from response body
				System.out.println("Id: " +res_Id);
				System.out.println("Email Id: " +res_email);
				System.out.println("First Name: " +res_fname);
				System.out.println("Last Name: " +res_lname);
				System.out.println("Avatar: " +res_Avatar);
				
				//Validate the response body parameters
				Assert.assertEquals(res_Id, exp_Id[i]);
				Assert.assertEquals(res_email, exp_email[i]);
				Assert.assertEquals(res_fname, exp_fname[i]);
				Assert.assertEquals(res_lname, exp_lname[i]);
				Assert.assertEquals(res_Avatar, exp_Avatar[i]);
				}
			}
				
	

}