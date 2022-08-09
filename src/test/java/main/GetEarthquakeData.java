package main;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

import jsonObjects.MainObject;
import jsonObjects.Features;

public class GetEarthquakeData {

	public static void main(String[] args) {

		// for getting the earth quake data after an interval of time
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				try {
					getEqdata();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		timer.scheduleAtFixedRate(task, 0, 2000);
	}

	public static void getEqdata() throws IOException {
		Base base = new Base();
		base.preSetup();

		// storing the response as a json object
		String jsonObj = base.response.asPrettyString();

		// regex for finding pune in the title property of the response
		Pattern pattern = Pattern.compile("(pune|Pune)");
		Matcher matcher;
		MainObject mainObject = new Gson().fromJson(jsonObj, MainObject.class);
		List<Features> feature = mainObject.features;
		for (Features property : feature) {
			matcher = pattern.matcher(property.properties.title);
			// checking if there is any earthquake in pune to notify the users
			if (matcher.find() == true) {
				System.out.println("EarthQuake!!!! in " + property.properties.title );
			}
		}


	}

}
