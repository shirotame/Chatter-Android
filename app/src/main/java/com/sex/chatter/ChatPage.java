package com.sex.chatter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sex.chatter.core.Client;
import com.sex.chatter.core.tools.ServerConnectionException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class ChatPage extends AppCompatActivity {
    private Client client;
    private TextView chatView;
    private Button sendButton;
    private EditText textInput;
    private String ipAddr = null;
    private Integer port = null;
    private String nickname = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.chat_page);
        chatView = findViewById(R.id.textView);
        ScrollView scrollView = findViewById(R.id.scrollView);

        chatView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // not needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ipAddr = bundle.getString("ipAddr");
            TextView header = findViewById(R.id.chatHeader);
            header.setText(ipAddr);
            port = bundle.getInt("port");
            nickname = bundle.getString("nickname");
        }
        new SocketActivity().execute();

        sendButton = findViewById(R.id.sendButton);
        textInput = findViewById(R.id.textInput);

        sendButton.setOnClickListener(sex -> {
            String msg = textInput.getText().toString();
            if (msg.length() > 2000 || msg.isEmpty()) {
                return;
            }
            if (client != null) {
                client.sendMessage(msg);
                textInput.setText("");
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.chat), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        new DisconnectSocketActivity().execute();
    }

    private class SocketActivity extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... sex) {
            PrintStream pr = new PrintStream(new TextViewOutputStream(chatView));
            try {
                if (ipAddr != null && port != null && nickname != null) {
                    client = new Client(nickname, ipAddr, port, pr);
                    client.sendInitialMsg();
                }
            }
            catch (ServerConnectionException ex) {
                runOnUiThread(() -> {
                    chatView.append(ex.getMessage());
                });
            }
            return null;
        }
    }

    private class DisconnectSocketActivity extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... sex) {
            PrintStream pr = new PrintStream(new TextViewOutputStream(chatView));
            if (client != null) {
                client.sendDisconnectMsg();
            }
            return null;
        }
    }
    private class TextViewOutputStream extends OutputStream {
        private TextView view;
        public TextViewOutputStream(TextView view) {
            this.view = view;
        }

        @Override
        public void write(int b) throws IOException {
            runOnUiThread(() -> {
                view.append(String.valueOf((char) b));
            });
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            runOnUiThread(() -> {
                view.append(new String(b, off, len));
            });
        }

        @Override
        public void write(byte[] b) throws IOException {
            write(b, 0, b.length);
        }
    }
}
