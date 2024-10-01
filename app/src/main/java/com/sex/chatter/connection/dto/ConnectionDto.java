package com.sex.chatter.connection.dto;

public class ConnectionDto {
    private String header;
    private String ip;
    private String nickname;

    public ConnectionDto(String header, String ip, String nickname) {
        this.header = header;
        this.ip = ip;
        this.nickname = nickname;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
