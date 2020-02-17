package org.androidtown.linenote.features.linenote

import org.androidtown.linenote.core.exception.Failure
import org.androidtown.linenote.core.functional.Either
import org.androidtown.linenote.core.functional.Either.Left
import org.androidtown.linenote.core.functional.Either.Right
import org.androidtown.linenote.core.interactor.UseCase.None
import org.androidtown.linenote.features.notepage.NotePageImageData
import org.androidtown.linenote.features.notepage.NotePageImageView
import javax.inject.Inject

interface LineNoteRepository {
    fun notes(): Either<Failure, List<LineNoteData>>
    //fun getFirstImageByNoteId(noteId :Int) : Either<Failure, String>
    fun getImages(noteId:Int) : Either<Failure,List<NotePageImageData>>
    fun getNoteById(id:Int):Either<Failure, LineNoteData>

    fun updateNote(lineNoteData:LineNoteData) : Either<Failure,Unit>


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

        override fun getNoteById(id: Int): Either<Failure, LineNoteData> {
            return try{
                Right(service.getNoteById(id).toNotes())
            }catch (exception : Throwable){
                Left(Failure.DatabaseError)
            }
        }

        override fun updateNote(lineNoteData: LineNoteData): Either<Failure, Unit> {
            return try{
                Right(service.updateNote(lineNoteData.toNoteEntity()))
            }catch (exception : Throwable){
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