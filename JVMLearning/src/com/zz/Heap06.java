package com.zz;

public class Heap06 {
	public static void alloc() {
		byte [] b = new byte[2];
	}
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for(int i=0;i<10000000;i++) {
			alloc();
		}
		long end = System.currentTimeMillis();
		System.out.println("耗时："+(end - start)+"ms");
		
		//TLAB分配
		//-XX:+UseTLAB -XX:+PrintTLAB -XX:+PrintGC -XX:TLABSize=102400
		//-XX:-ResizeTLAB -XX:TLABRefillWasteFraction=100 -XX:-DoEscapeAnalysis
		// -server
		
/*		TLAB: gc thread: 0x00000000198e4000 [id: 4580] desired_size: 100KB slow allocs: 0  refill waste: 1024B alloc: 0.15024     5000KB refills: 1 waste 99.9% gc: 102280B slow: 0B fast: 0B
		TLAB: gc thread: 0x0000000004d28000 [id: 6608] desired_size: 100KB slow allocs: 0  refill waste: 1024B alloc: 0.15024     5000KB refills: 1 waste 99.0% gc: 101376B slow: 0B fast: 0B
		TLAB: gc thread: 0x0000000004c32800 [id: 10972] desired_size: 100KB slow allocs: 2  refill waste: 1024B alloc: 0.15024     5000KB refills: 331 waste  0.0% gc: 0B slow: 5032B fast: 0B
		TLAB totals: thrds: 3  refills: 333 max: 331 slow allocs: 2 max 2 waste:  0.6% gc: 203656B max: 102280B slow: 5032B max: 5032B fast: 0B max: 0B
		[GC (Allocation Failure)  33280K->688K(125952K), 0.0008892 secs]
		TLAB: gc thread: 0x0000000004c32800 [id: 10972] desired_size: 100KB slow allocs: 0  refill waste: 1024B alloc: 0.57202    19037KB refills: 333 waste  0.0% gc: 0B slow: 5344B fast: 0B
		TLAB totals: thrds: 1  refills: 333 max: 333 slow allocs: 0 max 0 waste:  0.0% gc: 0B max: 0B slow: 5344B max: 5344B fast: 0B max: 0B
		[GC (Allocation Failure)  33968K->640K(125952K), 0.0011191 secs]
		TLAB: gc thread: 0x0000000004c32800 [id: 10972] desired_size: 100KB slow allocs: 0  refill waste: 1024B alloc: 0.72176    24020KB refills: 333 waste  0.0% gc: 0B slow: 5344B fast: 0B
		TLAB totals: thrds: 1  refills: 333 max: 333 slow allocs: 0 max 0 waste:  0.0% gc: 0B max: 0B slow: 5344B max: 5344B fast: 0B max: 0B
		[GC (Allocation Failure)  33920K->624K(125952K), 0.0008179 secs]
		TLAB: gc thread: 0x0000000004c32800 [id: 10972] desired_size: 100KB slow allocs: 0  refill waste: 1024B alloc: 0.81909    27259KB refills: 333 waste  0.0% gc: 0B slow: 5344B fast: 0B
		TLAB totals: thrds: 1  refills: 333 max: 333 slow allocs: 0 max 0 waste:  0.0% gc: 0B max: 0B slow: 5344B max: 5344B fast: 0B max: 0B
		[GC (Allocation Failure)  33904K->608K(159232K), 0.0008580 secs]
		TLAB: gc thread: 0x0000000004c32800 [id: 10972] desired_size: 100KB slow allocs: 0  refill waste: 1024B alloc: 0.88235    58729KB refills: 666 waste  0.0% gc: 0B slow: 10664B fast: 0B
		TLAB totals: thrds: 1  refills: 666 max: 666 slow allocs: 0 max 0 waste:  0.0% gc: 0B max: 0B slow: 10664B max: 10664B fast: 0B max: 0B
		[GC (Allocation Failure)  67168K->608K(159232K), 0.0012553 secs]
		耗时：85ms*/
		

		//TLAB分配,禁用TLAB
		//-XX:-UseTLAB -XX:+PrintTLAB -XX:+PrintGC -XX:TLABSize=102400
		//-XX:-ResizeTLAB -XX:TLABRefillWasteFraction=100 -XX:-DoEscapeAnalysis
		// -server
/*		[GC (Allocation Failure)  33279K->672K(125952K), 0.0008597 secs]
		[GC (Allocation Failure)  33952K->592K(125952K), 0.0010658 secs]
		[GC (Allocation Failure)  33872K->640K(125952K), 0.0006327 secs]
		[GC (Allocation Failure)  33920K->640K(159232K), 0.0008230 secs]
		[GC (Allocation Failure)  67200K->608K(159232K), 0.0011102 secs]
		耗时：192ms*/


	}
}
