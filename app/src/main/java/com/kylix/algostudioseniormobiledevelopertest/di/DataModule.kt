package com.kylix.algostudioseniormobiledevelopertest.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kylix.algostudioseniormobiledevelopertest.data.local.TaskDatabase
import com.kylix.algostudioseniormobiledevelopertest.data.local.entitiy.TaskEntity
import com.kylix.algostudioseniormobiledevelopertest.utils.getCurrentDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            get(), TaskDatabase::class.java, "task_database"
        ).addCallback(object: RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                CoroutineScope(Dispatchers.IO).launch {
                    val taskDao = get<TaskDatabase>().taskDao()

                    val today = getCurrentDate()
                    listOf(
                        TaskEntity(title = "Stand up meeting", description = "", date = today, time = "08:30", position = 1),
                        TaskEntity(title ="Register UI", description = "", date = today, time = "09:00", position = 2),
                        TaskEntity(title ="Retrospective Meeting", description = "", date = today, time = "08:30", position = 3),
                        TaskEntity(title ="To do List Mockup", description = "", date = today, time = "10:00", position = 4),
                        TaskEntity(title ="Checkout Mockup", description = "", date = today, time = "13:30", position = 5),
                        TaskEntity(title ="Delete Mockup", description = "", date = today, time = "14:30", position = 6),
                        TaskEntity(title ="Edit Mockup", description = "", date = today, time = "15:30", position = 7),
                        TaskEntity(title ="Slice Screen", description = "", date = today, time = "15:30", position = 8),
                    ).forEach {
                        taskDao.insertTask(it)
                    }
                }
            }
        }).build()
    }
    single { get<TaskDatabase>().taskDao() }
}