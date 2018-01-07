package com.zz;

import java.util.HashMap;
import java.util.Map;

/**
 * 另外大对象（新生代eden区无法装入时，也会直接进去老年代）。jvm里有个参数
 * 设置对象的大小超过在指定的大小之后，直接晋升老年代。
 * -XX:PretenureSizeThreshold
 * 
 * TLAB全称是Thread Loacl Allocation Buffer 即县城本地分配缓存，从名字上看是一个线程
 * 专用的内存分配区域，是为了加速对象分配而生的。每一个线程都会产生一个TLAB，该线程独享的
 * 工作区域，java虚拟机使用这种TLAB区来避免多线程冲突问题，提高了对象分配的效率。TLAB空间
 * 一般不会太大，当大对象无法再TALB分配时，则会直接分配到堆上。
 * -XX:+UseTLAB 默认使用TLAB
 */
public class Heap05 {
	public static void main(String[] args) {
		Map<Integer,byte[]> m = new HashMap<Integer,byte[]>();
		for(int i=0;i<5*1024;i++) {
			byte[] b = new byte[1024];
			m.put(i, b);
		}
		
//		-Xmx30m -Xms30m -XX:+UseSerialGC -XX:PretenureSizeThreshold=1000
//				-XX:+PrintGCDetails
		//使用XX:PretenureSizeThreshold可以指定老年代的对象大小，
		//但是要注意TLAB区域优先分配空间（上面的结果是老年代没有数据）
		
		
/*		Heap
		 def new generation   total 9216K, used 6915K [0x00000000fe200000, 0x00000000fec00000, 0x00000000fec00000)
		  eden space 8192K,  84% used [0x00000000fe200000, 0x00000000fe8c0f98, 0x00000000fea00000)
		  from space 1024K,   0% used [0x00000000fea00000, 0x00000000fea00000, 0x00000000feb00000)
		  to   space 1024K,   0% used [0x00000000feb00000, 0x00000000feb00000, 0x00000000fec00000)
		 tenured generation   total 20480K, used 0K [0x00000000fec00000, 0x0000000100000000, 0x0000000100000000)
		   the space 20480K,   0% used [0x00000000fec00000, 0x00000000fec00000, 0x00000000fec00200, 0x0000000100000000)
		 Metaspace       used 2699K, capacity 4486K, committed 4864K, reserved 1056768K
		  class space    used 299K, capacity 386K, committed 512K, reserved 1048576K*/
		
		
//		-Xmx30m -Xms30m -XX:+UseSerialGC -XX:PretenureSizeThreshold=1000
//		-XX:+PrintGCDetails -XX:-UseTLAB	
		
/*		Heap
		 def new generation   total 9216K, used 901K [0x00000000fe200000, 0x00000000fec00000, 0x00000000fec00000)
		  eden space 8192K,  11% used [0x00000000fe200000, 0x00000000fe2e16d8, 0x00000000fea00000)
		  from space 1024K,   0% used [0x00000000fea00000, 0x00000000fea00000, 0x00000000feb00000)
		  to   space 1024K,   0% used [0x00000000feb00000, 0x00000000feb00000, 0x00000000fec00000)
		 tenured generation   total 20480K, used 5423K [0x00000000fec00000, 0x0000000100000000, 0x0000000100000000)
		   the space 20480K,  26% used [0x00000000fec00000, 0x00000000ff14bf78, 0x00000000ff14c000, 0x0000000100000000)
		 Metaspace       used 2699K, capacity 4486K, committed 4864K, reserved 1056768K
		  class space    used 299K, capacity 386K, committed 512K, reserved 1048576K*/


	}
}
