package com.kzm.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

//@SpringBootTest
class BlogApplicationTests {

    @Test
    void contextLoads() {
        int[] nums={2,2,3};
        List<Integer> list=Arrays.stream(nums).boxed().collect(Collectors.toList());
        List<Integer> list2=new ArrayList();
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]==nums[j]){
                    list2.add(nums[j]);
                }
            }
        }
        list.removeAll(list2);
        System.out.println(list.get(0));
    }

}
