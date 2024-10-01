package com.sex.chatter.core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sex.chatter.R;
import com.sex.chatter.connection.ConnectionList;
import com.sex.chatter.connection.dto.ConnectionDto;

import java.util.List;

public class ConnectionListAdapter extends ArrayAdapter<ConnectionDto> {
    private List<ConnectionDto> connectionDtoList;
    private Context context;
    private int resource;

    public ConnectionListAdapter(Context context, int resource, List<ConnectionDto> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        connectionDtoList = list;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(resource, null, true);

        TextView headerText = view.findViewById(R.id.header);
        TextView ipText = view.findViewById(R.id.ip);
        TextView nicknameText = view.findViewById(R.id.connectionNickname);
        Button buttonRemove = view.findViewById(R.id.buttonRemove);

        ConnectionDto connectionDto = connectionDtoList.get(position);

        headerText.setText(connectionDto.getHeader());
        ipText.setText(connectionDto.getIp());
        nicknameText.setText(connectionDto.getNickname());
        buttonRemove.setOnClickListener(s -> {
            this.remove(connectionDto);
            ConnectionList.remove(position);
        });

        return view;
    }
}
