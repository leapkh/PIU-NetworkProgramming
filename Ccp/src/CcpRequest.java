import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * Request format: PROTOCOL_VERSION#OPERATION#[PARAMETERS]
 *
 */
public class CcpRequest {
	
	private String protocolVersion;
	private String operation;
	private List<String> params;
	
	public CcpRequest(String protocolVersion, String operation, List<String> params) {
		super();
		this.protocolVersion = protocolVersion;
		this.operation = operation;
		this.params = params;
	}
	public String getProtocolVersion() {
		return protocolVersion;
	}
	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public List<String> getParams() {
		return params;
	}
	public void setParams(List<String> params) {
		this.params = params;
	}
	
	public String toRawString() {
		String paramsString = "";
		if(params != null) {
			for(String param : params) {
				paramsString += "<" + param + ">";
			}
		}
		return protocolVersion + "#" + operation + "#" + paramsString + "\r\n";
	}
	
	public static CcpRequest fromRawString(String rawString) {
		String[] parts = rawString.split("#");
		String protocolVersion = parts[0];
		String operation = parts[1];
		List<String> params = null;
		if(parts.length == 3) {
			String paramsString = parts[2];
			if(!paramsString.isEmpty()) {
				params = new ArrayList<>();
				String[] paramParts = paramsString.split("><");
				for(String paramPart : paramParts) {
					String readyParam = paramPart.replace("<", "").replace(">", "");
					params.add(readyParam);
				}
			}
		}
		return new CcpRequest(protocolVersion, operation, params);
	}
	
}










