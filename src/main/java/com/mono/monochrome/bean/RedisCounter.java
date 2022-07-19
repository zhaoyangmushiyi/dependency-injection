package com.mono.monochrome.bean;

/**
 * @author monochrome
 * @date 2022/7/19
 */
public class RedisCounter {

    private String ipAddress;
    private String port;

    public RedisCounter(String ipAddress, String port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return String.format("ipAddress:{%s},port:{%s}", ipAddress, port);
    }
}
