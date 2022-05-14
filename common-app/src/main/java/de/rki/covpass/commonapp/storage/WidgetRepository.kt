package de.rki.covpass.commonapp.storage

import com.ensody.reactivestate.SuspendMutableValueFlow
import de.rki.covpass.sdk.storage.CborSharedPrefsStore


/**
 * Repository for [widgetIdToFullName] that maps a widget id of a certificate widget to the full name of the [CombinedCertificate]
 */
public class WidgetRepository(store: CborSharedPrefsStore) {

    public val widgetIdToFullName: SuspendMutableValueFlow<HashMap<Int, String>> = store.getData(
        "widgetIdToFullName",
        HashMap(),
    )

}
