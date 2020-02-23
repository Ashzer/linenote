package org.androidtown.linenote.features.repository

import org.androidtown.linenote.core.exception.Failure
import org.androidtown.linenote.core.functional.Either
import org.androidtown.linenote.core.functional.Either.Left
import org.androidtown.linenote.core.functional.Either.Right
import org.androidtown.linenote.features.NoteData
import org.androidtown.linenote.features.ImageData
import org.androidtown.linenote.features.entities.NoteEntity
import javax.inject.Inject

interface LineNoteRepository {
    fun notes(): Either<Failure, List<NoteData>>
    //fun getFirstImageByNoteId(noteId :Int) : Either<Failure, String>
    fun getImages(noteId:Int) : Either<Failure,List<ImageData>>
    fun getNoteById(id:Int):Either<Failure, NoteData>
    fun getFirstImages() : Either<Failure,List<ImageData>>

    fun updateNote(noteData: NoteData) : Either<Failure,Unit>
    fun insertNote(noteData: NoteData) : Either<Failure, Long>
    fun insertImage(imageData: ImageData) : Either<Failure, Unit>
    fun deleteImage(imageData: ImageData) : Either<Failure, Unit>

    class LineNoteDatabase
    @Inject constructor(private val service : LineNoteDatabaseService):
        LineNoteRepository {
        override fun notes() : Either<Failure, List<NoteData>>{
            return try{
                Right(service.notes().map{it.toNotes()})
            }catch (exception: Throwable){
                Left(Failure.DatabaseError)
            }
        }

        override fun getImages(noteId: Int): Either<Failure, List<ImageData>> {
            return try{
                Right(service.getImages(noteId).map{it.toImages()})
            }catch(exception: Throwable){
                Left(Failure.DatabaseError)
            }
        }

        override fun getNoteById(id: Int): Either<Failure, NoteData> {
            return try{
                Right(service.getNoteById(id).toNotes())
            }catch (exception : Throwable){
                Left(Failure.DatabaseError)
            }
        }

        override fun updateNote(noteData: NoteData): Either<Failure, Unit> {
            return try{
                Right(service.updateNote(noteData.toNoteEntity()))
            }catch (exception : Throwable){
                Left(Failure.DatabaseError)
            }
        }

        override fun insertImage(imageData: ImageData): Either<Failure, Unit> {
            return try{
                Right(service.insertImage(imageData.toNoteImageEntity()))
            }catch (exception: Throwable){
                Left(Failure.DatabaseError)
            }
        }

        override fun deleteImage(imageData: ImageData): Either<Failure, Unit> {
            return try{
                Right(service.deleteImage(imageData.toNoteImageEntity()))
            }catch(exception: Throwable){
                Left(Failure.DatabaseError)
            }
        }

        override fun getFirstImages(): Either<Failure, List<ImageData>> {
            return try{
                Right(service.getFirstImages().map{it.toImages()})
            }
            catch(exception:Throwable){
                Left(Failure.DatabaseError)
            }
        }

        override fun insertNote(noteData: NoteData): Either<Failure, Long> {
            return try{
                Right(service.insertNote(noteData.toNoteEntity()))
            }catch (exception : Throwable){
                Left(Failure.DatabaseError)
            }
        }
    }


}