package Utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataAtual {

	public static String Obter(boolean isBeauty) {

	    Date date = new Date();
	    
	    String strDateFormat = "";
	    
	    if(isBeauty)
	    {
	    	strDateFormat = "yyyy:MM:dd hh:mm:ss";
	    }
	    else
	    {
	    	strDateFormat = "ddMMyyyy";
	    }
	    
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);

	    String formattedDate= dateFormat.format(date);

	    System.out.println("data atual using Date - 12 hour format: " + formattedDate);
	    
	    return formattedDate;

	}
	
}
