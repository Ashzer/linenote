package org.androidtown.linenote.features.usecases

import org.androidtown.linenote.core.exception.Failure
import org.androidtown.linenote.core.functional.Either
import org.androidtown.linenote.core.interactor.UseCase
import org.androidtown.linenote.core.interactor.UseCase.None
import org.androidtown.linenote.features.linenote.LineNoteData
import org.androidtown.linenote.features.linenote.LineNoteRepository
import javax.inject.Inject

class GetNotes
@Inject constructor(private val noteRepository: LineNoteRepository): UseCase<List<LineNoteData>, None>(){
    override suspend fun run(paramas: None) = noteRepository.notes()
}