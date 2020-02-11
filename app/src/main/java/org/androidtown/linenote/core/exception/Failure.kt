package org.androidtown.linenote.core.exception

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object DatabaseError : Failure()

    abstract class FeatureFailure : Failure()
}