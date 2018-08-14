package se.walkercrou.places.processor;

import static se.walkercrou.places.GooglePlacesInterface.MAXIMUM_RADIUS;

import java.util.ArrayList;
import java.util.List;

import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;
import se.walkercrou.places.TypeParam;
import se.walkercrou.places.Types;

public class PlaceProcessor extends AbstractScraper {

	// private static final String TEST_PLACE_NAME = "Jaipur, Rajasthan, India";
	// private static final double TEST_PLACE_LAT = 26.912434, TEST_PLACE_LNG =
	// 75.787271;

	public static void main(String[] args) {

		// GooglePlaces client = new
		// GooglePlaces("AIzaSyALrSTy6NpqdhIOUs3IQMfvjh71td2suzY");
		GooglePlaces client = new GooglePlaces("AIzaSyAY-t7ABd6H1Kv-XnfXiM0drONBTjZd0nU");

		// List<Place> places = client.getNearbyPlaces(TEST_PLACE_LAT,
		// TEST_PLACE_LNG,
		// GooglePlacesInterface.MAXIMUM_RADIUS, GooglePlaces.MAXIMUM_RESULTS);

		// List<Place> places = client.getNearbyPlaces(TEST_PLACE_LAT,
		// TEST_PLACE_LNG, MAXIMUM_RADIUS,
		// 500, TypeParam.name("types").value(Types.TYPE_DOCTOR));

		/*
		 * List<Place> placesAll = new ArrayList<>(); for (int i = 0; i <
		 * arraysplace.length; i++) { String TEST_PLACE_NAME = arraysplace[i];
		 * List<Place> places = client.getPlacesByQuery(TEST_PLACE_NAME, 10);
		 * 
		 * // List<Place> places = client.getNearbyPlaces(TEST_PLACE_LAT, //
		 * TEST_PLACE_LNG, MAXIMUM_RADIUS, 2000, //
		 * TypeParam.name("types").value(Types.TYPE_DOCTOR), //
		 * TEST_PLACE_NAME); System.out.println("name :: " + TEST_PLACE_NAME +
		 * " :: size :: " + places.size()); placesAll.addAll(places);
		 * 
		 * } writetofile(placesAll, "lanlng");
		 */

		List<Place> placesAll = new ArrayList<>();

		for (int i = 0; i < lanlag.length; i++) {
			String TEST_PLACE_NAME = lanlag[i];
			// String lanlngtt[] = TEST_PLACE_NAME.substring(0,
			// TEST_PLACE_NAME.indexOf("|"));
			double TEST_PLACE_LAT = Double.parseDouble(TEST_PLACE_NAME.substring(0, TEST_PLACE_NAME.indexOf("|")));
			double TEST_PLACE_LNG = Double.parseDouble(TEST_PLACE_NAME.substring(TEST_PLACE_NAME.indexOf("|") + 1));
			try {
				List<Place> places = client.getNearbyPlaces(TEST_PLACE_LAT, TEST_PLACE_LNG, MAXIMUM_RADIUS, 2000,
						TypeParam.name("types").value(Types.TYPE_PHARMACY));
//				List<Place> places = client.getNearbyPlaces(TEST_PLACE_LAT, TEST_PLACE_LNG, MAXIMUM_RADIUS, 2000,
//						TypeParam.name("types").value(Types.TYPE_HOSPITAL));
				System.out.println("name :: " + TEST_PLACE_NAME + " :: size :: " + places.size());
				placesAll.addAll(places);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		System.out.println("size :: " + placesAll.size());

		writetofile(placesAll, "pharmacy_new");
		// for (Place place : places) {
		//// System.out.println(" Latitude ::: " + place.getLatitude() + "
		// Longitude ::: " + place.getLongitude());
		// System.out.println("placeid :: " + place.getPlaceId() + " name :: " +
		// place.getName() + " phone :: " + place.getPhoneNumber() + " website
		// :: " + place.getWebsite() + " status :: " + place.getStatus().name()
		// + " rating :: " + place.getRating() + " address :: " +
		// place.getVicinity() + " Latitude ::: " + place.getLatitude() + "
		// Longitude ::: " + place.getLongitude());
		// }
	}

	private static void writetofile(List<Place> places, String fileName) {
		AbstractScraper scraper = new PlaceProcessor();
		Hospital hospital = null;
		List<Hospital> hospitals = new ArrayList<>();

		// scraper.writeFaqsToFile(places);

		for (int i = 0; i < places.size(); i++) {
			Place place = places.get(i);
			hospital = new Hospital();
			hospital.setPlaceid(place.getPlaceId());
			hospital.setName(place.getName());
			hospital.setAddress(place.getVicinity());
			hospital.setLat(place.getLatitude());
			hospital.setLng(place.getLongitude());
			
			//hospital.setPhone(place.getPhoneNumber());
			//hospital.setRating(place.getRating());
			//hospital.setStatus(place.getStatus() != null ? place.getStatus().name() : "");
			//hospital.setWebsite(place.getWebsite());
			hospitals.add(hospital);
		}

		scraper.writeToFile(hospitals, fileName);
	}

	private static String arraysplace[] = { "Adarsh Nagar, Jaipur, Rajasthan, India",
			"Agra Road, Jaipur, Rajasthan, India", "Ajmer Road, Jaipur, Rajasthan, India",
			"Ajmeri Gate, Jaipur, Rajasthan, India", "Ambabari, Jaipur, Rajasthan, India",
			"Amer Road, Jaipur, Rajasthan, India", "Bais Godam, Jaipur, Rajasthan, India",
			"Bajaj Nagar, Jaipur, Rajasthan, India", "Bani Park, Jaipur, Rajasthan, India",
			"Bapu Bazaar, Jaipur, Rajasthan, India", "Bapu Nagar, Jaipur, Rajasthan, India",
			"Barkat Nagar, Jaipur, Rajasthan, India", "Bhawani Singh Road, Jaipur, Rajasthan, India",
			"Biseswarji, Jaipur, Rajasthan, India", "Brahmapuri, Jaipur, Rajasthan, India",
			"Chandpol, Jaipur, Rajasthan, India", "Civil Lines, Jaipur, Rajasthan, India",
			"Durgapura, Jaipur, Rajasthan, India", "Gangori Bazar, Jaipur, Rajasthan, India",
			"Ghat Darwaza, Jaipur, Rajasthan, India", "Gopalpura, Jaipur, Rajasthan, India",
			"Indira Bazar, Jaipur, Rajasthan, India", "Jagatpura, Jaipur, Rajasthan, India",
			"Jalupura, Jaipur, Rajasthan, India", "Janata Colony, Jaipur, Rajasthan, India",
			"Jawaharlal Nehru Marg, Jaipur, Rajasthan, India", "Jawahar Nagar, Jaipur, Rajasthan, India",
			"Jhotwara, Jaipur, Rajasthan, India", "Jhotwara Industrial Area, Jaipur, Rajasthan, India",
			"Jhotwara Road, Jaipur, Rajasthan, India", "Johari Bazar, Jaipur, Rajasthan, India",
			"Jyothi Nagar, Jaipur, Rajasthan, India", "Kalwar Road, Jaipur, Rajasthan, India",
			"Kartarpur, Jaipur, Rajasthan, India", "Khatipura, Jaipur, Rajasthan, India",
			"Mahesh Nagar, Jaipur, Rajasthan, India", "Malviya Nagar, Jaipur, Rajasthan, India",
			"Mansarovar, Jaipur, Rajasthan, India", "Mirza Ismail Road, Jaipur, Rajasthan, India",
			"Motidungri Marg, Jaipur, Rajasthan, India", "Muralipura, Jaipur, Rajasthan, India",
			"New Colony, Jaipur, Rajasthan, India", "Pink City, Jaipur, Rajasthan, India",
			"Raja Park, Jaipur, Rajasthan, India", "Ramganj, Jaipur, Rajasthan, India",
			"Sanganer, Jaipur, Rajasthan, India", "Sansar Chandra Road, Jaipur, Rajasthan, India",
			"Sethi Colony, Jaipur, Rajasthan, India", "Shastri Nagar, Jaipur, Rajasthan, India",
			"Shyam Nagar, Jaipur, Rajasthan, India", "Sikar Road, Jaipur, Rajasthan, India",
			"Sindhi Camp, Jaipur, Rajasthan, India", "Sirsi Road, Jaipur, Rajasthan, India",
			"Sitapura Industrial Area, Jaipur, Rajasthan, India", "Sodala, Jaipur, Rajasthan, India",
			"Subhash Nagar, Jaipur, Rajasthan, India", "Sudharshanpura Industrial Area, Jaipur, Rajasthan, India",
			"Surajpol Bazar, Jaipur, Rajasthan, India", "Tilak Nagar, Jaipur, Rajasthan, India",
			"Tonk Phatak, Jaipur, Rajasthan, India", "Tonk Road, Jaipur, Rajasthan, India",
			"Transport Nagar, Jaipur, Rajasthan, India", "Vaishali Nagar, Jaipur, Rajasthan, India",
			"Vidhyadhar Nagar, Jaipur, Rajasthan, India", "Vishwakarma Industrial Area, Jaipur, Rajasthan, India" };

	private static String lanlag[] = { "26.9018355|75.8271951", };
	
	private static String lanlagall[] = { "26.9018355|75.8271951", "26.8943157|75.86468649999999", "26.8935366|75.7466329",
			"26.8706431|75.6953046", "26.9176633|75.8166473", "26.9444814|75.7792992", "26.9595921|75.8447904",
			"26.8971351|75.7863962", "26.8657486|75.8013086", "26.930559|75.793611", "26.9194772|75.8236163",
			"26.9164445|75.82282289999999", "26.8898252|75.8083612", "26.8790027|75.79072699999999",
			"26.896874|75.8043331", "26.9194772|75.8236163", "26.9479409|75.8272482", "26.9242026|75.81344639999999",
			"26.9059311|75.78443829999999", "26.8517972|75.7862232", "26.9288794|75.816042",
			"26.917188|75.82942729999999", "26.8813418|75.78149940000002", "26.9197323|75.8119935",
			"26.8176736|75.86171709999999", "26.9218817|75.8070013", "26.9076941|75.83523819999999",
			"26.8755983|75.8101028", "26.8892814|75.8360422", "26.9564325|75.74125339999999", "26.9568893|75.7507408",
			"26.9401356|75.773061", "26.921113|75.82786180000001", "26.8972839|75.7967371", "26.9414529|75.736879",
			"26.9541396|75.7204987", "26.9562113|75.70677429999999", "26.9486509|75.71635169999999",
			"26.8866128|75.783799", "26.9277144|75.7594529", "26.8787862|75.7841892", "26.8548662|75.8242966",
			"26.8496828|75.7691863", "26.9167607|75.8131033", "26.9046252|75.8218373", "26.9776019|75.76391869999999",
			"26.917995|75.8090876", "26.9246438|75.818674", "26.8947446|75.8301169", "26.9202177|75.8308801",
			"26.8061453|75.7669262", "26.922375|75.80513959999999", "26.9237279|75.80753159999999",
			"26.903963|75.84104870000002", "26.9503102|75.8009833", "26.8886042|75.7628264", "26.9877449|75.7731531",
			"26.9679195|75.7614115", "26.9935336|75.7686337", "26.9253614|75.8019257", "26.9226332|75.68804279999999",
			"26.7839792|75.8342467", "26.9064744|75.77280139999999", "26.9326667|75.80294839999999",
			"26.8958956|75.79019819999999", "26.9196878|75.8421461", "26.8945243|75.8213018", "26.8809896|75.7923778",
			"26.8315021|75.7941057", "26.8800426|75.7997936", "26.8660501|75.79171339999999", "26.9109293|75.8420708",
			"26.9047751|75.74886409999999", "26.9620727|75.7816225", "26.997294|75.7854856" };

	@Override
	public String getFaqsUrl() {
		// TODO Auto-generated method stub
		return null;
	}
}
