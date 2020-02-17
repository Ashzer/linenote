package org.androidtown.linenote.features.usecases

import org.androidtown.linenote.core.exception.Failure
import org.androidtown.linenote.core.functional.Either
import org.androidtown.linenote.core.interactor.UseCase
import org.androidtown.linenote.core.interactor.UseCase.None
import org.androidtown.linenote.features.linenote.LineNoteData
import org.androidtown.linenote.features.linenote.LineNoteRepository
import javax.inject.Inject

class UpdateNote
@Inject constructor(private val noteRepository: LineNoteRepository) : UseCase<Unit, LineNoteData>(){
    override suspend fun run(paramas: LineNoteData) = noteRepository.updateNote(paramas)
}