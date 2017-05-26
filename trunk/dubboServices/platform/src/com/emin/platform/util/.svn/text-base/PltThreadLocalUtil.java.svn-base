package com.emin.platform.util;

import com.emin.base.util.ThreadLocalUtil;
import com.emin.platform.domain.Company;
import com.emin.platform.domain.Person;



public class PltThreadLocalUtil extends ThreadLocalUtil {
	
	/**
     * 当前用户
     */
    private static final ThreadLocal<Person> personLocal = new ThreadLocal<Person>();  
   
    /**
     * 当前用户所属公司
     */
    private static final ThreadLocal<Company> companyLocal = new ThreadLocal<Company>(); 


    private static final ThreadLocal<Boolean> isCompositive = new ThreadLocal<Boolean>();
    
    public static void setPerson(Person person){
    	personLocal.set(person);
    }
    public static Person getPerson(){
      
    	return (Person)personLocal.get();
    }
    
    public static void setCompany(Company company){
    	companyLocal.set(company);
    }
    public static Company getCompany(){
    	return (Company)companyLocal.get();
    }
    public static void destory(){
    	personLocal.remove();
    	companyLocal.remove();
    	isCompositive.remove();
    }
    public static void setCompositive(Boolean compositive){
    	isCompositive.set(compositive);
    }
    public static Boolean isCompositive(){
    	return isCompositive.get();
    }
   /* public static void setUser(User user){
    	userLocal.set(user);
    }    
    
    public static User getUser(){
//    	System.out.println("Current Thread Hash Code:"+Thread.currentThread().hashCode());
    	return (User)userLocal.get();
    }

    public static void setCompany(Company company){
    	companyLocal.set(company);
    }
    
    *//**
     * 当前用户所在的company
     *//*
    public static Company getCompany(){
    	return (Company)companyLocal.get();
    }*/
    
   
    
   
    
//    private  void setUserAuthSessionCache(UserAuthSessionCache cache){
//    	userAuthSessionCacheLocal.set(UserAuthSessionCache.getInstance());
//    }
//    
//    public static UserAuthSessionCache getUserAuthSessionCache(){
//    	return userAuthSessionCacheLocal.get();
//    }
    
  
    
    
}
