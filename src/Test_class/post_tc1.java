package Test_class;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.Commonmethodutilities;
import common_method.common_method_api;
import io.restassured.path.json.JsonPath;
import request_repository.post_request_repository;

public class post_tc1 {
	@Test
	public static void orchestrator() throws IOException
	{
		String responsebody= "";
		int responsestatuscode = 0;
		String baseuri = post_request_repository.baseuri();
		String resource = post_request_repository.resource();
		String requestbody = post_request_repository.post_request_Tc1();	
		for (int i=0;i<5;i++)
		{
		  responsestatuscode=common_method_api.responsestatuscode_extractor(baseuri,resource,requestbody);
		  if (responsestatuscode ==201)
		{
			 responsebody =common_method_api.responsebody_extractor(baseuri, resource, requestbody);
			responsebodyvalidator(responsebody);
			break;
		}
		else
		{
			System.out.println("correct status code is not found in the iteration" + i);
			
		}
	  }
		Commonmethodutilities.evidencefilecreator("post_tc1",requestbody,responsebody);
		Assert.assertEquals(responsestatuscode, 201);
	}
	
  public static void responsebodyvalidator(String responsebody)
  {
	  //create jsonpath object to extract responsebody parameters
	  JsonPath jsp =new JsonPath(responsebody);
	  
	  //extract responsebody parameters
	  String res_name=jsp.getString("name");
	  String res_job=jsp.getString("job");
	  String res_id=jsp.getString("id");
	  String res_createdAt=jsp.getString("createdAt");
	  
	System.out.println("name : " + res_name + "\njob :" + res_job + "\nid : " + res_id + "\ncreatedAt : " + res_createdAt );
	  
	  //validate responsebody parameters
	  
	  Assert.assertEquals(res_name, "morpheus");
	  Assert.assertEquals(res_job, "leader");
	  Assert.assertNotNull(res_id);
	  
	  // extract date from createdAt parameters
	  
	  String actual_date=res_createdAt.substring(0,10);
	  String current_date= LocalDate.now().toString(); // create a date object
	  Assert.assertEquals(actual_date, current_date);
	 System.out.println("Actual Date : " + actual_date + "\nCurrent Date :" + current_date);
	  
  }
}