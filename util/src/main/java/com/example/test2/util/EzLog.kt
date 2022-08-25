package com.example.test2.util

import android.util.Log

/**
 * My personal custom logger class, which only posts logs to [Log]
 * if the current build variant is [BuildConfig.DEBUG].
 */
@Suppress("unused")
object EzLog {

    private const val TAG_PREFIX = "-----"
    private const val MESSAGE_SEPARATOR = ", "
    private const val EMPTY_MESSAGE = "-"

    /**
     * If TRUE, the log will contain the whole stacktrace leading to this call, otherwise just the message.
     */
	var includeFullStackTrace = false

    /**
     * VERBOSE level log.
     *
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun v(vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.v(buildTag(), buildMessage(MESSAGE_SEPARATOR, objects, includeFullStackTrace))
    }

    /**
     * VERBOSE level log, using a custom separator.
     *
     * @param separator The separator to split the specified objects.
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun v_(separator: String, vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.v(buildTag(), buildMessage(separator, objects, includeFullStackTrace))
    }

    /**
     * DEBUG level log.
     *
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun d(vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.d(buildTag(), buildMessage(MESSAGE_SEPARATOR, objects, includeFullStackTrace))
    }

    /**
     * DEBUG level log, using a custom separator.
     *
     * @param separator The separator to split the specified objects.
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun d_(separator: String, vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.d(buildTag(), buildMessage(separator, objects, includeFullStackTrace))
    }

    /**
     * INFO level log.
     *
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun i(vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.i(buildTag(), buildMessage(MESSAGE_SEPARATOR, objects, includeFullStackTrace))
    }

    /**
     * INFO level log, using a custom separator.
     *
     * @param separator The separator to split the specified objects.
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun i_(separator: String, vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.i(buildTag(), buildMessage(separator, objects, includeFullStackTrace))
    }

    /**
     * WARNING level log.
     *
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun w(vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.w(buildTag(), buildMessage(MESSAGE_SEPARATOR, objects, includeFullStackTrace))
    }

    /**
     * WARNING level log, using a custom separator.
     *
     * @param separator The separator to split the specified objects.
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun w_(separator: String, vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.w(buildTag(), buildMessage(separator, objects, includeFullStackTrace))
    }

    /**
     * ERROR level log.
     *
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun e(vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.e(buildTag(), buildMessage(MESSAGE_SEPARATOR, objects, includeFullStackTrace))
    }

    /**
     * ERROR level log, using a custom separator.
     *
     * @param separator The separator to split the specified objects.
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun e_(separator: String, vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.e(buildTag(), buildMessage(separator, objects, includeFullStackTrace))
    }

    /**
     * ASSERT level log.
     *
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun wtf(vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.wtf(buildTag(), buildMessage(MESSAGE_SEPARATOR, objects, includeFullStackTrace))
    }

    /**
     * ASSERT level log, using a custom separator.
     *
     * @param separator The separator to split the specified objects.
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun wtf_(separator: String, vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.wtf(buildTag(), buildMessage(separator, objects, includeFullStackTrace))
    }

    /**
     * @return A human readable string representation of the [StackTraceElement].
     */
    private fun stackTraceToString(ste: StackTraceElement): String =
        "${ste.className.substringAfterLast('.')}.${ste.methodName}()"

    /**
     * @return A log tag that includes the source of the log call.
     */
    private fun buildTag(): String =
        "$TAG_PREFIX${stackTraceToString(Thread.currentThread().stackTrace[4])}"

    /**
     * @param separator The separator to split the message parts.
     * @param messageParts Objects to be fused into a readable log message.
     * @param isFullStackTrace See [includeFullStackTrace].
     * @return The message to display in the log.
     */
    private fun buildMessage(separator: String, messageParts: Array<out Any?>, isFullStackTrace: Boolean): String =
        if (isFullStackTrace) {
            val messageBuilder = StringBuilder(buildMessage(separator, messageParts, false))
            Thread.currentThread().stackTrace.run {
                if (size > 5)
                    for (i in 5 until size)
                        messageBuilder.append("\n").append(stackTraceToString(this[i]))
            }
            messageBuilder.toString()
        } else {
            when (messageParts.size) {
                0 -> EMPTY_MESSAGE
                1 -> messageParts[0].toString()
                else -> StringBuilder("${messageParts[0]}").apply {
                    for (i in 1 until messageParts.size)
                        append(separator).append(messageParts[i])
                }.toString()
            }
        }

}