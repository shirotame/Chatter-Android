package com.sex.chatter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sex.chatter.connection.ConnectionEntity;
import com.sex.chatter.connection.ConnectionList;

import java.io.Serializable;
import java.util.ArrayList;

public class ConnectionActivity extends AppCompatActivity {
    private EditText ipInput;
    private EditText portInput;
    private EditText nicknameInput;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_connection);

        ipInput = findViewById(R.id.ipAddr);
        portInput = findViewById(R.id.port);
        nicknameInput = findViewById(R.id.nickname);
        submit = findViewById(R.id.createConnectionButton);

        submit.setOnClickListener(sex -> {
            ConnectionEntity newConnection = new ConnectionEntity(
                    ipInput.getText().toString(),
                    Integer.parseInt(portInput.getText().toString()),
                    nicknameInput.getText().toString()
            );
            ConnectionList.add(newConnection);
            startActivity(new Intent(this, MainActivity.class));
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}