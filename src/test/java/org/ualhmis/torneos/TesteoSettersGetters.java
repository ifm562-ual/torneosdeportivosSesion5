package org.ualhmis.torneos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class TesteoSettersGetters<T> {
	
	public void parteComunMetodoTesteo(T obj, HashMap<Class<?>, Object> valoresTest) {
		
        for(Method setter : obj.getClass().getDeclaredMethods()) 
        {
        	if(setter.getName().startsWith("set") && setter.getParameterCount() == 1)
        	{
        		Class<?> tipoParametro = setter.getParameterTypes()[0];
        		Object valorAColocar = valoresTest.get(tipoParametro);
        		
        		try { setter.invoke(obj, valorAColocar);
				} catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
        		
        		try {
        			Object valorRealObtenido = obj.getClass().getMethod( "get" + setter.getName().substring(3) ).invoke(obj);
        			assertEquals(valorRealObtenido, valorAColocar);
        		}catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
        	}
        }
	}
}
