package com.example.collectiontoasts

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ToastAdapter(
    private val toastList: MutableList<MyToast>,
    private val onItemClickListener: (MyToast) -> Unit
) : RecyclerView.Adapter<ToastAdapter.ViewHolder>() {

    // ViewHolder для отображения элемента списка
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Инициализация элементов макета
        val toastTextView: TextView = view.findViewById(R.id.toastTextView)
        val descTextView: TextView = view.findViewById(R.id.descTextView)

        init {
            view.setOnClickListener {
                // Получаем позицию элемента, на который кликнули
                val position: Int = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Получаем объект MyToast по позиции
                    val toast: MyToast = toastList[position]
                    // Вызываем обработчик, передавая объект MyToast
                    onItemClickListener.invoke(toast)
                }
            }
        }
    }

    // Создание нового ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.toast_item, parent, false)
        return ViewHolder(view)
    }

    // Привязка данных к ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val toast = toastList[position]

        // Установка текста и изменение внешнего вида для TextView с названием сайта
        holder.toastTextView.apply {
            text = toast.toastName
            setTextColor(Color.WHITE)  // Изменить цвет текста на белый
            textSize = 22f  // Изменить размер текста (в sp)
        }

        // Установка ограниченного по длине текста в TextView с паролем
        holder.descTextView.apply {
            text = limitDescription(toast.toastDesc, 30) // Ограничение до 30 символов
            setTextColor(Color.WHITE)  // Изменить цвет текста на белый
            textSize = 18f  // Изменить размер текста (в sp)
        }

        holder.itemView.setOnClickListener {
            // При клике передаем объект MyToast в обработчик
            onItemClickListener.invoke(toast)
        }
    }


    // Пример функции, ограничивающей длину строки и добавляющей троеточие
    fun limitDescription(description: String, maxLength: Int): String {
        return if (description.length > maxLength) {
            description.substring(0, maxLength) + "..."
        } else {
            description
        }
    }

    // Возвращает общее количество элементов списка
    override fun getItemCount(): Int {
        return toastList.size
    }
}