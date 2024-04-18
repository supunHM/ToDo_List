package com.example.todo_list.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.databinding.EachTodoItemBinding
import com.google.android.gms.common.api.internal.TaskApiCall
import com.google.android.gms.tasks.Task
import com.google.android.material.animation.AnimatableView.Listener

class ToDOAdapter(private val list:MutableList<ToDoData>) :
RecyclerView.Adapter<ToDOAdapter.ToDoViewHolder>(){

    private var listener :ToDOAdapterClickInterface ? = null
    fun setListener(listener: ToDOAdapterClickInterface){
        this.listener = listener
    }
    inner class ToDoViewHolder(val binding: EachTodoItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = EachTodoItemBinding.inflate(LayoutInflater.from(parent.context),parent , false)
        return ToDoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
       with(holder){
           with(list[position]){
                binding.todoTask.text = this.task

                binding.deleteTask.setOnClickListener{
                        listener?.onDeleteTaskBtnClicked(this)
               }

               binding.editTask.setOnClickListener{
                    listener?.onEditTaskBtnClicked((this))
               }
           }
       }
    }

    interface ToDOAdapterClickInterface{
        fun onDeleteTaskBtnClicked(toDoData: ToDoData)
        fun onEditTaskBtnClicked(toDoData: ToDoData)

    }

}