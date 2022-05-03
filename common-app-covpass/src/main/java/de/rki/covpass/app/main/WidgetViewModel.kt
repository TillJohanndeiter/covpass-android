/*
 * (C) Copyright IBM Deutschland GmbH 2021
 * (C) Copyright IBM Corp. 2021
 */

package de.rki.covpass.app.main

import android.appwidget.AppWidgetManager
import android.content.Context
import android.util.Log
import com.ensody.reactivestate.BaseReactiveState
import com.ensody.reactivestate.DependencyAccessor
import com.ibm.health.common.android.utils.BaseEvents
import de.rki.covpass.app.dependencies.CovpassDependencies
import de.rki.covpass.app.dependencies.covpassDeps
import de.rki.covpass.app.widget.updateAppWidget
import kotlinx.coroutines.CoroutineScope


//TODO: Documentation
/**
 * ViewModel providing the [setWidgetIdToSelectedName]
 */
internal class WidgetViewModel constructor(
    scope: CoroutineScope,
) : BaseReactiveState<BaseEvents>(scope) {


    @OptIn(DependencyAccessor::class)
    val widgetIdToFullName = covpassDeps.widgetRepository.widgetIdToFullName

    fun setWidgetIdToSelectedName(context:Context, appWidgetId: Int, selectedName: String) {
        launch {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            widgetIdToFullName.update { it[appWidgetId] = selectedName }
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }
}
