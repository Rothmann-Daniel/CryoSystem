package com.example.cryosystems.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.cryosystems.R
import com.example.cryosystems.domain.model.Model

class SystemItemAdapter(
    private var items: List<Model>,
    private val onItemClick: (Model) -> Unit = {}
) : RecyclerView.Adapter<SystemItemAdapter.SystemItemViewHolder>() {

    // ViewHolder класс
    inner class SystemItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvLevel: TextView = itemView.findViewById(R.id.tv_level)
        val tvTemp: TextView = itemView.findViewById(R.id.tv_temp)

        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClick(items[adapterPosition])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SystemItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.system_data_item, parent, false)
        return SystemItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SystemItemViewHolder, position: Int) {
        val item = items[position]

        holder.tvDate.text = item.time
        holder.tvLevel.text = "Level: ${item.level}"
        holder.tvTemp.text = "${item.currentTemp}℃"

        // Можно добавить логику для изменения цвета в зависимости от значений
        when {
            item.tempAlarm == "1" -> holder.tvTemp.setTextColor(Color.RED)
            item.levelAlarm == "1" -> holder.tvLevel.setTextColor(Color.RED)
            else -> {
                holder.tvTemp.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.blue))
                holder.tvLevel.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.blue))
            }
        }
    }

    override fun getItemCount() = items.size

    // Метод для обновления данных
    fun updateData(newItems: List<Model>) {
        items = newItems
        notifyDataSetChanged()
    }
}