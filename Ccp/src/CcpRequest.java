import java.util.ArrayList;
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

	public static CcpRequest fromString(String rawRequest) {
		String[] infos = rawRequest.split("#");
		// Check if it's in the correct format
		if(infos.length != 3) {
			return null;
		}
		
		if(!Constants.SUPPORTED_VERSIONS.contains(infos[0])) {
			return null;
		}
		
		if(!Constants.SUPPORTED_OPERATIONS.contains(infos[1])) {
			return null;
		}
		
		CcpRequest request = new CcpRequest();
		request.setProtocolVersion(infos[0]);
		request.setOperation(infos[1]);
		
		// Example raw params = <USD><1><KHR>
		String rawParams = infos[2];
		String[] params = rawParams.split("><");
		List<String> paramList = new ArrayList<>();
		for(String param : params) {
			paramList.add(param.replace("<", "").replace(">", ""));
		}
		
		request.setParams(paramList);
		
		return request;
		
	}

	public String toRawRequest() {
		String paramString = "";
		for(String param : params) {
			paramString += "<" + param + ">";
		}
		String rawRequest = protocolVersion + "#" + operation + "#" + paramString + "\r\n";
		return rawRequest;
	}
	
}










