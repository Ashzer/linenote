package org.androidtown.linenote.features.usecases

import org.androidtown.linenote.core.interactor.UseCase
import org.androidtown.linenote.features.repository.LineNoteRepository
import org.androidtown.linenote.features.ImageData
import javax.inject.Inject

class GetImages
@Inject constructor(private val noteRepository: LineNoteRepository) : UseCase<List<ImageData>,Int>(){
    override suspend fun run(paramas: Int) = noteRepository.getImages(paramas)
}