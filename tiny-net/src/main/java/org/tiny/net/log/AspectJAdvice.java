package org.tiny.net.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectJAdvice {
	/** 
     * Pointcut 
     * 定义Pointcut，Pointcut的名称为aspectjMethod()，此方法没有返回值和参数 
     * 该方法就是一个标识，不进行调用 
     */  
    @Pointcut("execution(* logAspect*(..))")  
    private void aspectjMethod(){};  
      
    /**  
     * Before 
     * 在核心业务执行前执行，不能阻止核心业务的调用。 
     * @param joinPoint  
     */    
    @Before("aspectjMethod()")    
    public void doBefore(JoinPoint joinPoint) {    
//        System.out.println("-----doBefore.invoke-----");  
//        System.out.println(" 此处意在执行核心业务逻辑前，做一些安全性的判断等等");  
//        System.out.println(" 可通过joinPoint来获取所需要的内容");  
//        System.out.println("-----End of doBefore()------");  
    }  
      
      
  
    /**  
     * Around  
     * 手动控制调用核心业务逻辑，以及调用前和调用后的处理, 
     *  
     * 注意：当核心业务抛异常后，立即退出，转向AfterAdvice 
     * 执行完AfterAdvice，再转到ThrowingAdvice 
     * @param pjp 
     * @return 
     * @throws Throwable 
     */   
    @Around(value = "aspectjMethod()")    
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {    
//        System.out.println("-----doAround.invoke-----");  
//        System.out.println(" 此处可以做类似于Before的事情");  
//          
//        //调用核心逻辑  
        Object retVal = pjp.proceed();  
//        System.out.println(" 此处可以做类似于After的事情");  
//        System.out.println("-----End of doAround()------");  
        return retVal;  
    }    
      
      
    /**  
     * After  
     * 核心业务逻辑退出后（包括正常执行结束和异常退出），执行此Advice 
     * @param joinPoint 
     */  
    @After(value = "aspectjMethod()")    
    public void doAfter(JoinPoint joinPoint) {    
        System.out.println("-----doAfter.invoke-----");  
//        System.out.println(" 此处意在执行核心业务逻辑之后，做一些日志记录操作等等");  
//        System.out.println(" 可通过joinPoint来获取所需要的内容");  
//        System.out.println("-----End of doAfter()------");  
    }    
      
    /**  
     * AfterReturning  
     * 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行此Advice 
     * @param joinPoint 
     */   
    @AfterReturning(value = "aspectjMethod()", returning = "retVal")    
    public void doReturn(JoinPoint joinPoint, String retVal) {    
//        System.out.println("-----doReturn().invoke-----");  
//        System.out.println("Return Value: " + retVal);   
//        System.out.println(" 此处可以对返回值做进一步处理");  
//        System.out.println(" 可通过joinPoint来获取所需要的内容");  
//        System.out.println("-----End of doReturn()------");  
    }  
      
    /** 
     * 核心业务逻辑调用异常退出后，执行此Advice，处理错误信息 
     *  
     * 注意：执行顺序在Around Advice之后 
     * @param joinPoint 
     * @param ex 
     */  
    @AfterThrowing(value = "aspectjMethod()", throwing = "ex")    
    public void doThrowing(JoinPoint joinPoint, Exception ex) {    
        System.out.println("-----doThrowing().invoke-----");  
        System.out.println(" 错误信息："+ex.getMessage());  
        System.out.println(" 此处意在执行核心业务逻辑出错时，捕获异常，并可做一些日志记录操作等等");  
        System.out.println(" 可通过joinPoint来获取所需要的内容");  
        System.out.println("-----End of doThrowing()------");    
    }  
}
