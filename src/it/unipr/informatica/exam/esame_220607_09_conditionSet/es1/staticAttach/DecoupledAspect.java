package it.unipr.informatica.exam.esame_220607_09_conditionSet.es1.staticAttach;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DecoupledAspect {
    
	private static ExecutorService executor = Executors.newCachedThreadPool();

    @SuppressWarnings("unchecked")
	public static <T> T attach(T object) {
        
    	Class<?>[] interfaces = object.getClass().getInterfaces();
        
    	InvocationHandler handler = new InvocationHandler() {
            
    		@Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        		System.out.println("\nattach()");
    			
    			return executor.submit(() -> method.invoke(object, args)).get();
            }
        };
                
        return (T) Proxy.newProxyInstance(object.getClass().getClassLoader(), interfaces, handler);
    }
    
    public static void shutdown() {
        executor.shutdown();
    }    
}


