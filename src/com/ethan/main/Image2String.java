package com.ethan.main;

import java.awt.Color;
import java.awt.image.BufferedImage;

//用于把图片转换成字符串
public class Image2String {
	
	
	public Image2String() {
		
			
	}
	
	public String transform(BufferedImage bf) {//用于把图标转换成字符的方法
		StringBuilder sb = new StringBuilder();
		int height = bf.getHeight();
		int weight  = bf.getWidth();
		
		for(int x=0;x<height;x+=4){//利用循环遍历图片的每一个像素点
			for(int y=0;y<weight;y+=2) {
				int rgb = bf.getRGB(y, x);
				Color c = new Color(rgb);
				if(c.getRed()<120) {
					sb.append("M");	//深色用M代替
				}else {
					sb.append(" ");//浅色用空白代替
				}
				
				
				
			}
			sb.append("\r\n");//遍历一行结束后换行；仅限于windows;
			
		}
		
		
		
		return sb.toString();
	}
}
