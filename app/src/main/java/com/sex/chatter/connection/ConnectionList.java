package com.sex.chatter.connection;

import android.content.Context;

import com.sex.chatter.connection.dto.ConnectionDto;
import com.sex.chatter.connection.serializer.SerializeObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConnectionList {
    private static List<ConnectionEntity> connections = new ArrayList<>();

    public static List<ConnectionEntity> getConnections() {
        return connections;
    }

    public static void add(ConnectionEntity e) {
        connections.add(e);
    }

    public static void remove(int i) {
        connections.remove(i);
    }

    public static List<ConnectionDto> toDtoList() {
        List<ConnectionDto> connectionDtoList = new ArrayList<>();

        for (int i = 0; i < connections.size(); i++) {
            ConnectionEntity curr = connections.get(i);
            connectionDtoList.add(new ConnectionDto(
                    "Connection " + (i+1),
                    "IP: " + curr.getIpAddress() + ":" +curr.getPort(),
                    "Nickname: " + curr.getNickname()
            ));
        }
        return connectionDtoList;
    }

    public static void serialize(Context context) {
        String ser = SerializeObject.objectToString((Serializable) connections);
        if (ser != null && !ser.equalsIgnoreCase("")) {
            SerializeObject.WriteSettings(context, ser, "con.dat");
        }
        else {
            SerializeObject.WriteSettings(context, "", "con.dat");
        }
    }

    public static boolean load(Context context) {
        if (!connections.isEmpty()) {
            return false;
        }
        String ser = SerializeObject.ReadSettings(context, "con.dat");
        if (ser == null || ser.equalsIgnoreCase("")) {
            return false;
        }
        Object obj = SerializeObject.stringToObject(ser);
        if (obj instanceof ArrayList) {
            connections = (ArrayList<ConnectionEntity>) obj;
        }
        return true;
    }
}
