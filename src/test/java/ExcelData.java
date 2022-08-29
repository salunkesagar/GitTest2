import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import Resources.Utils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ExcelData extends Utils{
	String id;
	RequestSpecification req1;
	ResponseSpecification res;
	Response res1;
	@Test

	public void addBook() throws IOException {
		
		ExcelDriven d=  new ExcelDriven();
		ArrayList data = d.getData("AddBook8","testdata");
		
		HashMap <String, Object> map = new HashMap();
		map.put("name", data.get(1));
		map.put("isbn", data.get(2));
		map.put("aisle", data.get(3));
		map.put("author", data.get(4));

		
		
		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		req1 =given().spec(reqSpecBuild()).body(map);
		res1 = req1.when().post("Library/Addbook.php")
					 .then().spec(res).extract().response();

		id = getJsonPath(res1,"ID");

		System.out.println(id);
	}
	@Test
	public void getBook() throws IOException
	{
		/*RestAssured.baseURI = "http://216.10.245.166/";
		String get = given().log().all().header("Content-Type", "application/json").queryParam("ID", id)
		.when().get("Library/GetBook.php?ID="+id+"")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		//System.out.println(get);
		
		JsonPath jsp = ReusableMethod.rawToJson(get);

		String s1 = jsp.get("isbn").toString();

		System.out.println(s1);*/
		
		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		req1 =given().spec(reqSpecBuild()).queryParam("ID", id);
		
		res1 = req1.when().get("Library/GetBook.php?ID="+id+"")
				.then().spec(res).extract().response();
		String isbn = getJsonPath(res1,"isbn");
		System.out.println(isbn);
		System.out.println("Git Demo1");
		System.out.println("Git Demo1");
		System.out.println("Git Demo2");
		System.out.println("Git Demo12");
		System.out.println("Git Demo123");
		System.out.println("Git Demo1234");
		System.out.println("Git Demo1234");
		
		
		
		
		
		
	}
	
	@Test(dependsOnMethods= {"getBook"})
	public void deleteBook() throws IOException
	{
		/*RestAssured.baseURI = "http://216.10.245.166/";
		String str = given().log().all().header("Content-Type", "application/json").body("{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+id+"\"\r\n"
				+ " \r\n"
				+ "}")
		.when().post("/Library/DeleteBook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(str);*/
		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		req1 =given().spec(reqSpecBuild()).body(deleteBookAPI(id));
		res1 = req1.when().post("/Library/DeleteBook.php")
				 .then().spec(res).extract().response();
		String str = res1.toString();
		System.out.println(str);
		
		
	}
	
	
	

}
