package com.publicissapient.configinitializer.presentation.ui.home

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.lifecycle.ViewModelProvider
import com.publicissapient.configinitializer.R
import com.publicissapient.configinitializer.com.publicissapient.configinitializer.presentation.ui.home.HomeWidgets
import com.publicissapient.configinitializer.model.CHUCK
import com.publicissapient.configinitializer.model.ENVIRONMENT
import com.publicissapient.configinitializer.presentation.theme.ConfigInitializerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var widgets: HomeWidgets


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        setContent {
            ConfigInitializerTheme {
                Scaffold(
                    topBar = {
                        widgets.ConfigAppTopBar(getString(R.string.appname_launcher))
                    },
                    bottomBar = {
                        widgets.ConfigAppBottomBar(
                            launchButtonOnClick(),
                            "Launch ${getString(R.string.target_app)}"
                        )
                    }
                ) {
                    widgets.ConfigAppHomeLayout(
                        it,
                        viewModel.getListItems()
                    )
                }
            }
        }
    }

    private fun launchButtonOnClick(): () -> Unit = {
        /*val pkg = (viewModel.getListItems()?.targetPackage ?: "")
        val cls = "$pkg.search.SearchActivity"
        val intent = Intent().apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            component = ComponentName(pkg, cls)
            putExtra(
                ENVIRONMENT,
                viewModel.getListItems()?.items?.first()?.subItems?.first()?.value
            )
            putExtra(CHUCK, viewModel.getListItems()?.items?.get(1)?.subItems?.first()?.value)
        }
        startActivity(intent)*/
        Log.d("ARGUMENTS", widgets.args.toString())
    }
}
