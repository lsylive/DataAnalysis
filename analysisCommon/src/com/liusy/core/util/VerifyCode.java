


//   VerifyCode.java

package com.liusy.core.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.*;

// Referenced classes of package com.liusy.core.util:
//			Const

public class VerifyCode extends HttpServlet
{

	private String Default_ValidateCode;

	public VerifyCode()
	{
		Default_ValidateCode = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);
		int width = 60;
		int height = 20;
		BufferedImage image = new BufferedImage(width, height, 1);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", 0, 18));
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++)
		{
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		drawDisturb(g, random, width, height);
		String sRand = "";
		for (int i = 0; i < 4; i++)
		{
			String rand = String.valueOf(getRandomChar(random));
			sRand = (new StringBuilder(String.valueOf(sRand))).append(rand).toString();
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6, 16);
		}

		request.getSession().setAttribute(Const.LOGIN_VERFIYCODE, sRand);
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	private void drawDisturb(Graphics g, Random random, int width, int height)
	{
		for (int i = 0; i < width; i++)
		{
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			g.setColor(getRandColor(120, 255));
			g.drawLine(x, y, x + x1, y + y1);
			g.fillArc(x, y, x1, y1, random.nextInt(360), random.nextInt(360));
		}

	}

	private Color getRandColor(int fc, int bc)
	{
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	private char getRandomChar(Random random)
	{
		return Default_ValidateCode.charAt(random.nextInt(Default_ValidateCode.length()));
	}
}
