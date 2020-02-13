package org.androidtown.linenote.core.exception

sealed class Failure {

    object DatabaseError : Failure()

    abstract class FeatureFailure : Failure()
}