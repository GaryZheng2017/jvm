package com.zz;

/**
 * 堆分配参数（1）
 * -XX:+PrintGC 使用这个参数，虚拟机启动后，只要遇到GC就会打印日志。
 * -XX:+UseSerialGC 配置串行回收器（回收器的一种)
 * -XX:+PrintGCDetails 可以查看详细信息，包括各个区的情况。
 * -Xms:设置java程序启动时初始堆大小
 * -Xmx:设置java程序能获得的最大堆大小
 * -XX:+PrintCommandLineFlags 可以将隐式或者显示传给虚拟机的参数输出。
 * 
 * 说明：-XX 对于系统级别的（jvm）配置
 * 		 非-XX 基本上都是对应用层面上的配置
 * 		 + 启用
 * 		 - 禁用
 * 
 * 总结：在实际工作中，我们可以直接将初始的堆大小与最大堆大小设置相等，
 * 		 这样的好处是可以减少程序运行时的垃圾回收次数，从而提高性能。
 * @author zz
 *
 */
public class Heap01 
{
	public static void main(String[] args)
	{
		
		//查看GC信息
		System.out.println("max memory:  "+Runtime.getRuntime().maxMemory());		
		System.out.println("free memory:  "+Runtime.getRuntime().freeMemory());
		System.out.println("total memory:  "+Runtime.getRuntime().totalMemory());

		byte [] b1 = new byte[1*1024*1024];
		System.out.println("分配了1M");
		System.out.println("max memory:  "+Runtime.getRuntime().maxMemory());		
		System.out.println("free memory:  "+Runtime.getRuntime().freeMemory());
		System.out.println("total memory:  "+Runtime.getRuntime().totalMemory());

		byte [] b2 = new byte[4*1024*1024];
		System.out.println("分配了4M");
		System.out.println("max memory:  "+Runtime.getRuntime().maxMemory());		
		System.out.println("free memory:  "+Runtime.getRuntime().freeMemory());
		System.out.println("total memory:  "+Runtime.getRuntime().totalMemory());
		
		int a = 0x00000000fec00000;
		int b = 0x00000000fee10000;//第二个减去第一个，为def new generation 的used 50k;
		int c = 0x00000000ff2a0000;
		System.out.println("结果为"+(b-a)/(1024));
		//jvm参数配置为：-Xms5m -Xmx20m -XX:+PrintGCDetails -XX:+UseSerialGC 
		//              -XX:+PrintCommandLineFlags
		
		//结果为
/*		-XX:InitialHeapSize=5242880 -XX:MaxHeapSize=20971520 -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseSerialGC 
		max memory:  20316160
		free memory:  5147904
		total memory:  6094848
		[GC (Allocation Failure) [DefNew: 924K->192K(1856K), 0.0017050 secs] 924K->553K(5952K), 0.0017451 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		分配了1M
		max memory:  20316160
		free memory:  4447184
		total memory:  6094848
		[GC (Allocation Failure) [DefNew: 1247K->0K(1856K), 0.0010180 secs][Tenured: 1577K->1577K(4096K), 0.0021918 secs] 1609K->1577K(5952K), [Metaspace: 2688K->2688K(1056768K)], 0.0032870 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		分配了4M
		max memory:  20316160
		free memory:  4515200
		total memory:  10358784
		结果为6
		Heap
		 def new generation   total 1920K, used 50K [0x00000000fec00000, 0x00000000fee10000, 0x00000000ff2a0000) 
		 											--分别代表最小地址，当前分配的空间地址，最大的空间地址
		  eden space 1728K,   2% used [0x00000000fec00000, 0x00000000fec0ca98, 0x00000000fedb0000)
		  from space 192K,   0% used [0x00000000fedb0000, 0x00000000fedb0000, 0x00000000fede0000)
		  to   space 192K,   0% used [0x00000000fede0000, 0x00000000fede0000, 0x00000000fee10000)
		 tenured generation   total 8196K, used 5673K [0x00000000ff2a0000, 0x00000000ffaa1000, 0x0000000100000000)
		   the space 8196K,  69% used [0x00000000ff2a0000, 0x00000000ff82a678, 0x00000000ff82a800, 0x00000000ffaa1000)
		 Metaspace       used 2694K, capacity 4486K, committed 4864K, reserved 1056768K
		  class space    used 299K, capacity 386K, committed 512K, reserved 1048576K*/

		
	}

}
