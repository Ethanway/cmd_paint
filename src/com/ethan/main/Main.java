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
			image = ImageIO.read(new File(path+"BadApple"+String.format("%04d", x)+".png"));//��ȡͼƬ����ΪͼƬ��6574�ţ���pr������png���У�����ע���һ�µ�ַ
			s[x] = i2t.transform(image);
			System.out.println("���ڰ�ͼƬת�����֣����е�"+(x+1)+"//"+"6574");
		}
		System.out.println("ת����������ʼ����");
		
		new Thread(new Runnable() {//һ�����߳�ȥ������Ƶ����Ҫffpaly,����ɾ�����

			@Override
			public void run() {
				// TODO �Զ����ɵķ������
				try {
					Runtime.getRuntime().exec("C:\\Users\\thetb\\Downloads\\ffmpeg-20180321-7e0dc72-win64-shared\\bin\\ffplay 1.flv");
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}//�����������ɾ����������Ƶ������������Ƶ
			}
			
		}).start();
		
		
		new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(new Runnable() {//���ض��ļ��ִ�д�ӡ�����Ϊthread.sleep���ܴ�С�������̫��
            private int mCurrFrame = 0;
            
            @Override
            public void run() {
                Printer.INSTANCE.Print(s[mCurrFrame++]);//��jna���ñ��ط�����ӡ�����system.out.print����ɻ��涶��
            }
        }, 0, 33333333, TimeUnit.NANOSECONDS);
		
	}

	
	
	interface Printer extends StdCallLibrary {//���ñ��ط����Ľӿ�
	    public static final Printer INSTANCE = (Printer) Native.loadLibrary("printer", Printer.class);
	    public void Print(String text);
	}

}
