package com.example.myreminder.reminder.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ReminderResponse(

	@field:SerializedName("todos")
	val todos: List<TodosItem>
)

data class TodosItem(

	@field:SerializedName("dateTime")
	val dateTime: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String
)
