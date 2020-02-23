package org.androidtown.linenote.features.usecases

import org.androidtown.linenote.core.interactor.UseCase
import org.androidtown.linenote.features.ImageData
import org.androidtown.linenote.features.repository.LineNoteRepository
import javax.inject.Inject

class DeleteImage
@Inject constructor(private val noteRepository: LineNoteRepository): UseCase<Unit, ImageData>(){
    override suspend fun run(paramas: ImageData) = noteRepository.deleteImage(paramas)
}