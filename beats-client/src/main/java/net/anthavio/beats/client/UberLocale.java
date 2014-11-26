package net.anthavio.beats.client;

/**
 * Accepts-Language
 * 
 * @author martin.vanek
 *
 */
public enum UberLocale {

	ar_SA("Arabic (Saudi Arabia)"), de_DE("German (Germany)"), en_US("English (United States)"), //
	fr_FR("French (France)"), it_IT("Italian (Italy)"), ja_JP("Japanese (Japan)"), //
	ko_KR("Korean (Korea)"), ms_MY("Malay (Malaysia)"), nl_NL("Dutch (Netherlands)"), //
	pt_BR("Portuguese (Brazil)"), ru_RU("Russian (Russia)"), sv_SE("Swedish (Sweden)"), //
	th_TH("Thai (Thailand)"), tl_PH("Tagalog (Philippines)"), zh_CN("Chinese (China)"), zh_TW("Chinese (Taiwan)");

	private final String label;

	private UberLocale(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
