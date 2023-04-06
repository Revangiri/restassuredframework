package Test_class;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;


import common_method.Commonmethodutilities;
import common_method.Put_common_method;
import io.restassured.path.json.JsonPath;
import request_repository.put_request_repository;

public class patch_tc1 {
	
	public static void orchestrator() throws IOException
	{
		String responsebody= "";
		int responsestatuscode = 0;
		String baseuri = put_request_repository.baseuri();
		String resource = put_request_repository.resource();
		String requestbody = put_request_repository.put_request_tc1();	
		for (int i=0;i<5;i++)
		{
		  responsestatuscode=Put_common_method.responsestatuscode_extractor(baseuri, resource, requestbody);
		  if (responsestatuscode ==200)
		{
			 responsebody =Put_common_method.responsebody_extractor(baseuri, resource, requestbody);
			responsebodyvalidator(responsebody);
			break;
		}
		else
		{
			System.out.println("correct status code is not found in the iteration" + i);
			
		}
	  }
		Commonmethodutilities.evidencefilecreator("patch_tc1",requestbody,responsebody);
		Assert.assertEquals(responsestatuscode, 200);
	}

  public static void responsebodyvalidator(String responsebody)
  {
	  //create jsonpath object to extract responsebody parameters
	  JsonPath jsp =new JsonPath(responsebody);
	  
	  //extract responsebody parameters
	  String res_name=jsp.getString("name");
	  String res_job=jsp.getString("job");
	  String res_updatedAt=jsp.getString("updatedAt");
	  
	//  System.out.println("name : " + res_name + "\njob :" + res_job  + "\ncreatedAt : " + res_updatedAt );
	  
	  //validate responsebody parameters
	  
	  Assert.assertEquals(res_name, "morpheus");
	  Assert.assertEquals(res_job, "zion resident");
	  
	  // extract date from createdAt parameters
	  
	  String actual_date=res_updatedAt.substring(0,10);
	  String current_date= LocalDate.now().toString(); // create a date object
	  Assert.assertEquals(actual_date, current_date);
	 // System.out.println("Actual Date : " + actual_date + "\nCurrent Date :" + current_date);
	  
  }
}

	



