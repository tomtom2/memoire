package source;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Grapher g = new Grapher(Solver.class, 1);
		// g.printResume();
		System.out.println(Solver.class.getPackage().getName());
		System.out.println(Solver.class.getProtectionDomain());

		URL url = Solver.class.getResource("Solver.class");
		System.out.println(url);
		// try {
		// FileReader fr = new FileReader(new File("src/source/Solver.java"));
		// String str = "";
		// int buf;
		// while((buf = fr.read())!=-1){
		// str += buf;
		// }
		// System.out.println(str);
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		String myJson = "{ \"id\" : \"38476563\" }";
		//JSONObject obj=new JSONObject();

		System.out.println(myJson.substring(myJson.indexOf(":")+1,myJson.lastIndexOf("\"")));
		
		String jsonString = "{ \"id\" : \"00001937473\"";

		String strPattern = "{ \"id\" : \"(\\d*)\"}";
		Pattern pattern = Pattern.compile(strPattern);
		Matcher matcher = pattern.matcher(jsonString);
		if (matcher.find()) {
			String friendIdString = matcher.group(1);
			System.out.println("regex selection : " + jsonString + " --> "
					+ friendIdString);

		} else {
			System.out.println("no regex selection for " + jsonString);

		}

	}

}
