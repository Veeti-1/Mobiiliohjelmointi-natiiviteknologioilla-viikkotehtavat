package com.example.week1.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.example.week1.domain.*
import org.intellij.lang.annotations.JdkConstants

@Composable
fun homescreen(taskslist: List<Task>){
    var tasks by remember { mutableStateOf(taskslist) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Row(modifier = Modifier.padding(top = 22.dp)) {
            Button(onClick = {
            tasks=filterByDone(tasks, done = true)
        }) {Text(text = "Show only done tasks") }

            Button(onClick = {
                tasks=sortByDueDate(tasks)
            }) {Text(text = "Sort by dueDate") }

            Button(onClick = {
                val nextid = tasks.maxOf { it.id } +1
                val newTask =Task(
                    id = nextid,
                    title = "New Task ${nextid}",
                    description = "added a new task via button",
                    priority = (1..5).random(),
                    dueDate = "2026-1-6",
                    done = false,
                )
                tasks = addTask(tasks, newTask)
                println("taskit "+tasks)
            }) {Text(text="add task") }}

            tasks.forEach { task ->
                if (task.done) {

                    Text(
                        text = "Id: ${task.id} - ${task.title} Status: Done \n DueDate was: ${task.dueDate}",
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 20.dp)

                    )
                } else if (task.done == false) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Button(onClick = {
                            tasks = toggleDone(tasks, task.id)
                        }) { Text(text = "done") }
                        Text(
                            text = "Id: ${task.id} - ${task.title} \n Status: Task not done yet.\n Due Date: ${task.dueDate}",
                            modifier = Modifier.padding(horizontal = 15.dp, vertical = 20.dp)
                        )
                    }


            }
        }





    }
}