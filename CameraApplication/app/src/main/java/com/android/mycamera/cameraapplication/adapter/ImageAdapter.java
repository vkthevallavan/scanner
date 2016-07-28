package com.android.mycamera.cameraapplication.adapter;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.mycamera.cameraapplication.CacheImageLoader;
import com.android.mycamera.cameraapplication.ImageGridViewActivity;
import com.android.mycamera.cameraapplication.R;
import com.android.mycamera.cameraapplication.dataobjects.GridObject;

import java.util.ArrayList;


/**
 * The Class MyAdapter.
 */
public class ImageAdapter extends BaseAdapter {

    /** The m context. */
    private Context mContext;
    private View grid;
    private GridView gridView ;
    /** The image list. */
    private ArrayList<GridObject> imageList = new ArrayList<GridObject>();
    SparseBooleanArray mSparseBooleanArray;
    /**
     * Instantiates a new my adapter.
     *
     * @param c the c
     */

    public ImageAdapter(Context c) {
        mContext = c;
        mSparseBooleanArray = new SparseBooleanArray();
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount() {
        return getImageList().size();
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public Object getItem(int arg0) {
        return  imageList.get(arg0); //mThumbIds[arg0];
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    /**
     * Gets the image list.
     *
     * @return the image list
     */
    public ArrayList<GridObject> getImageList() {
        return imageList;
    }

    /**
     * Sets the image list.
     *
     * @param imageList the new image list
     */
    public void setImageList(ArrayList<GridObject> imageList) {
        this.imageList = imageList;

    }
    public ArrayList<Integer> getCheckedItems() {
        ArrayList<Integer> mTempArry = new ArrayList<Integer>();

        for(int i=0;i<imageList.size();i++) {
            if(mSparseBooleanArray.get(i)) {
                Log.i("Delete", "imageList.get(i).getState() " + imageList.get(i).getState());
                mTempArry.add(imageList.get(i).getState());
            }
        }

        return mTempArry;
    }
    public void unCheckedItems() {
        for(int i=0;i<imageList.size();i++) {
            if(mSparseBooleanArray.get(i)) {
                mSparseBooleanArray.put(i, false);
            }
        }


    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {




        final GridObject gridObject = imageList.get(position);
        if (convertView == null) {
            grid = new View(mContext);
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = inflater.inflate(R.layout.mygrid_layout, parent, false);
        } else {
            grid = (View) convertView;
        }

        gridView = (GridView) parent;

        int width = (Integer) gridView.getTag();
     //   grid.findViewById(R.id.image).setOnLongClickListener(new MyTouchListener());
      //  grid.findViewById(R.id.imageLayout).setOnDragListener(new MyDragListener());
       /* grid.findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                unCheckedItems();
                Log.i(Util.LOG_TAG,"Clicked  "+v.getTag());
                Log.i(Util.LOG_TAG,"Path in image adapter  "+gridObject.getPath());

                Intent intent = new Intent(mContext,ImageSwipeActivity.class);
                intent.putExtra("documentName", XIPSGlobalVariables.documentName);
                intent.putExtra("imageOrder", v.getTag().toString());
                intent.putExtra("imagePath",gridObject.getPath());
                ((Activity) mContext).startActivityForResult(intent,XIPSGlobalVariables.IMAGEPROCESSING_ACTIVITY_RESULT);
            }
        });*/
      /*  grid.findViewById(R.id.checkBox1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.i(Util.LOG_TAG,"on Item click ::"+mSparseBooleanArray.get(position));
                ((CompoundButton) v).setChecked((boolean)!mSparseBooleanArray.get(position));
                if(((CompoundButton) v).isChecked()){
                    mSparseBooleanArray.put(position, true);
                }
                else{
                    mSparseBooleanArray.put(position, false);
                }
            }
        });*/


      //  final CheckBox mCheckBox = (CheckBox)  grid.findViewById(R.id.checkBox1);
        ImageView imageView = (ImageView) grid.findViewById(R.id.image);
        RelativeLayout imageLayout = (RelativeLayout) grid.findViewById(R.id.imageLayout);

       // imageView.setTag(gridObject.getState());
        imageLayout.setTag(position);
      //  mCheckBox.setChecked(mSparseBooleanArray.get(position));




       /* if(gridObject.isCleaned()){
            grid.findViewById(R.id.imageLayout).setTag("true");
            grid.findViewById(R.id.imageLayout).setBackgroundResource(R.drawable.clean_bg);

        }
        else{
            grid.findViewById(R.id.imageLayout).setTag("false");
            grid.findViewById(R.id.imageLayout).setBackgroundResource(R.drawable.shape);

        }*/
       /* if(gridObject.getState()==999){
            imageView.setImageResource(R.drawable.ic_text_note);
            imageView.setOnLongClickListener(null);
            grid.findViewById(R.id.imageLayout).setOnDragListener(null);
            imageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    ((Activity) mContext).showDialog(10);
                }
            });
        }
        if(gridObject.getState()==998){
            imageView.setImageResource(R.drawable.play);
            imageView.setOnLongClickListener(null);
            grid.findViewById(R.id.imageLayout).setOnDragListener(null);
            imageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String filePath = Util.findFileByOrder(998,"filePath");

                    Intent intent = new Intent(mContext,AudioPlayActivity.class);
                    intent.putExtra("fileName",filePath);
                    ((Activity) mContext).startActivityForResult(intent, XIPSGlobalVariables.VOICE_PLAYING_CODE);

                }
            });
        }*/

       /* if(!(gridObject.getState()>=998)){
            loadBitmap(gridObject.getPath(), imageView,width);
        }*/
        loadBitmap(gridObject.getPath(), imageView,width);
        return grid;
    }
    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

         //   Log.i(Util.LOG_TAG,"Clicked  "+v.getTag());

        }

    }
    class MyTouchListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View view) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(data, shadowBuilder, view, 0);
            view.setVisibility(View.INVISIBLE);
            return true;

        }

    }
    class MyDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            View dragView = (View) event.getLocalState();

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //  Log.i("Drag","Drag started");

                    break;
                case DragEvent.ACTION_DRAG_ENTERED:

                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    Log.i("Drag","Drag dropped");
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState(); // on touch view
                    ViewGroup owner = (ViewGroup) view.getParent(); //find owner to reassign view from current owner

                    RelativeLayout container = (RelativeLayout) v; //the linearlayout whre u dropped

                    View existingView = container.getChildAt(0);
                    Log.i("Drag","existingView "+existingView);
                    if(existingView!=null){

                        ViewGroup owner2 = (ViewGroup) existingView.getParent();

                        Log.i("Drag","owner2 "+owner2);
                        Log.i("Drag","existingView.getTag() "+existingView.getTag());
                        String existingViewPath = String.valueOf(existingView.getTag());
                        //   String dirPath = Util.getDirectoryPathFromFileName(existingViewPath);
                        String changeViewPath = String.valueOf(view.getTag());
                        Log.i("Drag","changeViewPath "+changeViewPath+" existingViewPath "+existingViewPath);
                        Log.i("Drag","existing view Path in reorder "+existingView.getTag());
                        Log.i("Drag","change view Path in reorder "+view.getTag());
                        int existingViewOrder = (Integer) existingView.getTag();
                        int changeViewOrder = (Integer) view.getTag();
                        if(changeViewPath!= existingViewPath){

                            owner2.removeView(existingView);
                            owner.removeView(view);


                            //ViewGroup tempContainer = owner;
                            String ownerTag = (String) owner.getTag();
                            String ownerTag2 = (String) owner2.getTag();
                            if(ownerTag2.equalsIgnoreCase("true")){
                          //      owner.setBackgroundResource(R.drawable.clean_bg);
                                owner.setTag("true");
                            }
                            else{
                                owner.setBackgroundResource(R.drawable.shape);
                                owner.setTag("false");
                            }

                            if(ownerTag.equalsIgnoreCase("true")){
                            //    owner2.setBackgroundResource(R.drawable.clean_bg);
                                owner2.setTag("true");
                            }
                            else{
                                owner2.setBackgroundResource(R.drawable.shape);
                                owner2.setTag("false");
                            }
                            Log.i("Drag", "changeViewPath before rearrange " + changeViewPath + " existingViewPath " + existingViewPath);

                            existingView.setTag(changeViewPath);
                            view.setTag(existingViewPath);

                            Log.i("Drag","existing view Path in reorder "+existingView.getTag());
                            Log.i("Drag", "chnage view Path in reorder " + view.getTag());
                            owner.addView(existingView, 0);

                            owner2.addView(view, 0);


                        //    Util.rearrangeOrder(existingViewPath,changeViewPath);
                            GridObject existingGridObject = imageList.get(existingViewOrder-1);
                            GridObject changedGridObject = imageList.get(changeViewOrder-1);
                            existingGridObject.setState(changeViewOrder);
                            changedGridObject.setState(existingViewOrder);
                            imageList.set(existingViewOrder-1, changedGridObject);
                            imageList.set(changeViewOrder-1, existingGridObject);


                        }

                    }
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    if (dropEventNotHandled(event)) {
                        dragView.setVisibility(View.VISIBLE);
                    }

                default:
                    break;
            }
            return true;
        }
    }
    private boolean dropEventNotHandled(DragEvent dragEvent) {
        Log.i("Drag","dragEvent not handled "+!dragEvent.getResult());
        return !dragEvent.getResult();
    }

    /** The m thumb ids. */
    private Integer[] mThumbIds = { R.drawable.icon,
            R.drawable.icon, R.drawable.icon,
            R.drawable.icon, R.drawable.icon,
            R.drawable.icon, R.drawable.icon,
            R.drawable.icon, R.drawable.icon,
            R.drawable.icon, R.drawable.icon,
            R.drawable.icon, R.drawable.icon,
            R.drawable.icon, R.drawable.icon };


    /**
     * Load bitmap.
     *
     * @param fileid the fileid
     * @param imageView the image view
     */
    public void loadBitmap(String fileid, ImageView imageView,int requiredWidth) {



        final Bitmap bitmap = ImageGridViewActivity.getBitmapFromMemCache(fileid);

        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        else {

            CacheImageLoader imageLoader = new CacheImageLoader(mContext, imageView);
            try{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    imageLoader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                            fileid,String.valueOf(requiredWidth));
                } else {
                    imageLoader.execute(fileid,String.valueOf(requiredWidth));
                }
            }
            catch(Exception e){
               // Log.i(Util.LOG_TAG,"Exception in executeThread");
                e.printStackTrace();
            }
        }



    }
}
