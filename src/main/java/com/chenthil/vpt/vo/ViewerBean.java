package com.chenthil.vpt.vo;

/**
 * ValueObject for VieverBean that represents either a request or a response.
 * 
 * @author Chenthil Natarajan
 *
 */
public class ViewerBean {
		
		private String requestTimestamp;
		private String responseTimestamp;
		private String uri;
		private String action;
		private String guid;
		private boolean canBeSentToDB;
		
		
		public String getGuid() {
			return guid;
		}
		public void setGuid(String guid) {
			this.guid = guid;
		}
		public String getRequestTimestamp() {
			return requestTimestamp;
		}
		public void setRequestTimestamp(String requestTimestamp) {
			this.requestTimestamp = requestTimestamp;
		}
		public String getResponseTimestamp() {
			return responseTimestamp;
		}
		public void setResponseTimestamp(String responseTimestamp) {
			this.responseTimestamp = responseTimestamp;
		}
		public String getUri() {
			return uri;
		}
		public void setUri(String uri) {
			this.uri = uri;
		}
		public String getAction() {
			return action;
		}
		public void setAction(String action) {
			this.action = action;
		}
		
		public Boolean isEmpty(String str) {
			return str == null || str.trim().length() <=0;
		}
		
		public boolean canBeSentToDB() {
			return !isEmpty(getGuid()) && !isEmpty(getUri()) && !isEmpty(getAction()) && (!isEmpty(getRequestTimestamp()) || !isEmpty(getResponseTimestamp()));
		}
		@Override
		public String toString() {
			return "ViewerBean [requestTimestamp=" + requestTimestamp + ", responseTimestamp=" + responseTimestamp
					+ ", uri=" + uri + ", action=" + action + ", guid=" + guid + "]";
		}
		
		
		
		
		
		
	}