package de.rki.covpass.app.widget


import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import com.ensody.reactivestate.DependencyAccessor
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.ibm.health.common.android.utils.getString
import com.journeyapps.barcodescanner.BarcodeEncoder
import de.rki.covpass.app.R
import de.rki.covpass.app.dependencies.covpassDeps
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


/**
 * Widget to show current certificate on home screen.
 */
public class CertificateWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        Log.d("CertificateWidgetProvider", "onUpdate")
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    @OptIn(DependencyAccessor::class)
    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        Log.d("CertificateWidgetProvider", "onDeleted")
        val widgetIdToFullName = covpassDeps.widgetRepository.widgetIdToFullName

        if (appWidgetIds != null) {
            MainScope().launch {
                widgetIdToFullName.update { appWidgetIds.forEach { appWidgetId -> it.remove(appWidgetId) } }
            }
        }

        super.onDeleted(context, appWidgetIds)
    }
}


@OptIn(DependencyAccessor::class)
public fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
) {
    val widgetIdToFullName = covpassDeps.widgetRepository.widgetIdToFullName.value
    val fullName = widgetIdToFullName[appWidgetId]

    val mainCertificates =
        covpassDeps.certRepository.certs.value.certificates.map { groupedCertificates -> groupedCertificates.getMainCertificate() }

    val mainCertificate = mainCertificates.find { it.covCertificate.fullName == fullName }

    val views = RemoteViews(context.packageName, R.layout.certificate_widget)
    views.setTextViewText(R.id.certificate_name_textview, fullName)

    if (mainCertificate != null) {

        views.setViewVisibility(R.id.certificate_status_imageview, View.VISIBLE)

        val qrContent = mainCertificate.qrContent

        val bitmap = BarcodeEncoder().encodeBitmap(
            qrContent,
            BarcodeFormat.QR_CODE,
            context.resources.displayMetrics.widthPixels,
            context.resources.displayMetrics.widthPixels,
            mapOf(EncodeHintType.MARGIN to 0),
        )

        views.setImageViewBitmap(R.id.certificate_qr_imageview, bitmap)
    } else {
        Log.d("CertificateWidget", "No certificate found for $fullName and widget $appWidgetId")
        views.setViewVisibility(R.id.certificate_status_imageview, View.INVISIBLE)
        views.setTextViewText(
            R.id.certificate_widget_info_textview,
            getString(R.string.widget_certificate_not_available),
        )
        views.setImageViewUri(R.id.certificate_qr_imageview, Uri.parse(""))
    }

    appWidgetManager.updateAppWidget(appWidgetId, views)

}

public fun updateAllWidgets(context: Context) {
    val intent = Intent(context, CertificateWidgetProvider::class.java)
    intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
    val ids = AppWidgetManager.getInstance(context).getAppWidgetIds(
        ComponentName(
            context.applicationContext,
            CertificateWidgetProvider::class.java,
        ),
    )
    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
    context.sendBroadcast(intent)
}
