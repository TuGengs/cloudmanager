package com.halfmoon.cloudmanager.util.randomcode;

import java.util.Random;

/**
 * 该类用于生成课程代码，为了防止重复，还是采用了从0循环到36的若干次方的方式，
 * 并转换成三十六进制，不过转换过程中做了一次随机映射
 * @author hzq
 *
 */
public class RandomCode {
	
	private static int CODE_LENGTH;					// 代码位数
	private static int MAX_SERIAL_NUMBER;			// 最大序列
	
	private static int serialNumber = 23333;		// 序列
	
	public RandomCode(int codeLength) {
		
		CODE_LENGTH = codeLength;
		MAX_SERIAL_NUMBER = (int) Math.pow(36, CODE_LENGTH) - 1;
		
	}
	
	public String getCode() {
		
		String code = generateCode();

		serialNumber++;
		// 一旦超过序列允许的最大值，就从某个随机数重新开始
		if(serialNumber >= MAX_SERIAL_NUMBER) {
			Random random = new Random();
			serialNumber = random.nextInt(1024);
		}
		
		return code;
	}

	private String generateCode() {
		
		// 候选字母
		char candidate[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		
		// 映射表（随便生成的）
		int randomMap[] = { 23, 7, 34, 3, 4, 12, 6, 1, 31, 8, 21, 25, 5, 35, 17,
				15, 26, 14, 32, 19, 20, 10, 22, 0, 24, 11, 16, 27, 33, 29, 30,
				9, 18, 28, 2, 13 };
		
		StringBuilder code = new StringBuilder("");
		int numbers[] = new int[CODE_LENGTH];
		
		int temp = serialNumber;
		// 进制转换（十进制->三十六进制）
		for(int i = 0; i < CODE_LENGTH; i++) {
			int number = temp % 36;
			temp /= 36;
			numbers[CODE_LENGTH-1 - i] = number;
		}
		// 根据毎一位数生成对应的字母或数字
		for(int i = 0; i < CODE_LENGTH; i++) {
			char partialCode = candidate[randomMap[numbers[i]]];
			code.append(partialCode);
		}

		return code.toString();
		
	}

}
