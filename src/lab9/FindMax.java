package lab9;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

/**
 *
 * COMP 3021
 *
This is a class that prints the maximum value of a given array of 90 elements

This is a single threaded version.

Create a multi-thread version with 3 threads:

one thread finds the max among the cells [0,29]
another thread the max among the cells [30,59]
another thread the max among the cells [60,89]

Compare the results of the three threads and print at console the max value.

 *
 * @author valerio
 *
 */
public class FindMax {
	// this is an array of 90 elements
	// the max value of this array is 9999
	static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
			234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3 };

	public static void main(String[] args) {
		new FindMax().printMax();
	}

	public void printMax() {
		// this is a single threaded version
		//int max = findMax(0, array.length - 1);
		//System.out.println("the max value is " + max);
		ThreadRunner threadRunner1 = new ThreadRunner(0, 29);
		ThreadRunner threadRunner2 = new ThreadRunner(30, 59);
		ThreadRunner threadRunner3 = new ThreadRunner(60, 89);

		Thread thread1 = new Thread(threadRunner1);
		Thread thread2 = new Thread(threadRunner2);
		Thread thread3 = new Thread(threadRunner3);
		thread1.start();
		thread2.start();
		thread3.start();

		try {
			thread1.join();
			thread2.join();
			thread3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int max = Math.max(threadRunner1.getMax(), Math.max(threadRunner2.getMax(), threadRunner3.getMax()));
		System.out.println("the max value is " + max);
	}

	/**
	 * returns the max value in the array within a give range [begin,range]
	 *
	 * @param begin
	 * @param end
	 * @return
	 */
	private int findMax(int begin, int end) {
		// you should NOT change this function
		int max = array[begin];
		for (int i = begin + 1; i <= end; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}

	class ThreadRunner implements Runnable {
		private int input;
		private int output;
		private int max;

		public ThreadRunner(int start, int end) {
			// TODO Auto-generated constructor stub
			input = start;
			output = end;
			max = 0;
		}

		public int getMax() {
			return max;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			max = findMax(input, output);
		}

	}
}
