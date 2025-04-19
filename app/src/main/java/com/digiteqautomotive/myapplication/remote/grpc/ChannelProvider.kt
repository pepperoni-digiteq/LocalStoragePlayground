package com.digiteqautomotive.myapplication.remote.grpc

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

object GrpcChannelProvider {
    private const val SERVER_ADDRESS = "10.0.2.2" // Localhost for emulator, or real IP
    private const val SERVER_PORT = 50051

    val channel: ManagedChannel by lazy {
        ManagedChannelBuilder
            .forAddress(SERVER_ADDRESS, SERVER_PORT)
            .usePlaintext() // for development only
            .build()
    }
}