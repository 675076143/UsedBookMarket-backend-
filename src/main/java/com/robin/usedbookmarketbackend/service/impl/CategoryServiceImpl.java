package com.robin.usedbookmarketbackend.service.impl;

import com.robin.usedbookmarketbackend.mapper.CategoryMapper;
import com.robin.usedbookmarketbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<String> getAllCategories() {
        return categoryMapper.findCategoryname();
    }
}
