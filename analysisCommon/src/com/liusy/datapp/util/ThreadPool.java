


//   ThreadPool.java

package com.liusy.datapp.util;

import java.io.PrintStream;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool
{

	private static ExecutorService exec;
	public static int POOL_SIZE;

	public ThreadPool()
	{
	}

	public static void exec(Runnable r)
		throws InterruptedException
	{
		exec.execute(r);
	}

	public static void shutdown()
		throws InterruptedException
	{
		exec.shutdown();
	}

	public static void main(String args[])
	{
		for (int i = 0; i < 10; i++)
			System.out.println(UUID.randomUUID());

	}

	static 
	{
		POOL_SIZE = 10;
		exec = Executors.newFixedThreadPool(POOL_SIZE);
	}
}
