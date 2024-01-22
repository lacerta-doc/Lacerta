package one.nem.lacerta.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.concurrent.CompletableFuture;

public class TextInputDialog {
    public static CompletableFuture<String> show(Context context, String title, String message, String defaultValue, String positiveButton, String negativeButton) {
        return CompletableFuture.supplyAsync(() -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(title);
            builder.setMessage(message);
            final android.widget.EditText input = new android.widget.EditText(context);
            input.setText(defaultValue);
            builder.setView(input);
            builder.setPositiveButton(positiveButton, (dialog, which) -> {
                dialog.dismiss();
            });
            builder.setNegativeButton(negativeButton, (dialog, which) -> {
                dialog.cancel();
            });
            builder.show();
            return input.getText().toString();
        });
    }
}
