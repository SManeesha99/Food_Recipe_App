package com.example.foodrecipe.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipe.Models.Equipment;
import com.example.foodrecipe.Models.Ingredient;
import com.example.foodrecipe.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionEqupmentsAdapter extends RecyclerView.Adapter<InstructionEquipmentsViewHolder>{
    Context context;
    List<Equipment> list;


    public InstructionEqupmentsAdapter(Context context, List<Equipment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionEquipmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionEquipmentsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_step_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionEquipmentsViewHolder holder, int position) {
        holder.textView_instruction_step_item.setText(list.get(position).name);
        holder.imageView_intruction_step_iteam.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/equipment_100x100/"+list.get(position).image).into(holder.imageView_intruction_step_iteam);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionEquipmentsViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView_intruction_step_iteam;
    TextView textView_instruction_step_item;

    public InstructionEquipmentsViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_intruction_step_iteam = itemView.findViewById(R.id.imageView_intruction_step_iteam);
        textView_instruction_step_item = itemView.findViewById(R.id.textView_instruction_step_item);
    }
}