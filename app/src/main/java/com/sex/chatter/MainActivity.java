package com.sex.chatter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sex.chatter.connection.ConnectionEntity;
import com.sex.chatter.connection.ConnectionList;
import com.sex.chatter.core.ConnectionListAdapter;


public class MainActivity extends AppCompatActivity {
    private ListView servers;
    private FloatingActionButton addServerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConnectionList.load(MainActivity.this);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        servers = findViewById(R.id.serverList);
        addServerButton = findViewById(R.id.addServer);

        addServerButton.setOnClickListener(sex -> {
            startActivity(new Intent(this, ConnectionActivity.class));
        });
        servers.setAdapter(new ConnectionListAdapter(this, R.layout.list_item, ConnectionList.toDtoList()));
        servers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ConnectionEntity e = ConnectionList.getConnections().get((int)id);
                Intent serverConnect = new Intent(MainActivity.this, ChatPage.class);
                serverConnect.putExtra("ipAddr", e.getIpAddress());
                serverConnect.putExtra("port", e.getPort());
                serverConnect.putExtra("nickname", e.getNickname());
                startActivity(serverConnect);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ConnectionList.serialize(MainActivity.this);
    }
}