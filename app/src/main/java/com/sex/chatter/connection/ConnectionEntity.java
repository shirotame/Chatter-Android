package com.sex.chatter.connection;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Objects;

public class ConnectionEntity implements Serializable {
    private String ipAddress;
    private Integer port;
    private String nickname;

    public ConnectionEntity(String ipAddress, Integer port, String nickname) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.nickname = nickname;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipAddress, port, nickname);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        final ConnectionEntity other = (ConnectionEntity) obj;
        return Objects.equals(other.getIpAddress(), ipAddress) &&
                Objects.equals(other.getNickname(), nickname) &&
                Objects.equals(other.getPort(), port);
    }
}
