package com.nyi.movie.stick.sample;


import androidx.annotation.LayoutRes;
import com.nyi.movie.stick.HeaderData;


public class HeaderDataImpl implements HeaderData {
    public static final int HEADER_TYPE_1 = 1;
    public static final int HEADER_TYPE_2 = 2;

    private int headerType;

    @LayoutRes
    private final int layoutResource;

    public HeaderDataImpl(int headerType, @LayoutRes int layoutResource) {
        this.layoutResource = layoutResource;
        this.headerType = headerType;
    }

    @LayoutRes
    @Override
    public int getHeaderLayout() {
        return layoutResource;
    }

    @Override
    public int getHeaderType() {
        return headerType;
    }


}
