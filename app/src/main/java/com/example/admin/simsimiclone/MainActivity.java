package com.example.admin.simsimiclone;

import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.admin.simsimiclone.Adapter.CustomAdapter;
import com.example.admin.simsimiclone.Helper.HttpDataHandle;
import com.example.admin.simsimiclone.Model.ChatModel;
import com.example.admin.simsimiclone.Model.SimsimiModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    FloatingActionButton floatingActionButton;
    List<ChatModel> list_chat=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.list_of_message);
        editText=findViewById(R.id.user_message);
        floatingActionButton=findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=editText.getText().toString();
                ChatModel chatModel = new ChatModel(text,true);
                list_chat.add(chatModel);
                new SimsimiAPI().execute(list_chat);

                editText.setText("");
                editText.requestFocus();
            }
        });
    }

    private class SimsimiAPI extends AsyncTask<List<ChatModel>, Void,String>{
        String stream=null;
        List<ChatModel> models;
        String text=editText.getText().toString();
        @Override
        protected String doInBackground(List<ChatModel>... lists) {
            String url=String.format("http://sandbox.api.simsimi.com/request.p?key=%s&lc=en&ft=1.0&text=%s",getString(R.string.simsimi_api),text);
            models =lists[0];
            HttpDataHandle httpDataHandle=new HttpDataHandle();
            stream=httpDataHandle.GetHttpDataHandle(url);
            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            Gson gson=new Gson();
            SimsimiModel response=gson.fromJson(s,SimsimiModel.class);

            ChatModel chatModel=new ChatModel(response.getResponse(),false);
            models.add(chatModel);
            CustomAdapter adapter=new CustomAdapter(models,getApplicationContext());
            listView.setAdapter(adapter);
        }
    }
}
