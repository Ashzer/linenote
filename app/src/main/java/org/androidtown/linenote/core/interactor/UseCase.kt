package org.androidtown.linenote.core.interactor

//출처 표기
import kotlinx.coroutines.*
import org.androidtown.linenote.core.exception.Failure
import org.androidtown.linenote.core.functional.Either

abstract class UseCase<out Type, in Params> where Type : Any{
    abstract suspend fun run(paramas: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure,Type>) -> Unit = {}){
        val job = GlobalScope.async {run (params)}
        runBlocking {
            job.join()
        }
        GlobalScope.launch(Dispatchers.Main) { onResult(job.await())}
    }

    class None
}