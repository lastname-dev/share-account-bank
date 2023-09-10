package com.bank.shareaccount.account.dto.request;

public class ExchangeRequest {
	private DataHeader dataHeader;
	private DataBody dataBody;

	// Getter와 Setter 메서드

	// 생성자

	public DataHeader getDataHeader() {
		return dataHeader;
	}

	public void setDataHeader(DataHeader dataHeader) {
		this.dataHeader = dataHeader;
	}

	public DataBody getDataBody() {
		return dataBody;
	}

	public void setDataBody(DataBody dataBody) {
		this.dataBody = dataBody;
	}

	// 내부 클래스 DataHeader 정의
	public static class DataHeader {
		private String apikey;

		// Getter와 Setter 메서드

		public String getApikey() {
			return apikey;
		}

		public void setApikey(String apikey) {
			this.apikey = apikey;
		}
	}

	// 내부 클래스 DataBody 정의
	public static class DataBody {
		private String serviceCode;
		private String 환전금액;
		private String 환전통화;
		private String 거래자성명;
		private String 생년월일;
		private String 휴대폰번호;

		// Getter와 Setter 메서드

		public String getServiceCode() {
			return serviceCode;
		}

		public void setServiceCode(String serviceCode) {
			this.serviceCode = serviceCode;
		}

		public String get환전금액() {
			return 환전금액;
		}

		public void set환전금액(String 환전금액) {
			this.환전금액 = 환전금액;
		}

		public String get환전통화() {
			return 환전통화;
		}

		public void set환전통화(String 환전통화) {
			this.환전통화 = 환전통화;
		}

		public String get거래자성명() {
			return 거래자성명;
		}

		public void set거래자성명(String 거래자성명) {
			this.거래자성명 = 거래자성명;
		}

		public String get생년월일() {
			return 생년월일;
		}

		public void set생년월일(String 생년월일) {
			this.생년월일 = 생년월일;
		}

		public String get휴대폰번호() {
			return 휴대폰번호;
		}

		public void set휴대폰번호(String 휴대폰번호) {
			this.휴대폰번호 = 휴대폰번호;
		}
	}
}

