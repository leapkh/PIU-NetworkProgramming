import java.util.Map;

public class HttpResponse {

	private int statusCode;
	private String reasonPhrase;
	private Map<String, String> headers;
	private String body;
	
	public HttpResponse(int statusCode, String reasonPhrase, Map<String, String> headers, String body) {
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
		this.headers = headers;
		this.body = body;
	}
	
	
	@Override
	public String toString() {
		String statusLine = "HTTP/1.1 " + statusCode + " " + reasonPhrase + "\r\n";
		String responseHeaders = "";
		if(headers != null) {
			for(Map.Entry<String, String> entry : headers.entrySet()) {
				responseHeaders += entry.getKey() + ":" + entry.getValue() + "\r\n";
			}
		}
		
		return statusLine + responseHeaders + "\r\n" + body;
	}
	
}
