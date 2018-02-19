package com.halfmoon.cloudmanager.util.face;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.SSLException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FaceplusUtils {

	// 只有请求失败时才会返回error_message字段
	// 测试一下有人脸和没有人脸的图片的返回值有什么不同，根据不同点来返回isface
	public static int faceDetect(byte[] imgBuff) {
		
		JsonObject result = null;
		String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";
		HashMap<String, String> map = new HashMap<>();
		HashMap<String, byte[]> byteMap = new HashMap<>();
		map.put("api_key", "siP1yyxIlOcgMgHjzaqLb5Jni7YQT4XG");
		map.put("api_secret", "b83R5mvkfpoZfiWRVKls9S6qfeD5Fpay");
		byteMap.put("image_file", imgBuff);
		try {
			byte[] bacd = post(url, map, byteMap);
			String str = new String(bacd);
			System.out.println(str);
			result = new JsonParser().parse(str).getAsJsonObject();

			if (result.get("error_message") == null) {
				JsonArray faces = result.get("faces").getAsJsonArray();
				int faceNum = 0;
				for(Iterator<JsonElement> iter = faces.iterator(); iter.hasNext();) {
					faceNum++;
					iter.next();
				}
				return faceNum;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static boolean faceCompare(String imgUrl, byte[] imgBuff) {

		JsonObject result;
		boolean confident = false;
		File file = new File(imgUrl);
		byte[] buff = getBytesFromFile(file);

		String compareUrl = "https://api-cn.faceplusplus.com/facepp/v3/compare";
		HashMap<String, String> map2 = new HashMap<>();
		HashMap<String, byte[]> byteMap2 = new HashMap<>();
		map2.put("api_key", "siP1yyxIlOcgMgHjzaqLb5Jni7YQT4XG");
		map2.put("api_secret", "b83R5mvkfpoZfiWRVKls9S6qfeD5Fpay");
		byteMap2.put("image_file1", buff);
		byteMap2.put("image_file2", imgBuff);
		try {
			byte[] bacd2 = post(compareUrl, map2, byteMap2);
			String str2 = new String(bacd2);
			result = new JsonParser().parse(str2).getAsJsonObject();
			System.out.println(result.get("confidence").getAsDouble());

			if ((result.get("confidence").getAsDouble()) > 90.0) {
				confident = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return confident;

	}

	private final static int CONNECT_TIME_OUT = 30000;
	private final static int READ_OUT_TIME = 50000;
	private static String boundaryString = getBoundary();

	protected static byte[] post(String url, HashMap<String, String> map, HashMap<String, byte[]> fileMap)
			throws Exception {
		HttpURLConnection conne;
		URL url1 = new URL(url);
		conne = (HttpURLConnection) url1.openConnection();
		conne.setDoOutput(true);
		conne.setUseCaches(false);
		conne.setRequestMethod("POST");
		conne.setConnectTimeout(CONNECT_TIME_OUT);
		conne.setReadTimeout(READ_OUT_TIME);
		conne.setRequestProperty("accept", "*/*");
		conne.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
		conne.setRequestProperty("connection", "Keep-Alive");
		conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
		DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
		Iterator<?> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry) iter.next();
			String key = entry.getKey();
			String value = entry.getValue();
			obos.writeBytes("--" + boundaryString + "\r\n");
			obos.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"\r\n");
			obos.writeBytes("\r\n");
			obos.writeBytes(value + "\r\n");
		}
		if (fileMap != null && fileMap.size() > 0) {
			Iterator<?> fileIter = fileMap.entrySet().iterator();
			while (fileIter.hasNext()) {
				Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIter.next();
				obos.writeBytes("--" + boundaryString + "\r\n");
				obos.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey() + "\"; filename=\""
						+ encode(" ") + "\"\r\n");
				obos.writeBytes("\r\n");
				obos.write(fileEntry.getValue());
				obos.writeBytes("\r\n");
			}
		}
		obos.writeBytes("--" + boundaryString + "--" + "\r\n");
		obos.writeBytes("\r\n");
		obos.flush();
		obos.close();
		InputStream ins = null;
		int code = conne.getResponseCode();
		try {
			if (code == 200) {
				ins = conne.getInputStream();
			} else {
				ins = conne.getErrorStream();
			}
		} catch (SSLException e) {
			e.printStackTrace();
			return new byte[0];
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buff = new byte[4096];
		int len;
		while ((len = ins.read(buff)) != -1) {
			baos.write(buff, 0, len);
		}
		byte[] bytes = baos.toByteArray();
		ins.close();
		return bytes;
	}

	private static String getBoundary() {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 32; ++i) {
			sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(
					random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
		}
		return sb.toString();
	}

	private static String encode(String value) throws Exception {
		return URLEncoder.encode(value, "UTF-8");
	}

	public static byte[] getBytesFromFile(File f) {
		if (f == null) {
			return null;
		}
		try {
			FileInputStream stream = new FileInputStream(f);
			ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = stream.read(b)) != -1)
				out.write(b, 0, n);
			stream.close();
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
		}
		return null;
	}
}
