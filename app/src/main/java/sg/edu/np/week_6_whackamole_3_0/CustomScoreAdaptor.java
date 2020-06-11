/**
package sg.edu.np.week_6_whackamole_3_0;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class CustomScoreAdaptor extends RecyclerView.Adapter<CustomScoreViewHolder> {
    public UserData userData.

    private static final String FILENAME = "CustomScoreAdaptor.java";
    private static final String TAG = "Whack-A-Mole3.0!";

    public CustomScoreAdaptor(UserData userdata){

        this.userData = userdata;
    }

    public CustomScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

     ViewHolder viewHolder;
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.level_select,
                parent,
                false);
        viewHolder = ViewHolder(item);
        return viewHolder;
    }

    public void onBindViewHolder(CustomScoreViewHolder holder, final int position){


    }

    public int getItemCount(){

        return 0;
    }
}
**/