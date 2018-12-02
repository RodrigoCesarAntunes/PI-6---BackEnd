package Utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataAtual {

	public static String Obter() {

	    Date date = new Date();

	    String strDateFormat = "yyyy:MM:dd hh:mm:ss";

	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);

	    String formattedDate= dateFormat.format(date);

	    System.out.println("data atual using Date - 12 hour format: " + formattedDate);
	    
	    return formattedDate;

	}
	
}
