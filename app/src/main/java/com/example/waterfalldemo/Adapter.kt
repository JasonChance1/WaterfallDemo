package com.example.waterfalldemo

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.Random


class Adapter(private val imageList: List<Int>, private val context: Context) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var title: TextView
        var container: LinearLayoutCompat
        var initialHeight = 0

        init {
            image = itemView.findViewById(R.id.image)
            title = itemView.findViewById(R.id.title)
            container = itemView.findViewById(R.id.container)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_rv, parent, false)

        // 设置初始高度
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val viewHolder = ViewHolder(view)
        viewHolder.initialHeight = randomHeight
        layoutParams.height = viewHolder.initialHeight
        view.layoutParams = layoutParams
        return viewHolder
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 获取初始高度
        val initialHeight = holder.initialHeight

        // 在初始高度的基础上添加一个随机数
        val maxIncrement = 50 // 最大增量
        val minIncrement = 0 // 最小增量
        val randomIncrement = Random().nextInt(maxIncrement - minIncrement + 1) + minIncrement
        val newHeight = initialHeight + randomIncrement

        // 设置新的高度
        val layoutParams = holder.container.layoutParams
        layoutParams.height = newHeight

        // 设置margin
        val margin = 16 // 设置你想要的margin值
        if (layoutParams is MarginLayoutParams) {
            val marginLayoutParams = layoutParams
            marginLayoutParams.setMargins(margin, margin, margin, margin)
            holder.container.layoutParams = marginLayoutParams
        }
        holder.image.setImageResource(imageList[position])
        holder.title.text = "标题$position"
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    private val randomHeight: Int
        get() {
            // 为初始高度设置一个随机数
            val maxHeight = 800 // 初始最大高度
            val minHeight = 600 // 初始最小高度
            return Random().nextInt(maxHeight - minHeight + 1) + minHeight
        }
}
