package org.androidtown.linenote.features.linenote

import org.androidtown.linenote.core.exception.Failure
import org.androidtown.linenote.core.functional.Either
import org.androidtown.linenote.core.functional.Either.Left
import org.androidtown.linenote.core.functional.Either.Right
import javax.inject.Inject

interface LineNoteRepository {
    fun notes(): Either<Failure, List<LineNoteData>>

    class LineNoteDatabase
    @Inject constructor(private val service : LineNoteDatabaseService): LineNoteRepository{
        override fun notes() : Either<Failure, List<LineNoteData>>{
            return try{
                Right(service.notes().map{it.toNotes()})
            }catch (exception: Throwable){
                Left(Failure.DatabaseError)
            }

        }
    }
}