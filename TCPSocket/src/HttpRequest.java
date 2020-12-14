import java.util.Map;

public class HttpRequest {
	
	private String method;
	private String uri;
	private Map<String, String> headers;
	private String body;
	
	public HttpRequest(String method, String uri, Map<String, String> headers, String body) {
		this.method = method;
		this.uri = uri;
		this.headers = headers;
		this.body = body;
	}
	
	@Override
	public String toString() {
		String requestLine = method + " " + uri + " HTTP/1.1\r\n";
		String requestHeaders = "";
		if(headers != null) {
			for(Map.Entry<String, String> entry : headers.entrySet()) {
				requestHeaders += entry.getKey() + ":" + entry.getValue() + "\r\n";
			}
		}
		
		return requestLine + requestHeaders + "\r\n" + body;
	}

}












