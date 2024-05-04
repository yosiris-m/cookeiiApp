package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	// Expresion regular para obtener la extension de la imagen
	public static String getExtension(String fileName) {
		String extension = "";
		Pattern pattern = Pattern.compile("\\.(\\w+)$");
		Matcher matcher = pattern.matcher(fileName);

		if (matcher.find()) {
			extension = matcher.group(1);
		}

		return extension;
	}
}
