package com.bat.other;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @Author: caoke
 * @Date: 2019/7/23 20:51
 * @Version 1.0
 */
public class HostNameTest {
    public static void main(String[] args)throws Exception {
        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        while (netInterfaces.hasMoreElements()) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> ips = ni.getInetAddresses();
            while (ips.hasMoreElements()) {
                InetAddress addr = ips.nextElement();
                System.out.println(addr);
            }
        }
    }
}
