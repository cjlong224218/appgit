package com.youceedu.inter.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class AnnotationTransformerImpl implements IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
		IRetryAnalyzer retry=annotation.getRetryAnalyzer();//IRetryAnalyzer要被实现，实现后就有机会重试失败用例
		annotation.setRetryAnalyzer(IRetryAnalyzerImpl.class);
	}

}
