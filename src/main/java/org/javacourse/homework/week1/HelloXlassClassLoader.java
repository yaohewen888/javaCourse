package org.javacourse.homework.week1;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloXlassClassLoader extends ClassLoader {
	
	private byte[] InputStream2ByteArray(String filePath) throws IOException {
		 
	    InputStream in = new FileInputStream(filePath);
	    byte[] data = toByteArray(in);
	    in.close();
	 
	    return data;
	}
	 
	private byte[] toByteArray(InputStream in) throws IOException {
	 
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    byte[] buffer = new byte[1024 * 4];
	    int n = 0;
	    while ((n = in.read(buffer)) != -1) {
	        out.write(buffer, 0, n);
	    }
	    return out.toByteArray();
	}
	
	private static byte[] decode(byte[] byteArray) {
        byte[] targetArray = new byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            targetArray[i] = (byte) (255 - byteArray[i]);
        }
        return targetArray;
    }
	
	@Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] bytes = null;
		try {
			bytes = InputStream2ByteArray("D:/project/workspace/git/javaCourse/Hello.xlass");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] classBytes = decode(bytes);
		return defineClass(name, classBytes, 0, classBytes.length);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		// ��ز���
        final String className = "Hello";
        final String methodName = "hello";
        // �����������
        ClassLoader classLoader = new HelloXlassClassLoader();
        // ������Ӧ����
        Class<?> clazz = classLoader.loadClass(className);
        // ����������Щʲô����
        for (Method m : clazz.getDeclaredMethods()) {
            System.out.println(clazz.getSimpleName() + "." + m.getName());
        }
        // ��������
        Object instance = clazz.getDeclaredConstructor().newInstance();
        // ����ʵ������
        Method method = clazz.getMethod(methodName);
        method.invoke(instance);
	}
}
