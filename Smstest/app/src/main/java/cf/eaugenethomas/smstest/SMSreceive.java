package cf.eaugenethomas.smstest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.provider.Telephony.*;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import static android.R.attr.format;


/**
 * Created by Eaugene on 30/08/2017.
 */

public class SMSreceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        Log.e("tst","got");
        if(bundle!=null) {
            Log.e("tst","goes");
            Object[] pdus= (Object[])bundle.get("pdus");
            String msg=null;
            Log.e("tst", String.valueOf(pdus.length));
            final SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++) {
                Log.e("tst","hey");
                messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                if(msg==null)
                {
                    msg=messages[i].getMessageBody();
                    continue;
                }
                msg=msg+messages[i].getMessageBody();
            }
            if (msg.length() > -1) {
                String sender = messages[0].getOriginatingAddress();
                Toast.makeText(context,"From : "+sender+"-> \n"+msg,Toast.LENGTH_LONG).show();
                Log.e("tst","tries");
                if(sender.equals("7010441576")||sender.equals("+917010441576"))
                {
                    Log.e("tst","same");
                    AudioManager audio = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
                    audio.setRingerMode(0);
                }

                Log.i("tst", "Message recieved: " + msg);
                Log.e("tst",sender);

            }

        }
        //Toast.makeText(context,"Hey!,It works",Toast.LENGTH_LONG).show();
    }
}
