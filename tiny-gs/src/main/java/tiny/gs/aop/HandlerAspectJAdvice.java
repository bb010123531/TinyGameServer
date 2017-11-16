package tiny.gs.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class HandlerAspectJAdvice {
	
//	@Pointcut("execution(* process*(..))")
//	private void handlerProcess(){};
	@Pointcut("execution(* p*(..))")  
    private void handlerProcess(){};  
	
	@After(value = "handlerProcess()")    
    public void afterProcess(JoinPoint joinPoint) {    
        System.out.println("-----doAfter.invoke-----");  
        System.out.println(" 此处意在执行核心业务逻辑之后，做一些日志记录操作等等");  
        System.out.println(" 可通过joinPoint来获取所需要的内容");  
        System.out.println("-----End of doAfter()------");  
	}

	@Around(value = "handlerProcess()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("-----doAround.invoke-----");
		System.out.println(" 此处可以做类似于Before的事情");

		// 调用核心逻辑
		Object retVal = pjp.proceed();
		System.out.println(" 此处可以做类似于After的事情");
		System.out.println("-----End of doAround()------");
		return retVal;
	}
}
