package com.redis.guide.springbootredis.repository;

import com.redis.guide.springbootredis.model.Coder;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CoderRepository {

    void setCoderAsString(String id, String coderDetails);

    String getCodesAsString(String id);

    void addCoders(Coder coder);

    List<Coder> getAllCoders();


    Long getCodersCount();


    void addToCoderSet(Coder coder);

    Set<Coder> getAllCoderSet();


    Boolean isSetMember(Coder coder);

    void saveHash(Coder coder);
    void updateHash(Coder coder);

    Map<Integer,Coder> findAllHash();

    Coder findInHash(Integer id);

    void deleteHash(Integer id);
}
