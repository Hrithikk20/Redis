package com.redis.guide.springbootredis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.guide.springbootredis.model.Coder;
import com.redis.guide.springbootredis.service.CoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class CoderContoller {

    @Autowired
    CoderService coderService;

    @PostMapping("/coder")
    public void addCoder(@RequestBody Coder coder) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        coderService.setCoderAsString(coder.getId().toString(),objectMapper.writeValueAsString(coder));
    }

    @GetMapping("/coder/{id}")
    public String getCoder(@PathVariable String id){
        return coderService.getCoderAsString(id);
    }

    @PostMapping("/coders-list")
    public void addToCoderList(@RequestBody Coder coder){
        coderService.addCoders(coder);
    }

    @GetMapping("/coders-list")
    public List<Coder> getCodersList(){
        return coderService.getAllCoders();
    }

    @GetMapping("/coders-list/count")
    public Long getCount(){
        return coderService.getCodersCount();
    }

    @PostMapping("/coders-set")
    public void addToCoderSet(@RequestBody Coder coder){
        coderService.addToCoderSet(coder);
    }

    @GetMapping("/coders-set")
    public Set  <Coder> getCoderSet(){
        return coderService.getAllCoderSet();
    }

    @PostMapping("/coders-set/member")
    public Boolean isSetMember(@RequestBody Coder coder){
        return coderService.isSetMember(coder);
    }

    @PostMapping("/coders-hash")
    public void saveCoderHash(@RequestBody Coder coder){
        coderService.saveCHash(coder);
    }

    @PutMapping("/coders-hash")
    public void updateCoderHash(@RequestBody Coder coder){
        coderService.updateCHash(coder);
    }

    @GetMapping("/coders-hash")
    public Map<Integer,Coder> getAllCoderHash(){
        return coderService.findAllCHash();
    }

    @GetMapping("/coders-hash/{id}")
    public Coder findCoderHash(@PathVariable Integer id){
        return coderService.findCInHash(id);
    }

    @DeleteMapping("/coders-hash/{id}")
    public void deleteCoderHash(@PathVariable Integer id){
        coderService.deleteCHash(id);
    }
}
