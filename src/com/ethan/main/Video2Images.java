package com.thetbw.main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

//���ڰ���Ƶת����ͼƬ�Ĺ���
public class Video2Images {
	private String path;
	private VideoCapture cap;
	
	/**
	 * 
	 * ������ֱ����opencv����Ƶת��ͼƬ��������̫�У����������û����
	 * 
	 * 
	 * 
	 */
	
	
	public Video2Images(String path) {//������Ƶ·����ַ
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);//����opencv��
		
		cap = new VideoCapture(path);
		
		
		
	}
	

	public void transform(String temp) {//pathΪͼƬ���·��
		Mat m = new Mat();

		double frameCount = cap.get(Videoio.CV_CAP_PROP_FRAME_COUNT); 
		for (int i = 0; i < frameCount; i++) {
			// ������Ƶ��λ��(��λ:����)
			cap.set(Videoio.CV_CAP_PROP_POS_MSEC, i*1000);
			// ��ȡ��һ֡����
			if (cap.read(m)) {
				Imgcodecs.imwrite(temp + i + ".jpg", m);
			
			}else {
				throw new RuntimeException("δ֪����");
			}
		}
	}
	public int getVideoFPS() {//��ȡ��Ƶ֡��
		double fps = cap.get(Videoio.CV_CAP_PROP_FPS); 
		return (int)fps;
	}
	
	public int getVideoFrame() {//��ȡ��Ƶ��֡��
		double frameCount = cap.get(Videoio.CV_CAP_PROP_FRAME_COUNT); 
		return (int)frameCount;
	}
	
	
	/**	
	public Image[] transform() {//��ʱ��֪����ô��matת��Ϊbufferedimage,�ȷ���
		Mat m = new Mat();

		double frameCount = cap.get(Videoio.CV_CAP_PROP_FRAME_COUNT); 
		Image[] image  = new Image[(int)frameCount];
		for (int i = 0; i < frameCount; i++) {
			// ������Ƶ��λ��(��λ:����)
			cap.set(Videoio.CV_CAP_PROP_POS_MSEC, i * 1000);
			// ��ȡ��һ֡����
			if (cap.read(m)) {
				byte[] b= new byte[m.height()*m.width()];
				m.put(m.rows(), m.cols(), b);
				BufferedImage bf = new BufferedImage(m.width(),m.height(),BufferedImage.TYPE_INT_ARGB);
			

			}else {
				throw new RuntimeException("δ֪����");
			}
		}
			
		return image;	
	}
	*/
	
}
