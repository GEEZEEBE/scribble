package com.scribble.util;

import java.io.File;
import java.util.StringTokenizer;

public class FileUtil {

		public static String replace(String str, String pattern, String replace) {
			int s = 0, e = 0;
			StringBuffer result = new StringBuffer();

			while ((e = str.indexOf(pattern, s)) >= 0) {
				result.append(str.substring(s, e));
				result.append(replace);
				s = e + pattern.length();
			}
			result.append(str.substring(s));
			return result.toString();
		}

		public static void delete(String s) {
			File file = new File(s);
			if (file.isFile()) {
				file.delete();
			}
		}

		public static String con(String s) {
			String str = null;
			try {
//				str = new String(s.getBytes("8859_1"), "utf-8");
				str = new String(s.getBytes("utf-8"), "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return str;
		}
}