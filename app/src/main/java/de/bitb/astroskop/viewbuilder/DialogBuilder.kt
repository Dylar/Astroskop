package de.bitb.astroskop.viewbuilder

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog

import de.bitb.astroskop.R
import lombok.Getter
import lombok.Setter

class DialogBuilder {

    fun showSimpleDialog(builder: Builder) {
        val dialog = AlertDialog.Builder(builder.context, R.style.dialogStyle)
                .setTitle(builder.titleId)
                .setMessage(builder.messageId)
                .setPositiveButton(builder.positivBtnId, builder.positivListner)
        if (builder.negativBtnId != NO_BUTTON) {
            dialog.setNegativeButton(builder.negativBtnId, builder.negativListner)
        }
        if (builder.neutralBtnId != NO_BUTTON) {
            dialog.setNeutralButton(builder.neutralBtnId, builder.neutralListner)
        }
        dialog.show()
    }

    fun showDeleteDialog(context: Context, posListner: DialogInterface.OnClickListener) {
        val builder = Builder(context)
        builder.titleId = R.string.dialog_delete_title
        builder.messageId = R.string.dialog_delete_message
        builder.positivBtnId = R.string.delete
        builder.negativBtnId = R.string.cancel
        builder.positivListner = posListner
        showSimpleDialog(builder)
    }

    @Getter
    @Setter
    class Builder internal constructor(val context: Context) {
        var titleId: Int = 0
            set(titleId) {
                field = this.titleId
            }
        var messageId: Int = 0
            set(messageId) {
                field = this.messageId
            }
        var positivBtnId: Int = 0
            set(positivBtnId) {
                field = this.positivBtnId
            }
        var negativBtnId: Int = 0
            set(negativBtnId) {
                field = this.negativBtnId
            }
        var neutralBtnId: Int = 0
            set(neutralBtnId) {
                field = this.neutralBtnId
            }
        var positivListner: DialogInterface.OnClickListener? = null
            set(positivListner) {
                field = this.positivListner
            }
        var negativListner: DialogInterface.OnClickListener? = null
            set(negativListner) {
                field = this.negativListner
            }
        var neutralListner: DialogInterface.OnClickListener? = null
            set(neutralListner) {
                field = this.neutralListner
            }
    }

    companion object {

        val NO_BUTTON = 0
    }

}

