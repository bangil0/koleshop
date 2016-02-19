package com.koleshop.appkoleshop.ui.buyer.viewholders;

import android.content.Context;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.koleshop.appkoleshop.R;
import com.koleshop.appkoleshop.model.parcel.SellerSettings;
import com.koleshop.appkoleshop.util.AndroidCompatUtil;
import com.koleshop.appkoleshop.util.CommonUtils;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Gundeep on 02/02/16.
 */
public class NearbyShopsListViewHolder extends RecyclerView.ViewHolder {

    SellerSettings sellerInfo;
    Context mContext;

    @Bind(R.id.iv_vnsi_avatar)
    ImageView imageViewAvatar;
    @Bind(R.id.iv_vnsi_online)
    ImageView imageViewOnline;
    @Bind(R.id.tv_vnsi_name)
    TextView textViewName;
    @Bind(R.id.tv_vnsi_timings)
    TextView textViewTimings;
    @Bind(R.id.tv_vnsi_distance)
    TextView textViewDistance;

    public NearbyShopsListViewHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        ButterKnife.bind(this, itemView);
    }

    public void bindData(SellerSettings sellerInfo) {
        this.sellerInfo = sellerInfo;
        loadSellerInfoIntoUi();
    }

    private void loadSellerInfoIntoUi() {
        if (sellerInfo != null) {
            textViewName.setText(sellerInfo.getAddress().getName());
            //textViewTimings.setText(sellerInfo.getTimings());
            float[] results = new float[3];
            Location.distanceBetween(30d, 76d, sellerInfo.getAddress().getGpsLat(), sellerInfo.getAddress().getGpsLong(), results);

            if (results != null && results.length > 0) {
                String distance = CommonUtils.getReadableDistanceFromMetres(results[0]);
                textViewDistance.setText(distance);
            } else {
                textViewDistance.setText("");
            }
            if (sellerInfo.getImageUrl() != null && !sellerInfo.getImageUrl().isEmpty())
                Picasso.with(mContext)
                        .load(sellerInfo.getImageUrl())
                        .into(imageViewAvatar);
            if (sellerInfo.isShopOpen()) {
                imageViewOnline.setImageDrawable(AndroidCompatUtil.getDrawable(mContext, R.drawable.ic_green_dot_24dp));
            }/* else if (no delivery to your location || pickup only){
                imageViewOnline.setImageDrawable(AndroidCompatUtil.getDrawable(mContext, R.drawable.ic_orange_dot_24dp));
            }*/ else{
                imageViewOnline.setImageDrawable(AndroidCompatUtil.getDrawable(mContext, R.drawable.ic_grey_dot_24dp));
            }
        }
    }

    /*
     .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SellerInfo sellerInfo = getSellerInfo(mContext);
                if(sellerInfo!=null) {
                    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(sellerInfo.getName());
                }
                InventoryCategoryFragment myInventoryCategoryFragment = new InventoryCategoryFragment();
                Bundle bundleMyInventory = new Bundle();
                bundleMyInventory.putBoolean("myInventory", true);
                bundleMyInventory.putBoolean("customerView", true);
                myInventoryCategoryFragment.setArguments(bundleMyInventory);
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, myInventoryCategoryFragment, "frag_my_shop").commit();
            }
        });*/

}
