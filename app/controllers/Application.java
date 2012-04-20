package controllers;

import play.*;

import play.mvc.*;

import java.util.*;

import models.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.merlin.util.GoogleMapHelper;

public class Application extends Controller {

	private static String ok = "{\"result\":0}";
	private static String no = "{\"result\":1}";

	public static void index() {
		List<Users> users = null;
		users = Users.findAll();
		// for(Users u:users){
		// System.out.println(u.username);
		// }
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		String sUserString = gson.toJson(users);
		// System.out.println(sUserString);
		render();
		// renderJSON(sUserString);
	}

	public static void addgps(String lon, String lat, String userid) {
		Location l = new Location();
		l.lon = lon;
		l.lat = lat;
		l.userid = userid;
		l.save();
		// render();
	}

	public static void addgpspost() {
		try {
			Location l = new Location();
			l.edit("gps", params.all());
			if (l.lon != null && l.lon.length() > 0) {
				Location l2 = Location.find("userid = ?", l.userid).first();
				if(l2.userid==l.userid){
					l2.lon = l.lon;
					l2.lat = l.lat;
					l2.save();
				}else{
					l.save();
				}
				
				renderJSON(ok);
			}
		} catch (Exception e) {
			renderJSON(no);
		}
		render();
	}

	public static void getgpslist(String lon, String lat, String level) {
		List<Location> locations = null;
		locations = Location.findAll();
		// render(locations,lon,lat);
		// GoogleHelper
		List<Location> locations2 = new ArrayList<Location>();
		for (Location l : locations) {
			double distance2 = GoogleMapHelper.getDistance(Double.valueOf(lat),
					Double.valueOf(lon), Double.valueOf(l.lat), Double.valueOf(l.lon));
//			 System.out.println(distance2);
			if(distance2<100000){
				Location l2 = new Location();
				l2.lon = l.lon;
				l2.lat = l.lat;
				locations2.add(l2);
			}
		}
		
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		String sUserString = gson.toJson(locations2);
		renderJSON(sUserString);

		// double perDegree = 111120.0;
		// //勾股定理
		// double sqrt2 = Math.sqrt((x - x3) * (x - x3) + (y - y3) * (y - y3))
		// * perDegree;
		// System.out.println("sqrt2:" + sqrt2);
		// //使用jstott的API（个人觉得最准确的了）
		// LatLng latLng3 = new LatLng(x3, y3);
		// System.out.println(latLng.distance(latLng3) * 1000);

	}

	public static void showmap(String lon, String lat, String flag,
			String city, String name, String snick) {

		if (name != null) {
			render("Application/show.html", city, name, snick);
		}
		render(lon, lat, flag);
	}

	public static void gpslist(String lons, String lats) {
		String[] lon = lons.split(",");
		String[] lat = lats.split(",");
		List<Location> locations = new ArrayList<Location>();
		for (int i = 0; i < lon.length; i++) {
			if (lon[i].length() > 0) {
				Location l = new Location();
				l.lon = lon[i];
				l.lat = lat[i];
				locations.add(l);
			}
		}
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		String sUserString = gson.toJson(locations);
		// System.out.println(sUserString);
		renderJSON(sUserString);

	}
	
	public static void showgmap(){
		render();
	}
	

}