/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//소스코드 출처
//https://github.com/android10/Android-CleanArchitecture-Kotlin
package org.androidtown.linenote.core.interactor

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