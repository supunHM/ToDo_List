package com.example.todo_list.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.todo_list.R
import com.example.todo_list.databinding.FragmentAddPopupBinding
import com.example.todo_list.utils.ToDoData
import com.google.android.material.textfield.TextInputEditText


class AddPopupFragment : DialogFragment() {

    private lateinit var binding: FragmentAddPopupBinding
    private lateinit var listener: DialogNextBtnClickListener
    private var toDoData :ToDoData? = null

    fun setListener(listener:DialogNextBtnClickListener){
        this.listener = listener
    }

    companion object{
        const val TAG = "AddTodoPopupFragment"

        @JvmStatic
        fun newInstance(taskId:String , task:String) = AddPopupFragment().apply {
            arguments = Bundle().apply {
                putString("taskId",taskId)
                putString("task",task)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddPopupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null){
            toDoData = ToDoData(
                arguments?.getString("taskId",).toString(),
                arguments?.getString("task").toString()
            )
            binding.todoEt.setText(toDoData?.task)
        }
        registerEvents()
    }

    private fun registerEvents(){
        binding.todoNext.setOnClickListener{
            val todoTask = binding.todoEt.text.toString()
            if(todoTask.isNotEmpty()){
                if(toDoData == null){
                    listener.onSaveTask(todoTask , binding.todoEt)
                }else{
                    toDoData?.task = todoTask
                    listener.onUpdateTask(toDoData!! ,binding.todoEt)
                }

            }else{
                Toast.makeText(context,"Please type some task",Toast.LENGTH_SHORT).show()
            }
        }

        binding.todoClose.setOnClickListener{
            dismiss()
        }
    }

    interface DialogNextBtnClickListener{
        fun onSaveTask(todo:String,todoEt:TextInputEditText)
        fun onUpdateTask(toDoData: ToDoData,todoEt:TextInputEditText)
    }

}