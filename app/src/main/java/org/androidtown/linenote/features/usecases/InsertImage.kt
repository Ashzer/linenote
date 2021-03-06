package org.androidtown.linenote.features.usecases

import org.androidtown.linenote.core.interactor.UseCase
import org.androidtown.linenote.features.repository.LineNoteRepository
import org.androidtown.linenote.features.ImageData
import javax.inject.Inject

class InsertImage
@Inject constructor(private val noteRepository: LineNoteRepository) : UseCase<Unit, ImageData>(){
    override suspend fun run(paramas: ImageData) = noteRepository.insertImage(paramas)
}