package de.bitb.astroskop.viewbuilder;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import de.bitb.astroskop.R;
import lombok.Getter;
import lombok.Setter;

public class DialogBuilder {

    public static final int NO_BUTTON = 0;

    public void showSimpleDialog(Builder builder) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(builder.getContext(), R.style.dialogStyle)
                .setTitle(builder.getTitleId())
                .setMessage(builder.getMessageId())
                .setPositiveButton(builder.getPositivBtnId(), builder.getPositivListner());
        if (builder.getNegativBtnId() != NO_BUTTON) {
            dialog.setNegativeButton(builder.getNegativBtnId(), builder.getNegativListner());
        }
        if (builder.getNeutralBtnId() != NO_BUTTON) {
            dialog.setNeutralButton(builder.getNeutralBtnId(), builder.getNeutralListner());
        }
        dialog.show();
    }

    public void showDeleteDialog(Context context, DialogInterface.OnClickListener posListner) {
        Builder builder = new Builder(context);
        builder.setTitleId(R.string.dialog_delete_title);
        builder.setMessageId(R.string.dialog_delete_message);
        builder.setPositivBtnId(R.string.delete);
        builder.setNegativBtnId(R.string.cancel);
        builder.setPositivListner(posListner);
        showSimpleDialog(builder);
    }

    @Getter
    @Setter
    private static class Builder {
        private Context context;
        private int titleId;
        private int messageId;
        private int positivBtnId;
        private int negativBtnId;
        private int neutralBtnId;
        private DialogInterface.OnClickListener positivListner;
        private DialogInterface.OnClickListener negativListner;
        private DialogInterface.OnClickListener neutralListner;

        Builder(Context context) {
            this.context = context;
        }
    }

}

