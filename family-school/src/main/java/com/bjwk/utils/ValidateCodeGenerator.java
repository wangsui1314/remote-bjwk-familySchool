package com.bjwk.utils;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 图片验证码生成器
 * @author desolation
 *
 */
public class ValidateCodeGenerator {
	public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";// 放到session中的key
	private Random random = new Random();
	private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";// 随机产生的字符串

	private int width = 80;// 图片宽
	private int height = 26;// 图片高
	private int lineSize = 40;// 干扰线数量
	private int stringNum = 4;// 随机产生字符数量

	/*
	 * 获得字体
	 */
	private Font getFont() {
		return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
	}

	/*
	 * 获得颜色
	 */
	private Color getRandColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc - 16);
		int g = fc + random.nextInt(bc - fc - 14);
		int b = fc + random.nextInt(bc - fc - 18);
		return new Color(r, g, b);
	}

	/**
	 * 生成随机图片
	 */
	public void getRandcode(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		// BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
		g.setColor(getRandColor(110, 133));
		// 绘制干扰线
		for (int i = 0; i <= lineSize; i++) {
			drowLine(g);
		}
		// 绘制随机字符
		String randomString = "";
		for (int i = 1; i <= stringNum; i++) {
			randomString = drowString(g, randomString, i);
		}
		session.removeAttribute(RANDOMCODEKEY);
		session.setAttribute(RANDOMCODEKEY, randomString);
		g.dispose();
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());// 将内存中的图片通过流动形式输出到客户端
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取图片流和验证码
	 * 
	 * @return
	 */
	public Map<String, Object> getImageAndCode() {
		// BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
		g.setColor(getRandColor(110, 133));
		// 绘制干扰线
		for (int i = 0; i <= lineSize; i++) {
			drowLine(g);
		}
		// 绘制随机字符
		String randomString = "";
		for (int i = 1; i <= stringNum; i++) {
			randomString = drowString(g, randomString, i);
		}
		g.dispose();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", randomString);
		result.put("img", image);
		return result;
	}

	// private int width = 80;//图片宽
	// private int height = 26;//图片高
	// private int lineSize = 40;//干扰线数量
	// private int stringNum = 4;//随机产生字符数量

	/**
	 * @Description: 生成指定字符串图片
	 * @author 徐国飞
	 * @date 2016-4-26 上午11:26:32 void
	 */
	public static void getRandcode(OutputStream out, String codeStr, int width, int height) throws IOException {
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 得到该图片的绘图对象
		Graphics g = img.getGraphics();
		Random r = new Random();
		Color c = new Color(200, 150, 255);
		g.setColor(c);
		// 填充整个图片的颜色
		g.fillRect(0, 0, width, height);
		// 向图片中输出数字和字母
		char[] ch = codeStr.toCharArray();
		int len = ch.length;
		for (int i = 0; i < len; i++) {
			g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
			g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));// 输出的字体和大小
			g.drawString("" + ch[i], (i * 15) + 3, 18);// 写什么数字，在图片的什么位置画
		}
		ImageIO.write(img, "JPEG", out);
	}

	public static void main(String[] args) throws IOException {
		OutputStream out = new FileOutputStream(new File("D://imgtestcode.jpg"));
		getRandcode(out, "4khd", 70, 26);
		out.close();
	}

	/*
	 * 绘制字符串
	 */
	private String drowString(Graphics g, String randomString, int i) {
		g.setFont(getFont());
		g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
		String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
		randomString += rand;
		g.translate(random.nextInt(3), random.nextInt(3));
		g.drawString(rand, 13 * i, 16);
		return randomString;
	}

	/*
	 * 绘制干扰线
	 */
	private void drowLine(Graphics g) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(13);
		int yl = random.nextInt(15);
		g.drawLine(x, y, x + xl, y + yl);
	}

	/*
	 * 获取随机的字符
	 */
	public String getRandomString(int num) {
		return String.valueOf(randString.charAt(num));
	}

	/*
	 * 生成邮箱验证码
	 */
	public String getRandomCodeForEmail(HttpSession session) {
		String validateCodeStr = "";
		for (int i = 1; i <= 6; i++) {
			validateCodeStr += getRandomString(random.nextInt(randString.length()));
		}
		session.setAttribute("EmailValidateCode", validateCodeStr);
		return validateCodeStr;
	}

	/*
	 * 生成手机验证码
	 */
	public String getRandomCodeForPhone(HttpSession session) {
		String validateCodeStr = "";
		for (int i = 1; i <= 6; i++) {
			validateCodeStr += getRandomString(random.nextInt(randString.length()));
		}
		session.setAttribute("PhoneValidateCode", validateCodeStr);
		return validateCodeStr;
	}

	/*
	 * 生成手机校验码(不存储在session中)
	 */
	public String getCheckCodeForPhone() {
		String validateCodeStr = "";
		for (int i = 1; i <= 6; i++) {
			validateCodeStr += getRandomString(random.nextInt(randString.length()));
		}
		return validateCodeStr;
	}

	/**
	 * @Description: 随机生成指定位数的字母数字组合
	 * @author 徐国飞
	 * @date 2015-4-24 上午11:22:22 String
	 */
	public static String getCharAndNumr(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) // 字符串
			{
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) // 数字
			{
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	/**
	 * @Description: 随机生成指定位数的字母数字组合
	 * @author 徐国飞
	 * @date 2015-4-24 上午11:22:22 String
	 */
	public static String getNumr(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			val += String.valueOf(random.nextInt(10));
		}
		return val;
	}

}
