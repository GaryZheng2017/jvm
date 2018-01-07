package com.zz;

/**
 *java虚拟机提供了参数-Xss来指定线程的最大栈空间，整个参数也直接决定
 *了函数可调用的最大深度。
 */
public class stack {
	
	//栈调用深度
	private static int count;
	
	public static void recursion() {
		count++;
		recursion();
	}
	public static void main(String[] args) {
		try {
			recursion();
		}
		catch(Throwable t)
		{
			System.out.println("调用最大深入："+count);
			t.printStackTrace();
		}
		
		//-Xss1m
/*		调用最大深入：22799
		java.lang.StackOverflowError
			at com.zz.Heap04.recursion(Heap04.java:14)
			at com.zz.Heap04.recursion(Heap04.java:14)
			at com.zz.Heap04.recursion(Heap04.java:14)*/
		
		//-Xss5m
/*		调用最大深入：128346
		java.lang.StackOverflowError
			at com.zz.Heap04.recursion(Heap04.java:14)
			at com.zz.Heap04.recursion(Heap04.java:14)*/
		
	}
}
