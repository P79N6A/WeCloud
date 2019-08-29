package com.bat.other;

import com.alibaba.fastjson.JSONObject;
import com.bat.domain.we.MiaoShaUser;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: caoke
 * @Date: 2019/7/9 10:10
 * @Version 1.0
 */
public class FilterTest {
    public static void main(String[] args) {
//        List<MiaoShaUser> list = Lists.newArrayList();
//        for(long i =1;i<11;i++){
//            MiaoShaUser user = new MiaoShaUser();
//            user.setId(i);
//            user.setName("name_"+i);
//            list.add(user);
//        }
//        long flag = list.stream().filter(s->{if(s.getId().equals(5L)){ return true;}return false;}).count();
//        System.out.println("flag:"+flag);

//        String name = "a_b";
//        String[] a = name.split("_");
//        StringBuilder sb = new StringBuilder();
//        for(int i=0;i<a.length;i++){
//            if(i==0){
//                sb.append(a[i]);
//            }else {
//                sb.append(a[i].substring(0,1).toUpperCase()+a[i].substring(1));
//            }
//        }
//        System.out.println(sb);

        R r1 = new R();
        r1.setA(0);
        r1.setB(1);
        R r2 = new R();
        r2.setA(1);
        r2.setB(21);
        R r21 = new R();
        r21.setA(1);
        r21.setB(22);
        R r31 = new R();
        r31.setA(21);
        r31.setB(31);
        R r41 = new R();
        r41.setA(22);
        r41.setB(32);

        List<R> rs = new ArrayList<R>(){{add(r1);add(r2);add(r21);add(r31);add(r41);}};

        A first = new A();
        Map<Integer,List<Integer>> group = Maps.newHashMap();
        for(R r:rs){
            if(r.getA() == 0){
                first.setA(r.getB());
            }
            List<Integer> list = group.get(r.getA());
            if(CollectionUtils.isEmpty(list)){
                list = Lists.newArrayList();
            }
            list.add(r.getB());
            group.put(r.getA(),list);
        }

        recursive(first,group);
        System.out.println(JSONObject.toJSONString(first));
    }

    public static void recursive(A a,Map<Integer,List<Integer>> group){
        if(CollectionUtils.isEmpty(group.get(a.getA()))){
            a.setChildren(new ArrayList<>());
            return;
        }
        for(Integer i:group.get(a.getA())){
            A a1 = new A();
            a1.setA(i);
            recursive(a1,group);
            if(CollectionUtils.isEmpty(a.getChildren())){
                a.setChildren(new ArrayList<>());
            }
            a.getChildren().add(a1);
        }


    }

    @Data
    static class A {
        private Integer a;
        private Integer b;
        private List<A> children;
    }

    @Data
    static class R {
        private Integer a;
        private Integer b;
    }


}
