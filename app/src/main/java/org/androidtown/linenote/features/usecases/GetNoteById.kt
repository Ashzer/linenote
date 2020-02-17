package org.androidtown.linenote.features.usecases

import org.androidtown.linenote.core.exception.Failure
import org.androidtown.linenote.core.functional.Either
import org.androidtown.linenote.core.interactor.UseCase
import org.androidtown.linenote.features.linenote.LineNoteData
import org.androidtown.linenote.features.linenote.LineNoteRepository
import javax.inject.Inject

class GetNoteById
@Inject constructor(private val noteRepository: LineNoteRepository) : UseCase<LineNoteData,Int>(){
    override suspend fun run(paramas: Int) = noteRepository.getNoteById(paramas)
}
