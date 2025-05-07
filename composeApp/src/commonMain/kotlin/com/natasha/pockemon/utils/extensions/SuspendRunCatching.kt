package com.natasha.pockemon.utils.extensions


import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.TimeoutCancellationException

// https://github.com/android/nowinandroid/blob/607c24e7f7399942e278af663ea4ad350e5bbc3a/core/data/src/main/java/com/google/samples/apps/nowinandroid/core/data/SyncUtilities.kt#L57
// https://github.com/Kotlin/kotlinx.coroutines/issues/1814
/**
 * Attempts [block], returning a successful [Result] if it succeeds, otherwise a [Result.Failure]
 * taking care not to break structured concurrency
 */
suspend inline fun <T, R> T.suspendRunCatching(block: suspend T.() -> R): Result<R> = try {
    Result.success(block())
} catch (t: TimeoutCancellationException) {
    Result.failure(t)
} catch (cancellationException: CancellationException) {
    throw cancellationException
} catch (t: Throwable) {
    Result.failure(t)
}

suspend inline fun <R> suspendRunCatching(block: suspend () -> R): Result<R> = try {
    Result.success(block())
} catch (t: TimeoutCancellationException) {
    Result.failure(t)
} catch (cancellationException: CancellationException) {
    throw cancellationException
} catch (e: Throwable) {
    Result.failure(e)
}
