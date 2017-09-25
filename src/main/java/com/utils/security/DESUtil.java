package com.utils.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * DES加密 解密算法<br>
 * DES全称为Data Encryption Standard,即数据加密标准,是一种使用密钥加密的块算法.<br>
 * DES算法的入口参数有三个：Key、Data、Mode。
 * 其中Key为7个字节共56位，是DES算法的工作密钥；
 * Data为8个字节64位，是要被加密或被解密的数据；
 * Mode为DES的工作方式,有两种:加密或解密。
 * @author zhangf
 *
 */
public class DESUtil {
	
	/**加密算法*/
	public static final String DES = "DES";
	/**编码方式*/
	public static final String ENCODE_STR = "utf-8";
	/**默认加密键*/
	public static final String DEFAULT_KEY = "yxhtcode";
	
	/**
	 * DES加密，使用默认的加密键，返回16进制字符串
	 * @param data 要加密的字符串
	 * @return
	 * @throws InvalidKeyException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String encrypt(String data) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		if(data == null) {
			return null;
		}
		return encrypt(data, DEFAULT_KEY);
	}
	
	/**
	 * DES解密，使用默认的解密键，返回解密后的字符串
	 * @param data 要解密的字符串
	 * @return
	 * @throws DecoderException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeyException 
	 */
	public static String decrypt(String data) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, DecoderException {
		if(data == null) {
			return null;
		}
		return decrypt(data, DEFAULT_KEY);
	}
	
	/**
	 * 根据键值进行DES加密，返回16进制字符串
	 * @param data 要进行DES加密的字符串
	 * @param key 加密键
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String encrypt(String data, String key) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		if(data == null) {
			return null;
		}
		byte[] dataBytes = data.getBytes(ENCODE_STR);
		byte[] keyBytes = null;
		if(key == null) {
			keyBytes = DEFAULT_KEY.getBytes(ENCODE_STR);
		} else {
			keyBytes = key.getBytes(ENCODE_STR);
		}
		byte[] encodeBytes = encrypt(dataBytes, keyBytes);
		return Hex.encodeHexString(encodeBytes);
	}
	
	/**
	 * 根据键值进行DES解密
	 * @param data 要进行DES解密的字符串
	 * @param key 解密键
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws DecoderException
	 */
	public static String decrypt(String data, String key) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, DecoderException {
		if(data == null) {
			return null;
		}
		byte[] dataBytes = Hex.decodeHex(data.toCharArray());
		byte[] keyBytes = null;
		if(key == null) {
			keyBytes = DEFAULT_KEY.getBytes(ENCODE_STR);
		} else {
			keyBytes = key.getBytes(ENCODE_STR);
		}
		byte[] decodeBytes = decrypt(dataBytes, keyBytes);
		return new String(decodeBytes, ENCODE_STR);
	}
	
	/**
	 * 根据键值进行DES加密
	 * @param data 要加密的数据，字节数组
	 * @param key 加密键，byte数组
	 * @return
	 * @throws InvalidKeyException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchPaddingException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	private static byte[] encrypt(byte[] data, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		//1.生成一个随机的可信任源
		SecureRandom sr = new SecureRandom();
		
		SecretKey secretKey = getSecretKey(key);
		
		//4.Cipher对象实际完成加密操作，先获取Cipher对象
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		
		//5.用密钥初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);
		
		return cipher.doFinal(data);
	}
	
	/**
	 * 根据键值进行DES解密
	 * @param data 要解密的数据，byte数组
	 * @param key 解密键，byte数组
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	private static byte[] decrypt(byte[] data, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		//1.生成一个随机的可信任源
		SecureRandom sr = new SecureRandom();
		
		SecretKey secretKey = getSecretKey(key);
		
		//4.Cipher对象实际完成加密操作，先获取Cipher对象
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		
		//5.用密钥初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, secretKey, sr);
		
		return cipher.doFinal(data);
	}
	
	private static SecretKey getSecretKey(byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
		//2.从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		
		//3.创建一个密钥工厂，然后用它把DESKeySpec对象转化成secretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey secretKey = keyFactory.generateSecret(dks);
		
		return secretKey;
	}
	
}
