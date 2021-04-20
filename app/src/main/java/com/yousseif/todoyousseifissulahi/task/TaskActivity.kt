package com.yousseif.todoyousseifissulahi.task

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import com.yousseif.todoyousseifissulahi.R
import com.yousseif.todoyousseifissulahi.tasklist.Task
import java.util.*

class TaskActivity : Activity() {

    companion object {
        const val TASK_KEY = "task"
        const val ADD_TASK_REQUEST_CODE = 666
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val validButton = findViewById<ImageButton>(R.id.ButtonValid)
        val titleActivity = findViewById<EditText>(R.id.task_title)
        val descriptActivity = findViewById<EditText>(R.id.task_description)

        val task = intent.getSerializableExtra(TaskActivity.TASK_KEY) as? Task
        titleActivity.setText(task?.title)
        descriptActivity.setText(task?.description)

        validButton.setOnClickListener{
            val newTask = Task(
                id = task?.id ?: UUID.randomUUID().toString(),
                title = titleActivity.text.toString(),
                description = descriptActivity.text.toString()
            )

            intent.putExtra(TASK_KEY, newTask)
            setResult(RESULT_OK, intent)
            finish()


        }

    }
}