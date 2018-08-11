package com.ethan.main;

import java.awt.Color;
import java.awt.image.BufferedImage;

//���ڰ�ͼƬת�����ַ���
public class Image2String {
	
	
	public Image2String() {
		
			
	}
	
	public String transform(BufferedImage bf) {//���ڰ�ͼ��ת�����ַ��ķ���
		StringBuilder sb = new StringBuilder();
		int height = bf.getHeight();
		int weight  = bf.getWidth();
		
		for(int x=0;x<height;x+=4){//����ѭ������ͼƬ��ÿһ�����ص�
			for(int y=0;y<weight;y+=2) {
				int rgb = bf.getRGB(y, x);
				Color c = new Color(rgb);
				if(c.getRed()<120) {
					sb.append("M");	//��ɫ��M����
				}else {
					sb.append(" ");//ǳɫ�ÿհ״���
				}
				
				
				
			}
			sb.append("\r\n");//����һ�н������У�������windows;
			
		}
		
		
		
		return sb.toString();
	}
}
