package it.unipr.informatica.exam.esame_220607_09_conditionSet.es1.dynamicAttach;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DecoupledAspect<T> {
    
	private static ExecutorService executor;
	private T object;
	
	public DecoupledAspect(T object) {
		this.object = object;
		executor = Executors.newCachedThreadPool();
	}

    
	@SuppressWarnings("unchecked")
	public T attach() {
                
    	InvocationHandler handler = new InvocationHandler() {
            
    		@Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                
    			System.out.println("\nattach()");
                
    			// notare il metodo get() applicato al submit()
    			Object result = executor.submit(() -> method.invoke(object, args)).get();
				
    			return result;
            }
    		
        };
                
        return (T) Proxy.newProxyInstance(
        		object.getClass().getClassLoader(), 
        		object.getClass().getInterfaces(),
        		handler);
    }
    
    public static  void shutdown() {
        executor.shutdown();
    }
    
}


