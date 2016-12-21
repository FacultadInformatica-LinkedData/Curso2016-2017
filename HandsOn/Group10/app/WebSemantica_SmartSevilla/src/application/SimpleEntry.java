package application;

import java.util.Map.Entry;

public class SimpleEntry<T1, T2> implements Entry<Integer, String> {

	private Integer key;
	private String value;
	
	@Override
	public Integer getKey() {
		// TODO Auto-generated method stub
		return key;
	}
	
	public Integer setKey(Integer x) {
		this.key = x;
		return x;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public String setValue(String arg0) {
		// TODO Auto-generated method stub
		this.value = arg0;
		return this.value;
	}

}
