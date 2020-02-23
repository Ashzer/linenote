package org.androidtown.linenote.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import org.androidtown.linenote.AndroidApplication
import org.androidtown.linenote.core.di.database.NoteDatabase
import org.androidtown.linenote.features.repository.LineNoteRepository
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {
    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideNoteDatabase(): NoteDatabase {
        val db = Room.databaseBuilder(
            application,
            NoteDatabase::class.java,
            "notes"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Thread {
                    db.beginTransactionNonExclusive()
                }
            }
        }).build()

        return db
    }

    @Provides
    @Singleton
    fun provideLineNoteRepository(dataSource: LineNoteRepository.LineNoteDatabase): LineNoteRepository =
        dataSource
}