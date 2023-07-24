package it.unipr.informatica.exam.esame_220607_09_conditionSet.es2;

public class AImpl implements A {
    
	public void ma() throws InterruptedException {
		
		System.out.println("Creating 3 objects of BImpl and calling mb1 on each of them");
		
		BImpl[] objects = new BImpl[3];
                
		for (int i = 0; i < objects.length; i++) {
            objects[i] = new BImpl();
            
            System.out.println("\n" + Thread.currentThread().getName() 
            		+ ": Chiamata-" + (i+1) + ": ma() chiama mb1()");
            
            Object lock = objects[i].mb1();  // Bimpl.mb1()
		
            System.out.println(Thread.currentThread().getName() + 
            		": ritorno in ma() da mb1.");  // mb1 ha gia finito..., ma ha fatto partire mb2 il quale svegliera il lock
            
            synchronized (lock) {
            	System.out.println(Thread.currentThread().getName() +  
            			": ma() va in lock.wait(), aspetta che mb2() finisca e notifichi");
    			
            	lock.wait();	// aspetta che mb2 finisca (mb1 ha gia finito) ( mb2 fa lock.notify)
    			
    			System.out.println(Thread.currentThread().getName() + 
                		": ma() esce da lock.wait()");
    		}
        }
		
      System.out.println(Thread.currentThread().getName() +": ma() finished");
    }
}
