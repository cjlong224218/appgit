package com.youceedu.inter.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class AnnotationTransformerImpl implements IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
		IRetryAnalyzer retry=annotation.getRetryAnalyzer();//IRetryAnalyzerҪ��ʵ�֣�ʵ�ֺ���л�������ʧ������
		annotation.setRetryAnalyzer(IRetryAnalyzerImpl.class);
	}

}
