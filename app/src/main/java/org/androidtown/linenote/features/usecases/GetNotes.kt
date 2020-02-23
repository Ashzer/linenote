package org.androidtown.linenote.features.usecases

import org.androidtown.linenote.core.interactor.UseCase
import org.androidtown.linenote.core.interactor.UseCase.None
import org.androidtown.linenote.features.NoteData
import org.androidtown.linenote.features.repository.LineNoteRepository
import javax.inject.Inject

class GetNotes
@Inject constructor(private val noteRepository: LineNoteRepository): UseCase<List<NoteData>, None>(){
    override suspend fun run(paramas: None) = noteRepository.notes()
}