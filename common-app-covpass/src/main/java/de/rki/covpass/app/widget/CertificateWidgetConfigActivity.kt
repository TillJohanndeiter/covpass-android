package de.rki.covpass.app.widget

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.LayoutRes
import com.ensody.reactivestate.android.reactiveState
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ibm.health.common.android.utils.BaseHookedActivity
import de.rki.covpass.app.R
import de.rki.covpass.app.dependencies.covpassDeps
import de.rki.covpass.app.detail.adapter.DetailItem
import de.rki.covpass.app.main.CertificateViewModel
import de.rki.covpass.app.main.WidgetViewModel
import de.rki.covpass.commonapp.BaseActivity
import de.rki.covpass.commonapp.dependencies.commonDeps
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

public class CertificateWidgetConfigActivity(@LayoutRes contentLayoutId: Int = 0) :
    BaseHookedActivity(contentLayoutId = contentLayoutId) {

    private val viewModel by reactiveState { WidgetViewModel(scope) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setResult(Activity.RESULT_CANCELED)
        val appWidgetId = intent?.extras?.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID,
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID


        val mainCertificates =
            covpassDeps.certRepository.certs.value.certificates.map { groupedCertificates -> groupedCertificates.getMainCertificate() }

        val holderNames =
            mainCertificates.map { combinedCertificate -> combinedCertificate.covCertificate.fullName }.toTypedArray()

        var selectedIdx = 0

        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.widget_configuration_dialog_title))
            .setNeutralButton(resources.getString(R.string.cancel)) { _, _ ->
                finish()
            }
            .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                viewModel.setWidgetIdToSelectedName(this, appWidgetId, holderNames[selectedIdx])
                val resultValue = Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                setResult(Activity.RESULT_OK, resultValue)
                finish()

            }
            .setSingleChoiceItems(holderNames, 0) { _, which ->
                selectedIdx = which
            }
            .show()


    }

    override fun setLoading(isLoading: Boolean) {}
    override fun onError(error: Throwable) {
        commonDeps.errorHandler.handleError(error, supportFragmentManager)

    }

}
