import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
	
	public static final String PROTOCOL_VERSION = "CCP/1.0";
	public static final String[] SUPPORTED_CURRENCIES = {"KHR", "USD", "JPY", "CNY", "THB"};
	public static final Map<String, Double> EXCHANGE_RATES = new HashMap<String, Double>(){
		{
			put("KHR", 4100D);
			put("USD", 1D);
			put("JPY", 103.46);
			put("CNY", 6.55);
			put("THB", 120D);
		}
	}; 
	

}
