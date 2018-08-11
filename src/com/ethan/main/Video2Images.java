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

//用于把视频转化成图片的工具
public class Video2Images {
	private String path;
	private VideoCapture cap;
	
	/**
	 * 
	 * 本来想直接用opencv把视频转成图片，不过不太行，所以这个类没有用
	 * 
	 * 
	 * 
	 */
	
	
	public Video2Images(String path) {//传入视频路径地址
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);//加载opencv库
		
		cap = new VideoCapture(path);
		
		
		
	}
	

	public void transform(String temp) {//path为图片存放路径
		Mat m = new Mat();

		double frameCount = cap.get(Videoio.CV_CAP_PROP_FRAME_COUNT); 
		for (int i = 0; i < frameCount; i++) {
			// 设置视频的位置(单位:毫秒)
			cap.set(Videoio.CV_CAP_PROP_POS_MSEC, i*1000);
			// 读取下一帧画面
			if (cap.read(m)) {
				Imgcodecs.imwrite(temp + i + ".jpg", m);
			
			}else {
				throw new RuntimeException("未知错误");
			}
		}
	}
	public int getVideoFPS() {//获取视频帧率
		double fps = cap.get(Videoio.CV_CAP_PROP_FPS); 
		return (int)fps;
	}
	
	public int getVideoFrame() {//获取视频总帧数
		double frameCount = cap.get(Videoio.CV_CAP_PROP_FRAME_COUNT); 
		return (int)frameCount;
	}
	
	
	/**	
	public Image[] transform() {//咱时不知道怎么把mat转化为bufferedimage,先放这
		Mat m = new Mat();

		double frameCount = cap.get(Videoio.CV_CAP_PROP_FRAME_COUNT); 
		Image[] image  = new Image[(int)frameCount];
		for (int i = 0; i < frameCount; i++) {
			// 设置视频的位置(单位:毫秒)
			cap.set(Videoio.CV_CAP_PROP_POS_MSEC, i * 1000);
			// 读取下一帧画面
			if (cap.read(m)) {
				byte[] b= new byte[m.height()*m.width()];
				m.put(m.rows(), m.cols(), b);
				BufferedImage bf = new BufferedImage(m.width(),m.height(),BufferedImage.TYPE_INT_ARGB);
			

			}else {
				throw new RuntimeException("未知错误");
			}
		}
			
		return image;	
	}
	*/
	
}
