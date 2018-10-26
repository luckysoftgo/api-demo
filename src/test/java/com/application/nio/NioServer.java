package com.application.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @desc ：nio 的使用.
 * @author ：孤狼.
 */
public class NioServer implements Runnable{
	
	/**
	 * 1 多路复用器（管理所有的通道）
	 */
	private Selector seletor;
	/**
	 * 2 建立缓冲区
	 */
	private ByteBuffer readBuf = ByteBuffer.allocate(1024);
	/**
	 * 3 写入操作
	 */
	private ByteBuffer writeBuf = ByteBuffer.allocate(1024);
	
	/**
	 * 构造方法.
	 * @param port
	 */
	public NioServer(int port){
		try {
			//1 打开多路复用器（线程复用）
			this.seletor = Selector.open();
			//2 打开服务器通道
			ServerSocketChannel ssc = ServerSocketChannel.open();
			//3 设置服务器通道为非阻塞模式（调用accept不阻塞，否则跟BIO一样）
			ssc.configureBlocking(false);
			//4 绑定地址
			ssc.bind(new InetSocketAddress(port));
			//5 把服务器通道注册到多路复用器上，并且监听阻塞事件
			ssc.register(this.seletor, SelectionKey.OP_ACCEPT);
			System.out.println("Server start, port :" + port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true){
			try {
				//1 必须要让多路复用器开始监听
				this.seletor.select();
				//2 返回多路复用器已经选择的结果集，只有已经准备好数据进行IO操作的通道才会被选择到
				Iterator<SelectionKey> keys = this.seletor.selectedKeys().iterator();
				//3 进行遍历
				while(keys.hasNext()){
					//4 获取一个选择的元素
					SelectionKey key = keys.next();
					//5 直接从容器中移除就可以了，避免下一个循环重复操作
					keys.remove();
					//6 如果是有效的
					if(key.isValid()){
						//7 如果为阻塞状态
						if(key.isAcceptable()){
							this.accept(key);
						}
						//8 如果为可读状态
						if(key.isReadable()){
							this.read(key);
						}
						//9 写数据
						if(key.isWritable()){
							//ssc
							this.write(key);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 伪代码
	 * @param key
	 */
	private void write(SelectionKey key){
		try {
			ServerSocketChannel ssc =  (ServerSocketChannel) key.channel();
			ssc.register(this.seletor, SelectionKey.OP_WRITE);
		}catch (Exception e){
			System.out.println("写操作失败了.");
		}
	}
	
	private void read(SelectionKey key) {
		try {
			//1 清空缓冲区旧的数据
			this.readBuf.clear();
			//2 获取之前注册的socket通道对象
			SocketChannel sc = (SocketChannel) key.channel();
			//3 读取数据
			int count = sc.read(this.readBuf);
			//4 如果没有数据
			if(count == -1){
				key.channel().close();
				key.cancel();
				return;
			}
			//5 有数据则进行读取 读取之前需要进行复位方法(把position 和limit进行复位)
			this.readBuf.flip();
			//6 根据缓冲区的数据长度创建相应大小的byte数组，接收缓冲区的数据
			byte[] bytes = new byte[this.readBuf.remaining()];
			//7 接收缓冲区数据
			this.readBuf.get(bytes);
			//8 打印结果
			String body = new String(bytes).trim();
			System.out.println("Server : " + body);
			
			// 9..可以写回给客户端数据
			sc.write(this.writeBuf.get("收到消息".getBytes()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 接收数据.
	 * @param key
	 */
	private void accept(SelectionKey key) {
		try {
			//1 获取服务通道
			ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
			//2 执行阻塞方法
			SocketChannel sc = ssc.accept();
			//3 设置阻塞模式
			sc.configureBlocking(false);
			//4 将连接成功的客户端注册到多路复用器上，并设置读取标识
			sc.register(this.seletor, SelectionKey.OP_READ);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试数据.
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new NioServer(8765)).start();
	}
	
}
