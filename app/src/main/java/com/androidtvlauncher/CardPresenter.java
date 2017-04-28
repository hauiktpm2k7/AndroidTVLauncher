package com.androidtvlauncher;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.BaseCardView;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by leo on 14/04/2017.
 */

public class CardPresenter extends Presenter {
    private static Context mContext;
    private static int CARD_WIDTH = 250;
    private static int CARD_HEIGHT = 150;

    public  class  ViewHolder extends Presenter.ViewHolder{
        MyApp myApp;
        private ImageCardView mCardView;
        private Drawable mDefaultCardImage;

        public ViewHolder(View view) {
            super(view);
            mCardView = (ImageCardView) view;
            mDefaultCardImage = mContext.getResources().getDrawable(R.drawable.image_video_default);
        }

        public MyApp getMyApp() {
            return myApp;
        }
//
//        public void setMyApp(MyApp myApp) {
//            this.myApp = myApp;
//        }

        public ImageCardView getmCardView() {
            return mCardView;
        }

        public void setmCardView(ImageCardView mCardView) {
            this.mCardView = mCardView;
        }

        public Drawable getmDefaultCardImage() {
            return mDefaultCardImage;
        }

        public void setmDefaultCardImage(Drawable mDefaultCardImage) {
            this.mDefaultCardImage = mDefaultCardImage;
        }
    }

    @Override
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {

        mContext = parent.getContext();

        ImageCardView cardView = new ImageCardView(mContext);
        cardView.setCardType(BaseCardView.CARD_TYPE_INFO_UNDER_WITH_EXTRA);
        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        cardView.setBackgroundColor(mContext.getResources().getColor(R.color.background_color));
        return new ViewHolder(cardView);

    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        MyApp myApp = (MyApp) item;
     //   ((ViewHolder) viewHolder).setMyApp(myApp);   --- muc dich viet cai nay de lam gi ??????????
        ((ViewHolder) viewHolder).mCardView.setTitleText(myApp.getName());
        ((ViewHolder) viewHolder).mCardView.setContentText(myApp.getLabel());
        ((ViewHolder) viewHolder).mCardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);
        ((ViewHolder) viewHolder).mCardView.setMainImage(myApp.getIcon());

    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }



}
