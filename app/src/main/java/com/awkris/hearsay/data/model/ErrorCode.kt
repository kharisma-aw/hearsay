package com.awkris.hearsay.data.model

enum class ErrorCode(val text: String) {
    APIKEYDISABLED("apiKeyDisabled"),
    APIKEYEXHAUSTED("apiKeyExhausted"),
    APIKEYINVALID("apiKeyInvalid"),
    APIKEYMISSING("apiKeyMissing"),
    PARAMETERINVALID("parameterInvalid"),
    PARAMETERSMISSING("parametersMissing"),
    RATELIMITED("rateLimited"),
    SOURCESTOOMANY("sourcesTooMany"),
    SOURCEDOESNOTEXIST("sourceDoesNotExist"),
    UNEXPECTEDERROR("unexpectedError");

    companion object {
        private val textMap = values().associateBy { it.text }
        fun valueOfText(text: String) = textMap[text]?: UNEXPECTEDERROR
    }
}