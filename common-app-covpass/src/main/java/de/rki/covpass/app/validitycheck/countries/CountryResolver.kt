/*
 * (C) Copyright IBM Deutschland GmbH 2021
 * (C) Copyright IBM Corp. 2021
 */

package de.rki.covpass.app.validitycheck.countries

import com.ibm.health.common.android.utils.getString
import de.rki.covpass.app.R

public object CountryResolver {

    public val defaultDeDomesticCountry: Country = Country(
        R.string.DE2,
        "DE_domestic",
        R.drawable.flag_de
    )

    public val defaultDeCountry: Country = Country(
        R.string.DE,
        "DE",
        R.drawable.flag_de
    )

    private val countryList: Map<String, Country> = listOf(
        Country(
            R.string.IT,
            "IT",
            R.drawable.flag_it
        ),
        Country(
            R.string.LT,
            "LT",
            R.drawable.flag_lt
        ),
        Country(
            R.string.DK,
            "DK",
            R.drawable.flag_dk
        ),
        Country(
            R.string.GR,
            "GR",
            R.drawable.flag_gr
        ),
        Country(
            R.string.CZ,
            "CZ",
            R.drawable.flag_cz
        ),
        Country(
            R.string.HR,
            "HR",
            R.drawable.flag_hr
        ),
        Country(
            R.string.IS,
            "IS",
            R.drawable.flag_is
        ),
        Country(
            R.string.PT,
            "PT",
            R.drawable.flag_pt
        ),
        Country(
            R.string.PL,
            "PL",
            R.drawable.flag_pl
        ),
        Country(
            R.string.BE,
            "BE",
            R.drawable.flag_be
        ),
        Country(
            R.string.BG,
            "BG",
            R.drawable.flag_bg
        ),
        defaultDeDomesticCountry,
        defaultDeCountry,
        Country(
            R.string.LU,
            "LU",
            R.drawable.flag_lu
        ),
        Country(
            R.string.EE,
            "EE",
            R.drawable.flag_ee
        ),
        Country(
            R.string.CY,
            "CY",
            R.drawable.flag_cy
        ),
        Country(
            R.string.ES,
            "ES",
            R.drawable.flag_es
        ),
        Country(
            R.string.NL,
            "NL",
            R.drawable.flag_nl
        ),
        Country(
            R.string.AT,
            "AT",
            R.drawable.flag_at
        ),
        Country(
            R.string.LV,
            "LV",
            R.drawable.flag_lv
        ),
        Country(
            R.string.LI,
            "LI",
            R.drawable.flag_li
        ),
        Country(
            R.string.FI,
            "FI",
            R.drawable.flag_fi
        ),
        Country(
            R.string.SE,
            "SE",
            R.drawable.flag_se
        ),
        Country(
            R.string.SI,
            "SI",
            R.drawable.flag_si
        ),
        Country(
            R.string.RO,
            "RO",
            R.drawable.flag_ro
        ),
        Country(
            R.string.NO,
            "NO",
            R.drawable.flag_no
        ),
        Country(
            R.string.SK,
            "SK",
            R.drawable.flag_sk
        ),
        Country(
            R.string.FR,
            "FR",
            R.drawable.flag_fr
        ),
        Country(
            R.string.MT,
            "MT",
            R.drawable.flag_mt
        ),
        Country(
            R.string.HU,
            "HU",
            R.drawable.flag_hu
        ),
        Country(
            R.string.IE,
            "IE",
            R.drawable.flag_ie
        ),
        Country(
            R.string.CH,
            "CH",
            R.drawable.flag_ch
        ),
        Country(
            R.string.UA,
            "UA",
            R.drawable.flag_ua
        ),
        Country(
            R.string.VA,
            "VA",
            R.drawable.flag_va
        ),
        Country(
            R.string.SM,
            "SM",
            R.drawable.flag_sm
        ),
        Country(
            R.string.TR,
            "TR",
            R.drawable.flag_tr
        ),
        Country(
            R.string.MK,
            "MK",
            R.drawable.flag_mk
        ),
        Country(
            R.string.AD,
            "AD",
            R.drawable.flag_ad
        ),
        Country(
            R.string.MC,
            "MC",
            R.drawable.flag_mc
        ),
        Country(
            R.string.FO,
            "FO",
            R.drawable.flag_fo
        ),
        Country(
            R.string.MA,
            "MA",
            R.drawable.flag_ma
        ),
        Country(
            R.string.AL,
            "AL",
            R.drawable.flag_al
        ),
        Country(
            R.string.IL,
            "IL",
            R.drawable.flag_il
        ),
        Country(
            R.string.PA,
            "PA",
            R.drawable.flag_pa
        ),
        Country(
            R.string.TG,
            "TG",
            R.drawable.flag_tg
        ),
        Country(
            R.string.SG,
            "SG",
            R.drawable.flag_sg
        ),
        Country(
            R.string.GB,
            "GB",
            R.drawable.flag_gb
        ),
        Country(
            R.string.AM,
            "AM",
            R.drawable.flag_am
        ),
        Country(
            R.string.GE,
            "GE",
            R.drawable.flag_ge
        ),
        Country(
            R.string.NZ,
            "NZ",
            R.drawable.flag_nz
        ),
        Country(
            R.string.DZ,
            "DZ",
            R.drawable.flag_dz
        ),
        Country(
            R.string.AZ,
            "AZ",
            R.drawable.flag_az
        ),
        Country(
            R.string.BH,
            "BH",
            R.drawable.flag_bh
        ),
        Country(
            R.string.BJ,
            "BJ",
            R.drawable.flag_bj
        ),
        Country(
            R.string.BA,
            "BA",
            R.drawable.flag_ba
        ),
        Country(
            R.string.BR,
            "BR",
            R.drawable.flag_br
        ),
        Country(
            R.string.BI,
            "BI",
            R.drawable.flag_bi
        ),
        Country(
            R.string.CV,
            "CV",
            R.drawable.flag_cv
        ),
        Country(
            R.string.CA,
            "CA",
            R.drawable.flag_ca
        ),
        Country(
            R.string.CL,
            "CL",
            R.drawable.flag_cl
        ),
        Country(
            R.string.CO,
            "CO",
            R.drawable.flag_co
        ),
        Country(
            R.string.DO,
            "DO",
            R.drawable.flag_do
        ),
        Country(
            R.string.SV,
            "SV",
            R.drawable.flag_sv
        ),
        Country(
            R.string.EG,
            "EG",
            R.drawable.flag_eg
        ),
        Country(
            R.string.HK,
            "HK",
            R.drawable.flag_hk
        ),
        Country(
            R.string.JP,
            "JP",
            R.drawable.flag_jp
        ),
        Country(
            R.string.JO,
            "JO",
            R.drawable.flag_jo
        ),
        Country(
            R.string.KZ,
            "KZ",
            R.drawable.flag_kz
        ),
        Country(
            R.string.KE,
            "KE",
            R.drawable.flag_ke
        ),
        Country(
            R.string.KR,
            "KR",
            R.drawable.flag_kr
        ),
        Country(
            R.string.XK,
            "XK",
            R.drawable.flag_xk
        ),
        Country(
            R.string.KW,
            "KW",
            R.drawable.flag_kw
        ),
        Country(
            R.string.LB,
            "LB",
            R.drawable.flag_lb
        ),
        Country(
            R.string.MY,
            "MY",
            R.drawable.flag_my
        ),
        Country(
            R.string.MN,
            "MN",
            R.drawable.flag_mn
        ),
        Country(
            R.string.ME,
            "ME",
            R.drawable.flag_me
        ),
        Country(
            R.string.MD,
            "MD",
            R.drawable.flag_md
        ),
        Country(
            R.string.RU,
            "RU",
            R.drawable.flag_ru
        ),
        Country(
            R.string.RW,
            "RW",
            R.drawable.flag_rw
        ),
        Country(
            R.string.RS,
            "RS",
            R.drawable.flag_rs
        ),
        Country(
            R.string.SD,
            "SD",
            R.drawable.flag_sd
        ),
        Country(
            R.string.TW,
            "TW",
            R.drawable.flag_tw
        ),
        Country(
            R.string.TZ,
            "TZ",
            R.drawable.flag_tz
        ),
        Country(
            R.string.TH,
            "TH",
            R.drawable.flag_th
        ),
        Country(
            R.string.TN,
            "TN",
            R.drawable.flag_tn
        ),
        Country(
            R.string.AE,
            "AE",
            R.drawable.flag_ae
        ),
        Country(
            R.string.UG,
            "UG",
            R.drawable.flag_ug
        ),
        Country(
            R.string.US,
            "US",
            R.drawable.flag_us
        ),
        Country(
            R.string.UY,
            "UY",
            R.drawable.flag_uy
        ),
        Country(
            R.string.VN,
            "VN",
            R.drawable.flag_vn
        ),
        Country(
            R.string.AR,
            "AR",
            R.drawable.flag_ar
        ),
        Country(
            R.string.MX,
            "MX",
            R.drawable.flag_mx
        )
    ).associateBy { it.countryCode.uppercase() }

    public fun getSortedCountryList(countryCodes: List<String>): List<Country> {
        val list = countryCodes + defaultDeDomesticCountry.countryCode
        return list.mapNotNull {
            getCountryByCode(it.uppercase())
        }.sortedBy { getString(it.nameRes) }
    }

    private fun getCountryByCode(countryCode: String): Country? = countryList[countryCode]

    public fun getCountryLocalized(countryCode: String): String =
        getCountryByCode(countryCode.uppercase())?.let {
            getString(it.nameRes)
        } ?: countryCode
}
