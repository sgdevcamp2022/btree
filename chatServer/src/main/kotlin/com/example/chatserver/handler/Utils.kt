package com.example.chatserver.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import mu.KotlinLogging
import java.net.URI
import java.net.URLDecoder

fun parseQueryString(url: URI): Map<String, String> {
    val logger = KotlinLogging.logger {  }
    val queryPairs = mutableMapOf<String, String>()

    val query: String = url.query
    logger.info { query }
    val pairs = query.split("&")

    for (pair in pairs){
        val idx = pair.indexOf("=")
        queryPairs[URLDecoder.decode(pair.substring(0, idx), "UTF-8")] =
            URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
    }

    return queryPairs
}

fun jsonMapper(): ObjectMapper = jacksonObjectMapper().apply {
    propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
}