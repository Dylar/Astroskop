package de.bornholdtlee.snh.api.converter;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import de.bornholdtlee.snh.BuildConfig;
import de.bornholdtlee.snh.enums.ReportLocation;
import de.bornholdtlee.snh.helper.Logger;
import de.bornholdtlee.snh.model.Report;

import static de.bornholdtlee.snh.ui.report.form.ReportFormThreeFragment.TITLE_COMPANY;
import static de.bornholdtlee.snh.ui.report.form.ReportFormThreeFragment.TITLE_MAN;
import static de.bornholdtlee.snh.ui.report.form.ReportFormThreeFragment.TITLE_NOMAN;

public class ReportConverter implements JsonSerializer<Report> {
    @Override
    public JsonElement serialize(Report report, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        JsonObject jsonO = new JsonObject();

        jsonO.addProperty("key", "dd8c6b9e8e4d0b3716ea79738b66688");
        jsonO.addProperty("snh-app-test", String.valueOf(BuildConfig.DEBUG));

        result.add("secret_data", jsonO);
        jsonO = new JsonObject();
        result.add("form", jsonO);
        String title;
        switch (report.getTitle()) {
            case TITLE_NOMAN:
                title = "Frau";
                break;
            case TITLE_COMPANY:
                title = "Firma";
                break;
            case TITLE_MAN:
            default:
                title = "Herr";
                break;
        }
        jsonO.addProperty("Kontakt-Anrede", title);
        jsonO.addProperty("Kontakt-Firma", report.getCompany());
        jsonO.addProperty("Kontakt-Vorname", report.getCustomerName());
        jsonO.addProperty("Kontakt-Nachname", report.getCustomerFamilyName());
        jsonO.addProperty("Kontakt-Strasse", "");
        jsonO.addProperty("Kontakt-Hausnr", "");
        jsonO.addProperty("Kontakt-PLZ", "");
        jsonO.addProperty("Kontakt-Ort", "");
        jsonO.addProperty("Kontakt-Rückrufnummer", report.getPhone());
        jsonO.addProperty("Kontakt-Email", report.getEmail());
        jsonO.addProperty("ort-stromausfall", report.getLocation().getJsonString());
        jsonO.addProperty("Details-Einzelhaus", report.getLocation().equals(ReportLocation.SINGLEHOUSE) ? report.getDetails().getJsonString() : "");
        jsonO.addProperty("Details-Mehrfamilienhaus", report.getLocation().equals(ReportLocation.MULTIHOUSE) ? report.getDetails().getJsonString() : "");
        jsonO.addProperty("Beschreibung_sonstiger_Ort", report.getLocation().equals(ReportLocation.REST) ? report.getLocationDetails() : "");
        jsonO.addProperty("Beschreibung_weitere_Details", report.getMoreDetails());
        jsonO.addProperty("Schadenort-Strasse", report.getStreetName());
        jsonO.addProperty("Schadenort-Hausnr", report.getStreetNumber());
        jsonO.addProperty("Schadenort-PLZ", report.getPlz());
        jsonO.addProperty("Schadenort-Ort", report.getCityName());
        Logger.debug("CONVERTED: " + result.toString());
        return result;
    }
}
