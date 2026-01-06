package com.castlelecs.tg.conteroller

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.yield
import org.drinkless.tdlib.Client
import org.drinkless.tdlib.TdApi
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@RestController
@RequestMapping("/api/tg")
open class AggregationController {
    data class DTO(
        val greeting: String
    )

    @GetMapping("/collect")
    suspend fun collect(): DTO {
        val client = Client.create(
            { obj ->
                if (obj is TdApi.UpdateAuthorizationState) {
                    println("AUTH STATE: ${obj.authorizationState.javaClass.simpleName}")
                }
            },
            { err -> println("ERROR: $err") },
            { err -> println("FATAL: $err") }
        )

        return getAuthStateOnce(client)
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getAuthStateOnce(client: Client): DTO =
        suspendCancellableCoroutine { cont ->
            client.send(TdApi.GetAuthorizationState()) { result ->
                if (cont.isActive) cont.resume(DTO(result.toString()))
            }
        }
}