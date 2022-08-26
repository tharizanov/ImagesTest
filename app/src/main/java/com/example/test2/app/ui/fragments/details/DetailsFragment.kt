package com.example.test2.app.ui.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.test2.app.databinding.FragmentDetailsBinding

/**
 * [Fragment] displaying the details of an item from the home screen.
 */
class DetailsFragment : Fragment() {

    companion object {
        const val ARGS_KEY_LINK = "ARGS_KEY_LINK"
    }

    private var binding: FragmentDetailsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Perform initial setup on the WebView and immediately load the URL from the arguments.
        binding?.detailsWebview?.let { webview ->
            setupWebView(webview)
            arguments?.getString(ARGS_KEY_LINK)?.let { link ->
                webview.loadUrl(link)
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setupWebView(webView: WebView) {
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let { view?.loadUrl(it) }
                return true
            }
        }

        webView.settings.run {
            loadsImagesAutomatically = true
            javaScriptEnabled = true
        }

        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
    }

}