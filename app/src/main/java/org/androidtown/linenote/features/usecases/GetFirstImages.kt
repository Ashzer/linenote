package org.androidtown.linenote.features.usecases

import org.androidtown.linenote.core.exception.Failure
import org.androidtown.linenote.core.functional.Either
import org.androidtown.linenote.core.interactor.UseCase
import org.androidtown.linenote.core.interactor.UseCase.None
import org.androidtown.linenote.features.ImageData
import org.androidtown.linenote.features.repository.LineNoteRepository
import javax.inject.Inject

class GetFirstImages
@Inject constructor(private val noteRepository: LineNoteRepository) : UseCase<List<ImageData>, None>(){
    override suspend fun run(paramas: None) = noteRepository.getFirstImages()
}