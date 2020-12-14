import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Response example: PROTOCOL_VERSION#STATUS#DATA
 *
 */


public class CcpResponse {

	private String protocolVersion;
	private String status;
	private List<String> data;
	
	public String getProtocolVersion() {
		return protocolVersion;
	}
	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getData() {
		return data;
	}
	public void addData(String dataItem) {
		if(data == null) {
			data = new ArrayList<String>();
		}
		data.add(dataItem);
	}
	
	public String toRawResponse() {
		String dataString = "";
		for(String dataItem : data) {
			dataString += "<" + dataItem + ">";
		}
		String rawResponse = protocolVersion + "#" + status + "#" + dataString + "\r\n";
		return rawResponse;
	}
	
	public static CcpResponse fromString(String rawResponse) {
		//TODO
		return null;
	}
	
}










