package com.minamid.accessiblememorygame.ui.game;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.minamid.accessiblememorygame.R;
import com.minamid.accessiblememorygame.model.MemoryCard;
import com.minamid.accessiblememorygame.ui.game.GameBoardFragment;

import java.util.List;

public class BoardAdapter extends BaseAdapter {

    private Fragment fragment;
    private LayoutInflater layoutinflater;
    private List<MemoryCard> memoryCardList;
    private int layoutSize;
    private Context context;

    public BoardAdapter(Context context, List<MemoryCard> memoryCardList, Fragment fragment, int layoutSize) {
        this.context = context;
        layoutinflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.memoryCardList = memoryCardList;
        this.fragment = fragment;
        this.layoutSize = layoutSize;
    }

    @Override
    public int getCount() {
        return memoryCardList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = layoutinflater.inflate(R.layout.memory_card_layout, parent, false);
            listViewHolder.memoryCardInListView = (MemoryCard) convertView.findViewById(R.id.memory_card);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        
        listViewHolder.memoryCardInListView.setRevealed(memoryCardList.get(position).isRevealed());
        listViewHolder.memoryCardInListView.setSrc(memoryCardList.get(position).getSrc());
        listViewHolder.memoryCardInListView.setEnabled(memoryCardList.get(position).isEnabled());
        listViewHolder.memoryCardInListView.setFound(memoryCardList.get(position).isFound());
        listViewHolder.memoryCardInListView.setImageId(memoryCardList.get(position).getImageId());
        listViewHolder.memoryCardInListView.setColPosition(memoryCardList.get(position).getColPosition());
        listViewHolder.memoryCardInListView.setRowPosition(memoryCardList.get(position).getRowPosition());
        listViewHolder.memoryCardInListView.setDescription(memoryCardList.get(position).getDescription());

        if (listViewHolder.memoryCardInListView.getSrc() != null) {
            ((GameBoardFragment) fragment).bindImage(listViewHolder.memoryCardInListView);
        }

        convertView.getLayoutParams().height = layoutSize;
        convertView.getLayoutParams().width = layoutSize;

        return convertView;
    }

    static class ViewHolder {
        MemoryCard memoryCardInListView;
    }


}
