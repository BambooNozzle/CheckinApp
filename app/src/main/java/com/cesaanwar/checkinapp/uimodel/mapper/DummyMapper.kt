package com.cesaanwar.checkinapp.uimodel.mapper

import com.cesaanwar.checkinapp.R
import com.cesaanwar.checkinapp.uimodel.DashboardInfoUIModel
import com.cesaanwar.checkinapp.uimodel.DashboardMenuUIModel
import com.cesaanwar.checkinapp.uimodel.MenuMenuUIModel
import com.cesaanwar.checkinapp.uimodel.MenuStatisticsUIModel

object DummyMapper {

    fun getDashboardMenus(): List<DashboardMenuUIModel> {
        val res = mutableListOf<DashboardMenuUIModel>()
        res.add(
            DashboardMenuUIModel(
                icon = R.drawable.information_outline,
                title = "Information"
            )
        )
        res.add(
            DashboardMenuUIModel(
                icon = R.drawable.barcode_scan,
                title = "Product Check"
            )
        )
        res.add(
            DashboardMenuUIModel(
                icon = R.drawable.cart,
                title = "SKU Promo"
            )
        )
        res.add(
            DashboardMenuUIModel(
                icon = R.drawable.barcode_scan,
                title = "Ringkasan OOS"
            )
        )
        res.add(
            DashboardMenuUIModel(
                icon = R.drawable.chart_line,
                title = "Store Investment"
            )
        )
        return res
    }

    fun getMenuMenus(): List<MenuMenuUIModel> {
        val res = mutableListOf<MenuMenuUIModel>()
        res.add(
            MenuMenuUIModel(
                icon = R.drawable.store,
                title = "Kunjungan",
                isVisit = true
            )
        )
        res.add(
            MenuMenuUIModel(
                icon = R.drawable.bullseye_arrow,
                title = "Target"
            )
        )
        res.add(
            MenuMenuUIModel(
                icon = R.drawable.monitor_dashboard,
                title = "Dashboard"
            )
        )
        res.add(
            MenuMenuUIModel(
                icon = R.drawable.clipboard_text_clock,
                title = "Transmission History"
            )
        )
        return res
    }

    fun getMenuStatistics(): List<MenuStatisticsUIModel> {
        val res = mutableListOf<MenuStatisticsUIModel>()
        res.add(
            MenuStatisticsUIModel(
                icon = R.drawable.alert_circle,
                value = "150",
                title = "Total Score"
            )
        )
        res.add(
            MenuStatisticsUIModel(
                icon = R.drawable.checkbox_marked_circle,
                value = "150",
                title = "Actual Score"
            )
        )
        res.add(
            MenuStatisticsUIModel(
                icon = R.drawable.chart_pie,
                value = "50%",
                title = "Percentage"
            )
        )
        return res
    }

    fun getDashboardInfo(): List<DashboardInfoUIModel> {
        val res = mutableListOf<DashboardInfoUIModel>()
        res.add(
            DashboardInfoUIModel(
                color = "#efb400"
            )
        )
        res.add(
            DashboardInfoUIModel(
                color = "#24ccbf"
            )
        )
        res.add(
            DashboardInfoUIModel(
                color = "#3972b4"
            )
        )
        return res
    }

}