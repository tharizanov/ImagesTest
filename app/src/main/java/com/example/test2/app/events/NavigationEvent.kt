package com.example.test2.app.events

sealed class NavigationEvent

class NavigateToDetailsEvent(val link: String): NavigationEvent()
