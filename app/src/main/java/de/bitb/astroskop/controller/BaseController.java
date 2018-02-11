package de.bitb.astroskop.controller;

import javax.inject.Inject;

import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.injection.IInjection;
import de.bitb.astroskop.model.Profile;
import de.bitb.astroskop.utils.SharedPreferencesUtils;
import lombok.Getter;

@Getter
public class BaseController {

    @Inject
    protected SharedPreferencesUtils sharedPreferencesUtils;

    public BaseController(AstroApplication astroApplication){
        if (this instanceof IInjection) {
            ((IInjection) this).inject(astroApplication.getAppComponent());
        } else {
            astroApplication.getAppComponent().inject(this);
        }
    }
 }
