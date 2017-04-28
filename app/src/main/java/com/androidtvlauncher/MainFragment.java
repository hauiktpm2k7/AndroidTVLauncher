package com.androidtvlauncher;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by leo on 13/04/2017.
 */

public class MainFragment extends BrowseFragment {

    protected  static  final int CARD_WIDTH=0;
    protected  static  final int CARD_HEIGHT=0;
    PackageManager mPackageManager;
    List<MyApp> myAppList;
    List<IconHeaderItem> iconHeaderItemList;
    private ArrayObjectAdapter mRowsAdapter;




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupUIElement();
        loadRow();
        setupEventListeners();
    }
    private void setupEventListeners() {
        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectListener());
        setSearchAffordanceColor(getResources().getColor(R.color.lb_default_search_color));

        setOnSearchClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  RowPresenter.ViewHolder rowViewHolder, Row row) {
            // Toast.makeText(getActivity(),"Toast",Toast.LENGTH_SHORT).show();

            MyApp myApp = (MyApp) item;
            ActivityInfo activityInfo=myApp.getActivityInfo();
            Intent intent=new Intent(Intent.ACTION_MAIN).setClassName(activityInfo.applicationInfo.packageName,activityInfo.name);
            startActivity(intent);

        }
    }
    private final class ItemViewSelectListener implements OnItemViewSelectedListener {

        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        if(item instanceof String){
            Toast.makeText(getActivity(),"String",Toast.LENGTH_SHORT).show();
           Intent intent=new Intent(Intent.ACTION_MAIN).setClassName("com.android.tv.settings","com.android.tv.settings.MainSettings");
            startActivity(intent);


        }
            else if(item instanceof Object){
                Toast.makeText(getActivity(),"Ob",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public  void setupUIElement()
    {
        setBadgeDrawable(getActivity().getResources()
                .getDrawable(R.drawable.logo_foxconn));
        setTitle(getString(R.string.app_name));
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);
        setBrandColor(getResources().getColor(R.color.background_color));
        setSearchAffordanceColor(getResources().getColor(R.color.lb_default_search_color));
        setHeaderPresenterSelector(new PresenterSelector() {
            @Override
            public Presenter getPresenter(Object item) {
                return new IconHeaderItemPresenter();
            }
        });

    }


    private  class GridItemPresenter extends Presenter{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent) {
            TextView textView=new TextView(parent.getContext());
            textView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
            textView.setFocusable(true);
            textView.setFocusableInTouchMode(true);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(getResources().getColor(R.color.lb_tv_white));
            textView.setBackgroundColor(getResources().getColor(R.color.background_color));
            return new ViewHolder(textView);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Object item) {
            /*MyApp myApp = (MyApp)item;
            String label = myApp.getLabel().toString();
            ((TextView) viewHolder.view).setText(label);*/
            ((TextView) viewHolder.view).setText((String) item);
        }

        @Override
        public void onUnbindViewHolder(ViewHolder viewHolder) {

        }
    }

    private  void loadRow(){
        mPackageManager = getActivity().getPackageManager();
        myAppList = new ArrayList<MyApp>();
        iconHeaderItemList=new ArrayList<IconHeaderItem>();
        mRowsAdapter=new ArrayObjectAdapter(new CustomListRowPresenter());

        Intent i = new Intent(Intent.ACTION_MAIN);
        //i.hasCategory(Intent.CATEGORY_HOME);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        Intent intentApp=new Intent(Intent.ACTION_MAIN);
        intentApp.addCategory(Intent.CATEGORY_LEANBACK_LAUNCHER);

        List<ResolveInfo> availableActivitiesLeanBack = mPackageManager.queryIntentActivities(intentApp, 0);
        List<ResolveInfo> availableActivities = mPackageManager.queryIntentActivities(i, 0);


        IconHeaderItem iconHeaderItemHome=new IconHeaderItem(0,"Home",R.drawable.home_default);
        CardPresenter cardPresenterHome = new CardPresenter();
        ArrayObjectAdapter cardRowAdapterHome = new ArrayObjectAdapter(cardPresenterHome);


        IconHeaderItem iconHeaderItemGuide=new IconHeaderItem(1,"TV",R.drawable.icon_tvguide);
        ArrayObjectAdapter cardRowAdapterGuide = new ArrayObjectAdapter(cardPresenterHome);


        IconHeaderItem headerItemVideo=new IconHeaderItem(2,"Video",R.drawable.icon_video);
        ArrayObjectAdapter cardRowAdapterVideo = new ArrayObjectAdapter(cardPresenterHome);

        IconHeaderItem headerItemPhotos=new IconHeaderItem(2,"Photos",R.drawable.ic_photos);
        ArrayObjectAdapter cardRowAdapterPhotos = new ArrayObjectAdapter(cardPresenterHome);


        CardPresenter cardPresenterApp = new CardPresenter();
        ArrayObjectAdapter cardRowAdapterApp = new ArrayObjectAdapter(cardPresenterApp);
        IconHeaderItem headerItemApp=new IconHeaderItem(2,"Apps",R.drawable.icon_app_48);


        IconHeaderItem headerItemSetting=new IconHeaderItem(3,"Setting",R.drawable.icon_settting);
        GridItemPresenter gridItemPresenter=new GridItemPresenter();
        ArrayObjectAdapter cardRowAdapterSetting = new ArrayObjectAdapter(gridItemPresenter);
        cardRowAdapterSetting.add("");


        IconHeaderItem headerItemHelp=new IconHeaderItem(4,"Help",R.drawable.icon_help);
        ArrayObjectAdapter cardRowAdapterHelp = new ArrayObjectAdapter(cardPresenterHome);

        IconHeaderItem headerItemStuff=new IconHeaderItem(5,"You Stuff",R.drawable.my_stuff);
        ArrayObjectAdapter cardRowAdapterStuff = new ArrayObjectAdapter(cardPresenterHome);

        IconHeaderItem headerItemGame=new IconHeaderItem(5,"Game",R.drawable.icon_game);
        ArrayObjectAdapter cardRowAdapterGame = new ArrayObjectAdapter(cardPresenterHome);

        for(ResolveInfo resolveInfo: availableActivitiesLeanBack){
            MyApp myApp = new MyApp();
            myApp.label = resolveInfo.loadLabel(mPackageManager);
            myApp.name = resolveInfo.activityInfo.name;
            myApp.icon = resolveInfo.activityInfo.loadIcon(mPackageManager);
            myApp.setActivityInfo(resolveInfo.activityInfo);
            myAppList.add(myApp);
            cardRowAdapterApp.add(myApp);
        }

        for(ResolveInfo ri:availableActivities){
            MyApp myApp = new MyApp();
            myApp.label = ri.loadLabel(mPackageManager);
            myApp.name = ri.activityInfo.name;
            myApp.icon = ri.activityInfo.loadIcon(mPackageManager);
            myApp.setActivityInfo(ri.activityInfo);
            myAppList.add(myApp);
            cardRowAdapterHome.add(myApp);

            String nameApp= (String) myApp.getName();
            String nameSetting="com.android.tv.settings.MainSettings";
            String nameGameDessertCase= (String) myApp.getName();
            String nameGameMland= (String) myApp.getName();
            nameGameDessertCase="com.android.systemui.DessertCase";

            nameGameMland="com.android.systemui.egg.MLandActivity";
        /*   if(nameApp.equals(nameSetting)){
                cardRowAdapterSetting.add(myApp);
                System.out.println("Name"+ri.activityInfo.name);
                String packageName= ri.activityInfo.applicationInfo.packageName;
                System.out.println("Packet  name: "+packageName);
             }*/
            if(nameApp.equals(nameGameDessertCase)||nameApp.equals(nameGameMland)){
                cardRowAdapterGame.add(myApp);

            }
                   /* .android.systemui.DessertCase*/
        }

        mRowsAdapter.add(new CustomListRow(iconHeaderItemHome, cardRowAdapterHome));
        mRowsAdapter.add(new CustomListRow(iconHeaderItemGuide, cardRowAdapterGuide));
        mRowsAdapter.add(new CustomListRow(headerItemApp, cardRowAdapterApp));
        mRowsAdapter.add(new CustomListRow(headerItemGame, cardRowAdapterGame));
        mRowsAdapter.add(new CustomListRow(headerItemVideo, cardRowAdapterVideo));
        mRowsAdapter.add(new CustomListRow(headerItemPhotos, cardRowAdapterPhotos));
        mRowsAdapter.add(new CustomListRow(headerItemStuff, cardRowAdapterStuff));
        mRowsAdapter.add(new CustomListRow(headerItemHelp, cardRowAdapterHelp));
        mRowsAdapter.add(new CustomListRow(headerItemSetting, cardRowAdapterSetting));
        setAdapter(mRowsAdapter);


    }
}
