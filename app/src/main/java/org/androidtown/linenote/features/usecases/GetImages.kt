package org.androidtown.linenote.features.usecases

import org.androidtown.linenote.core.interactor.UseCase
import org.androidtown.linenote.features.linenote.LineNoteRepository
import org.androidtown.linenote.features.notepage.NotePageImageData
import javax.inject.Inject

class GetImages
@Inject constructor(private val noteRepository: LineNoteRepository) : UseCase<List<NotePageImageData>,Int>(){
    override suspend fun run(paramas: Int) = noteRepository.getImages(paramas)
}