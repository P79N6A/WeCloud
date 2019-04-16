package com.bat.other;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: caoke
 * @Date: 2019/4/15 12:04
 * @Version 1.0
 */
@Slf4j
public class SetTes {

    @Test
    public void testjiaoji(){
        Set<Long> s1 = new HashSet<Long>(){{
            add(1L);
            add(2L);
            add(3L);
        }};

        Set<Long> s4 = new HashSet<Long>(){{
            add(2L);
        }};

        Set<Integer> s2 = new HashSet<Integer>(){{
            add(1);
            add(2);
            add(3);
        }};
        Set<Long> s3 = s2.stream().map(s->s+"").map(Long::getLong).collect(Collectors.toSet());

        Set<Long> s5 = new HashSet<>();
        for(Integer a:s2){
            s5.add(Long.valueOf(a+""));
        }

        Set<Long> result = new HashSet<>();
        result.addAll(s1);
        result.retainAll(s5);
        log.info(JSONObject.toJSONString(result));

        log.info("{}",result.iterator().next());
    }
}
