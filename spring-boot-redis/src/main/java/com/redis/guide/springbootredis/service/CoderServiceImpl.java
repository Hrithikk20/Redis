package com.redis.guide.springbootredis.service;

import com.redis.guide.springbootredis.model.Coder;
import com.redis.guide.springbootredis.repository.CoderRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CoderServiceImpl implements CoderService {

    @Autowired
    CoderRepository coderRepository;
    @Override
    public void setCoderAsString(String id, String coder) {
        coderRepository.setCoderAsString(id,coder);
    }

    @Override
    public String getCoderAsString(String key) {
        return coderRepository.getCodesAsString(key);
    }

    @Override
    public void addCoders(Coder coder) {
        coderRepository.addCoders(coder);
    }

    @Override
    public List<Coder> getAllCoders() {
        return coderRepository.getAllCoders();
    }

    @Override
    public Long getCodersCount() {
        return coderRepository.getCodersCount();
    }

    @Override
    public void addToCoderSet(Coder coder) {
        coderRepository.addToCoderSet(coder);
    }

    @Override
    public Set<Coder> getAllCoderSet() {
        return coderRepository.getAllCoderSet();
    }

    @Override
    public Boolean isSetMember(Coder coder) {
        return coderRepository.isSetMember(coder);
    }

    @Override
    public void saveCHash(Coder coder) {
        coderRepository.saveHash(coder);
    }

    @Override
    public void updateCHash(Coder coder) {
        coderRepository.updateHash(coder);
    }

    @Override
    public Map<Integer, Coder> findAllCHash() {
        return coderRepository.findAllHash();
    }

    @Override
    public Coder findCInHash(int id) {
        return coderRepository.findInHash(id);
    }

    @Override
    public void deleteCHash(int id) {
        coderRepository.deleteHash(id);
    }
}
