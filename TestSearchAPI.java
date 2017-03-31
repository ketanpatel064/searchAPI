import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.*;
import org.testng.Assert;
import org.testng.annotations.Test;




public class TestiTunesSearchAPI {

	private String baseURL = "https://itunes.apple.com/search?";

	/*
	 * get result count 
	 */
	public int getResultCount(String keyValuePair){
		int resCount = -1;
		try{
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet( baseURL + keyValuePair);
			HttpResponse response = httpClient.execute(getRequest);
			String responseContent = response.getEntity().getContent().toString();
			JSONObject jsonObject = new JSONObject(responseContent);
			resCount = Integer.parseInt(jsonObject.get("resultCount").toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return resCount;
	}
	
	@Test
	public void testWrongURl(){
		//code to test wrong url
		//should return 404
	}
	
	@Test
	public void testNullParameterKeyValue(){
		String keyValuePair = null;
		Assert.assertEquals(true, getResultCount(keyValuePair) == 0);
	}
	
	@Test
	public void testTermisNull(){
		String keyValuePair = null;
		Assert.assertEquals(true, getResultCount(keyValuePair) == 0);
	}
	
	@Test
	public void testTermisEmpty(){
		String keyValuePair = "term=";
		Assert.assertEquals(true, getResultCount(keyValuePair) == 0);
	}
	
	@Test
	public void testTermCorrectValue(){
		String keyValuePair = "term=jack+johnson";
		Assert.assertEquals(true, getResultCount(keyValuePair) >= 0);
	}
	
	@Test
	public void testTermWithSpecialCharacter(){
		String keyValuePair = "term=!";
		Assert.assertEquals(true, getResultCount(keyValuePair) >= 0);
	}
	
	@Test
	public void testCountryWithoutTerm(){
		String keyValuePair = "country=us";
		Assert.assertEquals(true, getResultCount(keyValuePair) == 0);
	}
	
	
	/*
	 * test cases for other 3 parameters are very similar with above test cases.
	 */

}
