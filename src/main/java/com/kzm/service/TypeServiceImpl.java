package com.kzm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kzm.dao.TypeRepository;
import com.kzm.entity.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public int saveType(Type type) {
        return typeRepository.saveType(type);
    }

    @Override
    public Type getType(Long id) {
        return typeRepository.getType(id);
    }

    @Override
    public PageInfo<Type> listType(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Type> types = typeRepository.listType();
        return new PageInfo<Type>(types);
    }

    @Override
    public List<Type> listType(){
        return typeRepository.listType();
    }

    @Override
    public int updateType(Type type) {
        return typeRepository.updateType(type);
    }

    @Override
    public int removeType(Long id) {
        return typeRepository.removeType(id);
    }

    @Override
    public List<Type> listTypeTop(int size) {
        List<Type> types = typeRepository.listType();
        Type type=null;
        /*for(int i=0;i<types.size();i++){
            for(int j=0;j<types.size()-i-1;j++){
                if(types.get(j).getBlogs().size()<types.get(j+1).getBlogs().size()){
                    type=types.get(j);
                    types.add(j,types.get(j+1));
                    types.add(j+1,type);
                }
            }
        }*/
        Collections.sort(types,Collections.reverseOrder(new Comparator<Type>() {
            @Override
            public int compare(Type o1, Type o2) {
                if(o1.getBlogs().size()>o2.getBlogs().size()){
                    return 1;
                }
                return -1;
            }
        }));
//        List<Type> types1 = new ArrayList<Type>(types);
//        Collections.copy(types,types1);
//        BeanUtils.copyProperties(types,types1);
       /* for(Type type1:types1){
            types.remove(type1);
        }*/
        List<Type> types2 = types.subList(size, types.size());
        System.out.println(types.removeAll(types2));
        return types;
    }
}
