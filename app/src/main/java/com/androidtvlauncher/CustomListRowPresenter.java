package com.androidtvlauncher;

import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.RowPresenter;

/**
 * Created by leo on 21/04/2017.
 */

public class CustomListRowPresenter extends ListRowPresenter {
    private static final String TAG = CustomListRowPresenter.class.getSimpleName();

    public CustomListRowPresenter() {
        super();
    }

    @Override
    protected void onBindRowViewHolder(RowPresenter.ViewHolder holder, Object item) {
        /* This two line codes changes the number of rows of ListRow */
        int numRows = ((CustomListRow) item).getNumRows();
        ((ListRowPresenter.ViewHolder) holder).getGridView().setNumRows(numRows);

        super.onBindRowViewHolder(holder, item);
    }

    @Override
    protected void initializeRowViewHolder(RowPresenter.ViewHolder holder) {
        super.initializeRowViewHolder(holder);

        /* Disable Shadow */
        // setShadowEnabled(false);
    }
}
