/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unittest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jimstewart
 */
public class Tester {
    private final Class<?> methodClass;
    private final String methodName;
    private final Object[] constructorArgs;
    public Tester(Class<?> methodClass, String methodName, Object[] constructorArgs, Object[] methodArgs, Object returnExpected){
        this.methodClass = methodClass;
        this.methodName = methodName;
        this.constructorArgs = constructorArgs;
      }
    
    private boolean testMethod(Object[] methodArgs, Object returnExpected, byte testNumber){
        try{
            Constructor ctor = methodClass.getConstructor((Class<?>[]) constructorArgs);
            Object methodObject = ctor.newInstance(constructorArgs);
            
            Method m = methodObject.getClass().getMethod(methodName, (Class[])methodArgs);
            Object objectReturned = m.invoke(methodObject, methodArgs);
            if(objectReturned.equals(returnExpected)){
                output("Success on test number  "+ testNumber + "!" +
                        " Method " + methodName + 
                        " of class " + methodClass.getName() + 
                        " works as expected.");
                return true;
            } else {
                output("Incorrect return from method " + methodName + 
                        " of class " + methodClass.getName() + ". " +
                        "on trial number + " + testNumber + 
                        "expected output + " + returnExpected.toString() +
                        "but received output  " + objectReturned.toString());
                return false;
            }
        } catch (NoSuchMethodException ex){
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
            alert("Your object does not have a method named " + 
                    methodName + ". Check to make sure your method is " +
                    " named correctly. " +
                    " Exception returned: " + ex);
        } catch (InstantiationException ex) {
            alert("Could not create an instance of the class " + methodClass + 
                    ". Make sure your class is named correctly. " +
                    " Exception returned: " + ex);
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            alert("Couldn't access the object of the class " + methodClass +
                    ". Make sure your class and method are public. " +
                    "Exception: " + ex);
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            String alertString = "Constructor or method doesn't accept the correct arguments." +
                    " Your constructor should accept the following arguments: (";
           for(int i=0; i<constructorArgs.length;i++){
               alertString.concat(constructorArgs[i].getClass().toString());
               if(i<constructorArgs.length-1)
                   alertString.concat(", ");
           }
                    
            alertString.concat(" Exception: " + ex);
            alert(alertString);
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            alert("Could not create the object. Exception: " + ex.getCause());
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
   }
    private void output(String s){
        System.out.println(s);
    }
    
    private void alert(String s){
        System.err.println(s);
    }
}
