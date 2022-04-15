package de.rki.covpass.app.widget

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import de.rki.covpass.app.R
import de.rki.covpass.app.dependencies.covpassDeps
import de.rki.covpass.commonapp.BaseActivity

public class CertificateWidgetConfigActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.CovPassAppTheme);
        setResult(Activity.RESULT_CANCELED)


        val mainCertificates =
            covpassDeps.certRepository.certs.value.certificates.map { groupedCertificates -> groupedCertificates.getMainCertificate() }

        val holderNames =
            mainCertificates.map { combinedCertificate -> combinedCertificate.covCertificate.fullName }.toTypedArray()

        MaterialAlertDialogBuilder(this)
            .setTitle("TODO: Title")
            .setNeutralButton(resources.getString(R.string.cancel)) { _, _ ->
                finish()
            }
            .setPositiveButton(resources.getString(R.string.ok)) { _, which ->
                val selectedName = holderNames[which]
                finish()
            }
            .setSingleChoiceItems(holderNames, 1) { _, _ ->
            }
            .show()

        val appWidgetId = intent?.extras?.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID,
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID

        val appWidgetManager = AppWidgetManager.getInstance(this)

        val views = RemoteViews(packageName, R.layout.certificate_widget)
        appWidgetManager.updateAppWidget(appWidgetId, views)

        val resultValue = Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        setResult(Activity.RESULT_OK, resultValue)

    }
}
