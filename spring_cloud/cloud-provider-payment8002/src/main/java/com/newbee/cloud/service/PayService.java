package com.newbee.cloud.service;

import com.newbee.cloud.entities.Pay;

import java.util.List;
public interface PayService {
    public int add(Pay pay);
    public int delect(Integer id);
    public int update(Pay pay);

    public Pay getById(Integer id);
    public List<Pay> getAll();
}
