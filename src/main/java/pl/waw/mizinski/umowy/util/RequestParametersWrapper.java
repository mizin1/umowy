package pl.waw.mizinski.umowy.util;

import org.objectledge.parameters.RequestParameters;

public class RequestParametersWrapper {
	
	private final RequestParameters requestParameters;

	public RequestParametersWrapper(RequestParameters requestParameters) {
		this.requestParameters = requestParameters;
	}

	public String get(String name) {
		String value = requestParameters.get(name, null);
		return Utils.trim(value);
	}
	
}

