package de.rki.covpass.app.widget

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.ensody.reactivestate.android.reactiveState
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ibm.health.common.android.utils.BaseHookedActivity
import de.rki.covpass.app.R
import de.rki.covpass.app.dependencies.covpassDeps
import de.rki.covpass.app.main.WidgetViewModel
import de.rki.covpass.commonapp.dependencies.commonDeps

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
            mainCertificates.map { combinedCertificate -> combinedCertificate.covCertificate.fullName }
                .toTypedArray()


        when {
            mainCertificates.isEmpty() -> {
                Toast.makeText(this, R.string.widget_configuration_no_certificate_added, Toast.LENGTH_LONG).show()
                finish()
            }
            mainCertificates.size == 1 -> {
                viewModel.setWidgetIdToSelectedName(this, appWidgetId, holderNames[0])
                successfullyFinish(appWidgetId)
            }
            else -> {
                showDialogToSelectHolder(appWidgetId, holderNames)
            }
        }
    }

    private fun showDialogToSelectHolder(
        appWidgetId: Int,
        holderNames: Array<String>,
    ) {
        var selectedIdx = 0

        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.widget_configuration_dialog_title))
            .setNeutralButton(resources.getString(R.string.cancel)) { _, _ ->
                finish()
            }
            .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                viewModel.setWidgetIdToSelectedName(this, appWidgetId, holderNames[selectedIdx])
                successfullyFinish(appWidgetId)

            }
            .setSingleChoiceItems(holderNames, 0) { _, which ->
                selectedIdx = which
            }
            .show()
    }

    private fun successfullyFinish(appWidgetId: Int) {
        val resultValue = Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        setResult(RESULT_OK, resultValue)
        finish()
    }

    override fun setLoading(isLoading: Boolean) {}

    override fun onError(error: Throwable) {
        commonDeps.errorHandler.handleError(error, supportFragmentManager)
    }

}
