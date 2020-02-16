package org.androidtown.linenote.features.linenote

import org.androidtown.linenote.core.exception.Failure
import org.androidtown.linenote.core.functional.Either
import org.androidtown.linenote.core.functional.Either.Left
import org.androidtown.linenote.core.functional.Either.Right
import org.androidtown.linenote.core.functional.map
import org.androidtown.linenote.features.notepage.NotePageImageData
import org.androidtown.linenote.features.notepage.NotePageImageView
import javax.inject.Inject

interface LineNoteRepository {
    fun notes(): Either<Failure, List<LineNoteData>>
    //fun getFirstImageByNoteId(noteId :Int) : Either<Failure, String>
    fun getImages(noteId:Int) : Either<Failure,List<NotePageImageData>>

    class LineNoteDatabase
    @Inject constructor(private val service : LineNoteDatabaseService): LineNoteRepository{
        override fun notes() : Either<Failure, List<LineNoteData>>{
            return try{
                Right(service.notes().map{it.toNotes()})
            }catch (exception: Throwable){
                Left(Failure.DatabaseError)
            }
        }

        override fun getImages(noteId: Int): Either<Failure, List<NotePageImageData>> {
            return try{
                Right(service.getImages(noteId).map{it.toImages()})
            }catch(exception: Throwable){
                Left(Failure.DatabaseError)
            }
        }
/*
        override fun getFirstImageByNoteId(noteId : Int): Either<Failure, String> {
            return try{
                Right(service.getFirstImageByNoteId(noteId))
            }catch (exception: Throwable){
                Left(Failure.DatabaseError)
            }
        }*/
    }


}