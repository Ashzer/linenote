package org.androidtown.linenote.features.usecases

import org.androidtown.linenote.core.interactor.UseCase
import org.androidtown.linenote.features.repository.LineNoteRepository
import javax.inject.Inject

class DeleteNoteById
@Inject constructor(private val noteRepository: LineNoteRepository) : UseCase<Unit, Int>(){
    override suspend fun run(paramas: Int) = noteRepository.deleteNoteByNoteId(paramas)
}