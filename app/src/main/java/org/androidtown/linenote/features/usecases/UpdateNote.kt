package org.androidtown.linenote.features.usecases

import org.androidtown.linenote.core.interactor.UseCase
import org.androidtown.linenote.features.NoteData
import org.androidtown.linenote.features.repository.LineNoteRepository
import javax.inject.Inject

class UpdateNote
@Inject constructor(private val noteRepository: LineNoteRepository) : UseCase<Unit, NoteData>() {
    override suspend fun run(paramas: NoteData) = noteRepository.updateNote(paramas)
}