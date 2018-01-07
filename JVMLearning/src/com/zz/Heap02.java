package com.zz;

/**
 *新生代配置
 *	-Xmn:可以设置新生代的大小，设置一个比较大的新生带会减少老年代的
 *		 大小，这个参数对系统性能以及GC行为有很大的影响，新生代大小
 *		 一般会设置整个堆空间的1/3到1/4左右。
 *	—XX:SurvivorRatio 用来设置新生代中eden空间和from/to空间的比例。
 *		含义：-XX:SurvivorRatio=eden/from=eden/to
 *
 *	总结：不同的堆分布情况，对系统执行会产生一定的影响，在实际工作中，
 *       应根据系统的特点做出合理的配置，基本策略：尽可能将对象预留在
 *       新生代，减少老年代的GC次数。
 *       
 *       除了可以设置新生代的绝对大小（—Xmn),还可以使用（-XX:NewRatio)
 *       设置新生代和老年代的比例：-XX:NewRatio=老年代/新生代。
 */
public class Heap02 {
	public static void main(String[] args) {
		byte [] b = null;
		//连续向系统申请10MB空间
		for(int i = 0;i< 10;i++)
		{
			b = new byte[1*1024*1024];
		}
		
		//第一次配置
		//-Xms20m -Xmx20m -Xmn1m -XX:SurvivorRatio=2 -XX:+PrintGCDetails
		//-XX:+UseSerialGC
		
/*		[GC (Allocation Failure) [DefNew: 512K->256K(768K), 0.0014281 secs] 512K->429K(20224K), 0.0014720 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		Heap
		 def new generation   total 768K, used 597K [0x00000000fec00000, 0x00000000fed00000, 0x00000000fed00000)
		  eden space 512K,  66% used [0x00000000fec00000, 0x00000000fec557e8, 0x00000000fec80000)
		  from space 256K, 100% used [0x00000000fecc0000, 0x00000000fed00000, 0x00000000fed00000)
		  to   space 256K,   0% used [0x00000000fec80000, 0x00000000fec80000, 0x00000000fecc0000)
		 tenured generation   total 19456K, used 10413K [0x00000000fed00000, 0x0000000100000000, 0x0000000100000000)
		   the space 19456K,  53% used [0x00000000fed00000, 0x00000000ff72b4c0, 0x00000000ff72b600, 0x0000000100000000)
		 Metaspace       used 2692K, capacity 4486K, committed 4864K, reserved 1056768K
		  class space    used 299K, capacity 386K, committed 512K, reserved 1048576K*/
		
		
		//第二次配置
		//-Xms20m -Xmx20m -Xmn7m -XX:SurvivorRatio=2 -XX:+PrintGCDetails
		//-XX:+UseSerialGC
		
/*		[GC (Allocation Failure) [DefNew: 3077K->1576K(5376K), 0.0024678 secs] 3077K->1576K(18688K), 0.0025387 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 4717K->1024K(5376K), 0.0018581 secs] 4717K->1575K(18688K), 0.0018897 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 4157K->1024K(5376K), 0.0004510 secs] 4708K->1575K(18688K), 0.0004817 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		Heap
		 def new generation   total 5376K, used 3208K [0x00000000fec00000, 0x00000000ff300000, 0x00000000ff300000)
		  eden space 3584K,  60% used [0x00000000fec00000, 0x00000000fee22240, 0x00000000fef80000)
		  from space 1792K,  57% used [0x00000000ff140000, 0x00000000ff240010, 0x00000000ff300000)
		  to   space 1792K,   0% used [0x00000000fef80000, 0x00000000fef80000, 0x00000000ff140000)
		 tenured generation   total 13312K, used 551K [0x00000000ff300000, 0x0000000100000000, 0x0000000100000000)
		   the space 13312K,   4% used [0x00000000ff300000, 0x00000000ff389e40, 0x00000000ff38a000, 0x0000000100000000)
		 Metaspace       used 2692K, capacity 4486K, committed 4864K, reserved 1056768K
		  class space    used 299K, capacity 386K, committed 512K, reserved 1048576K*/
		
		//第三次配置
		//-Xms20m -Xmx20m -XX:NewRatio=2 -XX:+PrintGCDetails
		//-XX:+UseSerialGC
		
/*		[GC (Allocation Failure) [DefNew: 5203K->552K(6144K), 0.0019921 secs] 5203K->1576K(19840K), 0.0020343 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 5779K->0K(6144K), 0.0015808 secs] 6803K->2599K(19840K), 0.0016098 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		Heap
		 def new generation   total 6144K, used 1079K [0x00000000fec00000, 0x00000000ff2a0000, 0x00000000ff2a0000)
		  eden space 5504K,  19% used [0x00000000fec00000, 0x00000000fed0ddb0, 0x00000000ff160000)
		  from space 640K,   0% used [0x00000000ff160000, 0x00000000ff160000, 0x00000000ff200000)
		  to   space 640K,   0% used [0x00000000ff200000, 0x00000000ff200000, 0x00000000ff2a0000)
		 tenured generation   total 13696K, used 2599K [0x00000000ff2a0000, 0x0000000100000000, 0x0000000100000000)
		   the space 13696K,  18% used [0x00000000ff2a0000, 0x00000000ff529e60, 0x00000000ff52a000, 0x0000000100000000)
		 Metaspace       used 2692K, capacity 4486K, committed 4864K, reserved 1056768K
		  class space    used 299K, capacity 386K, committed 512K, reserved 1048576K*/

	}
}
