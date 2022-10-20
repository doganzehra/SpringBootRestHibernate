package com.bilgeadam.SpringBootRestHibernate.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

// @Aspect
// @Component
// cross cutting concern
public class AspectOrientedClass
{
	// @Pointcut(value = "execution(public * *(..))")

	// all implementations of interface
	// within(JPARepositoryImpl+)

//	@PostConstruct
//	public void asd()
//	{
//		System.err.println("Aspect sınıfı new yapıldı");
//	}

	// case sensitive
	@Before(value = "execution(* com.bilgeadam.SpringBootRestHibernate.controller.OgrenciController.*get*(..))")
	public void icindeGetGecenler()
	{
		System.err.println("Metod adı içinde get geçiyor");
	}

	@Before(value = "within(com.bilgeadam.SpringBootRestHibernate.controller.*)")
	private void allMethodsInsideControllerPackageBefore()
	{
		System.err.println("Before all methods of all classes of com.bilgeadam.SpringBootRestHibernate.controller package");
	}

	// bu metod ogretmen controller 'da herhangi bir metod çalıştığında tetiklenecek
	@Pointcut(value = "execution(* com.bilgeadam.SpringBootRestHibernate.controller.OgretmenController.*(..))")
	public void ogretmenControllerCalisti()
	{
		// burası çalışmaz
		System.err.println("Öğretmen controller 'dan bir metod çalıştı");
	}

	// kesişim noktasının hem öncesi hem sonrası
	@Around(value = "ogretmenControllerCalisti()")
	public Object aroundOgretmenControllerCalisti(ProceedingJoinPoint point)
	{
		System.err.println("Around Öğretmen controller 0");
		Object returnValue = null;
		try
		{
			// before after burada çalışıyor
			long startTime = System.currentTimeMillis();
			returnValue = point.proceed();
			long endtime = System.currentTimeMillis();
			System.err.println("Class Name: " + point.getSignature().getDeclaringTypeName() + ". Method Name: " + point.getSignature().getName() + ". Time taken for Execution is : " + (endtime - startTime) + "ms");
		}
		catch (Throwable e)
		{
		}
		System.err.println("Around Öğretmen controller 1");
		return returnValue;
	}

	// kesişim noktasına gelmeden önce
	@Before(value = "ogretmenControllerCalisti()")
	public void beforeOgretmenControllerCalisti(JoinPoint jp)
	{
		System.err.println("Öğretmen controller 'dan bir metod çalışacak " + jp.getSignature().getName());
	}

	// kesişim noktasından sonra
	@After(value = "ogretmenControllerCalisti()")
	public void afterOgretmenControllerCalisti(JoinPoint jp)
	{
		System.err.println("Öğretmen controller 'dan bir metod çalıştı " + jp.getSignature().getName());
	}
}