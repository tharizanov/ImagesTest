package com.example.test2.app.events

/**
 * Create child classes of this class to perform various navigations within the application.
 */
sealed class NavigationEvent

class NavigateToDetailsEvent(val link: String): NavigationEvent()
