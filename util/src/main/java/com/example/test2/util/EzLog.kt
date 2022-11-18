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

    private var separator = DEFAULT_MESSAGE_SEPARATOR
    private var includeFullStackTrace = false

    /**
     * Add customisations only to the next log message. Customisations will be reset after the log.
     *
     * @param separator The separator to split the log message parts.
     * @param fullStackTrace If TRUE - the log will contain the whole stack trace leading to this call.
     *                       If FALSE - only the last stack trace.
     * @return The same logger object.
     */
    @JvmStatic
    fun with(separator: String = DEFAULT_MESSAGE_SEPARATOR, fullStackTrace: Boolean = false): EzLog {
        if (BuildConfig.DEBUG) {
            if (this.separator != separator)
                this.separator = separator

            if (fullStackTrace)
                includeFullStackTrace = true
        }
        return this
    }

    /**
     * VERBOSE level log.
     *
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun verbose(vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.v(TAG, buildMessage(objects))
    }

    /**
     * DEBUG level log.
     *
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun debug(vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, buildMessage(objects))
    }

    /**
     * INFO level log.
     *
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun info(vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.i(TAG, buildMessage(objects))
    }

    /**
     * WARNING level log.
     *
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun warning(vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.w(TAG, buildMessage(objects))
    }

    /**
     * ERROR level log.
     *
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun error(vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.e(TAG, buildMessage(objects))
    }

    /**
     * ASSERT level log.
     *
     * @param objects Objects used to build the log message.
     */
    @JvmStatic
    fun assert(vararg objects: Any?) {
        if (BuildConfig.DEBUG)
            Log.wtf(TAG, buildMessage(objects))
    }



    private var callingFunctionStackIndex = -1

    /**
     * @param messageParts Objects to be fused into a readable log message.
     * @return The message to display in the log.
     */
    private fun buildMessage(messageParts: Array<out Any?>): String =
        StringBuilder()
            .appendMessageParts(messageParts)
            .appendStackTrace()
            .toString()

    private fun StringBuilder.appendMessageParts(messageParts: Array<out Any?>): StringBuilder =
        when (messageParts.size) {
            0 -> append(EMPTY_MESSAGE)
            1 -> append(nonEmptyMessage(messageParts[0]))
            else -> {
                append(nonEmptyMessage(messageParts[0]))
                for (i in 1 until messageParts.size) {
                    append(separator).append(nonEmptyMessage(messageParts[i]))
                }
                if (separator != DEFAULT_MESSAGE_SEPARATOR) {
                    separator = DEFAULT_MESSAGE_SEPARATOR
                }
                this@appendMessageParts
            }
        }

    private fun StringBuilder.appendStackTrace(): StringBuilder {
        val elements = Thread.currentThread().stackTrace
        findCallingFunctionStackIndex(elements)
        when {
            callingFunctionStackIndex < 0 -> {}
            includeFullStackTrace -> {
                includeFullStackTrace = false
                for (i in callingFunctionStackIndex until elements.size)
                    append("\n").append(stackTraceName(elements[i]))
            }
            else -> append("\n").append(stackTraceName(elements[callingFunctionStackIndex]))
        }
        return this@appendStackTrace
    }

    private fun findCallingFunctionStackIndex(elements: Array<StackTraceElement>) {
        if (callingFunctionStackIndex >= 0)
            return

        val thisClassName = this@EzLog.javaClass.name
        elements.forEachIndexed { index, stackTraceElement ->
            if (thisClassName == stackTraceElement.className) {
                callingFunctionStackIndex = index + 1
                return@forEachIndexed
            }
        }
    }

    private fun nonEmptyMessage(obj: Any?): String =
        obj.toString().trim().ifEmpty { EMPTY_MESSAGE }

    private fun stackTraceName(ste: StackTraceElement): String =
        "at ${ste.className}.${ste.methodName}()"

}