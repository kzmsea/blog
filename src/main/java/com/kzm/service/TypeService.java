package com.kzm.service;

import com.github.pagehelper.PageInfo;
import com.kzm.entity.Type;
import java.util.List;

public interface TypeService {

    int saveType(Type type);

    Type getType(Long id);

    PageInfo<Type> listType(int pageNum, int pageSize);

    List<Type> listType();

    int updateType(Type type);

    int removeType(Long id);

    List<Type> listTypeTop(int size);

}
