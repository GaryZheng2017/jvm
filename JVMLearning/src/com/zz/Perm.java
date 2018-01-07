package com.zz;

/**
 *和java堆一样，方法区是一块所有线程共享的内存区域，它用于保存系统的类信息
 *方法区（永久区）可以保存多少信息可以对其进行配置，在默认情况下，
 * -XX:MaxPermSize为62MB，如果系统运行时生产大量的类，就需要设置一个相对
 * 合适的方法区，以免出现永久区内存溢出的问题。
 * -XX:PermSize=64MB -XX:MaxPermSize=64MB
 * 
 * 有关jvm参数的博客
 * http://www.cnblogs.com/redcreen/archive/2011/05/04/2037057.html
 */
public class Perm {
	public static void main(String[] args) {
		for(int i=0;i<5;i++) {
			byte [] b = new byte[1024*1024];
		}
		
		//-Xmx64m -Xms64m -XX:+PrintGCDetails
/*		Heap
		 PSYoungGen      total 18944K, used 7087K [0x00000000feb00000, 0x0000000100000000, 0x0000000100000000)
		  eden space 16384K, 43% used [0x00000000feb00000,0x00000000ff1ebc90,0x00000000ffb00000)
		  from space 2560K, 0% used [0x00000000ffd80000,0x00000000ffd80000,0x0000000100000000)
		  to   space 2560K, 0% used [0x00000000ffb00000,0x00000000ffb00000,0x00000000ffd80000)
		 ParOldGen       total 44032K, used 0K [0x00000000fc000000, 0x00000000feb00000, 0x00000000feb00000)
		  object space 44032K, 0% used [0x00000000fc000000,0x00000000fc000000,0x00000000feb00000)
		 Metaspace       used 2691K, capacity 4486K, committed 4864K, reserved 1056768K
		  class space    used 299K, capacity 386K, committed 512K, reserved 1048576K*/

	}
}
