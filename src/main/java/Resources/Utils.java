package Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public static RequestSpecification req;
	public static ResponseSpecification res;
	PrintStream log ;
	public static String getURI(String key) throws IOException
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\Sagar Salunke\\ExcelData\\ExcelDataDriven\\src\\main\\java\\data.properties");
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
	public RequestSpecification reqSpecBuild() throws IOException
	{
		log = new PrintStream(new FileOutputStream("logging.txt"));
		req = new RequestSpecBuilder().setBaseUri(getURI("uri")).setContentType(ContentType.JSON)
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return req;
	}
	
	
	/*public ResponseSpecification resSpecBuild() throws FileNotFoundException
	{
		log = new PrintStream(new FileOutputStream("logging.txt"));
		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.
		return res;
		
	}*/
	
	public String getJsonPath(Response res1, String key)
	{
		String s = res1.asString();
    	JsonPath js = new JsonPath(s);
    	return js.get(key).toString();
	}
	
	public String deleteBookAPI(String id)
	{
		return "{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+id+"\"\r\n"
				+ " \r\n"
				+ "}";
	}

}
