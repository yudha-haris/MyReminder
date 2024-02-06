package com.example.myreminder.reminder.utils

import com.example.myreminder.reminder.data.source.local.entity.ReminderEntity
import com.example.myreminder.reminder.data.source.remote.response.TodosItem
import com.example.myreminder.reminder.domain.model.Reminder

object ReminderMapper {
    fun mapEntitiesToDomain(input: List<ReminderEntity>): List<Reminder> =
        input.map {
            Reminder(
                id = it.id,
                title = it.title,
                description = it.description,
                dateTime = it.dateTime,
            )
        }

    fun mapResponseToDomain(input: List<TodosItem>): List<Reminder> =
        input.map {
            Reminder(
                id = it.id,
                title = it.title,
                description = it.description,
                dateTime = it.dateTime,
            )
        }

    fun mapDomainToEntities(input: List<Reminder>): List<ReminderEntity> =
        input.map {
            ReminderEntity(
                id = it.id,
                title = it.title,
                description = it.description,
                dateTime = it.dateTime,
            )
        }

    fun mapDomainToEntity(input: Reminder): ReminderEntity = ReminderEntity(
        id = input.id,
        title = input.title,
        description = input.description,
        dateTime = input.dateTime
    )
}