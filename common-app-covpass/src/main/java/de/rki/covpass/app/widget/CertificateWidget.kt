package de.rki.covpass.app.widget


import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.ensody.reactivestate.DependencyAccessor
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.journeyapps.barcodescanner.BarcodeEncoder
import de.rki.covpass.app.R
import de.rki.covpass.app.dependencies.covpassDeps


/**
 * Widget to show current certificate on home screen.
 */
public class CertificateWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }


}

@OptIn(DependencyAccessor::class)
internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
) {
    val views = RemoteViews(context.packageName, R.layout.certificate_widget)
    val certs = covpassDeps.certRepository.certs.value

    val mainCert = certs.certificates[0].getMainCertificate()
    val qrContent = mainCert.qrContent

    val bitmap =  BarcodeEncoder().encodeBitmap(
        qrContent,
        BarcodeFormat.QR_CODE,
        context.resources.displayMetrics.widthPixels,
        context.resources.displayMetrics.widthPixels,
        mapOf(EncodeHintType.MARGIN to 0),
    )

    views.setImageViewBitmap(R.id.certificate_qr_imageview, bitmap)
    views.setTextViewText(R.id.certificate_name_textview, mainCert.covCertificate.fullName)

    appWidgetManager.updateAppWidget(appWidgetId, views)
}
