package com.zc.util.network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Random;
import java.util.regex.Pattern;

public class NetUtils {

    public static final String LOCAL_HOST = "127.0.0.1";

    public static final String ANY_HOST = "0.0.0.0";

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public static final int MIN_PORT = 0;

    public static final int MAX_PORT = 65535;

    private static volatile InetAddress LOCAL_ADDRESS = null;

    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");

    private NetUtils() {}

    public static int getRandomPort() {
        return MIN_PORT + RANDOM.nextInt(MAX_PORT - MIN_PORT + 1);
    }

    private static boolean isValidAddress(InetAddress inetAddress) {
        if (inetAddress != null && !inetAddress.isLoopbackAddress()) {
            String name = inetAddress.getHostAddress();
            return name != null
                    && !ANY_HOST.equals(name)
                    && !LOCAL_HOST.equals(name)
                    && IP_PATTERN.matcher(name).matches();
        } else {
            return false;
        }
    }

    private static final Pattern LOCAL_IP_PATTERN = Pattern.compile("127(\\.\\d{1,3}){3}$");

    public static boolean isInvalidLocalHost(String host) {
        return host == null || host.length() == 0 || host.equalsIgnoreCase("localhost")
                || host.equals("0.0.0.0") || LOCAL_IP_PATTERN.matcher(host).matches();
    }

    public static InetAddress getLocalAddress() {
        if (LOCAL_ADDRESS != null) {
            return LOCAL_ADDRESS;
        } else {
            InetAddress localAddress = innerGetLocalAddress();
            LOCAL_ADDRESS = localAddress;
            return localAddress;
        }
    }

    public static String getLocalHost() {
        InetAddress address = getLocalAddress();
        return address == null ? "127.0.0.1" : address.getHostAddress();
    }

    private static InetAddress innerGetLocalAddress() {
        InetAddress localAddress = null;

        try {
            localAddress = InetAddress.getLocalHost();

            if (isValidAddress(localAddress)) {
                return localAddress;
            }
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException("NetUtils exception case:" + e.getMessage(), e);
        }

        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces != null) {
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface networkInterface = networkInterfaces.nextElement();
                    Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                    if (inetAddresses != null) {
                        while (inetAddresses.hasMoreElements()) {
                            try {
                                InetAddress address = (InetAddress)inetAddresses.nextElement();
                                if (isValidAddress(address)) {
                                    return address;
                                }
                            } catch (Throwable e) {
                                throw new IllegalArgumentException("NetUtils exception case:" + e.getMessage(), e);
                            }
                        }
                    }
                }
            }

        } catch (SocketException e) {
            throw new IllegalArgumentException("NetUtils exception case:" + e.getMessage(), e);
        }

        return localAddress;
    }

}
