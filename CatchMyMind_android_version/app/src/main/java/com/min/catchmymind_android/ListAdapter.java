package com.min.catchmymind_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.min.catchmymind_android.dto.AllBoardResponseDto;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    List<AllBoardResponseDto> boards;

    public ListAdapter(Context mContext, List<AllBoardResponseDto> boards) {
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.boards = boards;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.list_element, null);

        TextView no =view.findViewById(R.id.boardNo);
        TextView title = view.findViewById(R.id.boardTitle);
        TextView name = view.findViewById(R.id.boardWriter);
        TextView content = view.findViewById(R.id.boardContent);

        no.setText(boards.get(position).getBoardId().toString());
        title.setText(boards.get(position).getTitle());
        name.setText(boards.get(position).getName());
        content.setText(boards.get(position).getContent());
        return view;
    }

    @Override
    public int getCount() {
        return boards.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

}
