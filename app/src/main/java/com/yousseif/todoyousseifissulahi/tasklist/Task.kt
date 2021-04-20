package com.yousseif.todoyousseifissulahi.tasklist

import java.io.Serializable

data class Task(val id: String, val title: String, val description: String = "default"):Serializable