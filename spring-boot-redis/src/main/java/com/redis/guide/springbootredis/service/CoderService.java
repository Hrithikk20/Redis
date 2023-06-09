package com.redis.guide.springbootredis.service;

import com.redis.guide.springbootredis.model.Coder;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CoderService {

    void setCoderAsString(String id,String coder);

    String getCoderAsString(String key);

    void addCoders(Coder coder);

    List<Coder> getAllCoders();


    Long getCodersCount();

    void addToCoderSet(Coder coder);

    Set<Coder> getAllCoderSet();


    Boolean isSetMember(Coder coder);


    void saveCHash(Coder coder);
    void updateCHash(Coder coder);

    Map<Integer,Coder> findAllCHash();

    Coder findCInHash(int id);

    void deleteCHash(int id);
}
