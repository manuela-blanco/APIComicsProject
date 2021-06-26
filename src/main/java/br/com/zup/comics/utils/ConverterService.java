package br.com.zup.comics.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class ConverterService {

	private static final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
	
    public <T, Y> Y convert(T source, Class<Y> targetClass) {

        if (source == null) {
            return null;
        }
        return mapperFactory.getMapperFacade().map(source, targetClass);
    }
    
    public <T, Y> List<Y> convertList(Collection<T> sourceCollection, Class<Y> targetClass) {

        if (sourceCollection == null) {
            return null;
        }
        if (sourceCollection.isEmpty()) {
            return Collections.<Y>emptyList();
        }
        return mapperFactory.getMapperFacade().mapAsList(sourceCollection, targetClass);
    }

}
