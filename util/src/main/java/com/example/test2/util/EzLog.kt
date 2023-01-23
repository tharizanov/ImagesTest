package com.example.test2.util

import android.util.Log

/**
 * Custom logger class, which only posts logs to [Log] if the current build variant is [BuildConfig.DEBUG].
 */
@Suppress("unused")
object EzLog {

    private const val DEFAULT_MESSAGE_SEPARATOR = ", "
    private const val EMPTY_MESSAGE_SUBSTITUTE = "_"
    private const val ERROR_CODE = -1

    private val TAG = javaClass.simpleName


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////// CUSTOMISATIONS SECTION /////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private var separator = DEFAULT_MESSAGE_SEPARATOR
    private var includeFullStackTrace = false

    /**
     * Print the full stack trace for the next log message only.
     */
    @JvmStatic
    fun fullStackTrace(): EzLog {
        if (BuildConfig.DEBUG) {
            includeFullStackTrace = true
        }
        return this
    }

    /**
     * Print the next log message (only) using a custom separator between the objects.
     */
    @JvmStatic
    fun separator(separator: String): EzLog {
        if (BuildConfig.DEBUG && this.separator != separator) {
            this.separator = separator
        }
        return this
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////// LOGS SECTION ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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



    /**
     * @param messageParts Objects to be fused into a readable log message.
     * @return The message to display in the log.
     */
    private fun buildMessage(messageParts: Array<out Any?>?): String =
        StringBuilder()
            .appendMessageParts(messageParts)
            .appendStackTrace()
            .toString()


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////// MESSAGE PARTS SECTION /////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private fun StringBuilder.appendMessageParts(messageParts: Array<out Any?>?): StringBuilder {
        if (messageParts.isNullOrEmpty()) {
            append(EMPTY_MESSAGE_SUBSTITUTE)
        } else {
            appendNonEmpty(messageParts[0])
            if (messageParts.size > 1)
                for (i in 1 until messageParts.size)
                    append(separator).appendNonEmpty(messageParts[i])
        }

        if (separator != DEFAULT_MESSAGE_SEPARATOR)
            separator = DEFAULT_MESSAGE_SEPARATOR

        return this@appendMessageParts
    }

    private fun StringBuilder.appendNonEmpty(obj: Any?): StringBuilder {
        if (obj is Throwable) {
            append("\n${obj::class.simpleName ?: "Unnamed class"}: ${obj.message}\n")
            for (ste in obj.stackTrace)
                append(stackTraceName(ste)).append("\n")
        } else {
            append(obj.toString().trim().ifEmpty { EMPTY_MESSAGE_SUBSTITUTE })
        }
        return this@appendNonEmpty
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////// STACK TRACE SECTION //////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private var callingFunctionStackIndex = ERROR_CODE

    private fun StringBuilder.appendStackTrace(): StringBuilder {
        val elements = Thread.currentThread().stackTrace

        when {
            findCallingFunctionStackIndex(elements).not() -> {} // Do nothing.
            includeFullStackTrace -> {
                for (i in callingFunctionStackIndex until elements.size)
                    append("\n").append(stackTraceName(elements[i]))
            }
            else -> append("\n").append(stackTraceName(elements[callingFunctionStackIndex]))
        }

        if (includeFullStackTrace)
            includeFullStackTrace = false

        return this@appendStackTrace
    }

    private fun findCallingFunctionStackIndex(elements: Array<StackTraceElement>): Boolean {
        if (callingFunctionStackIndex != ERROR_CODE)
            return true

        val thisClassName = this@EzLog.javaClass.name
        elements.forEachIndexed { index, stackTraceElement ->
            if (thisClassName == stackTraceElement.className) {
                callingFunctionStackIndex = index + 1
                return true
            }
        }

        return false
    }

    private fun stackTraceName(ste: StackTraceElement): String =
        "at ${ste.className}.${ste.methodName}()"

}