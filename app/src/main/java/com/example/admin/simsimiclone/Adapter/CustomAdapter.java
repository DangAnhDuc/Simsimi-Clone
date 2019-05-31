package com.example.admin.simsimiclone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.admin.simsimiclone.Model.ChatModel;
import com.example.admin.simsimiclone.R;
import com.github.library.bubbleview.BubbleTextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private List<ChatModel> list_chat_model;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomAdapter(List<ChatModel> list_chat_model,Context context){
        this.list_chat_model=list_chat_model;
        this.context=context;
        layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return list_chat_model.size();
    }

    @Override
    public Object getItem(int position) {
        return list_chat_model.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if (view==null){
            if(list_chat_model.get(position).isSend){
                view=layoutInflater.inflate(R.layout.list_item_message_send,null);
            }
            else {
                view=layoutInflater.inflate(R.layout.list_item_message_receive,null);
            }

            BubbleTextView message_text= (BubbleTextView)view.findViewById(R.id.message_text);
            message_text.setText(list_chat_model.get(position).message);
        }

        return view;
    }
}
