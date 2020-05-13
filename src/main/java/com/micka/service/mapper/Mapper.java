package com.micka.service.mapper;

public interface Mapper<D,E> {

    public D mapEntityToDomain(E e);

    public E mapDomainToEntity(D d);

}
