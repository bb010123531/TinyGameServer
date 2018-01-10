package org.tiny.net.tool;

import java.util.UUID;

/**
 * 本算法利用62个可打印字符，通过随机生成32位UUID，由于UUID都为十六进制，所以将UUID分成8组，每4个为一组，然后通过模62操作，结果作为索引取出字符，
 */
public class ShortUUID {

	public static String[] chars = new String[] {
		"a", "b", "c", "d", "e", "f", "g",
		"h", "i", "j", "k", "l", "m", "n",
		"o", "p", "q", "r", "s", "t", "u",
		"v", "w", "x", "y", "z", "0", "1",
		"2", "3", "4", "5", "6", "7", "8",
		"9", "A", "B", "C", "D", "E", "F",
		"G", "H", "I", "J", "K", "L", "M",
		"N", "O", "P", "Q", "R", "S", "T",
		"U", "V", "W", "X", "Y", "Z" };

	public static String generateShortUuid() 
	{
		StringBuffer shortBuffer = new StringBuffer();
		System.err.println(UUID.randomUUID().toString());
		String uuid = UUID.randomUUID().toString().replace("-", "");
		
		System.err.println(uuid);
		for (int i = 0; i < 8; i++) 
		{
			String str = uuid.substring(i * 4, i * 4 + 4);
			System.err.println(str);
			int x = Integer.parseInt(str, 16);
			System.err.println(x);
			System.err.println(0x3E);
			System.err.println(x%0x3E);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();
	}
	
	public static void main(String[] args)
	{
		for (int i = 0; i < 10; i++)
		{
			String uuid = generateShortUuid();
			System.err.println(uuid);
		}
	}
}
