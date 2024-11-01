package com.softworkshub.a7minworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softworkshub.a7minworkout.data.HistoryEntity
import com.softworkshub.a7minworkout.databinding.HistoryRowBinding

class HistoryAdapter(private val items: ArrayList<String>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

     class ViewHolder( binding : HistoryRowBinding): RecyclerView.ViewHolder(binding.root){
        val llHistory = binding.llHistoryView
        val tvDate = binding.tvItem
        val tvPosition = binding.tvPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(HistoryRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date : String = items.get(position)
        holder.tvPosition.text = (position+1).toString()
        holder.tvDate.text = date

        if (position % 2 == 0){
            holder.llHistory.setBackgroundColor(Color.parseColor("#EBEBEB"))
        }else{
            holder.llHistory.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }
    }

    override fun getItemCount(): Int {
        return  items.size
    }


}