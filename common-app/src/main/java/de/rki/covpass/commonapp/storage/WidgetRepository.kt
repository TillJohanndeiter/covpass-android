package de.rki.covpass.commonapp.storage

import com.ensody.reactivestate.SuspendMutableValueFlow
import de.rki.covpass.sdk.storage.CborSharedPrefsStore

public class WidgetRepository(store: CborSharedPrefsStore) {

    public val widgetIdToFullName: SuspendMutableValueFlow<HashMap<Int, String>> = store.getData(
        "widgetIdToFullName",
        HashMap(),
    )

}
