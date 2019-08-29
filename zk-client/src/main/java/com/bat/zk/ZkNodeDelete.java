package com.bat.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: caoke
 * @Date: 2019/8/22 11:27
 * @Version 1.0
 */
public class ZkNodeDelete {
    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("10.155.20.62:2181",
                new RetryNTimes(10, 5000));
//        CuratorFramework client = CuratorFrameworkFactory.newClient("zk1.trunk.koolearn.com:2181",
//                new RetryNTimes(10, 5000));
        client.start();

        String zpath = "/dubbo";
        List<String> zooChildren = new ArrayList<String>();
        List<String> deleteChildren = new ArrayList<String>();
        try {
            zooChildren = client.getChildren().forPath(zpath);
            for (String child : zooChildren) {
                if (child.startsWith("at%25") || child.startsWith("+")) {
                    deleteChildren.add(child);
                }
            }
            System.out.println("deleteChildren.size():" + deleteChildren.size());
            for (String child : deleteChildren) {
                System.out.println(child);
                remove(client,zpath+"/"+child);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        client.close();
    }

    public static void remove(CuratorFramework client,String path)throws Exception{
        List<String> children = client.getChildren().forPath(path);
        if(children.size() == 0){
            System.out.println("delete path:"+path);
            client.delete().forPath(path);
        }else{
            for(String c:children) {
                remove(client, path + "/"+c);
            }
        }
    }

}
