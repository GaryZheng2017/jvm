package com.zz;

import java.util.HashMap;
import java.util.Map;

/**
 *一般而言对象首次创建会被放置在新生代的eden区，如果没有GC介入，则对象不会
 *离开eden区，那么eden区的对象如何进入老年代的呢？一般来讲，只要对象的年龄达到一定
 *的大小，就会自动离开新生代进去老年代，对象年龄是由对象经历数次GC决定的，在新生代每次
 *GC之后如果对象没有被回收则年龄加1，虚拟机提供了一个参数来控制新生代对象的最大年龄，当
 *超过这个年龄范围就会晋升老年代。
 * -XX:MaxTenuringThreshold,默认情况下为15.
 * 
 * 总结：根据设置MaxTenuringThreshold参数，可以指定新生代对象经历多少次回收进入老年代。
 */
public class Heap04 {
	public static void main(String[] args) {
		Map<Integer,byte[]> m = new HashMap<Integer,byte[]>();
		for (int i=0;i<5;i++) {
			byte[] b = new byte[1024*1024];
			m.put(i, b);
		}
		
		for(int k=0;k<20;k++) {
			for(int j=0;j<300;j++) {
				byte[] b = new byte[1024*1024];
			}
		}
//		 -XX:+PrintHeapAtGC
//		-Xmx1024m -Xms1024m -XX:UseSerialGC -XX:MaxTenuringThreshold=15
//		-XX:+PrintGCDetails
		
/*		[GC (Allocation Failure) [DefNew: 278925K->5673K(314560K), 0.0033263 secs] 278925K->5673K(1013632K), 0.0033694 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 284509K->5672K(314560K), 0.0032533 secs] 284509K->5672K(1013632K), 0.0032823 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 284556K->5672K(314560K), 0.0013547 secs] 284556K->5672K(1013632K), 0.0013777 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 284596K->5672K(314560K), 0.0013244 secs] 284596K->5672K(1013632K), 0.0013850 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 284621K->5672K(314560K), 0.0016414 secs] 284621K->5672K(1013632K), 0.0016683 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 284638K->5672K(314560K), 0.0015548 secs] 284638K->5672K(1013632K), 0.0015910 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 284648K->5672K(314560K), 0.0018739 secs] 284648K->5672K(1013632K), 0.0019051 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 284655K->5672K(314560K), 0.0013525 secs] 284655K->5672K(1013632K), 0.0013769 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 284659K->5672K(314560K), 0.0015134 secs] 284659K->5672K(1013632K), 0.0015407 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 284662K->5672K(314560K), 0.0016209 secs] 284662K->5672K(1013632K), 0.0016482 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 284664K->5672K(314560K), 0.0016435 secs] 284664K->5672K(1013632K), 0.0016700 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 284665K->5672K(314560K), 0.0014242 secs] 284665K->5672K(1013632K), 0.0014686 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 284666K->5672K(314560K), 0.0014054 secs] 284666K->5672K(1013632K), 0.0014306 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 284666K->5672K(314560K), 0.0013666 secs] 284666K->5672K(1013632K), 0.0014242 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 284667K->5672K(314560K), 0.0015151 secs] 284667K->5672K(1013632K), 0.0015578 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 284667K->0K(314560K), 0.0039172 secs] 284667K->5672K(1013632K), 0.0039556 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 278994K->0K(314560K), 0.0002121 secs] 284667K->5672K(1013632K), 0.0002406 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 278995K->0K(314560K), 0.0002223 secs] 284667K->5672K(1013632K), 0.0002462 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 278995K->0K(314560K), 0.0003059 secs] 284667K->5672K(1013632K), 0.0003507 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 278995K->0K(314560K), 0.0002197 secs] 284667K->5672K(1013632K), 0.0002445 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 278995K->0K(314560K), 0.0002240 secs] 284667K->5672K(1013632K), 0.0002483 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [DefNew: 278995K->0K(314560K), 0.0002150 secs] 284667K->5672K(1013632K), 0.0002415 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		Heap
		 def new generation   total 314560K, used 39848K [0x00000000c0000000, 0x00000000d5550000, 0x00000000d5550000)
		  eden space 279616K,  14% used [0x00000000c0000000, 0x00000000c26ea090, 0x00000000d1110000)
		  from space 34944K,   0% used [0x00000000d1110000, 0x00000000d1110000, 0x00000000d3330000)
		  to   space 34944K,   0% used [0x00000000d3330000, 0x00000000d3330000, 0x00000000d5550000)
		 tenured generation   total 699072K, used 5672K [0x00000000d5550000, 0x0000000100000000, 0x0000000100000000)
		   the space 699072K,   0% used [0x00000000d5550000, 0x00000000d5ada1b8, 0x00000000d5ada200, 0x0000000100000000)
		 Metaspace       used 2693K, capacity 4486K, committed 4864K, reserved 1056768K
		  class space    used 299K, capacity 386K, committed 512K, reserved 1048576K*/

	}
}
