package com.application.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @desc Nio 测试客户端.
 * @author 孤狼.
 */
public class NioClient {
	
	/**
	 * 测试.
	 * @param args
	 */
	public static void main(String[] args) {
		
		InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8765);
		//声明连接通道
		SocketChannel sc = null;
		//建立缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		try {
			//打开通道
			sc = SocketChannel.open();
			//进行连接
			sc.connect(address);
			
			Scanner scanner = new Scanner(System.in);
			while(true){
				//定义一个字节数组，然后使用系统录入功能：
				String message = scanner.next();
				//把数据放到缓冲区中
				buf.put(message.getBytes());
				//对缓冲区进行复位
				buf.flip();
				//写出数据
				sc.write(buf);
				//清空缓冲区数据
				buf.clear();
				//获得返回结果.
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(sc != null){
				try {
					sc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
