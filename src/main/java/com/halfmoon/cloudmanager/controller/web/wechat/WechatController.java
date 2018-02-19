package com.halfmoon.cloudmanager.controller.web.wechat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.halfmoon.cloudmanager.util.local.Sign;

@Controller
public class WechatController {

	private static final String TOKEN = "gxykq";

	/**
	 * 微信公众号与服务器绑定
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @param rsp
	 * @throws IOException
	 */
	@RequestMapping(path = "/wechat", method = RequestMethod.GET)
	public void bind(String signature, String timestamp, String nonce, String echostr, HttpServletResponse rsp)
			throws IOException {

		PrintWriter writer = rsp.getWriter();

		boolean success = check_signature(signature, timestamp, nonce);
		if (success) {
			writer.write(echostr);
		}

		writer.write("");
		writer.close();

	}

	@RequestMapping(path = "/wechat", method = RequestMethod.POST)
	public void reply(HttpServletRequest req, HttpServletResponse rsp) throws Exception {

		req.setCharacterEncoding("UTF-8");
		rsp.setCharacterEncoding("UTF-8");

		InputStream in = req.getInputStream();
		SAXReader saxReader = new SAXReader();
		Document receiveXml = saxReader.read(in);

		Element root = receiveXml.getRootElement();
		String fromUser = root.elementText("FromUserName");
		String content = root.elementText("Content");

		File file = new File(WechatController.class.getResource("template/text.xml").getFile());
		BufferedReader br = new BufferedReader(new FileReader(file));
		char[] buff = new char[(int) file.length()];
		br.read(buff);
		br.close();

		String replyXml = String.copyValueOf(buff);
		replyXml = String.format(replyXml, fromUser, "gh_d3a4f47f9e96", System.currentTimeMillis() / 1000,
				"欢迎使用高校云考勤！");

		rsp.getWriter().write(replyXml);

	}

	public static boolean check_signature(String signature, String timestamp, String nonce) {

		String[] arr = new String[] { timestamp, nonce, TOKEN };
		Arrays.sort(arr);
		String s = arr[0] + arr[1] + arr[2];
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(s.getBytes("utf-8"));
			return signature.equals(bytes2HexString(digest));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static String bytes2HexString(byte[] src) {

		StringBuilder stringBuilder = new StringBuilder("");

		if (src == null || src.length <= 0) {
			return "";
		}

		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}

			stringBuilder.append(hv);
		}

		return stringBuilder.toString();

	}
	@RequestMapping(path="/wechat/jssdk",method=RequestMethod.GET)
	@ResponseBody
	public String jssdk(String url) throws UnsupportedEncodingException{
		url = URLDecoder.decode(url,"utf-8");
		Map<String,String> map = Sign.toResult(url);
		return new Gson().toJson(map);
	}
}
