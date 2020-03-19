/**
 *
 *  @author Adarczyn Piotr S19092
 *
 */

package zad1;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.Currency;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;



//api key:
//9a6d0312ff4d31904b5a7895193bad58
public class Service {

	final String API_KEY = "9a6d0312ff4d31904b5a7895193bad58";

	String country,
			isoCode,
				currencyCode,
					currencyToCompare;

	Map<String, String> isoMap = new LinkedHashMap<>();

	public Service(String country) {

		try {
			BufferedReader fileIso = new BufferedReader(
					new InputStreamReader(
							new FileInputStream("data\\country_codes")));

			for (String line = fileIso.readLine(); line != null; line = fileIso.readLine()) {

				String[] splitResult = line.split(",");
				isoMap.put(splitResult[0].toLowerCase(), splitResult[1].toLowerCase());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		this.country = country;
		this.isoCode = isoMap.getOrDefault(country.toLowerCase(), "pl");
		this.currencyCode = Currency.getInstance(new Locale("", isoCode)).getCurrencyCode();
	}

	public String getStringFromUrl(String strUrl)
			throws IOException {

		URL url = new URL(strUrl);
		StringBuilder result = new StringBuilder();
		BufferedReader urlReader = new BufferedReader(new InputStreamReader(url.openStream()));

		for (String line = urlReader.readLine(); line != null; line = urlReader.readLine())
			result.append(line);
		urlReader.close();

		return result.toString();

	}

	//http://api.openweathermap.org/data/2.5/weather?q=warsaw,pl&appid=9a6d0312ff4d31904b5a7895193bad58
	public String getWeather(String city) {


		String result = "Not found";
		try {
			result = getStringFromUrl(

					"http://api.openweathermap.org/data/2.5/weather?q=" + city.toLowerCase() + ","
							+ isoCode
							+ "&appid=" + API_KEY
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Double getRateFor(String currency) {

		this.currencyToCompare = currency;
		String jsonReply = "";

		try {
			//https://api.exchangeratesapi.io/latest?base=USD
			jsonReply = getStringFromUrl("https://api.exchangeratesapi.io/latest?base=" +
					currencyCode);

		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(jsonReply);
			JSONObject jsonObj = (JSONObject) obj;
			JSONObject rates = (JSONObject) jsonObj.get("rates");
			return (double) rates.get(currency);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Double getNBPRate() {

		if(currencyToCompare.equals("PLN"))
			return 1.0;
		try {
			Document tableA = Jsoup.connect("http://www.nbp.pl/kursy/kursya.html").get();
			Document tableB = Jsoup.connect("http://www.nbp.pl/kursy/kursyb.html").get();

			Elements listA = tableA.getElementsMatchingText(currencyToCompare);
			System.out.println(listA);
			System.out.println(listA.size() + "XDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
			System.out.println(listA.get);


		}catch (IOException e){
			e.printStackTrace();
		}
		return 0.0;
	}
}