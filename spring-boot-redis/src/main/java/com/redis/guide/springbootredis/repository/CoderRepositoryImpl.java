package com.redis.guide.springbootredis.repository;

import com.redis.guide.springbootredis.model.Coder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository
public class CoderRepositoryImpl implements CoderRepository{

    @Autowired
    private HashOperations<String,Integer,Coder> hashOps;
    @Autowired
    private SetOperations<String,Coder>  setOps;
    @Autowired
    private ListOperations<String,Coder> listOps;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public static final String REDIS_LIST_KEY="coderList";
    public static final String REDIS_SET_KEY="coderSet";
    public static final String REDIS_HASH_KEY="coderHash";
    @Override
    public void setCoderAsString(String id, String coderDetails) {
        redisTemplate.opsForValue().set(id,coderDetails);
        redisTemplate.expire(id,10, TimeUnit.SECONDS);
    }

    @Override
    public String getCodesAsString(String id) {
        return (String) redisTemplate.opsForValue().get(id);
    }

    @Override
    public void addCoders(Coder coder) {
        listOps.leftPush(REDIS_LIST_KEY,coder);
    }

    @Override
    public List<Coder> getAllCoders() {
        return listOps.range(REDIS_LIST_KEY,0,-1);
    }

    @Override
    public Long getCodersCount() {
        return listOps.size(REDIS_LIST_KEY);
    }

    @Override
    public void addToCoderSet(Coder coder) {
        setOps.add(REDIS_SET_KEY,coder);
    }

    @Override
    public Set<Coder> getAllCoderSet() {
        return setOps.members(REDIS_SET_KEY);
    }

    @Override
    public Boolean isSetMember(Coder coder) {
        return setOps.isMember(REDIS_SET_KEY,coder);
    }

    @Override
    public void saveHash(Coder coder) {
        hashOps.put(REDIS_HASH_KEY,coder.getId(),coder);
    }

    @Override
    public void updateHash(Coder coder) {
        hashOps.put(REDIS_HASH_KEY,coder.getId(),coder);
    }

    @Override
    public Map<Integer, Coder> findAllHash() {
        return hashOps.entries(REDIS_HASH_KEY);
    }

    @Override
    public Coder findInHash(Integer id) {
        return hashOps.get(REDIS_HASH_KEY,id);
    }

    @Override
    public void deleteHash(Integer id) {
        hashOps.delete(REDIS_HASH_KEY,id);
    }
}
