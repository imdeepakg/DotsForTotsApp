package in.co.magmacoin;



class AlphabetBean {
	private String 	name;
	private String 	key;
	private String[] alphabets;
	private String fontAssetName;
	private String[] imageAssetName; 
	
	AlphabetBean() {
		
	}
	
	AlphabetBean(String name, String key, String[] alphabets, String fontAssetName, String[] imageAssetName) {
		this.name = name;
		this.key = key;
		this.alphabets = alphabets;
		this.fontAssetName = fontAssetName;
		this.imageAssetName = imageAssetName;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String[] getAlphabets() {
		return alphabets;
	}

	public void setAlphabets(String[] alphabets) {
		this.alphabets = alphabets;
	}

	public String getFontAssetName() {
		return fontAssetName;
	}

	public void setFontAssetName(String fontAssetName) {
		this.fontAssetName = fontAssetName;
	}

	public String[] getImageAssetName() {
		return imageAssetName;
	}

	public void setImageAssetName(String[] imageAssetName) {
		this.imageAssetName = imageAssetName;
	}

	
}
