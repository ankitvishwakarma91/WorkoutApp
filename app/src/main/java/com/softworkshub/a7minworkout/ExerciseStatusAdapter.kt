package com.softworkshub.a7minworkout


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.softworkshub.a7minworkout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(var items: ArrayList<ExerciseModel>) :
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemExerciseStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvItem = binding.tvItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExerciseStatusAdapter.ViewHolder {
        return  ViewHolder(ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ExerciseStatusAdapter.ViewHolder, position: Int) {
        val model :ExerciseModel  = items[position]
        holder.tvItem.text = model.getId().toString()

        when{

            model.getIsSelected() -> {
                holder.tvItem.background = ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_selected_background_color)
                holder.tvItem.setTextColor(Color.WHITE)

            }
            model.getIsCompleted() -> {
                holder.tvItem.background = ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_circular_color_accent_background)
                holder.tvItem.setTextColor(Color.WHITE)

            }
            else -> {
                holder.tvItem.background = ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_circular_gray_background)
                holder.tvItem.setTextColor(Color.CYAN)

            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }


}