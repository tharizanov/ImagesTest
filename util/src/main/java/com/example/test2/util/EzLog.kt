package com.example.test2.util

import android.util.Log

/**
 * Custom logger class, which only posts logs to [Log] if the current build variant is [BuildConfig.DEBUG].
 */
@Suppress("unused")
object EzLog {

    private const val EMPTY_MESSAGE = "_"
    private const val DEFAULT_MESSAGE_SEPARATOR = ", "

    private val TAG = javaClass.simpleName

    /**
     * If TRUE, the log will contain the whole stacktrace leading to this call, otherwise just the message.
     */
    @JvmStatic
    var includeFullStackTrace = false

    /**
     * VERBOSE level log.
     *
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun v(vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.v(TAG, buildMessage(objects))
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
            Log.v(TAG, buildMessage(objects, separator))
    }

    /**
     * DEBUG level log.
     *
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun d(vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, buildMessage(objects))
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
            Log.d(TAG, buildMessage(objects, separator))
    }

    /**
     * INFO level log.
     *
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun i(vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.i(TAG, buildMessage(objects))
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
            Log.i(TAG, buildMessage(objects, separator))
    }

    /**
     * WARNING level log.
     *
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun w(vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.w(TAG, buildMessage(objects))
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
            Log.w(TAG, buildMessage(objects, separator))
    }

    /**
     * ERROR level log.
     *
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun e(vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.e(TAG, buildMessage(objects))
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
            Log.e(TAG, buildMessage(objects, separator))
    }

    /**
     * ASSERT level log.
     *
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun wtf(vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.wtf(TAG, buildMessage(objects))
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
            Log.wtf(TAG, buildMessage(objects, separator))
    }



    private var callingFunctionStackIndex = -1

    /**
     * @param messageParts Objects to be fused into a readable log message.
     * @param separator The separator to split the message parts.
     * @param elements Array of [StackTraceElement]s.
     * @param isFullStackTrace See [includeFullStackTrace].
     * @return The message to display in the log.
     */
    private fun buildMessage(
        messageParts: Array<out Any?>,
        separator: String = DEFAULT_MESSAGE_SEPARATOR,
        elements: Array<StackTraceElement> = Thread.currentThread().stackTrace,
        isFullStackTrace: Boolean = includeFullStackTrace
    ): String {
        findCallingFunctionStackIndex(elements)

        return StringBuilder().apply {
            // Append all message parts
            when (messageParts.size) {
                0 -> append(EMPTY_MESSAGE)
                1 -> append(nonEmptyMsg(messageParts[0]))
                else -> {
                    append(nonEmptyMsg(messageParts[0]))
                    for (i in 1 until messageParts.size)
                        append(separator).append(nonEmptyMsg(messageParts[i]))
                }
            }

            // Now append the stack trace
            when {
                callingFunctionStackIndex < 0 -> {}
                isFullStackTrace -> {
                    for (i in callingFunctionStackIndex until elements.size)
                        append("\n").append(steNameForMessage(elements[i]))
                }
                else -> append("\n").append(steNameForMessage(elements[callingFunctionStackIndex]))
            }
        }.toString()
    }

    private fun nonEmptyMsg(obj: Any?): String =
        obj.toString().ifEmpty { EMPTY_MESSAGE }

    private fun steNameForMessage(ste: StackTraceElement): String =
        "at ${ste.className}.${ste.methodName}()"

    private fun findCallingFunctionStackIndex(elements: Array<StackTraceElement>) {
        if (callingFunctionStackIndex >= 0)
            return

        val thisClassName = javaClass.name
        elements.forEachIndexed { index, stackTraceElement ->
            if (thisClassName == stackTraceElement.className) {
                callingFunctionStackIndex = index + 1
                return@forEachIndexed
            }
        }
    }

}