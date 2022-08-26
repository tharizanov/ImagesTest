package com.example.test2.app.ui.base

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel

/**
 * Base [ViewModel] class to be inherited by other ViewModels in this application.
 */
abstract class BaseViewModel : ViewModel(), DefaultLifecycleObserver