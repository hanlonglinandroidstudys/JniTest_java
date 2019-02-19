package com.jni.test;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class HelloWorld {

	Humen humen = new man();

	public native String getStringFromC(String text);

	public native int calculate(int a, int b);

	// ����java����
	// �޸ĳ�Ա����
	public String key = "hanlonglin";

	public native String accessField();

	// �޸ľ�̬����
	public static int count = 0;

	public native int accessStaticField();

	// ����java����
	// ��Ա����
	public native int accessMethod();

	// ��̬����
	public native String accessStaticMethod();

	// ���췽��
	public native Date accessConstructor();

	// ���෽��
	public native void accessNonvirtualMethod();

	// �������� ��������
	public native String chineseChars(String in);

	// ��������
	public native void giveArray(int[] intArray);

	// ��������
	public native int[] getArray(int len);

	// �ֲ�����
	public native void localRef();

	// ȫ������
	// ����
	public native void createGlobalRef();

	// ��ȡ
	public native String getGlobalRef();

	// �ͷ�
	public native void deleteGlobalRef();

	// �쳣����
	public native void exception();

	// �������
	public native void cached();
	
	//��ʼ��ȫ�ֱ���
	public native static void initIDs();
	
	//���ܽ���
	//����ʹ��c++��ʵ�ּ��ܽ���
	public native String encrypt(String str);
	
	public native String decrypt(String str);

	public static void main(String[] args) {
		HelloWorld hw = new HelloWorld();
		String result = hw.getStringFromC("aa");
		System.out.println("result:" + result);

		System.out.println("calculate result:" + hw.calculate(666, 5));

		System.out.println("key�޸�ǰ��" + hw.key);
		String new_str = hw.accessField();
		System.out.println("access Field:" + new_str);
		System.out.println("key�޸ĺ�" + hw.key);

		System.out.println("count�޸�ǰ��" + count);
		int new_count = hw.accessStaticField();
		System.out.println("new_count��" + new_count);
		System.out.println("count�޸ĺ�" + count);

		int ret = hw.accessMethod();
		System.out.println("����method֮�󷵻�ֵ��" + ret);

		String str_resujlt = hw.accessStaticMethod();
		System.out.println("����static method֮�󷵻�ֵ��" + str_resujlt);

		Date date = hw.accessConstructor();
		System.out.println("����construct method֮�󷵻�ֵ��" + date.toString());

		hw.accessNonvirtualMethod();
		// System.out.println("���ø��� method");

		String c_str = hw.chineseChars("��������java������");
		System.out.println("����chineseChars���أ�" + c_str);

		int intArray[] = { 44, 21, 2, 13, 53, 12, 55, 66, 77 };
		// ��������
		hw.giveArray(intArray);
		System.out.println("����֮��������ǣ�");
		for (int a : intArray) {
			System.out.print(a + ",");
		}

		int getArray[] = hw.getArray(10);
		System.out.println("��ȡc++���飺");
		for (int a : getArray) {
			System.out.print(a + ",");
		}

		System.out.println("���þֲ�����");
		hw.localRef();

		System.out.println("����ȫ������");
		hw.createGlobalRef();
		System.out.println("------����ȫ������------");
		String global_str = hw.getGlobalRef();
		System.out.println("------��ȡȫ������------" + global_str);
		hw.deleteGlobalRef();
		System.out.println("------�ͷ�ȫ������------");
		global_str = hw.getGlobalRef();
		// System.out.println("------��ȡȫ������------"+global_str);

		try {
			hw.exception();
			System.out.println("java������쳣��Ϣ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �������
		for (int i = 0; i < 10; i++) {
			hw.cached();
		}

		
		//���ܽ���
		String old_str="hahha,I am hanlonglin";
		System.out.println("ԭʼ�ַ�����"+old_str);
		String after_encrypt_str=hw.encrypt(old_str);
		System.out.println("���ܺ��ַ�����"+after_encrypt_str);
		//String after_decrypt_str=hw.decrypt(after_encrypt_str);
		String after_decrypt_str=hw.decrypt("jaWRdH0BVez+AyufHgIglGyLxFtn");
		System.out.println("���ܺ��ַ�����"+after_decrypt_str);
	}

	public int generateRandom() {
		System.out.println("generateRandom ִ����...");
		return new Random().nextInt(10);
	}

	public static String getUUID() {
		System.out.println("getUUID ִ����...");
		return UUID.randomUUID().toString();
	}

	static {
		System.load("E:\\projects\\Visual_Studio_2015\\JniTest\\x64\\Release\\JniTest.dll");
		initIDs();
	}
}
