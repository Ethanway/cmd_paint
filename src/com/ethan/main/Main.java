package com.ethan.main;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Callback; 
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary.StdCallCallback;

import com.sun.jna.win32.StdCallLibrary;

public class Main {
	private static String path = "C:\\Users\\ethan\\workspace\\pr\\";
	//private static String temp = "C:\\Users\\ethan\\Downloads\\temp\\";
	
	public static void main(String[] args) throws Exception {
		
		play();
	}
	
	
	private static void play() throws Exception {
		BufferedImage image;
		Image2String i2t = new Image2String();
		String[] s = new String[6574];
		for(int x = 0;x<6574;x++) {
			image = ImageIO.read(new File(path+"BadApple"+String.format("%04d", x)+".png"));//读取图片，因为图片有6574张，用pr导出成png就行，这里注意改一下地址
			s[x] = i2t.transform(image);
			System.out.println("正在把图片转成文字，进行到"+(x+1)+"//"+"6574");
		}
		System.out.println("转换结束，开始播放");
		
		new Thread(new Runnable() {//一个新线程去播放视频，需要ffpaly,否则删了这段

			@Override
			public void run() {
				// TODO 自动生成的方法存根
				try {
					Runtime.getRuntime().exec("C:\\Users\\thetb\\Downloads\\ffmpeg-20180321-7e0dc72-win64-shared\\bin\\ffplay 1.flv");
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}//无视这个，请删掉，调用视频播放器播放视频
			}
			
		}).start();
		
		
		new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(new Runnable() {//以特定的间隔执行打印命令，因为thread.sleep不能传小数，误差太大
            private int mCurrFrame = 0;
            
            @Override
            public void run() {
                Printer.INSTANCE.Print(s[mCurrFrame++]);//用jna调用本地方法打印输出，system.out.print会造成画面抖动
            }
        }, 0, 33333333, TimeUnit.NANOSECONDS);
		
	}

	
	
	interface Printer extends StdCallLibrary {//调用本地方法的接口
	    public static final Printer INSTANCE = (Printer) Native.loadLibrary("printer", Printer.class);
	    public void Print(String text);
	}

}
