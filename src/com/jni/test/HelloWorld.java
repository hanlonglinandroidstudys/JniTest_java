package com.jni.test;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class HelloWorld {

	Humen humen = new man();

	public native String getStringFromC(String text);

	public native int calculate(int a, int b);

	// 访问java属性
	// 修改成员属性
	public String key = "hanlonglin";

	public native String accessField();

	// 修改静态属性
	public static int count = 0;

	public native int accessStaticField();

	// 访问java方法
	// 成员方法
	public native int accessMethod();

	// 静态方法
	public native String accessStaticMethod();

	// 构造方法
	public native Date accessConstructor();

	// 父类方法
	public native void accessNonvirtualMethod();

	// 传入中文 返回中文
	public native String chineseChars(String in);

	// 传入数组
	public native void giveArray(int[] intArray);

	// 返回数组
	public native int[] getArray(int len);

	// 局部引用
	public native void localRef();

	// 全局引用
	// 创建
	public native void createGlobalRef();

	// 获取
	public native String getGlobalRef();

	// 释放
	public native void deleteGlobalRef();

	// 异常处理
	public native void exception();

	// 缓存策略
	public native void cached();
	
	//初始化全局变量
	public native static void initIDs();
	
	//加密解密
	//真正使用c++库实现加密解密
	public native String encrypt(String str);
	
	public native String decrypt(String str);

	public static void main(String[] args) {
		HelloWorld hw = new HelloWorld();
		String result = hw.getStringFromC("aa");
		System.out.println("result:" + result);

		System.out.println("calculate result:" + hw.calculate(666, 5));

		System.out.println("key修改前：" + hw.key);
		String new_str = hw.accessField();
		System.out.println("access Field:" + new_str);
		System.out.println("key修改后：" + hw.key);

		System.out.println("count修改前：" + count);
		int new_count = hw.accessStaticField();
		System.out.println("new_count：" + new_count);
		System.out.println("count修改后：" + count);

		int ret = hw.accessMethod();
		System.out.println("调用method之后返回值：" + ret);

		String str_resujlt = hw.accessStaticMethod();
		System.out.println("调用static method之后返回值：" + str_resujlt);

		Date date = hw.accessConstructor();
		System.out.println("调用construct method之后返回值：" + date.toString());

		hw.accessNonvirtualMethod();
		// System.out.println("调用父类 method");

		String c_str = hw.chineseChars("我是来自java的中文");
		System.out.println("调用chineseChars返回：" + c_str);

		int intArray[] = { 44, 21, 2, 13, 53, 12, 55, 66, 77 };
		// 进行排序
		hw.giveArray(intArray);
		System.out.println("排序之后的数组是：");
		for (int a : intArray) {
			System.out.print(a + ",");
		}

		int getArray[] = hw.getArray(10);
		System.out.println("获取c++数组：");
		for (int a : getArray) {
			System.out.print(a + ",");
		}

		System.out.println("调用局部引用");
		hw.localRef();

		System.out.println("调用全局引用");
		hw.createGlobalRef();
		System.out.println("------创建全局引用------");
		String global_str = hw.getGlobalRef();
		System.out.println("------获取全局引用------" + global_str);
		hw.deleteGlobalRef();
		System.out.println("------释放全局引用------");
		global_str = hw.getGlobalRef();
		// System.out.println("------获取全局引用------"+global_str);

		try {
			hw.exception();
			System.out.println("java中完成异常信息");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 缓存策略
		for (int i = 0; i < 10; i++) {
			hw.cached();
		}

		
		//加密解密
		String old_str="hahha,I am hanlonglin";
		System.out.println("原始字符串："+old_str);
		String after_encrypt_str=hw.encrypt(old_str);
		System.out.println("加密后字符串："+after_encrypt_str);
		//String after_decrypt_str=hw.decrypt(after_encrypt_str);
		String after_decrypt_str=hw.decrypt("jaWRdH0BVez+AyufHgIglGyLxFtn");
		System.out.println("解密后字符串："+after_decrypt_str);
	}

	public int generateRandom() {
		System.out.println("generateRandom 执行了...");
		return new Random().nextInt(10);
	}

	public static String getUUID() {
		System.out.println("getUUID 执行了...");
		return UUID.randomUUID().toString();
	}

	static {
		System.load("E:\\projects\\Visual_Studio_2015\\JniTest\\x64\\Release\\JniTest.dll");
		initIDs();
	}
}
