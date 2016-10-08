package util;

import java.util.Iterator;

public class ShowSystemProperties {

	public static void main(String[] args) {
		for(
			Iterator<?> it = System.getProperties().keySet().iterator();
			it.hasNext();
		) {
			Object key = it.next();
			System.out.printf("%s\t%s\n", key, System.getProperties().get(key));
		}
	}

}
