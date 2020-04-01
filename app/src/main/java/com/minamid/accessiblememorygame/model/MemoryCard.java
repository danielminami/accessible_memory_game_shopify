package com.minamid.accessiblememorygame.model;

import android.content.Context;
import android.util.AttributeSet;

public class MemoryCard extends android.support.v7.widget.AppCompatImageView {

    private int id;
    private String src;
    private String description;
    private boolean isRevealed;
    private int rowPosition;
    private int colPosition;



    public MemoryCard(Context context) {
        super(context);
    }

    public MemoryCard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MemoryCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }


    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }



    public int getColPosition() {
        return colPosition;
    }

    public void setColPosition(int colPosition) {
        this.colPosition = colPosition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }
}
