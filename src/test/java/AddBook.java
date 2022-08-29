import java.io.IOException;
import java.util.ArrayList;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class AddBook {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ExcelDriven d = new ExcelDriven();
		ArrayList<String> data = d.getData("AddBook3","testdata");
		//String a = data.get(1);
		//String b = data.get(2);
		//String c = data.get(3);
		//System.out.println(a);
		RestAssured.baseURI = "http://216.10.245.166";
		String s = given().log().all().header("Content-Type","application/json").body("{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+data.get(1)+"\",\r\n"
				+ "\"aisle\":\""+data.get(2)+"\",\r\n"
				+ "\"author\":\""+data.get(3)+"\"\r\n"
				+ "}")
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(s);
		String s1 = js.getString("ID");
		System.out.println(s1);
		
		
		
		
		
		

	}

}
