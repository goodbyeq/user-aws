package com.beatus.goodbyeq.users.model;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.Map;

import com.beatus.goodbyeq.users.session.management.SessionConfiguration;
import com.google.common.collect.Maps;

public class SessionModel implements Cloneable {
    
    private Map<String, String> parameters = Maps.newHashMap(), 
    		cookieContent = Maps.newHashMap();
    private boolean secureRequest = false;
    private String verifier = "", contextPath = "",
    		requestMethod = "", servletPath = "", usersId = "", uid = "", authToken = "", locale="";
    
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public Map<String, String> getCookieContent() {
		return cookieContent;
	}

	public void setCookieContent(Map<String, String> cookieContent) {
		this.cookieContent = cookieContent;
	}
	
	public boolean isSecureRequest() {
		return secureRequest;
	}

	public void setSecureRequest(boolean secureRequest) {
		this.secureRequest = secureRequest;
	}

	public String getRequestSessionId() {
		if(cookieContent == null || 
			isBlank(cookieContent.get(SessionConfiguration.REQUEST_SESSION_ID))) {
			return "";
		}
		return cookieContent.get(SessionConfiguration.REQUEST_SESSION_ID);
	}
	
	public String getSessionId() {
		if(cookieContent == null || 
			isBlank(cookieContent.get(SessionConfiguration.SESSION_ID))) {
			return "";
		}
		return cookieContent.get(SessionConfiguration.SESSION_ID);
	}
	
	public String getVerifier() {
		return verifier;
	}
	
	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}
	
	public String getSeed() {
		if(isBlank(verifier) || 
			isBlank(cookieContent.get(verifier))) {
			return "";
		}
		return cookieContent.get(verifier);
	}
	
	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getServletPath() {
		return servletPath;
	}
	
	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}

	public String getCompanyId() {
		return usersId;
	}

	public void setCompanyId(String usersId) {
		this.usersId = usersId;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

}
