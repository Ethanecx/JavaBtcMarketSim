import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.json.JSONObject;

public class btcPrice {

  private static String readAll(Reader rd){
    StringBuilder sb = new StringBuilder();
    int cp;
    try {
		while ((cp = rd.read()) != -1) {
		  sb.append((char) cp);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return sb.toString();
  }

  private static JSONObject readJsonFromUrl(String urlS) {
    // = 
    URL url;
	try {
		url = new URL(urlS);
	    URLConnection hc = url.openConnection();
	    hc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64)");
	    InputStream is = hc.getInputStream();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	    String jsonText = readAll(rd);
	    JSONObject json = new JSONObject(jsonText);
	    return json;
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return new JSONObject();

  }

  public static float[] getPrices() {
	  
	  float[] prices = new float[3];
	  JSONObject json = readJsonFromUrl("https://api.coindesk.com/v1/bpi/currentprice.json");
	  JSONObject bpi = json.getJSONObject("bpi");
	  for(int i = 0; i < 3; i++) {
		  prices[i] = bpi.getJSONObject(new ArrayList<String>(bpi.keySet()).get(i)).getFloat("rate_float");
	  }
	  return prices;
  }
}