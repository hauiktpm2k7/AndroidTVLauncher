package com.androidtvlauncher;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.RowHeaderPresenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class IconHeaderItemPresenter  extends RowHeaderPresenter {
    private float mUnselectedAlpha;

    @Override
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {

        mUnselectedAlpha = parent.getResources()
                .getFraction(R.fraction.lb_browse_header_unselect_alpha, 1, 1);
        LayoutInflater inflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.icon_header_item, null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        super.onBindViewHolder(viewHolder, item);
        IconHeaderItem iconHeaderItem = (IconHeaderItem) ((ListRow) item).getHeaderItem();
        View rootView = viewHolder.view;

        ImageView iconView = (ImageView) rootView.findViewById(R.id.header_icon);
        int iconResId = iconHeaderItem.getIconResId();
        if( iconResId != IconHeaderItem.ICON_NONE) { // Show icon only when it is set.
            Drawable icon = rootView.getResources().getDrawable(iconResId, null);
            iconView.setImageDrawable(icon);
        }

        TextView label = (TextView) rootView.findViewById(R.id.header_label);
        label.setText(iconHeaderItem.getName());
        rootView.setFocusable(true);
    }

    @Override
    protected void onSelectLevelChanged(ViewHolder holder) {
        holder.view.setAlpha(mUnselectedAlpha + holder.getSelectLevel() *
                (1.0f - mUnselectedAlpha));
    }

}