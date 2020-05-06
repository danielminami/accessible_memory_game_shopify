package com.minamid.accessiblememorygame.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.minamid.accessiblememorygame.R;

public class MemoryCard extends android.support.v7.widget.AppCompatImageView {

    private String imageId;
    private String src;
    private String description;
    private boolean isFound;
    private boolean isRevealed;
    private int rowPosition;
    private int colPosition;
    private boolean shouldAnnounce;

    public MemoryCard(Context context) {
        super(context);
    }

    public MemoryCard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MemoryCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public String getImageId() { return imageId; }

    public void setImageId(String imageId) { this.imageId = imageId; }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) { isRevealed = revealed; }

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

    public boolean isFound() { return isFound; }

    public void setFound(boolean found) { isFound = found; }

    public boolean isShouldAnnounce() { return shouldAnnounce; }

    public void setShouldAnnounce(boolean shouldAnnounce) { this.shouldAnnounce = shouldAnnounce; }
}
