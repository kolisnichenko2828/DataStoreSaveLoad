package com.staskokoc.datastoresaveload.datastore

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val language: String = "English",
    val name: String = "NoName"
)