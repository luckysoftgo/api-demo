package com.javabase.disruptor.demo1;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ThreadFactory;


/**
 * @author : 孤狼
 * @NAME: Application
 * @DESC: 主函数：
 * @DATE: 2019年9月3日
 **/
public class Application1 {
	
	/**
	 * 主测试函数.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		ThreadFactory nameFactory =new ThreadFactoryBuilder().setNameFormat("disruptor-pool-%d").build();
		
		/**
		 * 创建LongEvent事件工厂
		 */
		LongEventFactory factory = new LongEventFactory();
		
		/**
		 * 创建bufferSize缓冲区 ,也就是RingBuffer大小，要求必须是2的N次方
		 */
		int ringBufferSize = 1024 * 1024;
		
		/**
		 * 创建disruptor实例，并传入泛型LongEvent事件类型（数据类型）
		 * 构造参数：
		 * Disruptor(
		 * factory,--事件工厂，用于创建LongEvent，也就是实际最终被消费的数据
		 * ringBufferSize,--缓冲区大小
		 * executor,--线程池，作用是使用线程池进行内部数据接收处理调度
		 * producerType,--两种形式：SINGLE(生产者只有一个)和MULTI(生产者有多个)
		 * waitStrategy--决定一个消费者将如何等待生产者将EVENT放入disruptor
		 * )
		 
		 /*
		 //BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小，并且在各种不同部署环境中能提供更加一致的性能表现
		 WaitStrategy BLOCKING_WAIT = new BlockingWaitStrategy();
		 //SleepingWaitStrategy 的性能表现跟BlockingWaitStrategy差不多，对CPU的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景
		 WaitStrategy SLEEPING_WAIT = new SleepingWaitStrategy();
		 //YieldingWaitStrategy 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于CPU逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性
		 WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy();
		 */
		
		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, ringBufferSize, nameFactory, ProducerType.SINGLE, new YieldingWaitStrategy());
		
		/**
		 * 连接消费事件方法，监听ringbuffer环形队列容器中的事件，有则取出消费，无则阻塞，所以disruptor相当于一个特殊的有界阻塞队列
		 * LongEventHandler 理解为数据消费者
		 */
		disruptor.handleEventsWith(new LongEventHandler());
		
		/**
		 *启动disruptor
 		 */
		disruptor.start();
		
		//Disruptor 的事件发布过程是一个两阶段提交的过程：
		
		/**
		 * 发布事件
		 * 使用该方法获得具体存放数据的容器ringBuffer(环形结构)
		 */
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		
		/**
		 * 定义事件生产者
		 */
		LongEventProducer producer = new LongEventProducer(ringBuffer);
		
		//LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);
		
		/**
		 * 定义一个含有8个空间的字节缓冲
		 */
		ByteBuffer byteBuffer = ByteBuffer.allocate(8);
		for(long a = 0; a < 100; a++){
			//每次覆盖byteBuffer下标为0的位置
			byteBuffer.putLong(0, a);
			
			//producer将数据byteBuffer存入ringbuffer事件槽
			producer.onData(byteBuffer);
			//Thread.sleep(1000);
		}
		//关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
		disruptor.shutdown();
		//关闭 disruptor 使用的线程池；如果需要的话，必须手动关闭， disruptor 在 shutdown 时不会自动关闭；
	}

}
